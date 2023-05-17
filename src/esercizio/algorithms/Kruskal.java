package esercizio.algorithms;

import esercizio.graph.Edge;
import esercizio.graph.Graph;
import esercizio.utils.UnionFind;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    private final Graph graph;
    private List<Edge> mst;

    public Kruskal(Graph graph) {
        this.graph = graph;
    }

    public synchronized List<Edge> kruskal() {
        mst = new ArrayList<Edge>();

        // Create a new edge class that includes source, destination, and weight
        class ExtendedEdge implements Comparable<ExtendedEdge> {
            int source, dest, weight;

            ExtendedEdge(int source, int dest, int weight) {
                this.source = source;
                this.dest = dest;
                this.weight = weight;
            }

            @Override
            public int compareTo(ExtendedEdge compareEdge) {
                return this.weight - compareEdge.weight;
            }
        }

        List<ExtendedEdge> edges = new ArrayList<ExtendedEdge>();

        // Get all edges of the graph
        for (int i = 0; i < graph.getNumVertices(); i++) {
            for (Edge e : graph.getEdges(i)) {
                edges.add(new ExtendedEdge(i, e.getDest(), e.getWeight()));
            }
        }

        // Sort the edges in non-decreasing order
        Collections.sort(edges);

        // Create union-find data structure
        UnionFind uf = new UnionFind(graph.getNumVertices());

        for (ExtendedEdge edge : edges) {
            int u = edge.source;
            int v = edge.dest;

            // Check if the selected edge makes a cycle with the MST formed so far
            if (uf.find(u) != uf.find(v)) {
                // If it doesn't, include this edge in the MST
                mst.add(new Edge(v, edge.weight));
                // Merge the two sets
                uf.union(u, v);
            }
        }

        return mst;
    }

    public synchronized List<Edge> getMST() {
        return mst;
    }
}

