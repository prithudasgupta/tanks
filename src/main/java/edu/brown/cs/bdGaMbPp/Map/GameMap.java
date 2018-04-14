package edu.brown.cs.bdGaMbPp.Map;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
	
	  private final List<List<Location>> tiles;
	  private final int length;
	  private final int width;
	  
	  public GameMap(List<List<Location>> locations) {
		  tiles = locations;
		  length = locations.size();
		  if (length > 0) {
			  width = locations.get(0).size();
		  }
		  else {
			  width = 0;
		  }
	  }
	  
	  public GameMap(GameMap oldMap) {
		  int oldLength = oldMap.getLength();
		  int oldWidth = oldMap.getWidth();
		  List<List<Location>> newLocations = new ArrayList<List<Location>>();
				  
		  for (int i = 0; i < oldLength; i++) {
			  List<Location> rows = new ArrayList<Location>();
			  for(int j = 0; j < oldWidth; j++) {
				  rows.add(oldMap.get(i, j));
				  
			  }
			  newLocations.add(rows);
		  }
		  
		  tiles = newLocations;
		  length = newLocations.size();
		  if (length > 0) {
			  width = newLocations.get(0).size();
		  }
		  else {
			  width = 0;
		  }
		  
	  }
	  
	  public static GameMap representationToMap(List<List<String>> representations) {
		  List<List<Location>> newLocations = new ArrayList<List<Location>>();
		  
		  for (int i = 0; i < representations.size(); i++) {
			  List<Location> rows = new ArrayList<Location>();
			  for(int j = 0; j < representations.get(i).size(); j++) {
				  Location newLoc = representationToLocation(representations.get(i).get(j));
				  if (newLoc != null) {
					  rows.add(newLoc);
				  }
				  else {
					  return null;
				  }
				  
			  }
			  newLocations.add(rows);
		  }
	  		return new GameMap(newLocations);
	  }
	  
	  
	  
	  public static Location representationToLocation(String representation) {
		   if (representation.equals("p")) {
			   return new Pothole();
		   }
		   else if(representation.equals("b")) {
			   return new BreakableWall();
		   }
		   else if(representation.equals("u")) {
			   return new UnbreakableWall();
		   }
		   else if(representation.equals("l")) {
			   return new Land();
		   }
		   else {
			   return null;
		   }
	   }
	  
	  public int getLength() {
		  return length;
	  }
	  
	  public int getWidth() {
		  return width;
	  }
	  
	  public Location get(int r, int c) {
		    if (r < 0 || c < 0) {
		      return null;
		    }
		    if (r >= tiles.size()) {
		      return null;
		    }
		    List<Location> line = tiles.get(r);
		    if (c >= line.size()) {
		      return null;
		    }
		    return line.get(c);
		    
		  }
	  
	  @Override
	  public String toString() {
	    return toString('\n');
	  }

	  /**
	   * Return a human readable version of this Map.
	   *
	   * @param separator
	   *          A character used to separate the four rows.
	   * @return c string of r characters, joined by separator
	   */
	  public String toString(char separator) {
	    StringBuilder sb = new StringBuilder();
	    for (List<Location> row : tiles) {
	      for (Location c : row) {
	        sb.append(c.toString());
	      }
	      sb.append(separator);
	    }
	    return sb.toString();
	  }
	
	

}
