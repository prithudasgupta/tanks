package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Database.Querier;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateProfileHandler implements Route {

	@Override
	public Object handle(Request request, Response arg1) throws Exception {
		
		QueryParamsMap qm = request.queryMap();
		String username = qm.value("username");
		String password = qm.value("password");
		
		List<String> usernames = Querier.getUsernames();
		
		boolean canCreate = false;
		
		if (!usernames.contains(username)) {
			canCreate = true;
			Querier.createNewAccount(username, password);
		}
		
		Map<String, Object> variables = ImmutableMap.of("username", username, "password", password, "exists", canCreate);
		Gson GSON = new Gson();
		return GSON.toJson(variables);
		
	}
	
	

}
