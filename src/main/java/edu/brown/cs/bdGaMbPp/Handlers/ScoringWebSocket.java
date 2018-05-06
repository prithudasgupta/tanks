package edu.brown.cs.bdGaMbPp.Handlers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import edu.brown.cs.bdGaMbPp.Database.PlayerSession;
import edu.brown.cs.bdGaMbPp.Database.Profile;
import edu.brown.cs.bdGaMbPp.Database.Querier;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;

@WebSocket
public class ScoringWebSocket {
  private static final Gson GSON = new Gson();
  private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();
  private static Map<Integer, Integer> scoresCache = new HashMap<Integer, Integer>();
  private static int nextId = 0;
  private static Map<Session, PlayerSession> sessionMap = new ConcurrentHashMap<Session, PlayerSession>();

  private static enum MESSAGE_TYPE {
    CONNECT,
    SCORE,
    UPDATE
  }

  @OnWebSocketConnect
  public void connected(Session session) throws IOException {
    // TODO Add the session to the queue
    sessions.add(session);
    
//    // TODO Build the CONNECT message
//    JsonObject payload = new JsonObject(); 
//    payload.addProperty("payload", 0);
//    
//    JsonObject connect = new JsonObject(); 
//    connect.addProperty("type", MESSAGE_TYPE.CONNECT.ordinal());
//    connect.add("payload", payload);
//    
//    // TODO Send the CONNECT message
//    session.getRemote().sendString(GSON.toJson(connect));
  }

  @OnWebSocketClose
  public void closed(Session session, int statusCode, String reason) {
    // TODO Remove the session from the queue
    sessions.remove(session);
  }

  @OnWebSocketMessage
  public void message(Session session, String message) throws IOException {
    JsonObject received = GSON.fromJson(message, JsonObject.class);
    
    int score;
    
    int type = received.get("type").getAsInt();
    
    if(type == 1) {
      JsonObject pload = received.get("payload").getAsJsonObject();
      
      int id = pload.get("id").getAsInt();
      String username  = pload.get("name").getAsString();
      
      PlayerSession ses = new PlayerSession(id, username);
      sessionMap.put(session, ses);
      
      
    }else if(type == 2) {
      
      JsonObject pload = received.get("payload").getAsJsonObject();
      
      String friendName = pload.get("name").getAsString();
      boolean exists = false;
      List<Profile> all = Querier.getAllProfiles();
      
      int friendId = -1;
      
      for(Profile profile : all) {
        if(profile.getUsername().equals(friendName)) {
          exists = true;
          friendId = profile.getId();
          break;
              
        }
      }
      
      boolean alreadyFriends = false;
      if(exists) {
        
        List<Profile> friends = Querier.getLocalPlayers(sessionMap.get(session).getId());
        
        for(Profile p : friends) {
          if(p.getUsername().equals(friendName)) {
            alreadyFriends = true;
            break;
          }
        }
        
      }
      
      boolean success = true;
      
      if(!exists || !alreadyFriends) {
        success = false;
      }
      
      if(!alreadyFriends) {
        Querier.friendRequest(sessionMap.get(session).getId(), friendId, 1);
        
        // TODO Send an UPDATE message to all users
        JsonObject friendRequestMessage = new JsonObject();
        friendRequestMessage.addProperty("type", MESSAGE_TYPE.UPDATE.ordinal());
        
        JsonObject payload = new JsonObject();
        payload.addProperty("success", success);
        payload.addProperty("username", friendName);
        
        friendRequestMessage.add("payload", payload);
        
        int activePlayers = sessions.size();
        for(int i = 0; i < activePlayers; i++) {
          
          Session curr = sessions.poll();
          if(sessionMap.get(curr).getId() == friendId) {
            curr.getRemote().sendString(GSON.toJson(friendRequestMessage));
          }
        }
      } 
    }
  }
  
}