package edu.brown.cs.bdGaMbPp.Tank;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class StationaryEnemyTank implements Tank{

	private Coordinate location;
	private Angle angleForward;
	private Angle launcherAngle;
	private boolean isAlive;
	private Direction [] directions = Direction.values();
	
	private static final double ROTATE_SPEED = .01;
	
	public StationaryEnemyTank(Coordinate startCoord, double startDegrees) {
		location = startCoord;
		angleForward = new Angle(startDegrees, ROTATE_SPEED);
		launcherAngle = new Angle(startDegrees, ROTATE_SPEED);
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
		
		Direction curr = directions[(int)(Math.random() * directions.length)];
		
		if (curr == Direction.LEFT) {
			launcherAngle.rotateClockwise();
		}
		
		else if(curr == Direction.RIGHT) {
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
}
