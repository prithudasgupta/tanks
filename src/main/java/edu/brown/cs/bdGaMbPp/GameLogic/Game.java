package edu.brown.cs.bdGaMbPp.GameLogic;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;
import edu.brown.cs.bdGaMbPp.Tank.Bullet;
import edu.brown.cs.bdGaMbPp.Tank.Direction;
import edu.brown.cs.bdGaMbPp.Tank.Tank;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;

import java.util.ArrayList;
import java.util.List;

public class Game {
	
  private GameMap map;
  private Tank user;
  private List<Tank> enemies;
  private List<Bullet> bullets;

  public Game(GameMap aMap, Tank userTank, List<Tank> enemyTanks) {
    map = aMap;
    user = userTank;
    enemies = enemyTanks;
    bullets = new ArrayList<Bullet>();
  }

  public Tank getUser(){
    return user;
  }
  
  /**
  public void moveUser(Direction dirr, double height, double width) {
	  Coordinate updatedCoord = user.potenitalMove(dirr);
	  List<Coordinate> corners = user.getCorners(height, width, updatedCoord);
	  for (int i = 0; i < corners.size(); i++) {
		  if (!map.get((int)(corners.get(i).getCoordinate(1)), (int)(corners.get(i).getCoordinate(0))).getRepresentation().equals("l")) {
			  break;
		  }
		  if (i == corners.size() - 1) {
			  user.move(dirr);
		  }
	  }
  }
  */
  
  public List<List<String>> getRepresentations(){
    return map.getRepresentations();
  }
}
