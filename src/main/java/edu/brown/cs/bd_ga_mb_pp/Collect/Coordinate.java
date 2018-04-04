package edu.brown.cs.bd_ga_mb_pp.Collect;

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

  /**
   * Construct a Coordinate using the a list of length numDimmensions
   * representing the value of each dimension.
   *
   * @param numDimmensions
   *          Integer representing the number of dimensions in space
   * @param coordinates
   *          List of doubles representing value of each dimension
   */
  public Coordinate(int numDimmensions, List<Double> coordinates) {
    if (numDimmensions == coordinates.size()) {
      this.numDimmensions = numDimmensions;
      this.coordinates = coordinates;
    } else {
      System.out.println("Error: Incorrect dimmensions to size of vector");
    }
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
    return getCoordinateVector().get(dimmension);
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
}

