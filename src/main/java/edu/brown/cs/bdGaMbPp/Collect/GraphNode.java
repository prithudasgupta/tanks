package edu.brown.cs.bdGaMbPp.Collect;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.common.collect.ImmutableSet;


/**
 * Singular GraphNode that will be held in total graph.
 *
 * @author prithudasgupta
 *
 * @param <V>
 *          Type of nodes
 * @param <E>
 *          Type of edges
 */
public class GraphNode<V, E> {
  private V node;
  private String nodeID;
  private Map<String, Pair<E, Double>> neighbors;

  /**
   * Creates a new graph node with singular node.
   *
   * @param newNode
   *          Node to be held in graph
   */
  public GraphNode(V newNode) {
    node = newNode;
    nodeID = newNode.toString();
    neighbors = new HashMap<String, Pair<E, Double>>();
  }

  /**
   * Create a new graph node with neighbors initialized.
   *
   * @param newNode
   *          Node to be held in graph
   * @param neighborIds
   *          neighbors of this node
   * @param weights
   *          weights of this node
   * @param edges
   *          edges of this node
   */
  public GraphNode(V newNode, List<String> neighborIds, List<Double> weights,
      List<E> edges) {
    node = newNode;
    nodeID = newNode.toString();
    neighbors = new HashMap<String, Pair<E, Double>>();
    for (int i = 0; i < neighborIds.size(); i++) {
      neighbors.put(neighborIds.get(i),
          new Pair<E, Double>(edges.get(i), weights.get(i)));
    }
  }

  /**
   * Empty graph node constructor which sets to null.
   */
  public GraphNode() {
    node = null;
  }

  /**
   * Set new neighbors for graphNode.
   *
   * @param newNeighbors
   *          new map of neighbors
   */
  public void setNeighbors(Map<String, Pair<E, Double>> newNeighbors) {
    neighbors = newNeighbors;
  }

  /**
   * Add a neighbor to the current graphNode.
   *
   * @param neighborID
   *          id of neighbor
   * @param distance
   *          distance between current and neighbor
   * @param edge
   *          identifier for edge
   */
  public void addNeighbor(String neighborID, double distance, E edge) {
    neighbors.put(neighborID, new Pair<E, Double>(edge, distance));
  }

  /**
   * Get the current neighbor ids.
   *
   * @return Set of strings with neighbor ids
   */
  public Set<String> getNeighborIds() {
    return ImmutableSet.copyOf(neighbors.keySet());
  }

  /**
   * Get the current node.
   *
   * @return current node
   */
  public V getVertex() {
    return node;
  }

  /**
   * Gets the current edge.
   *
   * @param neighborId
   *          destination id
   * @return edge between nodes
   */
  public E getEdge(String neighborId) {
    if (neighbors.containsKey(neighborId)) {
      return neighbors.get(neighborId).getFirst();
    }
    return null;

  }

  /**
   * Returns weight between ids.
   *
   * @param neighborId
   *          destination of node
   * @return weight of edge
   */
  public double getWeight(String neighborId) {
    if (neighbors.containsKey(neighborId)) {
      return neighbors.get(neighborId).getSecond();
    }
    return Double.MAX_VALUE;

  }

  /**
   * Gets node id of graphNode.
   *
   * @return current id
   */
  public String getId() {
    return nodeID;
  }

  @Override
  public String toString() {
    return nodeID;
  }

  @Override
  public int hashCode() {
    return Objects.hash(nodeID);
  }
}
