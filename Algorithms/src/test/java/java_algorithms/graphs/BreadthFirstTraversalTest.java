package java_algorithms.graphs;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;


public class BreadthFirstTraversalTest
{
    @Test
    public void testExample1()
    {
        // Set the graph up
        Vertex vA = new Vertex("A");
        Vertex vB = new Vertex("B");
        Vertex vC = new Vertex("C");
        Vertex vD = new Vertex("D");

        Graph<Vertex> graph = new Graph<Vertex>();
        graph.vertices.add(vA);
        graph.vertices.add(vB);
        graph.vertices.add(vC);
        graph.vertices.add(vD);
        graph.edges.add(new Edge<Vertex>(vA, vB));
        graph.edges.add(new Edge<Vertex>(vA, vC));
        graph.edges.add(new Edge<Vertex>(vB, vD));
        graph.edges.add(new Edge<Vertex>(vC, vD));

        // Do the traversal
        List<Vertex> traversal = graph.breadthFirst();
        assertEquals(traversal.get(0), vA);
        assertEquals(traversal.get(1), vB);
        assertEquals(traversal.get(2), vC);
        assertEquals(traversal.get(3), vD);
        assertEquals(traversal.size(), 4);
    }
}