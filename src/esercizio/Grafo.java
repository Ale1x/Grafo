package esercizio;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Vector;

public class Grafo {

    private Vector<Vector<Pair<Integer, Integer>>> grafo;

    public Grafo(Vector<Vector<Pair<Integer, Integer>>> g) {
        this.grafo = g;
    }

    public Grafo(int n) {
        this.grafo = new Vector<Vector<Pair<Integer, Integer>>>();

        for (int i = 0; i < n; i++)
            grafo.add(new Vector<Pair<Integer, Integer>>());
    }

    /* Metodo per aggiungere un nodo in un grafo NON orientato. */
    public void aggiungi_arco(int s, int d, int w) {
        // Check if the edge already exists
        boolean edgeExists = false;
        for (Pair<Integer, Integer> pair : grafo.get(s)) {
            if (pair.getKey() == d) {
                edgeExists = true;
                break;
            }
        }

        // Add the edge only if it doesn't exist already
        if (!edgeExists) {
            grafo.get(s).add(new Pair<>(d, w));
            grafo.get(d).add(new Pair<>(s, w));
        }
    }


    public int[] dijkstra(int start) {
        int V = grafo.size();
        int[] dist = new int[V];

        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;
        boolean[] visited = new boolean[V];
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<
                Pair<Integer, Integer>>
                ((a, b) -> a.getValue() - b.getValue());
        pq.add(new Pair<>(start, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().getKey();
            visited[u] = true;

            for (Pair<Integer, Integer> v : grafo.get(u)) {
                if (!visited[v.getKey()]) {
                    int newDist = dist[u] + v.getValue();
                    if (newDist < dist[v.getKey()]) {
                        dist[v.getKey()] = newDist;
                        pq.add(new Pair<>(v.getKey(), newDist));
                    }
                }
            }
        }

        return dist;
    }

    public Vector<Pair<Integer, Integer>> kruskal() {
        Vector<Pair<Integer, Integer>> mst = new Vector<Pair<Integer, Integer>>();

        // Crea un nuovo vettore per tenere traccia degli archi
        Vector<Triple<Integer, Integer, Integer>> edges
                = new Vector<Triple<Integer, Integer, Integer>>();
        for (int i = 0; i < grafo.size(); i++) {
            for (Pair<Integer, Integer> v : grafo.get(i)) {
                edges.add(new Triple<>(i, v.getKey(), v.getValue()));
            }
        }

        // Ordina gli archi in base al peso
        Collections.sort(edges, (a, b) -> a.getThird() - b.getThird());

        // Crea un nuovo oggetto UnionFind per tenere traccia di quali nodi sono connessi
        UnionFind uf = new UnionFind(grafo.size());

        // Aggiungi un arco, tranne se crea un ciclo
        for (Triple<Integer, Integer, Integer> e : edges) {
            int u = e.getFirst(), v = e.getSecond(), w = e.getThird();
            if (!uf.find(u, v)) {
                uf.union(u, v);
                mst.add(new Pair<>(u, v));
            }
        }

        return mst;
    }



    void stampaGrafo() {
        for (int i = 0; i < grafo.size(); i++) {
            System.out.println("\nVertice: " + i + ":");
            for (int j = 0; j < grafo.get(i).size(); j++) {
                System.out.print(" -> " + grafo.get(i).get(j).getKey() + " (weight: " + grafo.get(i).get(j).getValue() + ")");
            }
            System.out.println();
        }
    }
}