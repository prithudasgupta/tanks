package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Database.Querier;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class HomingTankHandler implements Route {

	public HomingTankHandler() {

	}
	
	@Override
	  public String handle(Request request, Response response) {
		System.out.println("here");
	    QueryParamsMap qm = request.queryMap();
	    int userTankRow = Integer.parseInt(qm.value("userRow"));
	    int userTankCol = Integer.parseInt(qm.value("userCol"));
	    int enemyRow = Integer.parseInt(qm.value("enemyRow"));
	    int enemyCol = Integer.parseInt(qm.value("enemyCol"));
	    String representation = qm.value("representation");
	    System.out.println("tank row " + userTankRow);
	    System.out.println("tank col " + userTankCol);
	    System.out.println("enemy row " + enemyRow);
	    System.out.println("enemy col " + enemyCol);

		GameMap map = Querier.convertFromDatabase(representation);
	    Pair<Integer, Integer> start = new Pair<Integer, Integer>(enemyRow, enemyCol);
	    Pair<Integer, Integer> end;
	    if(userTankRow == -1 || userTankCol == -1) {
	    	List<Pair<Integer, Integer>> options = map.indicesByType("l");
	    	int rand = (int)(Math.random()*(options.size()-1));
	    	end = (options.get(rand));
	    	while(end == start) {
	    		rand = (int)(Math.random()*(options.size()-1));
		    	end = (options.get(rand));
	    	}
	    }else {
		    end = new Pair<Integer, Integer>(userTankRow, userTankCol);

	    }
	    List<Pair<Integer, Integer>> route = map.getRoute(start, end);

	    /*Pair<Integer, Integer> next = start;


		if (route.size() > 0) {
			next = route.get(0);
		}*/

		Map<String, Object> variables = ImmutableMap.of("route", route);
		Gson GSON = new Gson();
	    return GSON.toJson(variables);
	    
	}

}
