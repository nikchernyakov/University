import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {
    class Vertex{
        int color = 0;
        ArrayList<Integer> verteces = new ArrayList<>();
    }

    List<Vertex> graph;
    LinkedList<Integer> cycle;

    Graph(int n){
        graph = new ArrayList<>();
        for(int i = 0; i < n; i++){
            graph.add(new Vertex());
        }
        cycle = new LinkedList<>();
    }

    // Add edge to graph
    void addEdge(int from, int to){
        graph.get(from).verteces.add(to);
    }

    // Return color of vertex with index ind
    int getColor(int ind){
        return graph.get(ind).color;
    }

    // Set new color to vertex
    void setColor(int ind, int color){
        graph.get(ind).color = color;
    }

    // Get adjacent verteces
    List<Integer> getVerteces(int ind){
        return graph.get(ind).verteces;
    }

    // Count of vertex
    int size(){
        return graph.size();
    }


}
