package edu.brown.cs.bdGaMbPp.Map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import edu.brown.cs.bdGaMbPp.Collect.Angle;
import edu.brown.cs.bdGaMbPp.Collect.Pair;
import edu.brown.cs.bdGaMbPp.Tank.Direction;

public class GameMap {

	private final List<List<Location>> tiles;
	private final int length;
	private final int width;
	private int id;

	public GameMap(List<List<Location>> locations) {
		tiles = locations;
		width = locations.size();
		if (width > 0) {
			length = locations.get(0).size();
		}
		else {
			length = 0;
		}
	}

	public GameMap(GameMap oldMap) {
		int oldLength = oldMap.getLength();
		int oldWidth = oldMap.getWidth();
		List<List<Location>> newLocations = new ArrayList<List<Location>>();

		for (int i = 0; i < oldLength; i++) {
			List<Location> rows = new ArrayList<Location>();
			for(int j = 0; j < oldWidth; j++) {
				rows.add(oldMap.get(i, j));

			}
			newLocations.add(rows);
		}

		tiles = newLocations;
		width = newLocations.size();
		if (width > 0) {
			length = newLocations.get(0).size();
		}
		else {
			length = 0;
		}

	}

	public static GameMap representationToMap(List<List<String>> representations) {
		List<List<Location>> newLocations = new ArrayList<List<Location>>();

		for (int i = 0; i < representations.size(); i++) {
			List<Location> rows = new ArrayList<Location>();
			for(int j = 0; j < representations.get(i).size(); j++) {
				Location newLoc = representationToLocation(representations.get(i).get(j));
				if (newLoc != null) {
					rows.add(newLoc);
				}
				else {
					return null;
				}

			}
			newLocations.add(rows);
		}
		return new GameMap(newLocations);
	}

