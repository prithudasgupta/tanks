package edu.brown.cs.bdGaMbPp.GameLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;
import edu.brown.cs.bdGaMbPp.Tank.DrunkWalkTank;
import edu.brown.cs.bdGaMbPp.Tank.HomingTank;
import edu.brown.cs.bdGaMbPp.Tank.PathTank;
import edu.brown.cs.bdGaMbPp.Tank.StationaryEnemyTank;
import edu.brown.cs.bdGaMbPp.Tank.Tank;

public final class GameInitializer {
	
	private static final int MAX_DIFFICULTY = 100;
	private static final int DUMB_PROB = 2;
	private static GameMap map;
	private static Pair<Integer, Integer> userTankStart;
	
	
	public static Game initializeGame(GameMap newMap, int difficulty) {
		
		List<Pair<Integer, Integer>> landIndices = newMap.indicesByType("l");
		int index = (int)(Math.random() * landIndices.size());
		userTankStart = landIndices.get(index);
    Tank user = new UserTank(convertToCoordinate(userTankStart));
		landIndices.remove(index);
		map = newMap;
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

//		int numTanks = (int) (Math.random() * Math.min(difficulty, landIndices.size()));
//
//		if (numTanks == 0) {
//			numTanks = 1;
//		}
//
//		for (int i = 0; i < numTanks; i++) {
//			int randIndex = (int) (Math.random() * landIndices.size());
//			Pair<Integer, Integer> newStart = landIndices.get(randIndex);
//			landIndices.remove(randIndex);
//			int randProb = (int) (Math.random() * difficulty);
//			Tank newTank = null;
//			if (randProb < DUMB_PROB) {
//				newTank = new DrunkWalkTank(convertToCoordinate(newStart));
//			}
//			else {
//			 newTank = new StationaryEnemyTank(convertToCoordinate(newStart));
//			}
//			currentList.add(newTank);
//		}
//		//System.out.println("currentList = " + currentList);
//		return currentList;
//	}
	  
	  if(isBetween(difficulty, 0, 15)) {
	    return firstDifficultyLevel(difficulty, currentList, landIndices);
	  }else if(isBetween(difficulty, 16, 35)) {
	    return secondDifficultyLevel(difficulty, currentList, landIndices);
	  }else if(isBetween(difficulty, 36, 50)) {
	    return thirdDifficultyLevel(difficulty, currentList, landIndices);
	  }else if(isBetween(difficulty, 51, 65)) {
	    return fourthDifficultyLevel(difficulty, currentList, landIndices);
	  }else if(isBetween(difficulty, 66, 80)) {
	    return fifthDifficultyLevel(difficulty, currentList, landIndices);
	  }else if(isBetween(difficulty, 81, 100)) {
	    return sixthDifficultyLevel(difficulty, currentList, landIndices);
	  }
	  
	  return null;
	}
	
  private static List<Tank> sixthDifficultyLevel(int difficulty,
      List<Tank> currentList, List<Pair<Integer, Integer>> landIndices) {
    
    Map<Integer, Integer> tankNumbers = new HashMap<Integer, Integer>();
    int randStat = 0;
    int randDumb = getRandomInt(0,2);
    int randPath = getRandomInt(2,4);
    int randHome = getRandomInt(2,4);
   
    tankNumbers.put(0, randStat);
    tankNumbers.put(1, randDumb);
    tankNumbers.put(2, randPath);
    tankNumbers.put(3, randHome);
    List<Tank> tanks = assignLocations(tankNumbers, currentList, landIndices);
   
  return tanks;
  }
	
  private static List<Tank> fifthDifficultyLevel(int difficulty,
      List<Tank> currentList, List<Pair<Integer, Integer>> landIndices) {
    
    Map<Integer, Integer> tankNumbers = new HashMap<Integer, Integer>();
    int randStat = 0;
    int randDumb = getRandomInt(0,2);
    int randPath = getRandomInt(2,4);
    int randHome = getRandomInt(1,3);
    
    tankNumbers.put(0, randStat);
    tankNumbers.put(1, randDumb);
    tankNumbers.put(2, randPath);
    tankNumbers.put(3, randHome);
    List<Tank> tanks = assignLocations(tankNumbers, currentList, landIndices);
   
  return tanks;
  }
	
	 private static List<Tank> fourthDifficultyLevel(int difficulty,
	      List<Tank> currentList, List<Pair<Integer, Integer>> landIndices) {
	    
	   Map<Integer, Integer> tankNumbers = new HashMap<Integer, Integer>();
	    int randStat = 0;
	    int randDumb = getRandomInt(0,3);
	    int randPath = getRandomInt(1,3);
	    int randHome = getRandomInt(1,2);
	   
	    tankNumbers.put(0, randStat);
      tankNumbers.put(1, randDumb);
      tankNumbers.put(2, randPath);
      tankNumbers.put(3, randHome);
	    List<Tank> tanks = assignLocations(tankNumbers, currentList, landIndices);
	   
	  return tanks;
	  }
	
