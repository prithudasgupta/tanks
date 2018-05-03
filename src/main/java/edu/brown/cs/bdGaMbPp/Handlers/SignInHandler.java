package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Database.Profile;
import edu.brown.cs.bdGaMbPp.Database.Querier;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class SignInHandler implements Route {
	
	@Override
	public Object handle(Request request, Response arg1) throws Exception {
		
		QueryParamsMap qm = request.queryMap();
		String username = qm.value("username");
		String password = qm.value("password");
		
		boolean signedIn = false;
		
		Profile check = Querier.signIn(username, password);
		
		if (check != null) {
			signedIn = true;
		}
		
		Map<String, Object> variables = ImmutableMap.of("signedIn", signedIn, "profile", check);
		Gson GSON = new Gson();
		return GSON.toJson(variables);
		
	}

}
