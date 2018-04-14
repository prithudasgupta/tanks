package edu.brown.cs.bdGaMbPp.Map;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Tank.Tank;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public final class MapBuilder {
	
	private static final int ROW_SIZE = 16;
	private static final int COLUMN_SIZE = 24;
    private static final int LEAST_WALL = 4;
    
    private GameMap map;
    
   public static GameMap newMap() {
	   List<List<Location>> initialMap = initalizeMap();
	   //need to continue
	   
	   return null;
   }
    
   public static GameMap reuseMap(GameMap oldMap) {
    		return new GameMap(oldMap);
   }
   
   public static GameMap reuseMap(List<List<String>> oldMapRepresentaion) {
	   return GameMap.representationToMap(oldMapRepresentaion);
	   
	   
   } 
   
   private static List<List<Location>> initalizeMap(){
	   List<List<Location>> initial = new ArrayList<List<Location>>();
	   for (int i = 0; i < ROW_SIZE; i++) {
		   List<Location> row = new ArrayList<Location>();
		   for (int j = 0; j < COLUMN_SIZE; j++) {
			   row.add(new BreakableWall());
		   }
		   initial.add(row);
	   }
	   return initial;
   }

    public Character[][] createMap(){
        Character[][] map = new Character[16][24];
        int i;
        int j;
        for(i = 0 ; i < 16; i ++){
            for(j=0;j<24;j++){
                map[i][j] = 'w';
            }
        }
        Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> curr = new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(new Pair(0, 16),new Pair(0, 24));
        Queue<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>> q = new LinkedList<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>>();
        q.add(curr);
        int rand;
        int start;
        int end;
        int iter = 0;
        while(!q.isEmpty()){
           curr = q.remove();
           switch(iter % 2){
               case 0:
                   start = curr.getFirst().getFirst();
                   end = curr.getFirst().getSecond();
                   rand = (int)(curr.getSecond().getSecond() - curr.getSecond().getFirst());
                   for(i = start; i < end; i ++){
                       if(map[i][rand] == 'w' && this.isValid(map, i, rand, "col")){
                           map[i][rand] = 'b';
                       }
                   }
                   if(curr.getSecond().getFirst() != rand){
                       q.add(new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(new Pair<Integer, Integer>(start,end), new Pair<Integer, Integer>(curr.getSecond().getFirst(), rand)));
                   }
                   if(rand != curr.getSecond().getSecond()){
                       q.add(new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(new Pair<Integer, Integer>(start,end), new Pair<Integer, Integer>(rand,curr.getSecond().getSecond())));

                   }

                   break;
               case 1:
                   start = curr.getSecond().getFirst();
                   end = curr.getSecond().getSecond();
                   rand = (int)(end - start);
                   for(i = start; i < end; i ++){
                       if(map[rand][i] == 'w' && this.isValid(map, rand, i, "row")){
                           map[rand][i] = 'b';
                       }
                   }
                   if(curr.getFirst().getFirst() != rand){
                       q.add(new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(new Pair<Integer, Integer>(curr.getFirst().getFirst(), rand), new Pair<Integer, Integer>(start,end)));

                   }
                   if(rand!=curr.getFirst().getSecond()){
                       q.add(new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(new Pair<Integer, Integer>(rand,curr.getFirst().getSecond()), new Pair<Integer, Integer>(start,end)));

                   }
                   break;
               default:
                   System.out.println("ERROR: wrong mod");
           }
            iter++;

        }



        return map;
    }

    public boolean isValid(Character[][] map, int row, int col, String roworcol){
        int numofblocks = 0;
        int i;
        switch(roworcol){
            case "row":
                for(i = row - LEAST_WALL; i < row + LEAST_WALL ; i ++){
                    if(map[i][col] == 'w'){
                        numofblocks ++;
                    }else{
                        numofblocks = 0;
                    }
                    if(numofblocks == LEAST_WALL){
                        return true;
                    }
                }
                break;
            case "col":
                for(i = col - LEAST_WALL; i < col + LEAST_WALL ; i ++){
                    if(map[row][i] == 'w'){
                        numofblocks ++;
                    }else{
                        numofblocks = 0;
                    }
                    if(numofblocks == LEAST_WALL){
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public GameMap getMap() {
        return map;
    }
}
