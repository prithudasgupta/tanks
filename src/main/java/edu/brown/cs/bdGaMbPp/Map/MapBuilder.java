package edu.brown.cs.bdGaMbPp.Map;

import edu.brown.cs.bdGaMbPp.Collect.Coordinate;
import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Tank.Tank;
import edu.brown.cs.bdGaMbPp.Tank.UserTank;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MapBuilder {
    private Integer leastwall;
    public MapBuilder(){
        leastwall = 4;
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
                for(i = row - leastwall; i < row + leastwall ; i ++){
                    if(map[i][col] == 'w'){
                        numofblocks ++;
                    }else{
                        numofblocks = 0;
                    }
                    if(numofblocks == leastwall){
                        return true;
                    }
                }
                break;
            case "col":
                for(i = col - leastwall; i < col + leastwall ; i ++){
                    if(map[row][i] == 'w'){
                        numofblocks ++;
                    }else{
                        numofblocks = 0;
                    }
                    if(numofblocks == leastwall){
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public Map getMap() {
        return new Map();
    }

    public List<Tank> getEnemies() {
        return Collections.emptyList();
    }

    public Tank getUser() {
        return null;
    }
}
