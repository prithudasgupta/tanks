package edu.brown.cs.bdGaMbPp.Collect;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;


/**
 * Dijkkstra class for calculating lightest path in a graph.
 *
 * @author prithudasgupta
 *
 * @param <V>
 *          Type of the vertices of the graph
 * @param <E>
 *          Type of the edges of the graph
 */
public class Dijkstra<V, E> {

  private Graph<V, E> graph;
  private String source;
  private String destination;

  /**
   * Finds shortest path from start id to end in in graph.
   *
   * @param g
   *          Graph to be searched
   * @param startId
   *          The start of the search
   * @param endId
   *          The end of the search
   */
  public Dijkstra(Graph<V, E> g, String startId, String endId) {
    graph = g;
    source = startId;
    destination = endId;
  }

  /**
   * Returns path object as result of dijkstra's algorithm.
   *
   * @return Path of V, E
   */
  public Path<V, E> findShortestPath() {

    // initialize distance and predecessor dictionaries and visited set and
    // priority queue
    Map<String, Double> bestDistances = new HashMap<String, Double>();
    Map<String, String> bestPredecessors = new HashMap<String, String>();
    Set<String> visited = new HashSet<String>();
    PriorityQueue<Pair<String, Double>> dijsktraQueue;
    dijsktraQueue = new PriorityQueue<Pair<String, Double>>(
        new DijsktraQueueRanker());

    // initialize structures with source
    dijsktraQueue.add(new Pair<String, Double>(source, 0.0));
    bestDistances.put(source, 0.0);

    // iterate until queue is empty and no more of graph can be traverse
    while (!dijsktraQueue.isEmpty()) {

      // pop the top element of priority queue
      Pair<String, Double> currentNodePair = dijsktraQueue.poll();
      String currentNodeID = currentNodePair.getFirst();

      // if id is destination, we have found shortest path and create path
      if (currentNodeID.equals(destination)) {

        if (!graph.hasNode(destination)) {
          graph.addNode(destination);
        }

        return new Path<V, E>(source, destination, bestPredecessors, graph,
            bestDistances.get(destination));

      } else if (visited.contains(currentNodeID)) {
        // if node visited, no need to search again
        continue;
      } else {

        // add to visited and find current node's neighbors
        visited.add(currentNodeID);
        Set<String> neighbors = graph.buildNeighbors(currentNodeID);

        // iterate through neighbors and update dictionaries if shorter path
        // found
        for (String neighbor : neighbors) {
          double testDistance = bestDistances.get(currentNodeID)
              + graph.getEdgeWeight(currentNodeID, neighbor);
          if (!bestDistances.containsKey(neighbor)
              || testDistance < bestDistances.get(neighbor)) {
            bestDistances.put(neighbor, testDistance);
            bestPredecessors.put(neighbor, currentNodeID);
          }
          dijsktraQueue.add(
              new Pair<String, Double>(neighbor, bestDistances.get(neighbor)));
        }
      }
    }
    return new Path<V, E>();

  }

  /**
   * Comparator class for dijkstra priority queue.
   *
   * @author prithudasgupta
   *
   */
  private static class DijsktraQueueRanker
      implements Comparator<Pair<String, Double>> {
    public int compare(Pair<String, Double> loc1, Pair<String, Double> loc2) {
      return Double.compare(loc1.getSecond(), loc2.getSecond());
    }
  }

}
