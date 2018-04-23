package edu.brown.cs.bdGaMbPp.Tank;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class Bullet {
	
	private Coordinate location;
	private Angle angle;
	private double speed;

	public Bullet(Coordinate coord, Angle angleForward, double bulletSpeed) {
		location = coord;
		angle = angleForward;
		speed = bulletSpeed;
	}
	
	public void move() {
		location.forwardByAngle(angle, speed);
	}
	
	
	
	

}
