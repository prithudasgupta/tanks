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

  public Game(MapBuilder mapBuilder) {
    
  }

  public Tank getUser(){
    return user;
  }


}
