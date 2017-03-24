public class Edge implements Comparable<Edge> {
    int from;
    int to;
    int weight;

    // For suspended graph
    Edge(int from, int to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    // For not suspended graph
    Edge(int from, int to){
        this.from = from;
        this.to = to;
        this.weight = 1;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}
