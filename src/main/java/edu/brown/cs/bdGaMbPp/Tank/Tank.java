package edu.brown.cs.bdGaMbPp.Tank;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public interface Tank {
	
	void move();
	
	void move(Direction d);
	
	void shoot();

	Coordinate getCoord();
	
	Angle getAngleForward();
	
	Angle getLauncherAngle();
}


