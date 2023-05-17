package esercizio.utils;

public class ExtendedEdge implements Comparable<ExtendedEdge> {
    private final int source;
    private final int dest;
    private final int weight;

    public ExtendedEdge(int source, int dest, int weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(ExtendedEdge compareEdge) {
        return this.weight - compareEdge.weight;
    }

    public int getSource() {
        return source;
    }

    public int getDest() {
        return dest;
    }

    public int getWeight() {
        return weight;
    }
}