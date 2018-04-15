package edu.brown.cs.bdGaMbPp.Map;

import edu.brown.cs.bdGaMbPp.Collect.Pair;

import java.util.LinkedList;
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
        Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> curr =
                new Pair<Pair<Integer,Integer>,
                        Pair<Integer,Integer>>(new Pair(0, 16),new Pair(0, 24));
        Queue<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>> q =
                new LinkedList<>();
        q.add(curr);
        int rand;
        int rowstart;
        int rowend;
        int colstart;
        int colend;
        int iter = 0;

        boolean twowide;
        while(!q.isEmpty()){
            twowide = false;
            if(Math.random()*100 < 60){
                twowide = true;
            }
            curr = q.remove();
            rowstart = curr.getFirst().getFirst();
            rowend = curr.getFirst().getSecond();
            colstart = curr.getSecond().getFirst();
            colend = curr.getSecond().getSecond();
            switch(iter % 2){
                case 0:

                    rand = (int)(Math.random() * (colend - colstart));
                    rand += colstart;
                    for(i = rowstart; i < rowend; i ++){
                        if(map[i][rand] == 'w' && this.isValid(map, i, rand, "col")){
                            map[i][rand] = 'b';
                        }if(twowide){
                            if(i - 1 >= 0 && map[i-1][rand] == 'w' && this.isValid(map, i-1, rand, "col")){
                                map[i-1][rand] = 'b';
                            }else if(i + 1 < 16 && map[i+1][rand] == 'w' && this.isValid(map, i+1, rand, "col")){
                                map[i+1][rand] = 'b';
                            }

                            //count nearby
                        }else{
                            //count nearby
                        }

                    }
                    if(rand - colstart > 3){
                        q.add(new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(new Pair<Integer, Integer>(rowstart,rowend), new Pair<Integer, Integer>(colstart, rand)));
                    }
                    if(colend - rand > 3){
                        q.add(new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(new Pair<Integer, Integer>(rowstart,rowend), new Pair<Integer, Integer>(rand,colend)));

                    }

                    break;
                case 1:
                    rand = (int)(Math.random() * (rowend - rowstart));
                    rand += rowstart;
                    for(i = colstart; i < colend; i ++){
                        if(map[rand][i] == 'w' && this.isValid(map, rand, i, "row")){
                            map[rand][i] = 'b';
                        }
                        if(twowide){
                            if(i-1 >= 0 && map[rand][i-1] == 'w' && this.isValid(map, rand, i-1, "row")){
                                map[rand][i-1] = 'b';
                            }else if(i+1 < 24 && map[rand][i+1] == 'w' && this.isValid(map, rand, i+1, "row")){
                                map[rand][i+1] = 'b';

                            }
                        }
                    }
                    if(rand - rowstart > 3){

                        q.add(new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(new Pair<Integer, Integer>(rowstart, rand),new Pair<Integer, Integer>(colstart,colend)));

                    }
                    if(rowend - rand > 3){

                        q.add(new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(new Pair<Integer, Integer>(rand,rowend),new Pair<Integer, Integer>(colstart,colend)));

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
    }


    
}