	private static List<Tank> thirdDifficultyLevel(int difficulty,
      List<Tank> currentList, List<Pair<Integer, Integer>> landIndices) {
    
	  Map<Integer, Integer> tankNumbers = new HashMap<Integer, Integer>();
    int randStat = getRandomInt(0, 1);
    int randDumb = getRandomInt(2,3);
    int randPath = getRandomInt(1,2);
    int randHome = getRandomInt(0,1);
   
    tankNumbers.put(0, randStat);
    tankNumbers.put(1, randDumb);
    tankNumbers.put(2, randPath);
    tankNumbers.put(3, randHome);
    List<Tank> tanks = assignLocations(tankNumbers, currentList, landIndices);
   
  return tanks;
  }

	private static List<Tank> secondDifficultyLevel(int difficulty,
	    List<Tank> currentList, List<Pair<Integer, Integer>> landIndices) {
    
	  Map<Integer, Integer> tankNumbers = new HashMap<Integer, Integer>();
    int randStat = getRandomInt(0, 2);
    int randDumb = getRandomInt(1,2);
    int randPath = getRandomInt(0,1);
    int randHome = 0;
   
    tankNumbers.put(0, randStat);
    tankNumbers.put(1, randDumb);
    tankNumbers.put(2, randPath);
    tankNumbers.put(3, randHome);
    List<Tank> tanks = assignLocations(tankNumbers, currentList, landIndices);
   
  return tanks;
  }

  private static List<Tank> firstDifficultyLevel(int difficulty, List<Tank> currentList,
      List<Pair<Integer, Integer>> landIndices) {
    // TODO Auto-generated method stub

      //List<Integer> tankNumbers = new ArrayList<Integer>();
    
    Map<Integer, Integer> tankNumbers = new HashMap<Integer, Integer>();
	    int randStat = getRandomInt(1, 3);
	    int randDumb = getRandomInt(1,2);
	    int randPath = getRandomInt(0,1);
	    int randHome = 0;
	   
      tankNumbers.put(0, randStat);
      tankNumbers.put(1, randDumb);
      tankNumbers.put(2, randPath);
      tankNumbers.put(3, randHome);
      
	    List<Tank> tanks = assignLocations(tankNumbers, currentList, landIndices);
	   
    return tanks;
  }
  
  private static List<Tank> assignLocations(Map<Integer, Integer> tankNumbers,
      List<Tank> currentList, List<Pair<Integer, Integer>> landIndices) {
    // TODO Auto-generated method stub
    
    int randStat = tankNumbers.get(0);
    int randDumb = tankNumbers.get(1);
    int randPath = tankNumbers.get(2);
    int randHome = tankNumbers.get(3);
    
    System.out.println(randStat + " " + randDumb + " " + randPath + " " + randHome);
    
    int currType = -1;
    
    currType = incrementType(currType, tankNumbers);

    
    int total = randStat + randDumb + randPath + randHome;

    while(total > 0) {
      
      int temp = total;
      for(int i = 0; i < temp; i++) {
        
        int randIndex = (int) (Math.random() * landIndices.size());
        Pair<Integer, Integer> newStart = landIndices.get(randIndex);
        
        if(!map.withinSight(newStart, userTankStart)) {
          
          Tank newTank = addTank(newStart, currType);
          
          landIndices.remove(randIndex);
          currentList.add(newTank);
          
          total--;
          tankNumbers.put(currType, tankNumbers.get(currType)-1);
          
          if(tankNumbers.get(currType) == 0) {
            
            System.out.println(currType + "," + tankNumbers.get(currType));
            
            int end = incrementType(currType, tankNumbers);
            
            if(end == -1) {
              total = 0;
            }else {
              currType = end;
            }
           
          }
          
        }else {
          
          i--;
          
        }
      }
    }
    
    return currentList;
    
  }
  
  private static int incrementType(int currType, Map<Integer, Integer> tankNumbers) {
    // TODO Auto-generated method stub
    currType++;
    
    if(tankNumbers.get(currType) == 0 && currType == 3) {
      return -1;
    }else if(tankNumbers.get(currType) == 0) {
      incrementType(currType, tankNumbers);
    }
    
    return currType;
  }


  private static Tank addTank(Pair<Integer, Integer> newStart, int currType) {
    
    Tank newTank = null;
    
    switch(currType) {
      case 0:
        newTank = new StationaryEnemyTank(convertToCoordinate(newStart));
        break;
      case 1:
        newTank = new DrunkWalkTank(convertToCoordinate(newStart));
        break;
      case 2:
        //newTank = new PathTank(convertToCoordinate(newStart));
        break;
      case 3:
        newTank = new HomingTank(convertToCoordinate(newStart));
        break;
      default:
        break;
    }
    
    return newTank;
    
  }

  public static boolean isBetween(int x, int lower, int upper) {
	  return lower <= x && x <= upper;
	}
  
  public static int getRandomInt(int min, int max) {
    return (int) (Math.floor(Math.random() * (max - min + 1)) + min); //The maximum is inclusive and the minimum is inclusive 
  }

}
