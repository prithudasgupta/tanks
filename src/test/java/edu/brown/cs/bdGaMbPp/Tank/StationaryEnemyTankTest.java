package edu.brown.cs.bdGaMbPp.Tank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class StationaryEnemyTankTest {
	
	
	public void construction() {
		
		Tank user = new StationaryEnemyTank(new Coordinate(3, 4));
		assertEquals(user.getCoord(), new Coordinate(3, 4));
		assertEquals((int)user.getAngleForward().getDegrees(), 90);
		assertEquals((int)user.getLauncherAngle().getDegrees(), 90);	
		
		
	}
	
	
	public void launcherRotation() {
		StationaryEnemyTank user = new StationaryEnemyTank(new Coordinate(3, 4));
		user.rotateLauncher();
		assertEquals(Math.abs((int)user.getLauncherAngle().getDegrees() - 90),  1);
	}

}
