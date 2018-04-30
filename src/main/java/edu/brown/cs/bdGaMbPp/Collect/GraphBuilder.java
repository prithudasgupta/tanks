package edu.brown.cs.bdGaMbPp.Collect;


/**
 * Builder object dynamic graphs that will fetch neighbors as graph grows.
 *
 * @author prithudasgupta
 *
 * @param <V>
 *          Type of nodes
 * @param <E>
 *          Type of edges
 */
public interface GraphBuilder<V, E> {

  /**
   * Finds the neighbors of the current id.
   *
   * @param currentNodeId
   *          current id to be built
   * @return new graphNode with neighbors added
   */
  GraphNode<V, E> createNeighbors(String currentNodeId);

  /**
   * Creates an object of type V with current id.
   *
   * @param currentNodeId
   *          current id to be built
   * @return new type V using builder class
   */
  V createNode(String currentNodeId);
}
