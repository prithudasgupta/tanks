package edu.brown.cs.bdGaMbPp.Map;

public interface Location {
	
	boolean isTravesable();
	
	boolean isBreakable();
	
	boolean isShootable();
	
	String getRepresentation();
	
	String toString();

}
