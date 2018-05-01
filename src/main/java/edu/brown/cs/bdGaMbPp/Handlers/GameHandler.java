package edu.brown.cs.bdGaMbPp.Handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.GameLogic.Game;
import edu.brown.cs.bdGaMbPp.GameLogic.GameInitializer;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;
import edu.brown.cs.bdGaMbPp.Tank.Direction;
import edu.brown.cs.bdGaMbPp.Tank.Tank;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;
import spark.ModelAndView;
import spark.QueryParamsMap;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;

public class GameHandler implements TemplateViewRoute{

  private Gson GSON;
  private Game game = null;
  private Direction [] directions = Direction.values();
  
  
  @Override
  public ModelAndView handle(Request request, Response response) {
    
    QueryParamsMap qm = request.queryMap();
    
    
    
    Map<String, Object> variables = ImmutableMap.of("title", "Tanks!");
    return new ModelAndView(variables, "game.ftl");
  }
}
