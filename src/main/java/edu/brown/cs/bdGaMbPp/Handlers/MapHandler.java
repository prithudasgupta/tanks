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
import java.util.Set;

public class MapHandler implements Route {
	
	private GameMap theMap;
	
  @Override
  public String handle(Request request, Response response) {
		GameMap map;
		List<List<String>> representations;
		Map<String, Object> variables;
    QueryParamsMap qm = request.queryMap();
    String url = qm.value("url");
    String id = convertUrl(url);
    if (id.equals("")) {
			map = new MapBuilder().createMap(0.1, 0);
			theMap = map;

			Game aGame = GameInitializer.initializeGame(map, 5);
			representations = map.getRepresentations();
			variables = ImmutableMap.of("map", representations, "game", aGame, "enemies", aGame.getEnemies(), "survival", false, "playerTwo", -1);
		} 
    else if (id.contains("#")) {
    		String [] splited = id.split("#");
    		int gameId = Integer.parseInt(splited[0]);
    		int playerTwo = Integer.parseInt(splited[1]);
    		if (playerTwo < 0){

				}
    		Game data = Querier.getGameById(gameId);
    		variables = ImmutableMap.of("map", data.getRepresentations(), "game", data, "enemies", data.getEnemies(), "survival", false, "playerTwo", playerTwo);
    }
    else if (id.equals("survival")) {
    		
    		int difficulty = 1;
    	
    		Set<String> attributes = request.session().attributes();
		if (!attributes.contains("survival")) {
			request.session(true);
			request.session().attribute("survival", "1"); 
		}
		else {
			difficulty = Integer.parseInt(request.session().attribute("survival").toString());
		}
		map = new MapBuilder().createMap(0.1, 0);
		theMap = map;
		Game aGame = GameInitializer.initializeGame(map, difficulty);
		representations = map.getRepresentations();
		variables = ImmutableMap.of("map", representations, "game", aGame, "enemies", aGame.getEnemies(), "survival", true, "round", difficulty);
    }
    else {
			Game data = Querier.getGameById(Integer.parseInt(id));
			if (data == null) {
				map = new MapBuilder().createMap(0.1, 0);
				theMap = map;

				Game aGame = GameInitializer.initializeGame(map, 5);
				representations = map.getRepresentations();
				variables = ImmutableMap.of("map", representations, "game", aGame, "enemies", aGame.getEnemies());
			}
			else {
				variables = ImmutableMap.of("map", data.getRepresentations(), "game", data, "enemies", data.getEnemies(), "survival", false, "playerTwo", -1);
			}
		}
    
    Gson GSON = new Gson();
    return GSON.toJson(variables);
  }
  
  public GameMap getMap() {
	  return theMap;
  }
  
  private static String convertUrl(String url) {
	  int index = url.lastIndexOf("me/");
	  if (index == -1) {
	  	return "";
		}
	  String id = url.substring(index + 3);
		System.out.println("id = " + id);
	  return id;
  }
  
  
}
