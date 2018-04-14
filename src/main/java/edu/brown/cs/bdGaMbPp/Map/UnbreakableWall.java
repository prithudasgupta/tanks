package edu.brown.cs.bdGaMbPp.Map;

public class UnbreakableWall implements Location{
	
	public UnbreakableWall() {
		
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
		return "u";
	}
	
	public String toString() {
		return getRepresentation();
	}

}
