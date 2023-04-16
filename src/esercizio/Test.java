package esercizio;

import java.util.Vector;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Grafo g = new Grafo(7);

        g.aggiungi_arco(0, 1, 5);
        g.aggiungi_arco(0, 2, 2);
        g.aggiungi_arco(1, 2, 3);

        g.aggiungi_arco(2, 0, 1);
        g.aggiungi_arco(2, 3, 7);
        g.aggiungi_arco(3, 3, 5);

        // Print the original graph
        System.out.println("Grafo originale:");
        g.stampaGrafo();

        // Compute the MST using Kruskal's algorithm
        Vector<Pair<Integer, Integer>> mst = g.kruskal();

        // Print the MST edges
        System.out.println("\nMinimum Spanning Tree con Kruskal:");
        for (Pair<Integer, Integer> edge : mst) {
            System.out.println(edge.getKey() + " - " + edge.getValue());
        }
    }
}