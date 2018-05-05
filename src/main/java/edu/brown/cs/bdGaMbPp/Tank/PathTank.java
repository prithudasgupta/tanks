package edu.brown.cs.bdGaMbPp.Tank;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class PathTank implements Tank {
  
  private Coordinate location;
  private String type = "p";
  private boolean isAlive;
  public PathTank(Coordinate startCoord) {
    location = startCoord;
    isAlive = true;
  }

  @Override
  public void move() {
    // TODO Auto-generated method stub

  }

  @Override
  public void move(Direction d) {
    // TODO Auto-generated method stub

  }

  @Override
  public void shoot() {
    // TODO Auto-generated method stub

  }

  @Override
  public Coordinate getCoord() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Angle getAngleForward() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Angle getLauncherAngle() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getType() {
    // TODO Auto-generated method stub
    return type;
  }

}
