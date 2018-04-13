package edu.brown.cs.bdGaMbPp.Tank;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class UserTank implements Tank{
	
	private Coordinate location;
	private Angle angleForward;
	private Angle launcherAngle;
	private boolean isAlive;
	
	private static final double MOVE_SPEED = .01;
	private static final double ROTATE_SPEED = .01;
	
	public UserTank(Coordinate startCoord, double startDegrees) {
		location = startCoord;
		angleForward = new Angle(startDegrees, ROTATE_SPEED);
		launcherAngle = new Angle(startDegrees, ROTATE_SPEED);
		isAlive = true;
	}
	

	@Override
	public void move(Direction d) {
		if (d.equals(Direction.FORWARD)) {
			
		}
		else if (d.equals(Direction.BACKWARD)) {
			
		}	
	}

	@Override
	public void rotateTank(Direction d) {
		if (d.equals(Direction.LEFT)) {
			angleForward.rotateCounterClockwise();
			
		}
		else if (d.equals(Direction.RIGHT)) {
			angleForward.rotateClockwise();
		}
		
	}

	@Override
	public void rotateLauncher(Direction d) {
		if (d.equals(Direction.LEFT)) {
			
		}
		else if (d.equals(Direction.RIGHT)) {
			
		}			
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	
}
