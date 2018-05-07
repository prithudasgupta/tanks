package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Database.Querier;
import spark.Request;
import spark.Response;
import spark.Route;

public class MatchmakerHandler implements Route {

	@Override
	public Object handle(Request req, Response res) throws Exception {
		int user = Integer.parseInt(req.session().attribute("user").toString());
		
		int numPlayers = Querier.getNumProfiles();
		int numGames = Querier.getNumGames();
		
		int randGame = (int)(Math.random() * numGames);
		int randPlayer = user;
		while (randPlayer == user) {
			randPlayer = (int)(Math.random() * numPlayers);
		}
		
		Map<String, Object> variables = ImmutableMap.of("game", randGame, "opponent", randPlayer);
		Gson GSON = new Gson();
		return GSON.toJson(variables);
	}

}
