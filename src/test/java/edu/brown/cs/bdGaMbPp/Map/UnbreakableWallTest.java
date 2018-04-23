package edu.brown.cs.bdGaMbPp.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UnbreakableWallTest {
	
	@Test
	public void construction() {
		Location x = new UnbreakableWall();
		
		assertFalse(x.isBreakable());
		assertTrue(x.isShootable());
		assertFalse(x.isTravesable());
		assertEquals(x.getRepresentation(), "u");
	}


}
