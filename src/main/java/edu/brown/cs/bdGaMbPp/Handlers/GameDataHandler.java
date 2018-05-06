package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Database.Querier;
import org.omg.CORBA.INTERNAL;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Set;

public class GameDataHandler implements Route{

	@Override
	public Object handle(Request request, Response arg1) {
		// TODO Auto-generated method stub


		QueryParamsMap qm = request.queryMap();

		int kills = Integer.parseInt(qm.value("kills"));

		String gameId = qm.value("gameId");

		String survival = qm.value("survival");

		String result = qm.value("result");


		int time = Integer.parseInt(qm.value("currentTime"));

		int user2 = Integer.parseInt(qm.value("userTwo"));

		int id = -1;
		Set<String> attributes = request.session().attributes();
		if (attributes.contains("user")) {

			id = Integer.parseInt(request.session().attribute("user").toString());
		}


		Querier.setKills(id, kills);
		Querier.setTime(id, time);
		

		if (survival.equals("true") && result.equals("win")) {
			int currentRound = Integer.parseInt(request.session().attribute("survival").toString());
			Querier.setSurvival(id, currentRound);
		}else if(survival.equals("true") && result.equals("lose")) {
		  request.session().removeAttribute("survival");
		}

		if (user2 != 0) {
			if (user2 > 0) {
				Querier.gameSent(id, user2, Integer.parseInt(gameId), time, kills);
			}
			else{
				Querier.updateGameFinal(-1 * user2, id, Integer.parseInt(gameId), time, kills);
			}
		}

		if (!gameId.equals("survival")) {

			if (user2 == 0) {
				int gameIdNumber = Integer.parseInt(gameId);
				if (gameIdNumber >= 0 && gameIdNumber < 20) {
					Querier.setCampaign(id, gameIdNumber);
				}
				Querier.updateLeaderboard(gameIdNumber, id, time);
			}
		}

		return new Gson();
	}

}
