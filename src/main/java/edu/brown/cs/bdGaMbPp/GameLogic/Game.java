package edu.brown.cs.bdGaMbPp.GameLogic;

import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;
import edu.brown.cs.bdGaMbPp.Tank.Tank;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;

import java.util.List;

public class Game {
	
  private GameMap map;
  private Tank user;
  private List<Tank> enemies;

  public Game(GameMap aMap, Tank userTank, List<Tank> enemyTanks) {
    map = aMap;
    user = userTank;
    enemyTanks = enemies;
  }

  public Tank getUser(){
    return user;
  }


}
