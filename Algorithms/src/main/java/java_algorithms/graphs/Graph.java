package java_algorithms.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Graph<V extends Vertex>
{
    public List<V> vertices = new ArrayList<V>();
    public List<Edge<V>> edges = new ArrayList<Edge<V>>();

    public List<Edge<V>> edgesConnectedToVertex(V vertex)
    {
        List<Edge<V>> edgesFound = new ArrayList<Edge<V>>();
        // List edges and see if this is part of them
        for (Edge<V> edge : edges) {
            if (edge.start.equals(vertex) || edge.end.equals(vertex)) {
                edgesFound.add(edge);
            } 
        }

        return edges;
    }

    public List<V> vertexNeighbours(V vertex)
    {
        List<V> neighboursFound = new ArrayList<V>();
        // List edges, and if this is in it, add the other to the list
        for (Edge<V> edge : edges) {
            if (edge.start.equals(vertex)) {
                neighboursFound.add(edge.end);
            } else if (edge.end.equals(vertex)) {
                neighboursFound.add(edge.start);
            }
        }

        return neighboursFound;
    }

    /**
     * Performs a breadth-first traversal, starting at the first vertex, and returns the resulting list. 
     * @return List<V> Breadth-first traversal
     */
    public List<V> breadthFirst()
    {
        // Avoid an error when selecting the arbitrary vertex
        if (vertices.size() == 0) {
            return new ArrayList<V>();
        } else {
            return breadthFirst(vertices.get(0));
        }
    }

    /**
     * Performs a breadth-first traversal, starting at the given vertex. 
     * @return List<V> Breadth-first traversal
     */
    public List<V> breadthFirst(V startVertex)
    {
        // The list that will eventually be returned
        List<V> traversal = new ArrayList<V>();

        // A queue to hold new elements as we find them
        Queue<V> queue = new ArrayDeque<V>();

        // Create a map of vertex->discovered, to mark if the vertex has been found (not necesarily included)
        Map<V, Boolean> discovered = new HashMap<V, Boolean>();
        // Fill the map with all the vertices, marking them as undiscovered
        for (V vertex : vertices) {
            discovered.put(vertex, Boolean.valueOf(false));
        }
        // Mark the starting vertex as discovered
        discovered.put(startVertex, Boolean.valueOf(true));

        // Add the starting vertex to the queue
        queue.add(startVertex);

        // Keep going until no new vertices are left
        while (queue.size() != 0) {
            // Get the next item
            V vertex = queue.remove();

            // Add it to the list - it has been traversed
            traversal.add(vertex);

            // Get everything pointed to by it
            List<V> connectedToVertex = vertexNeighbours(vertex);

            // For all of them, if they were not already discovered, add them to the
            // queue and mark them as discovered
            for (V connected : connectedToVertex) {
                // If undiscovered
                if (!discovered.get(connected).booleanValue()) {
                    // Add to queue and mark as discovered
                    queue.add(connected);
                    discovered.put(connected, Boolean.valueOf(true));
                }
            }
        }

        // Should have now traversed the graph
        return traversal;
    }

    /**
     * Performs a depth-first traversal, starting at the first vertex, and returns the resulting list. 
     * @return List<V> Breadth-first traversal
     */
    public List<V> depthFirst()
    {
        // Avoid an error when selecting the arbitrary vertex
        if (vertices.size() == 0) {
            return new ArrayList<V>();
        } else {
            return depthFirst(vertices.get(0));
        }
    }

    /**
     * Performs a depth-first traversal, starting at the given vertex. 
     * @return List<V> Depth-first traversal
     */
    public List<V> depthFirst(V startVertex)
    {
        // The list that will eventually be returned
        List<V> traversal = new ArrayList<V>();

        // A queue to hold new elements as we find them
        Stack<V> queue = new Stack<V>();

        // Create a map of vertex->discovered, to mark if the vertex has been found (not necesarily included)
        Map<V, Boolean> discovered = new HashMap<V, Boolean>();
        // Fill the map with all the vertices, marking them as undiscovered
        for (V vertex : vertices) {
            discovered.put(vertex, Boolean.valueOf(false));
        }
        // Mark the starting vertex as discovered
        discovered.put(startVertex, Boolean.valueOf(true));

        // Add the starting vertex to the queue
        queue.add(startVertex);

        // Keep going until no new vertices are left
        while (queue.size() != 0) {
            // Get the next item
            V vertex = queue.pop();

            // Add it to the list - it has been traversed
            traversal.add(vertex);

            // Get everything pointed to by it
            List<V> connectedToVertex = vertexNeighbours(vertex);

            // For all of them, if they were not already discovered, add them to the
            // queue and mark them as discovered
            for (V connected : connectedToVertex) {
                // If undiscovered
                if (!discovered.get(connected).booleanValue()) {
                    // Add to queue and mark as discovered
                    queue.add(connected);
                    discovered.put(connected, Boolean.valueOf(true));
                }
            }
        }

        // Should have now traversed the graph
        return traversal;
    }
}