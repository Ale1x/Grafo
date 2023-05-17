package esercizio.algorithms;

import esercizio.graph.Edge;
import esercizio.graph.Graph;

import java.util.Arrays;

public class BellmanFord {
    private final Graph graph;
    private int[] dist;
    private int[] parent;

    public BellmanFord(Graph graph) {
        this.graph = graph;
    }

    private synchronized void initialize(int start) {
        int V = graph.getNumVertices();
        dist = new int[V];
        parent = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        parent[start] = start;
    }

    private synchronized void calculateShortestPaths() {
        int V = graph.getNumVertices();
        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (Edge edge : graph.getEdges(u)) {
                    int v = edge.getDest();
                    int weight = edge.getWeight();
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        parent[v] = u;
                    }
                }
            }
        }
    }

    private synchronized boolean checkNegativeCycles() {
        int V = graph.getNumVertices();
        for (int u = 0; u < V; u++) {
            for (Edge edge : graph.getEdges(u)) {
                int v = edge.getDest();
                int weight = edge.getWeight();
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    System.out.println("Graph contains a negative-weight cycle");
                    return false;
                }
            }
        }
        return true;
    }

    public synchronized boolean bellmanFord(int start) {
        initialize(start);
        calculateShortestPaths();
        return checkNegativeCycles();
    }

    public synchronized int[] getDistances() {
        return dist;
    }

    public synchronized int[] getParents() {
        return parent;
    }
}
