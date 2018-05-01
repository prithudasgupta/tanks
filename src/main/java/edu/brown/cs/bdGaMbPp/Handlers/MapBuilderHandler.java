package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.gson.Gson;

import com.google.gson.JsonArray;
import edu.brown.cs.bdGaMbPp.Database.Querier;
import netscape.javascript.JSObject;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.lang.reflect.Array;

public class MapBuilderHandler implements Route {
	
	  @Override
	  public String handle(Request request, Response response) {
		  
		  QueryParamsMap qm = request.queryMap();
		  String representation = qm.value("representation");
		  String tanks = qm.value("tanks");
		  String user = qm.value("user");
			System.out.println("tanks = " + tanks);

//		  int id = Querier.addMap(representation, 0);


		  System.out.println(representation);
		  Gson resp = new Gson();
		  return resp.toJson("");
	  }
}
