package edu.brown.cs.bdGaMbPp.Map;

public class Pothole implements Location{
	
	public Pothole() {
		
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
		return true;
	}

	@Override
	public String getRepresentation() {
		return "p";
	}
	
	public String toString() {
		return getRepresentation();
	}

}
