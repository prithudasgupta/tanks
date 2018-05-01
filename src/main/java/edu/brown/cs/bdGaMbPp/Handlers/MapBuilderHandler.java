package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.gson.Gson;

import com.google.gson.JsonArray;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.Database.Querier;
import edu.brown.cs.bdGaMbPp.Tank.StationaryEnemyTank;
import edu.brown.cs.bdGaMbPp.Tank.Tank;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;
import netscape.javascript.JSObject;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapBuilderHandler implements Route {
	
	  @Override
	  public String handle(Request request, Response response) {
		  
		  QueryParamsMap qm = request.queryMap();
		  String representation = qm.value("representation");
		  String tanks = qm.value("tanks");
		  String user = qm.value("user");
			
			String concat = tanks + "u," + user;
			
			List<Tank> tankList = parseTanks(concat);
			int id = Querier.addMap(representation, 0);
			
			Querier.addGameToDatabase(id, tankList);

		  Gson resp = new Gson();
		  return resp.toJson("");
	  }
	  
	  private static List<Tank> parseTanks(String concat) {
		Scanner delim = new Scanner(concat);
		delim.useDelimiter("#");
		List<Tank> tankList = new ArrayList<Tank>();
		while (delim.hasNext()) {
			String parsed = delim.next();
			
			String [] single = parsed.split(",");
			int xCoord = Integer.parseInt(single[1]);
			int yCoord = Integer.parseInt(single[2]);
			if (single[0].equals("u")) {
				tankList.add(new UserTank(new Coordinate(xCoord, yCoord)));
			}
			else if(single[0].equals("s")) {
				tankList.add(new StationaryEnemyTank(new Coordinate(xCoord, yCoord)));
			}
		}
		delim.close();
		return tankList;  
	  }
}
