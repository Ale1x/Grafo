package esercizio;

import java.util.*;

public class Grafo {

    private final Vector<Vector<Pair<Integer, Integer>>> grafo;

    public Grafo(int n) {
        this.grafo = new Vector<>();

        for (int i = 0; i < n; i++)
            grafo.add(new Vector<>());
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

    public void bfs(int start) {

        int v = grafo.size();
        int[] dist = new int[v];
        Queue<Integer> q = new LinkedList<>();
        int[] parent = new int[v];
        boolean[] visited = new boolean[v];

        for(int i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        visited[start] = true;
        parent[start] = start;
        dist[start] = 0;
        q.add(start);

        while(!q.isEmpty()) {

            int top = q.poll();

            for(Pair<Integer, Integer> neighbor : grafo.get(top)) {
                int neighborNode = neighbor.getKey();

                if(!visited[neighborNode]) {
                    visited[neighborNode] = true;
                    q.add(neighborNode);
                    parent[neighborNode] = top;
                    dist[neighborNode] = dist[top] + 1;
                }
            }
        }

        System.out.println("Node\\Distance\\Parent");
        for(int i = 0; i < v; i++) {
            if(dist[i] != Integer.MAX_VALUE)
                System.out.println(i + "\t" + dist[i] + "\t" + parent[i]);
        }
    }

    public void dfs(int start) {

        int v = grafo.size();
        int[] dist = new int[v];
        Stack<Integer> stack = new Stack<>();
        int[] parent = new int[v];
        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            dist[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }

        visited[start] = true;
        parent[start] = start;
        dist[start] = 0;
        stack.add(start);

        while (!stack.isEmpty()) {

            int top = stack.pop();

            for (Pair<Integer, Integer> neighbor : grafo.get(top)) {
                int neighborNode = neighbor.getKey();

                if (!visited[neighborNode]) {
                    visited[neighborNode] = true;
                    stack.add(neighborNode);
                    parent[neighborNode] = top;
                    dist[neighborNode] = dist[top] + 1;
                }
            }
        }
        System.out.println("Node\\Distance\\Parent");
        for(int i = 0; i < v; i++) {
            if(dist[i] != Integer.MAX_VALUE)
                System.out.println(i + "\t" + dist[i] + "\t" + parent[i]);
        }
    }

    public int[] dijkstra(int start) {
        int V = grafo.size();
        int[] dist = new int[V];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        boolean[] visited = new boolean[V];
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>
                (Comparator.comparingInt(Pair::getValue));
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
        Vector<Pair<Integer, Integer>> mst = new Vector<>();

        // Crea un nuovo vettore per tenere traccia degli archi
        Vector<Triple<Integer, Integer, Integer>> edges
                = new Vector<>();
        for (int i = 0; i < grafo.size(); i++) {
            for (Pair<Integer, Integer> v : grafo.get(i)) {
                edges.add(new Triple<>(i, v.getKey(), v.getValue()));
            }
        }

        // Ordina gli archi in base al peso
        edges.sort(Comparator.comparingInt(Triple::getThird));

        // Crea un nuovo oggetto UnionFind per tenere traccia di quali nodi sono connessi
        UnionFind uf = new UnionFind(grafo.size());

        // Aggiungi un arco, tranne se crea un ciclo
        for (Triple<Integer, Integer, Integer> e : edges) {
            int u = e.getFirst(), v = e.getSecond();
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