package edu.brown.cs.bdGaMbPp.Tank;

import java.util.ArrayList;
import java.util.List;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class UserTank implements Tank{
	
	private Coordinate location;
	private Angle angleForward;
	private Angle launcherAngle;
	private double movementSpeed;
	private boolean isAlive;
	
	private static final double EPSILON = .01;
	
	public UserTank(Coordinate startCoord) {
		location = startCoord;
		
		isAlive = true;
	}
	

	


	@Override
	public void move(Direction d) {
		if (d.equals(Direction.FORWARD)) {
			location.forwardByAngle(angleForward, movementSpeed);
		} else if (d.equals(Direction.BACKWARD)) {
			location.backwardByAngle(angleForward, movementSpeed);
		}
		else if (d.equals(Direction.LEFT)) {
			angleForward.rotateCounterClockwise();
		}
		else if (d.equals(Direction.RIGHT)) {
			angleForward.rotateClockwise();
		}
	}

	public void rotateLauncher(Coordinate mouseCoord) {
		double tankCoordX = location.getCoordinate(0);
		double tankCoordY = location.getCoordinate(1);
		double mouseCoordX = mouseCoord.getCoordinate(0);
		double mouseCoordY = mouseCoord.getCoordinate(1);
		
		if (Math.abs(tankCoordX - mouseCoordX) > EPSILON) {
			launcherAngle.setDegrees(Math.toDegrees(Math.atan((mouseCoordY-tankCoordY)/(mouseCoordX-tankCoordX))));
		}
		
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Coordinate getCoord() {
		return location;
	}


	@Override
	public void move() {
		//must be passed direction
		
	}


	@Override
	public Angle getAngleForward() {
		return angleForward;
	}


	@Override
	public Angle getLauncherAngle() {
		return launcherAngle;
	}


	public Coordinate potenitalMove(Direction d) {
		
		double x = location.getCoordinate(0);
		double y = location.getCoordinate(1);
		
		if (d == Direction.FORWARD) {
			 x = location.getCoordinate(0) + (movementSpeed * angleForward.getCos());
			 y = location.getCoordinate(1) + (movementSpeed * angleForward.getSin());
		}
		else if (d == Direction.BACKWARD) {
			 x = location.getCoordinate(0) + (movementSpeed * angleForward.getCos());
			 y = location.getCoordinate(1) + (movementSpeed * angleForward.getSin());
		}
		return new Coordinate(x, y);
	}


  public List<Coordinate> getCorners(double height, double width, Coordinate newCenter) {
    
    List<Coordinate> corners = new ArrayList<Coordinate>();
    
    double hypotenuse = Math.sqrt(Math.pow(width/2, 2) + Math.pow(height/2, 2));
    double currDegrees = angleForward.getDegrees() + 45;
    
    double sine = Math.sin(currDegrees * (Math.PI / 180));
    double cosine = Math.cos(currDegrees * (Math.PI / 180));
    
    Coordinate TopLeft = new Coordinate(newCenter.getCoordinate(0) - hypotenuse*cosine, newCenter.getCoordinate(1) - hypotenuse*sine);
    Coordinate TopRight = new Coordinate(newCenter.getCoordinate(0) + hypotenuse*cosine, newCenter.getCoordinate(1) - hypotenuse*sine);
    Coordinate BottomLeft = new Coordinate(newCenter.getCoordinate(0) - hypotenuse*cosine, newCenter.getCoordinate(1) - hypotenuse*sine);
    Coordinate BottomRight = new Coordinate(newCenter.getCoordinate(0) + hypotenuse*cosine, newCenter.getCoordinate(1) + hypotenuse*sine);
    
    corners.add(TopLeft);
    corners.add(TopRight);
    corners.add(BottomLeft);
    corners.add(BottomRight);
    
    return corners;
  }





@Override
public String getType() {
	return "0";
}
	
}
