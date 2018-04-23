package edu.brown.cs.bdGaMbPp.Map;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GameMapTest {
	
	@Test
	public void construction() {
	
	List<List<Location>> total = new ArrayList<List<Location>>();	
	List<Location> row1 = new ArrayList<Location>();
	List<Location> row2 = new ArrayList<Location>();
	
	row1.add(new UnbreakableWall());
	row1.add(new BreakableWall());
	row1.add(new Pothole());
	row2.add(new Land());
	row2.add(new Land());
	row2.add(new Pothole());
	total.add(row1);
	total.add(row2);
	
	GameMap g = new GameMap(total);
	
	List<List<String>> totalString = new ArrayList<List<String>>();	
	List<String> row1String = new ArrayList<String>();
	List<String> row2String = new ArrayList<String>();
	
	row1String.add(new UnbreakableWall().getRepresentation());
	row1String.add(new BreakableWall().getRepresentation());
	row1String.add(new Pothole().getRepresentation());
	row2String.add(new Land().getRepresentation());
	row2String.add(new Land().getRepresentation());
	row2String.add(new Pothole().getRepresentation());
	totalString.add(row1String);
	totalString.add(row2String);
	
	assertEquals(g.getLength(), 3);
	assertEquals(g.getWidth(), 2);
	assertEquals(g.get(1, 1).getRepresentation(), "l");
	assertEquals(g.get(1, 2).getRepresentation(), "p");
	assertEquals(g.get(0, 1).getRepresentation(), "b");
	assertEquals(g.get(0, 0).getRepresentation(), "u");
	assertEquals(g.toString(), "ubp\nllp\n");
	assertEquals(g.getRepresentations(), totalString);
	
	}

}
