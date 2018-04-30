package edu.brown.cs.bdGaMbPp.Tank;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class UserTankTest {
	
	
	public void construction() {
		
		Tank user = new UserTank(new Coordinate(3, 4));
		assertEquals(user.getCoord(), new Coordinate(3, 4));
		assertEquals((int)user.getAngleForward().getDegrees(), 90);
		assertEquals((int)user.getLauncherAngle().getDegrees(), 90);	
	}
	
	 
	public void movementTest() {
		Tank user = new UserTank(new Coordinate(0, 0));
		user.move(Direction.FORWARD);
		assertEquals(user.getCoord(), new Coordinate(0, 1));
		user.move(Direction.RIGHT);
		assertEquals((int)user.getAngleForward().getDegrees(), 89);
		user.move(Direction.LEFT);
		assertEquals((int)user.getAngleForward().getDegrees(), 90);
		user.move(Direction.BACKWARD);
		assertEquals(user.getCoord(), new Coordinate(0, 0));
	}
	
	
	public void complexMovement() {
		Tank user = new UserTank(new Coordinate(0, 0));
	
		user.move(Direction.FORWARD);
		assertEquals(user.getCoord(), new Coordinate(.70710678118, .70710678118));
		
		user = new UserTank(new Coordinate(0, 0 ));
		for (int i = 0; i < 5; i++) {
			user.move(Direction.FORWARD);
		}
		assertEquals(user.getCoord(), new Coordinate(4, 3));
		assertEquals((int)user.getLauncherAngle().getDegrees(), 36);	
	}
	
	@Test
	public void launcherRotation() {
		UserTank user = new UserTank(new Coordinate(0, 0));
		user.rotateLauncher(new Coordinate(0, 1));
		
		//need to do
		//assertEquals((int)user.getLauncherAngle().getDegrees(), 90);
	}

}
