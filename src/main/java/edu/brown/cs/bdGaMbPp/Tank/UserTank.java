package edu.brown.cs.bdGaMbPp.Tank;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class UserTank implements Tank{
	
	private Coordinate location;
	private Angle angleForward;
	private Angle launcherAngle;
	private double movementSpeed;
	private boolean isAlive;
	
	
	private static final double EPSILON = .01;
	
	public UserTank(Coordinate startCoord, double startDegrees, double movement, double rotate) {
		location = startCoord;
		movementSpeed = movement;
		angleForward = new Angle(startDegrees, rotate);
		launcherAngle = new Angle(startDegrees, rotate);
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


	@Override
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
	
}
