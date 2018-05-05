package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.gson.Gson;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class GameDataHandler implements Route{

	@Override
	public Object handle(Request request, Response arg1) throws Exception {
		// TODO Auto-generated method stub
//		if (request.session().attributes().contains("survival")) {
//			int currentRound = Integer.parseInt(request.session().attribute("survival").toString());
//			request.session().removeAttribute("survival");
//			//update database
//		}

		QueryParamsMap qm = request.queryMap();

		int kills = Integer.parseInt(qm.value("kills"));
		int currentTime = Integer.parseInt(qm.value("tanks"));
		String id = qm.value("gameId");
		System.out.println("id = " + id);
		String survival = qm.value("survival");
		System.out.println("survival = " + survival);
		String result = qm.value("won");
		System.out.println("result = " + result);
		
		return new Gson();
	}

}
