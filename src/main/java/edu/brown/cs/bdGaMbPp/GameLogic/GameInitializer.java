package edu.brown.cs.bdGaMbPp.GameLogic;

import java.util.ArrayList;
import java.util.List;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;
import edu.brown.cs.bdGaMbPp.Tank.StationaryEnemyTank;
import edu.brown.cs.bdGaMbPp.Tank.Tank;

public final class GameInitializer {
	
	private static final int MAX_DIFFICULTY = 10;
	
	
	public static Game initializeGame(GameMap newMap, int difficulty) {
		
		List<Pair<Integer, Integer>> landIndices = newMap.indicesByType("l");
		Pair<Integer, Integer> userTankStart = landIndices.get((int)(Math.random() * landIndices.size()));
		Tank user = new UserTank(convertToCoordinate(userTankStart));
		List<Tank> enemies = createTanks(difficulty, newMap);
		
		return new Game(newMap, user, enemies);
		
		
	}
	
	private static Coordinate convertToCoordinate(Pair<Integer, Integer> indice) {
		return new Coordinate(indice.getSecond() + 0.5, indice.getFirst() + 0.5);
	}
	
	private static List<Tank> createTanks(int difficulty, GameMap newMap){
		return createTanks(difficulty, new ArrayList<Tank>(), newMap);
	}
	
	private static List<Tank> createTanks(int difficulty, List<Tank> currentList,  GameMap newMap){
		//template method need to add when more tank types added
		List<Pair<Integer, Integer>> landIndices = newMap.indicesByType("l");
		
		int numTanks = (int) Math.random() * Math.min(difficulty, landIndices.size());
		
		for (int i = 0; i < numTanks; i++) {
			int randIndex = (int) Math.random() * landIndices.size();
			Pair<Integer, Integer> newStart = landIndices.get(randIndex);
			landIndices.remove(randIndex);
			
			Tank newTank = new StationaryEnemyTank(convertToCoordinate(newStart));
			currentList.add(newTank);
		}
		return currentList;
	}

}
