package edu.brown.cs.bdGaMbPp.Tank;

import java.util.List;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class DrunkWalkTank implements Tank{

	private Coordinate location;
	private Angle angleForward;
	private Angle launcherAngle;
	private boolean isAlive;
	private String type = "d";
	private Direction [] directions = Direction.values();
	
	
	private static final double MOVE_SPEED = .01;
	private static final double ROTATE_SPEED = .01;
	
	public DrunkWalkTank(Coordinate startCoord) {
		location = startCoord;
		isAlive = true;
	}
	

	@Override
	public void move(Direction d) {
		
		if (d == Direction.LEFT) {
			angleForward.rotateClockwise();
		}
		
		else if(d == Direction.RIGHT) {
			angleForward.rotateClockwise();
		}
		else if(d == Direction.FORWARD) {
			location.forwardByAngle(angleForward, MOVE_SPEED);
		}
		else if(d == Direction.BACKWARD) {
			location.backwardByAngle(angleForward, MOVE_SPEED);
		}
	}
	
	@Override
	public void move() {
		Direction curr = directions[(int)(Math.random() * directions.length)];
		move(curr);
		
	}

	public void rotateLauncher() {
		
		Direction curr = directions[(int)(Math.random() * directions.length)];
		
		if (curr == Direction.LEFT) {
			launcherAngle.rotateClockwise();
		}
		
		else if(curr == Direction.RIGHT) {
			launcherAngle.rotateCounterClockwise();
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

	public Coordinate potenitalMove(Direction d) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Coordinate> getCorners(double height, double width, Coordinate newCenter) {
	// TODO Auto-generated method stub
	return null;
	}


	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}


	@Override
	public Coordinate getEndCoord() {
		// TODO Auto-generated method stub
		return null;
	}
}
