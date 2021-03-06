package java_algorithms.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.PriorityQueue;
import java.util.Comparator;

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

    /**
     * Lists all of the vertices that start at the given vertex
     */
    public List<Edge<V>> edgesStartingAtVertex(V vertex)
    {
        List<Edge<V>> edgesFound = new ArrayList<Edge<V>>();
        // List edges and see if this is the start
        for (Edge<V> edge : edges) {
            if (edge.start.equals(vertex)) {
                edgesFound.add(edge);
            } 
        }

        return edges;
    }

    /**
     * Lists all of the vertices that end at the given vertex
     */
    public List<Edge<V>> edgesEndingAtVertex(V vertex)
    {
        List<Edge<V>> edgesFound = new ArrayList<Edge<V>>();
        // List edges and see if this is the end
        for (Edge<V> edge : edges) {
            if (edge.end.equals(vertex)) {
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
     * Returns a map, with the keys being each neighbour of this vertex, and the edge being the edge getting there
     */
    public Map<V, Edge<V>> neighboursAndEdges(V vertex)
    {
        Map<V, Edge<V>> map = new HashMap<V, Edge<V>>();

        // List edges, and if this is in it, make a note
        for (Edge<V> edge : edges) {
            if (edge.start.equals(vertex)) {
                map.put(edge.end, edge);
            } else if (edge.end.equals(vertex)) {
                map.put(edge.start, edge);
            }
        }

        return map;
    }

    /**
     * Returns the edge that connects the two vertices
     */
    public Edge<V> edgeBetween(V v1, V v2)
    {
        for (Edge<V> edge : edges) {
            if (edge.start.equals(v1) && edge.end.equals(v2)) {
                return edge;
            } else if (edge.start.equals(v2) && edge.end.equals(v1)) {
                return edge;
            }
        }

        return null;
    }

    /**
     * Gives the direct distance between the two vertices. 
     */
    public double directDistance(V v1, V v2) {
        // Get the edge between the two vertices
        Edge<V> connection = edgeBetween(v1, v2);
        
        // Return the length
        if (connection == null) {
            return Double.POSITIVE_INFINITY;
        } else {
            return connection.weight;
        }
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

    /**
     * Uses Prim's algorithm to produce a minimum spanning tree of this graph, starting
     * with the first vertex in the list.
     */
    public Graph<V> minimumSpanningTreePrim()
    {
        // Avoid an error if vertices[0] does not exist
        if (vertices.size() == 0) {
            return new Graph<V>();
        } else {
            return minimumSpanningTreePrim(vertices.get(0));
        }
    }

    /**
     * Uses Prim's algorithm to produce a minimum spanning tree of this graph, starting from
     * the given vertex. 
     * 
     * Prim's algorithm starts with a single vertex as a seed, and grows the tree out from there.
     * 
     * @return Minimum Spanning Tree of this graph
     */
    public Graph<V> minimumSpanningTreePrim(V startVertex)
    {
        // The minimum spanning tree to be built up
        Graph<V> mst = new Graph<V>();

        // The maps to hold the current parent and weight of each edge
        Map<V, V> parents = new HashMap<V, V>();
        Map<V, Double> keys = new HashMap<V, Double>();

        // Fill all parents with null and keys with the max possible value
        for (V vertex : vertices) {
            parents.put(vertex, null);
            keys.put(vertex, Double.POSITIVE_INFINITY);
        }

        // Set key for the starting vertex to 0
        keys.put(startVertex, (double) 0);

        // Create a priority queue, to be able to efficiently find the smallest key
        // PriorityQueue<V> priQ = new PriorityQueue<V>(vertices.size(), new Comparator<V>() {
        //     public double compare(V v1, V v2) {
        //         return keys.get(v1) - keys.get(v2);
        //     }
        // });


        PriorityQueue<V> priQ = new PriorityQueue<V>(vertices.size(), Comparator.comparing(keys::get));

        // Add all of the vertices to the priority queue
        for (V v : vertices) {
            priQ.add(v);
        }

        // For as long as something is left, keep processing
        while (priQ.size() > 0) {
            // Get the closest vertex
            V newVertex = priQ.poll();

            // Add the vertex to the graph, along with its edge
            mst.vertices.add(newVertex);
            mst.edges.add(edgeBetween(newVertex, parents.get(newVertex)));

            mstPrimUpdate(priQ, newVertex, parents, keys);
        }

        return mst;
    }

    /**
     * Used by minimumSpanningTreePrim() to update the parents and keys of the vertices.
     * Takes the given vertex, and looks in the graph for new connections given the new
     * addition to the MST.
     */
    private void mstPrimUpdate(PriorityQueue<V> priQ, V newVertex, Map<V, V> parents, Map<V, Double> keys)
    {
        // Loop through the neighbours to the new vertex
        for (V adjVertex : this.vertexNeighbours(newVertex)) {
            // If this is not in the tree, and has a shorter distance than that currently recorded,
            // then update its parent and key
            double distance = directDistance(newVertex, adjVertex);
            if (priQ.contains(adjVertex) && distance < keys.get(adjVertex)) {
                // Set the tree's new vertex to be the parent of this vertex
                parents.put(adjVertex, newVertex);

                // Set the key to be the distance between the two
                keys.put(adjVertex, distance);

                // Removing and re-adding the vertex
                priQ.remove(adjVertex);
                priQ.add(adjVertex);
            }
        }
    }

    /**
     * Gets the distance between the two vertices using dijkstra's algorithm
     */
    public double dijkstraDistanceBetween(V from, V to)
    {
        return dijkstraTable(from).get(to);
    }

    /**
     * Runs Dijkstra's algorithm starting at the given vertex, and returns a map of vertices
     * to their shortest distance from the start vertex. 
     */
    public Map<V, Double> dijkstraTable(V from)
    {
        if (!vertices.contains(from)) {
            throw new IllegalArgumentException("Given vertex must be a member of this graph");
        }

        // Maintain the current way we would get to each vertex, and how long it is
        Map<V, V> parents = new HashMap<V, V>();
        Map<V, Double> distances = new HashMap<V, Double>();

        // At the start, each item has no parent and is infinitely far away from the start
        for (V vertex : this.vertices) {
            parents.put(vertex, null);
            distances.put(vertex, Double.POSITIVE_INFINITY);
        }

        // The start vertex has distance 0 away
        distances.put(from, (double) 0);

        // Make a priority queue of vertices
        PriorityQueue<V> priQ = new PriorityQueue<V>(vertices.size(), Comparator.comparing(distances::get));

        // Add all the keys to the priority queue
        for (V vertex : this.vertices) {
            priQ.add(vertex);
        }

        // For as long as something is left, keep processing
        while (priQ.size() > 0) {
            // Get the nearest vertex to the tree
            V vertex = priQ.poll();

            // Update all nodes adjacent to this one
            // That is, if getting to them via this node would be quicker than the way we have already found,
            // set this to be the parent and update their distance
            for (V adjacent : vertexNeighbours(vertex)) {
                double newDistance = distances.get(vertex) + directDistance(vertex, adjacent);

                if (priQ.contains(adjacent) && newDistance < distances.get(adjacent)) {
                    // New, closer way found
                    parents.put(adjacent, vertex);
                    distances.put(adjacent, newDistance);

                    // Remove from the queue and add again - needed for new placement
                    priQ.remove(adjacent);
                    priQ.add(adjacent);
                }
            }
        }

        return distances;
    }
}