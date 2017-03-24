import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Graph graph = test1();
        // Answer
        List<Edge> result = graph.findMst();
        for(Edge edge : result){
            System.out.println(edge.from + " " + edge.to);
        }
    }

    private static Graph test1(){
        int n = 6;
        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 5));
        edges.add(new Edge(0, 4, 6));
        edges.add(new Edge(0, 5, 1));
        edges.add(new Edge(1, 2, 2));
        edges.add(new Edge(1, 5, 5));
        edges.add(new Edge(2, 3, 6));
        edges.add(new Edge(2, 5, 4));
        edges.add(new Edge(3, 4, 3));
        edges.add(new Edge(3, 5, 6));
        edges.add(new Edge(4, 5, 5));

        return new Graph(n, edges);
    }

    private static Graph test2(){
        int n = 10;
        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 6));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(0, 3, 9));
        edges.add(new Edge(1, 2, 4));
        edges.add(new Edge(1, 4, 2));
        edges.add(new Edge(1, 6, 9));
        edges.add(new Edge(2, 4, 2));
        edges.add(new Edge(2, 3, 9));
        edges.add(new Edge(3, 5, 8));
        edges.add(new Edge(3, 9, 18));
        edges.add(new Edge(4, 5, 8));
        edges.add(new Edge(4, 6, 9));
        edges.add(new Edge(5, 6, 7));
        edges.add(new Edge(5, 8, 9));
        edges.add(new Edge(5, 9, 10));
        edges.add(new Edge(6, 7, 4));
        edges.add(new Edge(6, 8, 5));
        edges.add(new Edge(7, 8, 1));
        edges.add(new Edge(7, 9, 4));
        edges.add(new Edge(8, 9, 3));

        return new Graph(n, edges);
    }

    private static Graph test3(){
        int n = 5;
        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 1));
        edges.add(new Edge(0, 2, 3));
        edges.add(new Edge(1, 2, 4));
        edges.add(new Edge(1, 3, 6));
        edges.add(new Edge(1, 4, 7));
        edges.add(new Edge(2, 3, 5));
        edges.add(new Edge(3, 4, 2));

        return new Graph(n, edges);
    }
}
