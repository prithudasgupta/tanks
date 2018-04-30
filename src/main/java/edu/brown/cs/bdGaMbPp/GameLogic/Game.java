package edu.brown.cs.bdGaMbPp.GameLogic;

import edu.brown.cs.bdGaMbPp.Map.GameMap;

import edu.brown.cs.bdGaMbPp.Tank.Tank;

import java.util.List;

public class Game {
	
  private GameMap map;
  private Tank user;
  private List<Tank> enemies;

  public Game(GameMap aMap, Tank userTank, List<Tank> enemyTanks) {
    map = aMap;
    user = userTank;
    enemies = enemyTanks;
  }

  public Tank getUser(){
    return user;
  }
  
  public List<List<String>> getRepresentations(){
	    return map.getRepresentations();
	  }
  
  public List<Tank> getEnemies(){
	  return enemies;
  }
  
}
