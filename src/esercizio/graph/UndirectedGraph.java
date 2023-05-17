package esercizio.graph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph implements Graph {
    private List<List<Edge>> adjacencyList;

    public UndirectedGraph(int numVertices) {
        adjacencyList = new ArrayList<List<Edge>>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    @Override
    public void addEdge(int src, int dest, int weight) {
        adjacencyList.get(src).add(new Edge(dest, weight));
        adjacencyList.get(dest).add(new Edge(src, weight));
    }

    @Override
    public List<Edge> getEdges(int vertex) {
        return adjacencyList.get(vertex);
    }

    @Override
    public int getNumVertices() {
        return adjacencyList.size();
    }
}