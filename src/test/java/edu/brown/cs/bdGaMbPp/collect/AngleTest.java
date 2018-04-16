package edu.brown.cs.bdGaMbPp.collect;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.brown.cs.bdGaMbPp.Collect.Angle;

public class AngleTest {
	
	@Test
	public void angleConstruction() {
		
		Angle testAngle = new Angle(90, 1);
		Angle largeAngle = new Angle(400, 1);
		Angle smallAngle = new Angle(-100, 1);
		
		assertEquals((int)testAngle.getDegrees(), 90);
		assertEquals((int)largeAngle.getDegrees(), 40);
		assertEquals((int)smallAngle.getDegrees(), 260);
	}
	
	@Test 
	public void sinAndCos() {
		Angle zero = new Angle(0, 1);
		Angle ninety = new Angle(90, 1);
		Angle oneEighty = new Angle(180, 1);
		
		assertEquals((int)zero.getCos(), 1);
		assertEquals((int)zero.getSin(), 0);
		assertEquals((int)ninety.getCos(), 0);
		assertEquals((int)ninety.getSin(), 1);
		assertEquals((int)oneEighty.getCos(), -1);
		assertEquals((int)oneEighty.getSin(), 0);	
	}
	
	@Test
	public void rotation() {
		Angle smallRotate = new Angle(90, 1);
		Angle largeRotate = new Angle(350, 20);
		
		largeRotate.rotateCounterClockwise();
		smallRotate.rotateCounterClockwise();
		
		assertEquals((int)largeRotate.getDegrees(), 10);
		assertEquals((int)smallRotate.getDegrees(), 91);
		
		largeRotate.rotateClockwise();
		smallRotate.rotateClockwise();
		assertEquals((int)largeRotate.getDegrees(), 350);
		assertEquals((int)smallRotate.getDegrees(), 90);
		
		
		
	}

}
