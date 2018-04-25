package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.GameLogic.Game;
import edu.brown.cs.bdGaMbPp.GameLogic.GameInitializer;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;
import edu.brown.cs.bdGaMbPp.Tank.Direction;
import edu.brown.cs.bdGaMbPp.Tank.Tank;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class GameHandler implements Route{

  private Gson GSON;
  private Game game = null;
  private Direction [] directions = Direction.values();
  public GameHandler(){
    GSON = new Gson();
    
    GameMap map = new MapBuilder().createMap(0.2, 0.2);
    game = GameInitializer.initializeGame(map, 1);
  }
  
  @Override
  public String handle(Request request, Response response) {
    
    QueryParamsMap qm = request.queryMap();
    
    if(qm.value("refresh") != null && Integer.parseInt(qm.value("refresh")) == 1) {
      GameMap map = new MapBuilder().createMap(0.2, 0.2);
      game = GameInitializer.initializeGame(map, 1);
    }
    else {
    		Direction d = directions[Integer.valueOf(qm.value("direction"))];
    		//game.moveUser(d, Double.parseDouble(qm.value("height")), Double.parseDouble(qm.value("width")));
    }
    
    
    Map<String, Object> variables = ImmutableMap.of("game", game, "representations", game.getRepresentations());
    return GSON.toJson(variables);
  }
}
