package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Database.Profile;
import edu.brown.cs.bdGaMbPp.Database.Querier;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class FriendRequestHandler implements Route {

	@Override
	public Object handle(Request request, Response arg1) throws Exception {
		
		  QueryParamsMap qm = request.queryMap();
		  
		  
		  String friendName = qm.value("username");
		  
		  int id = -1;
			
			Set<String> attributes = request.session().attributes();
		  if (attributes.contains("user")) {
				id = Integer.parseInt(request.session().attribute("user").toString());
			}
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
	        
	        List<Profile> friends = Querier.getLocalPlayers(id);
	        
	        for(Profile p : friends) {
	          if(p.getUsername().equals(friendName)) {
	            alreadyFriends = true;
	            break;
	          }
	        }
	        
	      }
	      
	      boolean success = true;
	      
	      if(!exists || alreadyFriends) {
	        success = false;
	      }
	      else {
	    	  	Querier.friendRequest(id, friendId, 1);
	      }
	      Gson GSON = new Gson();
	      return new Gson();
	}
}
