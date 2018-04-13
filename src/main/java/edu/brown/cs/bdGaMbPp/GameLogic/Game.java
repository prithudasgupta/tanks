package edu.brown.cs.bdGaMbPp.GameLogic;

import edu.brown.cs.bdGaMbPp.Map.Map;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;
import edu.brown.cs.bdGaMbPp.Tank.Tank;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;

import java.util.List;

public class Game {
  private Map map;
  private Tank user;
  private List<Tank> enemies;

  public Game(MapBuilder mapBuilder) {
    map = mapBuilder.getMap();
    user = mapBuilder.getUser();
    enemies = mapBuilder.getEnemies();
  }

  public Tank getUser(){
    return user;
  }


}
