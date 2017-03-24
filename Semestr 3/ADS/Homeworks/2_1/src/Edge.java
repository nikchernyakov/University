public class Edge {
    int from;
    int to;
    int weight;

    // Для невзвешенного графа
    Edge(int from, int to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    // Для невзвешенного графа
    Edge(int from, int to){
        this.from = from;
        this.to = to;
        this.weight = 1;
    }
}
