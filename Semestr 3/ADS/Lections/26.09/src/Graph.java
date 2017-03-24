import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {
    private static class Arc {
        int to;
        int weight;

        Arc(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    List<Arc>[] graph;

    public Graph(int n){
        graph = new ArrayList[n];

    }

    public void addArc(int from, int to, int weight){
        graph[from].add(new Arc(to, weight));
    }

    public static class Vertex implements Comparable<Vertex>{
        private int num;
        private int dist;
        private int from;

        Vertex(int num, int dist, int from){
            this.num = num;
            this.dist = dist;
            this.from = from;
        }

        public int compareTo(Vertex v){
            return dist - v.dist;
        }

        public int getNum() {
            return num;
        }

        public int getDist() {
            return dist;
        }

        public int getFrom() {
            return from;
        }
    }

    public List<Vertex> dijkstra(int u){
        PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(graph.length);
        for(int v = 0; v < graph.length; v++) {
            queue.offer(new Vertex(v, v == u ? 0 : Integer.MAX_VALUE, -1));
        }
        List<Vertex> result = new ArrayList<Vertex>();
        while(!queue.isEmpty()){
            Vertex v = queue.poll();
            result.add(v);
            for(Arc arc : graph[v.num]) relax(arc); // Невохможно здесь это реализовать со стандартной PriorityQueue
        }

        return result;
    }
}
