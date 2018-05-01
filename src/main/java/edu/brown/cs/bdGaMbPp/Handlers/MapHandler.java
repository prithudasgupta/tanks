package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Database.Querier;
import edu.brown.cs.bdGaMbPp.GameLogic.Game;
import edu.brown.cs.bdGaMbPp.GameLogic.GameInitializer;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;
import edu.brown.cs.bdGaMbPp.Tank.Direction;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapHandler implements Route {
	
	private GameMap theMap;
	
  @Override
  public String handle(Request request, Response response) {
    QueryParamsMap qm = request.queryMap();
//    String url = qm.value("url");
//    String id = convertUrl(url);
    GameMap map;
    List<List<String>> representations;
    
//    //get id from url based on webpage structure
//    if (id.equals("-1")) {
//    		map = new MapBuilder().createMap(.1, .1);
//    		representations = map.getRepresentations();
//    		Querier.addMap(convertToDatabase(representations), -1);
//
//    }
//    else {
//    		String data = Querier.getMapById(Integer.parseInt(id));
//    		if (data.equals("")) {
		map = new MapBuilder().createMap(0.1, 0);
		theMap = map;
		Game aGame = GameInitializer.initializeGame(map, 5);
		representations = map.getRepresentations();
		//System.out.println("aGame.getEnemies() = " + aGame.getEnemies());
		Map<String, Object> variables = ImmutableMap.of("map", representations, "game", aGame, "enemies", aGame.getEnemies());
        		
//    		}
//    		else {
//    			representations = convertFromDatabase(data);
//    		}
//    }
    
    Gson GSON = new Gson();
    return GSON.toJson(variables);
  }
  
  public GameMap getMap() {
	  return theMap;
  }
  
  private static String convertUrl(String url) {
	  int index = url.indexOf("id");
	  String id = url.substring(index);;
	  if (id.equals("id")) {
		  return "-1";
	  }
	  else {
		  return id.substring(2);
	  }
  }
  
  
}
