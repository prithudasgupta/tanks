package edu.brown.cs.bdGaMbPp.Collect;

import java.util.ArrayList;

import java.util.List;

import com.google.common.collect.ImmutableList;


/**
 * A coordinate class which represents an n dimensional space and n values in
 * that space.
 *
 * @author Prithu Dasgupta
 */

public class Coordinate {

  private int numDimmensions;
  private List<Double> coordinates;
  private static final double EPSILON = .0001;

  /**
   * Construct a Coordinate using the a list of length numDimmensions
   * representing the value of each dimension.
   *
   * @param numDimmensions
   *          Integer representing the number of dimensions in space
   * @param coordinates
   *          List of doubles representing value of each dimension
   */
  public Coordinate(List<Double> theCoordinates) {
	coordinates = theCoordinates;
    numDimmensions = coordinates.size();
  }
  
  public Coordinate(double x) {
	  numDimmensions = 1;
	  List<Double> theCoords = new ArrayList<Double>();
	  theCoords.add(x);
	  coordinates = theCoords;
  }
  
  public Coordinate(double x, double y) {
	  numDimmensions = 2;
	  List<Double> theCoords = new ArrayList<Double>();
	  theCoords.add(x);
	  theCoords.add(y);
	  coordinates = theCoords;
  }
  
  public Coordinate(double x, double y, double z) {
	  numDimmensions = 3;
	  List<Double> theCoords = new ArrayList<Double>();
	  theCoords.add(x);
	  theCoords.add(y);
	  theCoords.add(z);
	  coordinates = theCoords;
  }

  /**
   * Gets the amount of dimmensions.
   *
   * @return The number of dimmensions
   */
  public int getNumDimmensions() {
    return this.numDimmensions;
  }

  /**
   * Gets the coordinate vector .
   *
   * @return The coordinate vector as arrayList.
   */
  public List<Double> getCoordinateVector() {
    return ImmutableList.copyOf(this.coordinates);
  }

  /**
   * Takes in a dimension and returns the numerical value of the coordinate in
   * that dimension.
   *
   * @param dimmension
   *          Integer from 0 to numDimmensions - 1 representing axis
   * @return Numerical value of axis in given dimension
   */
  public double getCoordinate(int dimmension) {
    return coordinates.get(dimmension);
  }

  /**
   * Takes in two coordinates and finds euclidian distance between the two.
   *
   * @param other
   *          Another coordinate in the same dimensional space
   * @return Distance between the two points in space
   */
  public double getDistance(Coordinate other) {

    double distance = 0;
    double difference;
    for (int i = 0; i < this.getNumDimmensions(); i++) {
      difference = this.getCoordinate(i) - other.getCoordinate(i);
      distance = difference * difference + distance;
    }
    return Math.sqrt(distance);
  }

  /**
   * Takes in a level of depth and returns the numerical value of the coordinate
   * in that splitting axis.
   *
   * @param level
   *          Non negative integer representing depth of tree
   * @return numerical value of axis in given splitting axis
   */
  public double getCoordinateByAxis(int level) {
    return getCoordinate(level % getNumDimmensions());
  }
  
  public void forwardByAngle(Angle currentAngle, double moveSpeed) {
	  coordinates.set(0, coordinates.get(0) + (moveSpeed * currentAngle.getCos()));
	  coordinates.set(1, coordinates.get(1) + (moveSpeed * currentAngle.getSin()));
  }
  
  public void backwardByAngle(Angle currentAngle, double moveSpeed) {
	  coordinates.set(0, coordinates.get(0) - (moveSpeed * currentAngle.getCos()));
	  coordinates.set(1, coordinates.get(1) - (moveSpeed * currentAngle.getSin()));
  }

  /**
   * Returns readable version of coordinate in form (x, y, z, ...).
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append('(');
    for (double coord : this.getCoordinateVector()) {
      sb.append(String.format("%.2f", coord));
      sb.append(' ');
    }
    sb.append(')');
    return sb.toString();
  }
  
  @Override
  public boolean equals(Object o) {
	  if (o == this) {
	      return true;
	    }
	    if (!(o instanceof Coordinate)) {
	      return false;
	    }
	    Coordinate other = (Coordinate) o;
	    if (this.getNumDimmensions() == other.getNumDimmensions()) {
	    		for (int i = 0; i < this.getNumDimmensions(); i++) {
	    			if (Math.abs(this.getCoordinate(i) - other.getCoordinate(i)) > EPSILON) {
	    				return false;
	    			}
	    		}
	    		return true;
	    }
	    return false;
  }
}

