package edu.brown.cs.bdGaMbPp.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class LandTest {

	@Test
	public void construction() {
		Location x = new Land();
		
		assertFalse(x.isBreakable());
		assertTrue(x.isShootable());
		assertTrue(x.isTravesable());
		assertEquals(x.getRepresentation(), "l");
	}

}
