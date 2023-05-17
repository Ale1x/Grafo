package esercizio.graph;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph implements Graph {
    private List<List<Edge>> adjacencyList;

    public DirectedGraph(int numVertices) {
        initializeAdjacencyList(numVertices);
    }

    private void initializeAdjacencyList(int numVertices) {
        adjacencyList = new ArrayList<List<Edge>>(numVertices);
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    @Override
    public void addEdge(int src, int dest, int weight) {
        adjacencyList.get(src).add(new Edge(dest, weight));
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
