package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuthenticateHandler implements Route {

	@Override
	public Object handle(Request request, Response res) throws Exception {
		
		boolean signedIn = false;
		int id = -1;
		
		Set<String> attributes = request.session().attributes();
		if (attributes.contains("user")) {
			signedIn = true;
			id = Integer.parseInt(request.session().attribute("user").toString());
		}
		
		Map<String, Object> variables = ImmutableMap.of("signedIn", signedIn, "id", id);
		Gson GSON = new Gson();
		return GSON.toJson(variables);
		
	}

}
