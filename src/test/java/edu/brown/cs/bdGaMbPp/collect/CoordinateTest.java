package edu.brown.cs.bdGaMbPp.collect;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;

public class CoordinateTest {

	  @Test
	  public void basicConstruction() {

	    List<Double> coordList = new ArrayList<Double>();
	    coordList.add(0.0);
	    coordList.add(0.0);
	    Coordinate goodCoord = new Coordinate(0.00, 0.00);

	    assertEquals(goodCoord.getCoordinateVector(), coordList);
	    assertEquals(goodCoord.getNumDimmensions(), coordList.size());
	    assertEquals(goodCoord.toString(), "(0.00 0.00 )");
	  }
	  
	  @Test
	  public void greaterThanTwoDimmensions() {
		  
		  Coordinate positive = new Coordinate(3.0, 4.0, 5.0);

		  assertEquals(positive.getNumDimmensions(), 3);
		  assertEquals((int) positive.getCoordinate(0), 3);
		  assertEquals((int) positive.getCoordinate(1), 4);
		  assertEquals((int) positive.getCoordinate(2), 5);
		  
		  List<Double> coordList = new ArrayList<Double>();
		  coordList.add(1.0);
		  coordList.add(2.0);
		  coordList.add(3.0);
		  coordList.add(4.0);
		    
		  Coordinate four =  new Coordinate(coordList);
		  assertEquals(four.getNumDimmensions(), 4);
		  assertEquals((int) four.getCoordinate(0), 1);
		  assertEquals((int) four.getCoordinate(1), 2);
		  assertEquals((int) four.getCoordinate(2), 3);
		  assertEquals((int) four.getCoordinate(3), 4);
	  }

	  @Test
	  public void testDistances() {
	
	    Coordinate origin = new Coordinate(0, 0);
	    Coordinate positive = new Coordinate(3, 4);

	    assertEquals((int) origin.getDistance(positive), 5);

	  }
}
