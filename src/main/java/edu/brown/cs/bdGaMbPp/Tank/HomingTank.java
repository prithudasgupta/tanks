package edu.brown.cs.bdGaMbPp.Tank;

import java.util.List;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Map.GameMap;

public class HomingTank implements Tank {
	
	private Coordinate location;
	private GameMap map;
	private boolean isAlive;
	private String type = "h";
	
	public HomingTank(Coordinate startCoord) {
		location = startCoord;
		isAlive = true;
	}
	
	public Pair<Integer, Integer> nextMove(Pair<Integer, Integer> userTankLoc){
		
		Pair<Integer, Integer> start = new Pair<Integer, Integer>((int)location.getCoordinate(0), (int)location.getCoordinate(1));
		List<Pair<Integer, Integer>> route = map.getRoute(start, userTankLoc);
		if (route.size() > 0) {
			return route.get(0);
		}
		return start;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Coordinate getCoord() {
		// TODO Auto-generated method stub
		return location;
	}

	@Override
	public Angle getAngleForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Angle getLauncherAngle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return type;
	}

	@Override
	public Coordinate getEndCoord() {
		// TODO Auto-generated method stub
		return location;
	}
	
	
	

}
