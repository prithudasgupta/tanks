package edu.brown.cs.bdGaMbPp.Tank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class StationaryEnemyTankTest {
	
	@Test
	public void construction() {
		
		Tank user = new StationaryEnemyTank(new Coordinate(3, 4), 90);
		assertEquals(user.getCoord(), new Coordinate(3, 4));
		assertEquals((int)user.getAngleForward().getDegrees(), 90);
		assertEquals((int)user.getLauncherAngle().getDegrees(), 90);	
		
		
	}
	
	@Test
	public void launcherRotation() {
		StationaryEnemyTank user = new StationaryEnemyTank(new Coordinate(3, 4), 90);
		user.rotateLauncher();
		assertEquals(Math.abs((int)user.getLauncherAngle().getDegrees() - 90),  1);
	}

}
