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
		int index = (int)(Math.random() * landIndices.size());
		Pair<Integer, Integer> userTankStart = landIndices.get(index);
		landIndices.remove(index);
		Tank user = new UserTank(convertToCoordinate(userTankStart));
		List<Tank> enemies = createTanks(difficulty, landIndices);
		
		return new Game(newMap, user, enemies);
		
		
	}
	
	private static Coordinate convertToCoordinate(Pair<Integer, Integer> indice) {
		return new Coordinate(indice.getSecond(), indice.getFirst());
	}
	
	private static List<Tank> createTanks(int difficulty, List<Pair<Integer, Integer>> landSpaces){
		return createTanks(difficulty, new ArrayList<Tank>(), landSpaces);
	}
	
	private static List<Tank> createTanks(int difficulty, List<Tank> currentList,  List<Pair<Integer, Integer>> landIndices){
		//template method need to add when more tank types added

		int numTanks = (int) (Math.random() * Math.min(difficulty, landIndices.size()));

		if (numTanks == 0) {
			numTanks = 1;
		}

		for (int i = 0; i < numTanks; i++) {
			int randIndex = (int) (Math.random() * landIndices.size());
			Pair<Integer, Integer> newStart = landIndices.get(randIndex);
			landIndices.remove(randIndex);
			
			Tank newTank = new StationaryEnemyTank(convertToCoordinate(newStart));
			currentList.add(newTank);
		}
		//System.out.println("currentList = " + currentList);
		return currentList;
	}


}
