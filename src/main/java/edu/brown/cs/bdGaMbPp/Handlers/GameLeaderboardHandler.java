package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.gson.Gson;
import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Database.Profile;
import edu.brown.cs.bdGaMbPp.Database.Querier;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameLeaderboardHandler implements Route{

  @Override
  public Object handle(Request request, Response res) {

    QueryParamsMap qm  = request.queryMap();
    String survival = qm.value("survival");
    String id = qm.value("gameId");
    List<Pair<String, Integer>> topFive;
    if (survival.equals("true")){
      List<Profile> allProfiles = Querier.getAllProfiles();
      List<Profile> survivalAll = new ArrayList<Profile>(allProfiles);
      Collections.sort(survivalAll, new Comparator<Profile>() {

        public int compare(Profile o1, Profile o2) {
          return Integer.compare(o2.getSurvival(), o1.getSurvival());
        }
      });

      List<Pair<String, Integer>> survList = seperateSurvival(survivalAll);
      topFive = survList.subList(0, Math.min(5, survList.size()));

    }
    else{
      int gameId = Integer.parseInt(id);
      topFive = Querier.getGameLeaderboard(gameId);
    }

    return new Gson().toJson(topFive);
  }

  public List<Pair<String, Integer>> seperateSurvival(List<Profile> l) {
    List<Pair<String, Integer>> loSurvival = new ArrayList<>();
    for(int i = 0; i < l.size(); i++) {
      loSurvival.add(new Pair<>(l.get(i).getUsername(),l.get(i).getSurvival()));
    }
    return loSurvival;
  }


}
