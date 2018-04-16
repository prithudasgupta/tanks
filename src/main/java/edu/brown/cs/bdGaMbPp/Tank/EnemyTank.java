package edu.brown.cs.bdGaMbPp.Tank;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public interface EnemyTank {
	
	void move();
	
	void shoot();

	Coordinate getCoord();

}
