package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.gson.Gson;
import edu.brown.cs.bdGaMbPp.GameLogic.Game;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;
import edu.brown.cs.bdGaMbPp.Tank.Direction;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class MapHandler implements Route {

  @Override
  public String handle(Request request, Response response) {
    QueryParamsMap qm = request.queryMap();
    Gson GSON = new Gson();
    return GSON.toJson(new MapBuilder().createMap());
  }
}
