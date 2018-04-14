package edu.brown.cs.bdGaMbPp.Handlers;

import edu.brown.cs.bdGaMbPp.GameLogic.Game;
import edu.brown.cs.bdGaMbPp.Tank.Direction;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;

public class UserTankHandler implements Route{

  private Game currentGame;
  private Direction [] directions = Direction.values();


  public UserTankHandler(Game g){
    this.currentGame = g;
  }

  @Override
  public String handle(Request request, Response response) {
    QueryParamsMap qm = request.queryMap();

    Direction d = directions[Integer.valueOf(qm.value("direction"))];
    currentGame.getUser().move(d);
    return "hello";
  }
}
