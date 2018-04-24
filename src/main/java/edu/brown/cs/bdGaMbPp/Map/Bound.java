package edu.brown.cs.bdGaMbPp.Map;

public class Bound implements Location {
	
	public Bound() {
		
	}

	@Override
	public boolean isTravesable() {
		return false;
	}

	@Override
	public boolean isBreakable() {
		return false;
	}

	@Override
	public boolean isShootable() {
		return false;
	}

	@Override
	public String getRepresentation() {
		return "x";
	}

	
	public String toString() {
		return getRepresentation();
	}

}
