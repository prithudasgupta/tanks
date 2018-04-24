package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Database.Querier;
import edu.brown.cs.bdGaMbPp.GameLogic.Game;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;
import edu.brown.cs.bdGaMbPp.Tank.Direction;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.List;

public class MapHandler implements Route {
	
  @Override
  public String handle(Request request, Response response) {
    QueryParamsMap qm = request.queryMap();
    String url = qm.value("url");
    int id = -1;
    GameMap map;
    List<List<String>> representations;
    
    //get id from url based on webpage structure
    if (id == -1) {
    		map = new MapBuilder().createMap(.1, .1);
    		representations = map.getRepresentations();
    		Querier.addMap(convertToDatabase(representations), -1);

    }
    else {
    		String data = Querier.getMapById(id);
    		representations = convertFromDatabase(data);
    }
    
    Gson GSON = new Gson();
    return GSON.toJson(representations);
  }
  
  private static String convertToDatabase(List<List<String>> representations) {
	  StringBuilder sb = new StringBuilder();
	  for (int i = 0; i < representations.size(); i++) {
		  List<String> currentRow = representations.get(i);
		  for (int j = 0; j < currentRow.size(); j++) {
			  sb.append(currentRow.get(j));
		  }
	  }
	  return sb.toString();
  }
  
  private static List<List<String>> convertFromDatabase(String representations) {
	  assert representations.length() == 384;
	  List<List<String>> locs = new ArrayList<List<String>>();
	  for (int r = 0; r < 16; r++) {
	      locs.add(new ArrayList<>());
	      for (int c = 0; c < 24; c++) {
	        locs.get(r).add(Character.toString(representations.charAt(16 * r + c)));
	      }
	    }
	  return locs;
  }
}
