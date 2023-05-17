package esercizio.algorithms;

import esercizio.graph.Edge;
import esercizio.graph.Graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {
    private final Graph graph;
    private int[] dist;
    private PriorityQueue<Edge> pq;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    private synchronized void initialize(int start) {
        int V = graph.getNumVertices();
        dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        pq.add(new Edge(start, 0));
    }

    private synchronized void calculateShortestPaths() {
        while (!pq.isEmpty()) {
            int u = pq.poll().getDest();
            for (Edge edge : graph.getEdges(u)) {
                int v = edge.getDest();
                int weight = edge.getWeight();
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Edge(v, dist[v]));
                }
            }
        }
    }

    public synchronized int[] dijkstra(int start) {
        initialize(start);
        calculateShortestPaths();
        return dist;
    }
}
