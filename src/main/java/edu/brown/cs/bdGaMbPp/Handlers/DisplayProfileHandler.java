package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Database.Friend;
import edu.brown.cs.bdGaMbPp.Database.Profile;
import edu.brown.cs.bdGaMbPp.Database.Querier;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class DisplayProfileHandler implements Route {
	
	@Override
	public Object handle(Request request, Response rep) throws Exception {
		
		QueryParamsMap qm = request.queryMap();
		
		int userId = -1;
		
		Set<String> attributes = request.session().attributes();
		if (attributes.contains("user")) {
			userId = Integer.parseInt(request.session().attribute("user").toString());
		}
		
		Map<String, Object> variables;
		
		if(userId != -1) { 
			Profile prof = Querier.getProfile(userId);
			List<Friend> friends = Querier.getFriendIds(userId);
			List<Integer> games = Querier.getGamesByCreator(userId);
			variables = ImmutableMap.of("profile", prof, "friends", friends, "games", games);
			Gson GSON = new Gson();
			return GSON.toJson(variables);

		}
		
		
		return new Gson();

		
	}

}
