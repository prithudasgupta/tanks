package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Database.Querier;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class MapBuilderHandler implements Route {
	
	  @Override
	  public String handle(Request request, Response response) {
		  
		  QueryParamsMap qm = request.queryMap();
		  String representation = qm.value("representation");
		  
		  Querier.addMap(representation, 0);
		  System.out.println(representation);
		  
		  return new Gson().toString();
	  }
}
