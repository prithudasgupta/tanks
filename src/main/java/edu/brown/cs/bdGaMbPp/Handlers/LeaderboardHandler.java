package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Database.Profile;
import edu.brown.cs.bdGaMbPp.Database.Querier;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class LeaderboardHandler implements Route {

  @Override
  public Object handle(Request request, Response res) throws Exception {

    int id = -1;

    Set<String> attributes = request.session().attributes();
    if (attributes.contains("user")) {
      id = Integer.parseInt(request.session().attribute("user").toString());
    }

    List<Profile> allProfiles = Querier.getAllProfiles();
    List<Profile> friends = Querier.getLocalPlayers(id);

    List<Profile> killsAll = new ArrayList<Profile>(allProfiles);
    List<Profile> timeAll = new ArrayList<Profile>(allProfiles);
    List<Profile> survivalAll = new ArrayList<Profile>(allProfiles);

    List<Profile> killsFriends = new ArrayList<Profile>(friends);
    List<Profile> timeFriends = new ArrayList<Profile>(friends);
    List<Profile> survivalFriends = new ArrayList<Profile>(friends);

    Collections.sort(killsAll, new Comparator<Profile>() {

      public int compare(Profile o1, Profile o2) {
        return Integer.compare(o2.getKills(), o1.getKills());
      }
    });

    Collections.sort(timeAll, new Comparator<Profile>() {

      public int compare(Profile o1, Profile o2) {
        return Integer.compare(o2.getTime(), o1.getTime());
      }
    });

    Collections.sort(survivalAll, new Comparator<Profile>() {

      public int compare(Profile o1, Profile o2) {
        return Integer.compare(o2.getSurvival(), o1.getSurvival());
      }
    });

    Collections.sort(killsFriends, new Comparator<Profile>() {

      public int compare(Profile o1, Profile o2) {
        return Integer.compare(o2.getKills(), o1.getKills());
      }
    });

    Collections.sort(timeFriends, new Comparator<Profile>() {

      public int compare(Profile o1, Profile o2) {
        return Integer.compare(o2.getTime(), o1.getTime());
      }
    });

    Collections.sort(survivalFriends, new Comparator<Profile>() {

      public int compare(Profile o1, Profile o2) {
        return Integer.compare(o2.getSurvival(), o1.getSurvival());
      }
    });



    Pair<List<Pair<String, Integer>>, List<Pair<String, Integer>>> kills
            = new Pair<>(seperateKills(killsAll), seperateKills(killsFriends));
    Pair<List<Pair<String, Integer>>, List<Pair<String, Integer>>> survival
            = new Pair<>(seperateSurvival(survivalAll), seperateSurvival(survivalFriends));
    Pair<List<Pair<String, Integer>>, List<Pair<String, Integer>>> time
            = new Pair<>(seperateTime(timeAll), seperateTime(timeFriends));
    Map<String, Object> variables = ImmutableMap.of("kills", kills, "survival", survival, "time", time);
    Gson GSON = new Gson();
    return GSON.toJson(variables);

  }

  private List<Pair<String, Integer>> seperateKills(List<Profile> l) {
    List<Pair<String, Integer>> loKills = new ArrayList<>();
    for(int i = 0; i < l.size(); i++) {
      loKills.add(new Pair<>(l.get(i).getUsername(),l.get(i).getKills()));
    }
    return loKills;
  }

  public List<Pair<String, Integer>> seperateTime(List<Profile> l) {
    List<Pair<String, Integer>> loTime = new ArrayList<>();
    for(int i = 0; i < l.size(); i++) {
      loTime.add(new Pair<>(l.get(i).getUsername(),l.get(i).getTime()));
    }
    return loTime;
  }

  public List<Pair<String, Integer>> seperateSurvival(List<Profile> l) {
    List<Pair<String, Integer>> loSurvival = new ArrayList<>();
    for(int i = 0; i < l.size(); i++) {
      loSurvival.add(new Pair<>(l.get(i).getUsername(),l.get(i).getSurvival()));
    }
    return loSurvival;
  }

}
