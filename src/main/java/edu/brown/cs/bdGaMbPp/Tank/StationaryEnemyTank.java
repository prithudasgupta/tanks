package edu.brown.cs.bdGaMbPp.Tank;

import java.util.List;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class StationaryEnemyTank implements Tank{

	private Coordinate location;
	private Angle angleForward;
	private Angle launcherAngle;
	private boolean isAlive;
	
	private static final double ROTATE_SPEED = 1;

	public StationaryEnemyTank(Coordinate coordinate) {
		location = coordinate;
		isAlive = true;
	}


	@Override
	public void move(Direction d) {
		//cannot move
	}
	
	@Override
	public void move() {
		//cannot move	
	}
	
	
	public void rotateLauncher() {
		
		int rand = (int)(Math.random() * 2);
		
		if (rand == 0) {
			launcherAngle.rotateCounterClockwise();
		}
		
		else if(rand == 1) {
			launcherAngle.rotateClockwise();
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
	public Angle getAngleForward() {
		return angleForward;
	}


	@Override
	public Angle getLauncherAngle() {
		return launcherAngle;
	}
	
	@Override
	public String getType() {
		return "s";
	}
}
