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

import edu.brown.cs.bdGaMbPp.Map.GameMap;
import edu.brown.cs.bdGaMbPp.Map.Location;
import edu.brown.cs.bdGaMbPp.Map.MapBuilder;

public class MapBuilderTest {


	public void testTraversableRoad() {
		MapBuilder builder = new MapBuilder();
		List<List<Location>> map = builder.getMapAsList(.2, .2);
		Point2D initial = null;
		for(int i = 0 ; i < map.size(); i ++){
			for(int j=0;j<map.get(0).size();j++){
				if(!map.get(i).get(j).toString().equals("b")) {
					initial = new Point(i, j);
				}
			}
		}
		int actualFreeBlocks = this.getNumberOfFreeBlocks(map, initial);
		System.out.println("actual = " + actualFreeBlocks);
		//use pripri's method here
		System.out.println("from input = " + actualFreeBlocks);

	}
	
	public List<Point2D> getNeighbors(Point2D point, List<List<Location>> map){
		List<Point2D> output = new ArrayList<Point2D>();
		int row = (int) point.getX();
		int col = (int) point.getY();
		for(int rowdiff = -1; rowdiff <=1; rowdiff+=2) {
			for(int coldiff = -1; coldiff <=1; coldiff+=2) {
				row = (int) point.getX() + rowdiff ;
				col = (int) point.getY() + coldiff ;
				if(!map.get(row).get(col).toString().equals("b")) {
					Point2D neighbor = new Point(row,col);
					output.add(neighbor);
				}
			}
		}
		return output;
			
		

		
	}
	public int getNumberOfFreeBlocks(List<List<Location>> mapAsList, Point2D point) {
		Set<Point2D> checked = new HashSet<Point2D>();
		Queue<Point2D> q = new LinkedList<Point2D>();
		q.add(point);
		while(!q.isEmpty()) {
			Point2D curr = q.remove();
			checked.add(curr);
			List<Point2D> neighbors = this.getNeighbors(curr, mapAsList);
			for(Point2D neighbor: neighbors) {
				if(!checked.contains(neighbor)) {
					q.add(neighbor);
				}
			}
			
		}
		
		return checked.size();

	}
	
		
	}


