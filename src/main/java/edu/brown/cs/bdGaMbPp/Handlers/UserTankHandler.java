package edu.brown.cs.bdGaMbPp.Handlers;

import com.google.gson.Gson;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.GameLogic.Game;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;
import edu.brown.cs.bdGaMbPp.Tank.Direction;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class UserTankHandler implements Route{

  private Game currentGame;
  private Direction [] directions = Direction.values();
  private Gson GSON;

  public UserTankHandler(){
    GSON = new Gson();
  }

  @Override
  public String handle(Request request, Response response) {
    QueryParamsMap qm = request.queryMap();

    Direction d = directions[Integer.valueOf(qm.value("direction"))];
    Double x = Double.valueOf(qm.value("x"));
    Double y = Double.parseDouble(qm.value("y"));
    Double curDeg = Double.parseDouble(qm.value("deg"));
    Coordinate cur = new Coordinate(x, y);
    UserTank tank = new UserTank(cur, 0.01, 0.1);
    tank.move(d);
    return GSON.toJson(tank);
  }
}
