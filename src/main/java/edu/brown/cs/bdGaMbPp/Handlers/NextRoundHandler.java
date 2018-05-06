package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;

public class NextRoundHandler implements Route{

	@Override
	public Object handle(Request request, Response arg1) throws Exception {
		// TODO Auto-generated method stub
	  
		if (request.session().attributes().contains("survival")) {
			int currentRound = Integer.parseInt(request.session().attribute("survival").toString());
			
			request.session().attribute("survival", Integer.toString((currentRound + 1))); 	
		}
		
		return new Gson();
	}
}
