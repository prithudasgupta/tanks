package edu.brown.cs.bdGaMbPp.Map;

public class BreakableWall implements Location {
	
	public BreakableWall() {
		
	}

	@Override
	public boolean isTravesable() {
		return false;
	}

	@Override
	public boolean isBreakable() {
		return true;
	}

	@Override
	public boolean isShootable() {
		return true;
	}

	@Override
	public String getRepresentation() {
		return "b";
	}
	
	public Location breakWall() {
		return new Land();
	}
	
	public String toString() {
		return getRepresentation();
	}

}
