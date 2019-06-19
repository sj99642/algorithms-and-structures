package java_algorithms.graphs;

public class Edge<V extends Vertex>
{
    public V start;
    public V end;
    public double weight;

    public Edge(V start, V end, double weight)
    {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    public Edge(V start, V end)
    {
        this.start = start;
        this.end = end;
        this.weight = 1;
    }
}