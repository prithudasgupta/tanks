package edu.brown.cs.bdGaMbPp.Map;

import edu.brown.cs.bdGaMbPp.Collect.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MapBuilder {
    private static final double BREAKABLE_LIMIT = .4;
	private static final double POTHOLE_LIMIT = .1;
	private Integer leastwall;
    public MapBuilder(){
        leastwall = 4;
    }
    
    private List<List<Location>> getMapOfWalls(){
        List<List<Location>> map = new ArrayList<List<Location>>();
        int i;
        int j;
    	for(i = 0 ; i < 16; i ++){
        	map.add(new ArrayList<Location>());
            for(j=0;j<24;j++){
                map.get(i).add(new UnbreakableWall());
            }
        }
    	return map;
    }

    public GameMap createMap(){
    	//16 rows, 24 cols
  
        String upDownLeftRight = "0,16,0,24";
        Queue<String> q = new LinkedList<>();
        q.add(upDownLeftRight);
        int rand;
        int rowstart;
        int rowend;
        int colstart;
        int colend;
        int iter = 0;
        List<List<Location>> map = this.getMapOfWalls();
        boolean twowide;
        while(!q.isEmpty()){
            twowide = false;
            if(Math.random()*100 < 60){
                twowide = true;
            }
            String[] coords = q.remove().split(",");
            rowstart = Integer.parseInt(coords[0]);
            rowend = Integer.parseInt(coords[1]);
            colstart = Integer.parseInt(coords[2]);
            colend = Integer.parseInt(coords[3]);
            int i;
            int j;
            switch(iter % 2){
                case 0:

                    rand = (int)(Math.random() * (colend - colstart));
                    rand += colstart;
                    for(i = rowstart; i < rowend; i ++){
                        if(map.get(i).get(rand).getRepresentation().equals("u") && this.isValid(map, i, rand, "col")){
                        	map.get(i).set(rand, new Land());
                        }if(twowide){
                            if(i - 1 >= 0 && map.get(i-1).get(rand).getRepresentation().equals("u") && this.isValid(map, i-1, rand, "col")){
                                map.get(i-1).set(rand, new Land());
                            }else if(i + 1 < 16 && map.get(i+1).get(rand).getRepresentation().equals("u") && this.isValid(map, i+1, rand, "col")){
                                map.get(i+1).set(rand, new Land());
                            }
                            
                            //count nearby
                        }else{
                            //count nearby
                        }

                    }
                    if(rand - colstart > 3){
                    	q.add(rowstart + "," + rowend + "," + colstart + "," + rand);
                    }
                    if(colend - rand > 3){
                    	q.add(rowstart + "," + rowend + "," + rand + "," + colend);

                    }

                    break;
                case 1:
                    rand = (int)(Math.random() * (rowend - rowstart));
                    rand += rowstart;
                    for(i = colstart; i < colend; i ++){
                        if(map.get(rand).get(i).getRepresentation().equals("u") && this.isValid(map, rand, i, "row")){
                            map.get(rand).set(i, new Land());
                        }
                        if(twowide){
                            if(i-1 >= 0 && map.get(rand).get(i-1).getRepresentation().equals("u") && this.isValid(map, rand, i-1, "row")){
                                map.get(rand).set(i - 1, new Land());
                            }else if(i+1 < 24 && map.get(rand).get(i+1).getRepresentation().equals("u") && this.isValid(map, rand, i+1, "row")){
                                map.get(rand).set(i+1, new Land());

                            }
                        }
                    }
                    if(rand - rowstart > 3){
                    	q.add(rowstart + "," + rand + "," + colstart + "," + colend);

                    }
                    if(rowend - rand > 3){
                    	q.add(rand + "," + rowend + "," + colstart + "," + colend);

                    }
                    break;
                default:
                    System.out.println("ERROR: wrong mod");
            }
            iter++;

        }
        
        return new GameMap(addPotholesandBreakable(map));
    }
    
    private static List<List<Location>> addPotholesandBreakable(List<List<Location>> locs) {
    		List<List<Location>> converted = new ArrayList<List<Location>>();
    		for (int i = 0; i < locs.size(); i++) {
    			List<Location> currentRow = locs.get(i);
    			List<Location> newRow = new ArrayList<Location>();
    			for (int j = 0; j < currentRow.size(); j++) {
    				if (i == 0 || j == 0) {
    					newRow.add(new UnbreakableWall());
    				}
    				else if(i == locs.size() - 1 || j == currentRow.size() - 1) {
    					newRow.add(new UnbreakableWall());
    				}
    				else {
    					if (currentRow.get(j).getRepresentation().equals("u")) {
    						double breakableTest = Math.random();
    						if (breakableTest < BREAKABLE_LIMIT) {
    							newRow.add(new BreakableWall());
    						}
    						else {
    							newRow.add(new UnbreakableWall());
    						}
    					}
    					else if (currentRow.get(j).getRepresentation().equals("l")) {
    						double potholeTest = Math.random();
    						if (potholeTest < POTHOLE_LIMIT) {
    							newRow.add(new Pothole());
    						}
    						else {
    							newRow.add(new Land());
    						}
    					}
    				}
    			}
    			converted.add(newRow);	
    		}
    		return converted;
    }

    /*private boolean isValid(Character[][] map, int row, int col, String roworcol){
        int numofblocks = 0;
        int i;
        switch(roworcol){
            case "row":
                for(i = row - leastwall; i < row + leastwall ; i ++){
                    if(i >= 0 && i < 16){

                        if(map[i][col] == 'w'){
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
                    if(i >= 0 && i < 24){
                        if(map[row][i] == 'w'){
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
    }*/
    
    private boolean isValid(List<List<Location>> map, int row, int col, String roworcol){
        int numofblocks = 0;
        int i;
        switch(roworcol){
            case "row":
                for(i = row - leastwall; i < row + leastwall ; i ++){
                    if(i >= 0 && i < 16){
                    	
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
                    if(i >= 0 && i < 24){
                    	
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
        for(int i = 0 ; i < 16; i ++){
            for(int j=0;j<24;j++){
                System.out.print(map.get(i).get(j).getRepresentation());
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

}