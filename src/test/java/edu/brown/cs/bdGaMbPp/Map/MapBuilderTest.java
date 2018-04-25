package edu.brown.cs.bdGaMbPp.Map;

import static org.junit.Assert.*;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.junit.Test;

import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;

public class MapBuilderTest {

	@Test
	public void testTraversableRoad() {
		MapBuilder builder = new MapBuilder();
		GameMap map;
		for(int k = 0; k < 50 ; k++) {
			map = builder.createMap(.2, .2);
			Pair<Integer,Integer> initial = null;
			for(int i = 0 ; i < map.getWidth(); i ++){
				for(int j =0;j<map.getLength();j++){
					if(map.get(i,j).toString().equals("l")) {
						initial = new Pair<Integer,Integer>(i, j);
					}
				}
			}
			assertNotNull(initial);
			int actualFreeBlocks = this.getNumberOfFreeBlocks(map, initial);
			int ourFreeBlocks = 0;
			ourFreeBlocks += map.indicesByType("l").size();
			ourFreeBlocks += map.indicesByType("b").size();
			assertEquals(ourFreeBlocks, actualFreeBlocks);
		}



	}

	
	public int getNumberOfFreeBlocks(GameMap map, Pair<Integer,Integer> initial) {
		Set<Pair<Integer,Integer>> checked = new HashSet<Pair<Integer,Integer>>();
		Queue<Pair<Integer,Integer>> q = new LinkedList<Pair<Integer,Integer>>();
		q.add(initial);
		checked.add(initial);

		while(!q.isEmpty()) {

			Pair<Integer,Integer> curr = q.remove();
		
			//List<Pair<Integer,Integer>> neighbors = this.getNeighbors(curr, map);
			List<Pair<Integer,Integer>> neighbors = map.getValidNeighbors(curr);
			for(Pair<Integer,Integer> neighbor: neighbors) {
				if(!checked.contains(neighbor)) {
					checked.add(neighbor);
					q.add(neighbor);
				}
			}

		}

		return checked.size();

	}


}


