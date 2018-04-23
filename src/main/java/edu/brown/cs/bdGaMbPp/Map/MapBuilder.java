package edu.brown.cs.bdGaMbPp.Map;

import edu.brown.cs.bdGaMbPp.Collect.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MapBuilder {
	private Integer numOfRows;
	private Integer numOfCols;

	private Integer leastwall;

	public MapBuilder(){
		leastwall = 3;
		numOfRows = 16;
		numOfCols = 24;
	}

	private List<List<Location>> getMapOfWalls(){
		List<List<Location>> map = new ArrayList<List<Location>>();
		int i;
		int j;
		for(i = 0 ; i < numOfRows ; i ++){
			map.add(new ArrayList<Location>());
			for(j=0;j<numOfCols;j++){
				map.get(i).add(new UnbreakableWall());
			}
		}
		return map;
	}
	private boolean withProbability(double prob) {
		if(Math.random() < prob){
			return true;
		}
		return false;
	}

	public List<List<Location>> getMapAsList(double potHoleProb, double breakableWallProb){
		String upDownLeftRight = "1," + (numOfRows-1) + ",1," + (numOfCols-1) + ",horizontal";

		Queue<String> q = new LinkedList<>();
		q.add(upDownLeftRight);
		int rand;
		int rowstart;
		int rowend;
		int colstart;
		int colend;
		List<List<Location>> map = this.getMapOfWalls();
		boolean twoWide;
		List<String> listOfLandCoords = new ArrayList<String>();

		while(!q.isEmpty()){

			twoWide = this.withProbability(.6);
			String[] coords = q.remove().split(",");
			rowstart = Integer.parseInt(coords[0]);
			rowend = Integer.parseInt(coords[1]);
			colstart = Integer.parseInt(coords[2]);
			colend = Integer.parseInt(coords[3]);
			switch(coords[4]){
			case "horizontal":

				rand = (int)(Math.random() * (colend - colstart));
				rand += colstart;
				for(int i = rowstart; i < rowend; i ++){
					if(map.get(i).get(rand).getRepresentation().equals("u") && this.isValid(map, i, rand, "col")){
						map.get(i).set(rand, new Land());
						if(!listOfLandCoords.contains(i + "," + rand)) {
							listOfLandCoords.add(i + "," + rand);

						}
					}if(twoWide){
						if(i - 1 >= 1 && map.get(i-1).get(rand).getRepresentation().equals("u") && this.isValid(map, i-1, rand, "col")){
							map.get(i-1).set(rand, new Land());
							if(!listOfLandCoords.contains(i-1 + "," + rand)) {

								listOfLandCoords.add(i-1 + "," + rand);
							}

						}else if(i + 1 < numOfRows - 1 && map.get(i+1).get(rand).getRepresentation().equals("u") && this.isValid(map, i+1, rand, "col")){
							map.get(i+1).set(rand, new Land());
							if(!listOfLandCoords.contains(i+1 + "," + rand)) {
								listOfLandCoords.add(i+1 + "," + rand);
							}

						}

					}

				}
				if(rand - colstart > leastwall){
					q.add(rowstart + "," + rowend + "," + colstart + "," + rand + ",vertical");
				}
				if(colend - rand > leastwall){
					q.add(rowstart + "," + rowend + "," + rand + "," + colend + ",vertical");

				}

				break;
			case "vertical":
				rand = (int)(Math.random() * (rowend - rowstart));
				rand += rowstart;
				for(int i = colstart; i < colend; i ++){
					if(map.get(rand).get(i).getRepresentation().equals("u") && this.isValid(map, rand, i, "row")){
						map.get(rand).set(i, new Land());
						if(!listOfLandCoords.contains(rand + "," + i)) {
							listOfLandCoords.add(rand + "," + i);

						}


					}
					if(twoWide){
						if(i-1 >= 1 && map.get(rand).get(i-1).getRepresentation().equals("u") && this.isValid(map, rand, i-1, "row")){
							map.get(rand).set(i - 1, new Land());
							if(!listOfLandCoords.contains(rand + "," + (i-1))) {
								listOfLandCoords.add(rand + "," + (i-1));

							}


						}else if(i+1 < numOfCols - 1 && map.get(rand).get(i+1).getRepresentation().equals("u") && this.isValid(map, rand, i+1, "row")){
							map.get(rand).set(i+1, new Land());
							if(!listOfLandCoords.contains(rand + "," + i+1)) {
								listOfLandCoords.add(rand + "," + i+1);

							}



						}
					}
				}
				if(rand - rowstart > leastwall){
					q.add(rowstart + "," + rand + "," + colstart + "," + colend + ",horizontal");


				}
				if(rowend - rand > leastwall){
					q.add(rand + "," + rowend + "," + colstart + "," + colend + ",horizontal");

				}
				break;
			default:
			}


		}

		//map = this.addBreakablesAndPotHoles(map, listOfLandCoords, potHoleProb, breakableWallProb);
		return map;

	}
	public GameMap createMap(double potHoleProb, double breakableWallProb){
		//16 rows, 24 cols
		if(potHoleProb + breakableWallProb >= 100) {
			System.out.println("ERROR: the sum of potHoleProb breakableWallProb are >= 100");
			return null;
		}
		List<List<Location>> map = this.getMapAsList(potHoleProb,breakableWallProb);

		return new GameMap(map);
	}

	private List<List<Location>> addBreakablesAndPotHoles(List<List<Location>> map, List<String> landCoords, double potHoleProb, double breakableWallProb) {
		for(String coord: landCoords) {
			String[] rowCol = coord.split(",");
			int row = Integer.parseInt(rowCol[0]);
			int col = Integer.parseInt(rowCol[1]);
			Double random = Math.random();
			if(random <= potHoleProb) {
				map.get(row).set(col, new Pothole());
			}else if(random <= potHoleProb + breakableWallProb) {
				map.get(row).set(col, new BreakableWall());

			}

		}
		return map;

	}
	private boolean isValid(List<List<Location>> map, int row, int col, String roworcol){
		int numofblocks = 0;
		int i;
		switch(roworcol){
		case "row":
			for(i = row - leastwall; i < row + leastwall ; i ++){
				if(i >= 0 && i < numOfRows){

					if(map.get(i).get(col).getRepresentation().equals("u")){
						numofblocks ++;
					}else{
						numofblocks = 0;
					}
					if(numofblocks == leastwall){
						return true;
					}
				}

			}
			break;
		case "col":
			for(i = col - leastwall; i < col + leastwall ; i ++){
				if(i >= 0 && i < numOfCols){

					if(map.get(row).get(i).getRepresentation().equals("u")){
						numofblocks ++;
					}else{
						numofblocks = 0;
					}
					if(numofblocks == leastwall){
						return true;
					}
				}
			}
			break;
		}
		return false;
	}


	public void printMap(List<List<Location>> map) {
		for(int i = 0 ; i < numOfRows; i ++){
			for(int j=0;j<numOfCols;j++){
				System.out.print(map.get(i).get(j).getRepresentation());
				System.out.print(" ");
			}
			System.out.print("\n");
		}
	}

}