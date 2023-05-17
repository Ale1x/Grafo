package esercizio.graph;

public class Edge implements Comparable<Edge>{
    private final int dest;
    private final int weight;

    public Edge(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }


    public int getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge other) {
        return this.weight - other.weight;
    }
}
