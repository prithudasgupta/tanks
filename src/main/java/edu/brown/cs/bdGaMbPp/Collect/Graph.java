package edu.brown.cs.bdGaMbPp.Collect;


import java.util.Set;

/**
 * Interface for a graph network.
 *
 * @author prithudasgupta
 *
 * @param <V>
 *          Type of nodes in graph
 * @param <E>
 *          Type of edges in graph
 */
public interface Graph<V, E> {

  /**
   * Returns boolean whether node with id is in graph.
   *
   * @param id
   *          Id of given node
   * @return Boolean if id present
   */
  boolean hasNode(String id);

  /**
   * Gets graphNode in graph with given id.
   *
   * @param id
   *          Id of given node
   * @return GraphNode of that
   */
  GraphNode<V, E> getNodeByID(String id);

  /**
   * Adds node to graph.
   *
   * @param id
   *          Id of new node to be added.
   */
  void addNode(String id);

  /**
   * Gets neighbors of node with given id.
   *
   * @param id
   *          Id of nodes to be found
   * @return Set of ids that are neighboring current node
   */
  Set<String> buildNeighbors(String id);

  /**
   * Gets edge weight between two nodes.
   *
   * @param from
   *          Start node id
   * @param to
   *          End node id
   * @return Edge weight between
   */
  double getEdgeWeight(String from, String to);
}
