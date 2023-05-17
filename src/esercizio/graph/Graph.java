package esercizio.graph;

import java.util.List;

public interface Graph {
    void addEdge(int src, int dest, int weight);

    List<Edge> getEdges(int vertex);

    int getNumVertices();
}