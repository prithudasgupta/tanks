package edu.brown.cs.bdGaMbPp.Tank;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public interface Tank {
	
	void move(Direction d);
	
	void rotateTank(Direction d);
	
	void shoot();

	Coordinate getCoord();
}


