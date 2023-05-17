package esercizio;

import esercizio.algorithms.BellmanFord;
import esercizio.algorithms.Dijkstra;
import esercizio.algorithms.Kruskal;
import esercizio.graph.DirectedGraph;
import esercizio.graph.Edge;
import esercizio.graph.Graph;

import java.util.List;
import java.util.concurrent.*;

public class Test {

    private static ExecutorService executor;

    public static void main(String[] args) throws InterruptedException {
        Graph graph = createGraph();

        System.out.println("Graph:");

        executor = Executors.newFixedThreadPool(3);

        processDijkstra(graph);

        processBellmanFord(graph);

        processKruskal(graph);

        executor.shutdown();
    }

    private static Graph createGraph() {
        Graph graph = new DirectedGraph(5);

        graph.addEdge(0, 1, 10);
        graph.addEdge(1, 2, 20);
        graph.addEdge(2, 3, 30);
        graph.addEdge(3, 4, 40);
        graph.addEdge(4, 0, 50);

        return graph;
    }

    private static void processDijkstra(Graph graph) {
        Future<int[]> dijkstraFuture = executor.submit(() -> {
            Dijkstra dijkstra = new Dijkstra(graph);
            return dijkstra.dijkstra(0);
        });

        try {
            int[] shortestPaths = dijkstraFuture.get();
            System.out.println("\nDijkstra's algorithm:");
            for (int i = 0; i < shortestPaths.length; i++) {
                System.out.println("Distance from 0 to " + i + ": " + shortestPaths[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processBellmanFord(Graph graph) {
        Future<int[]> bellmanFordFuture = executor.submit(() -> {
            BellmanFord bellmanFord = new BellmanFord(graph);
            boolean result = bellmanFord.bellmanFord(0);
            if (result) {
                return bellmanFord.getDistances();
            } else {
                return null;
            }
        });

        try {
            int[] shortestPathsBF = bellmanFordFuture.get();
            if (shortestPathsBF != null) {
                System.out.println("\nBellman-Ford's algorithm:");
                for (int i = 0; i < shortestPathsBF.length; i++) {
                    System.out.println("Distance from 0 to " + i + ": " + shortestPathsBF[i]);
                }
            } else {
                System.out.println("Bellman-Ford's algorithm detected a negative-weight cycle.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processKruskal(Graph graph) {
        Future<List<Edge>> kruskalFuture = executor.submit(() -> {
            Kruskal kruskal = new Kruskal(graph);
            return kruskal.kruskal();
        });

        try {
            List<Edge> mst = kruskalFuture.get();
            System.out.println("\nKruskal's algorithm:");
            int edgeCount = 0;
            for (Edge edge : mst) {
                System.out.println("Edge " + edgeCount + ": " + edge.getDest() + " (weight: " + edge.getWeight() + ")");
                edgeCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
