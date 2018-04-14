package edu.brown.cs.bdGaMbPp.Map;

public class Land implements Location{
	
	public Land() {
		
	}

	@Override
	public boolean isTravesable() {
		return true;
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
		return "l";
	}
	
	public String toString() {
		return getRepresentation();
	}

}
