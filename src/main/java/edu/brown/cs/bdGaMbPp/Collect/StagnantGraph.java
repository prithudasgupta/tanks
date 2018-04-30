package edu.brown.cs.bdGaMbPp.Collect;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Graph that is immutable once constructed. Used for testing dijkstra.
 *
 * @author prithudasgupta
 *
 * @param <V>
 *          Type of nodes
 * @param <E>
 *          Type of edges
 */
public class StagnantGraph<V, E> implements Graph<V, E> {

  private Map<String, GraphNode<V, E>> graphMap;

  /**
   * Stagnant graph needs list of nodes, and for each node, its neighbors
   * weights, and edges.
   *
   * @param nodes
   *          List of nodes in graph
   * @param edgeIds
   *          Map for each node with its neighboring edge ids
   * @param weights
   *          Map for each node with its neighboring weights
   * @param edges
   *          Map for each node its neighboring edges
   */
  public StagnantGraph(List<V> nodes, Map<String, List<String>> edgeIds,
      Map<String, List<Double>> weights, Map<String, List<E>> edges) {
    graphMap = new HashMap<String, GraphNode<V, E>>();
    for (V node : nodes) {
      String id = node.toString();
      GraphNode<V, E> newNode = new GraphNode<V, E>(node, edgeIds.get(id),
          weights.get(id), edges.get(id));
      graphMap.put(node.toString(), newNode);
    }
  }

  @Override
  public boolean hasNode(String id) {

    return graphMap.containsKey(id);
  }

  @Override
  public GraphNode<V, E> getNodeByID(String id) {
    if (graphMap.containsKey(id)) {
      return graphMap.get(id);
    }
    return null;
  }

  @Override
  public void addNode(String id) {
    return;
  }

  @Override
  public Set<String> buildNeighbors(String id) {
    if (graphMap.containsKey(id)) {
      return graphMap.get(id).getNeighborIds();
    }
    return Collections.<String>emptySet();
  }

  @Override
  public double getEdgeWeight(String from, String to) {
    return graphMap.get(from).getWeight(to);
  }

}