	public List<Pair<Integer, Integer>> indicesByType(String representation){
		List<Pair<Integer, Integer>> indices = new ArrayList<Pair<Integer, Integer>>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length ; j++) {
				if (this.get(i, j).getRepresentation().equals(representation)) {
					indices.add(new Pair<Integer, Integer>(i, j));
				}
			}
		}
		return indices;  
	}


	public static Location representationToLocation(String representation) {
		if (representation.equals("p")) {
			return new Pothole();
		}
		else if(representation.equals("b")) {
			return new BreakableWall();
		}
		else if(representation.equals("l")) {
			return new Land();
		}
		else {
			return new UnbreakableWall();
		}
	}

	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	public Location get(int r, int c) {
		if (r < 0 || c < 0) {
			return null;
		}
		if (r >= tiles.size()) {
			return null;
		}
		List<Location> line = tiles.get(r);
		if (c >= line.size()) {
			return null;
		}
		return line.get(c);

	}

	public List<List<String>> getRepresentations() {
		List<List<String>> rep = new ArrayList<>();
		for (List<Location> row : tiles) {
			List<String> temp = new ArrayList<>();
			for (Location c : row) {
				temp.add(c.getRepresentation());
			}
			rep.add(temp);
		}
		return rep;
	}

	@Override
	public String toString() {
		return toString('\n');
	}

	/**
	 * Return a human readable version of this Map.
	 *
	 * @param separator
	 *          A character used to separate the four rows.
	 * @return c string of r characters, joined by separator
	 */
	public String toString(char separator) {
		StringBuilder sb = new StringBuilder();
		for (List<Location> row : tiles) {
			for (Location c : row) {
				sb.append(c.toString() + " ");
			}
			sb.append(separator);
		}
		return sb.toString();
	}

	public List<Pair<Integer, Integer>> getValidNeighbors(Pair<Integer, Integer> curr){
		String currentSpot = this.get(curr.getFirst(), curr.getSecond()).getRepresentation();
		List<Pair<Integer, Integer>> valid = new ArrayList<Pair<Integer, Integer>>();
		if (currentSpot.equals("l") || currentSpot.equals("b")) {

			Location left = this.get(curr.getFirst() - 1, curr.getSecond());
			Location right = this.get(curr.getFirst() + 1, curr.getSecond());
			Location top = this.get(curr.getFirst() , curr.getSecond() - 1);
			Location bottom = this.get(curr.getFirst() , curr.getSecond() + 1);

			if (left != null && (left.getRepresentation().equals("l") || left.getRepresentation().equals("b"))) {
				valid.add(new Pair<Integer, Integer>(curr.getFirst() - 1, curr.getSecond()));
			}
			if (right != null && (right.getRepresentation().equals("l") || right.getRepresentation().equals("b"))) {
				valid.add(new Pair<Integer, Integer>(curr.getFirst() + 1, curr.getSecond()));
			}
			if (top != null && (top.getRepresentation().equals("l") || top.getRepresentation().equals("b"))) {
				valid.add(new Pair<Integer, Integer>(curr.getFirst(), curr.getSecond() - 1));
			}
			if (bottom != null && (bottom.getRepresentation().equals("l") || bottom.getRepresentation().equals("b"))) {
				valid.add(new Pair<Integer, Integer>(curr.getFirst(), curr.getSecond() + 1));
			}

		}
		return valid;
	}

	public static double getDistance(Pair<Integer, Integer> start, Pair<Integer, Integer> end) {
		int row1 = start.getFirst();
		int col1 = start.getSecond();
		int row2 = end.getFirst();
		int col2 = end.getSecond();
		return Math.sqrt(Math.pow(row2 - row1, 2) + Math.pow(col2 - col1, 2));

	}
	
	public boolean withinSight(Pair<Integer, Integer> cpu, Pair<Integer, Integer> user) {
		double deltaY = (user.getSecond() - cpu.getSecond());
        double deltaX = (user.getFirst() - cpu.getFirst());
        double distance = GameMap.getDistance(cpu, user);
       
        double currX = cpu.getFirst();
        double currY = cpu.getSecond();
        
        for (double i = 0; i < distance; i+=.25) {
                      currX += (deltaX/(distance/.25));
                      currY += (deltaY/(distance/.25));
                      
                      Pair<Integer, Integer> currIndex = new Pair<Integer, Integer>((int) currX, (int) currY);
                      String representation = this.get((int) currX, (int) currY).getRepresentation();
                      if (currIndex.equals(user)) {
                            return true;
                      }
                      else if (representation.equals("u")) {
                            return false;
                      }
                     
        }                         
        return false;
	}



	public Character bfs(Pair<Integer, Integer> start, Pair<Integer, Integer> end){
		if (!this.get(start.getFirst(), start.getSecond()).getRepresentation().equals("l") || 
				!this.get(end.getFirst(), end.getSecond()).getRepresentation().equals("l")) {

			return null;

		}
		Set<Pair<Integer, Integer>> visited = new HashSet<Pair<Integer, Integer>>();
		PriorityQueue<Pair<Integer, Integer>> q = new PriorityQueue<Pair<Integer, Integer>>(new BfsComparator(end));
		Character[][] direction = new Character[length][width];
		List<Pair<Integer, Integer>> neighbors = this.getValidNeighbors(start);
		for(Pair<Integer, Integer> neighbor: neighbors) {
			switch(neighbor.getFirst() - start.getFirst()) {
			case 0:
				switch(neighbor.getFirst() - start.getFirst()) {
				case 1:
					direction[neighbor.getFirst()][neighbor.getSecond()] = 'r';
					break;
				case -1:
					direction[neighbor.getFirst()][neighbor.getSecond()] = 'l';
					break;
				}
				break;
			case 1:
				direction[neighbor.getFirst()][neighbor.getSecond()] = 'd';
				break;
			case -1:
				direction[neighbor.getFirst()][neighbor.getSecond()] = 'u';
				break;
			}
		}

		q.add(start);
		while(!q.isEmpty()) {
			Pair<Integer, Integer> curr = q.poll();
			if (curr.equals(end)) {
				return direction[curr.getFirst()][curr.getSecond()];
			}
			visited.add(curr);
			neighbors = this.getValidNeighbors(curr);
			for(Pair<Integer, Integer> neighbor: neighbors) {
				if(!visited.contains(neighbor)) {
					q.add(neighbor);
					direction[neighbor.getFirst()][neighbor.getSecond()] = direction[curr.getFirst()][curr.getSecond()];
					
				}
				}
			}

		return null;
		}





	}

	class BfsComparator implements Comparator<Pair<Integer, Integer>> {

		private Pair<Integer, Integer> goal;	

		public BfsComparator(Pair<Integer, Integer> reference) {
			goal = reference;
		}

		@Override
		public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
			double distance1 = GameMap.getDistance(o1, goal);
			double distance2 = GameMap.getDistance(o2, goal);

			return Double.compare(distance1, distance2);
		}

	}
