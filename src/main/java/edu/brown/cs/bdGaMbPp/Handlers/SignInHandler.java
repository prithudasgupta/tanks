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
import spark.Session;

public class SignInHandler implements Route {
	
	@Override
	public Object handle(Request request, Response rep) throws Exception {
		
		QueryParamsMap qm = request.queryMap();
		String username = qm.value("username");
		String password = qm.value("password");
		
		boolean signedIn = false;
		
		Profile check = Querier.signIn(username, password);
		//Session session = null
		
		if (check != null) {
			signedIn = true;
			request.session(true);
			request.session().attribute("user", Integer.toString(check.getId())); 
			//session = request.session(true);   // create and return session 
			//session.attribute("user", check.getId());
			//request.session().attribute("user",check.getId()); 
		}
		
		
		Map<String, Object> variables = ImmutableMap.of("signedIn", signedIn, "profile", check);
		Gson GSON = new Gson();
		return GSON.toJson(variables);
		
	}

}
