import java.lang.reflect.Array;
import java.util.*;

/**
 * Chernyakov Nikita
 * A3201
 * Homework Algorithms 1_2
 */

class Dijkstra {

    /**
        Vertex in graph
     */
    class Vertex implements Comparable<Vertex>{
        int x,y;  // Coordinate in array
        int value; // Value in array
        int distance = Integer.MAX_VALUE; // Min distance value to this vertex
        boolean flag = false; // True, if already diverse this vertex
        Vertex prev = null; // Link to previous vertex in minimal Path to this Vertex

        Vertex(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }

        // Mark vertex's flag
        Vertex mark(){
            this.flag = true;
            return this;
        }

        @Override
        public int compareTo(Vertex vertex) {
            return this.distance - vertex.distance;
        }
    }

    private Vertex[][] graph;  // Graph
    private PriorityQueue<Vertex> queue = new PriorityQueue<>();  // Queue for current vertex, that dijkstra diverse

    public Deque<Vertex> dijkstra(int[][] array){
        createGraph(array);

        // Mark first vertex
        graph[0][0].distance = graph[0][0].value;
        queue.add(graph[0][0].mark());

        // While not diverse all vertex in array
        while (!queue.isEmpty()){
            Vertex currentVertex = queue.poll(); // Pick minimal vertex

            // Diverse neighbors of currentVertex
            for(Vertex neighbor : getNeighbors(currentVertex)){
                neighbor.distance = currentVertex.distance + neighbor.value;
                neighbor.prev = currentVertex;
                queue.add(neighbor.mark());
            }
        }

        return minPath(graph[graph.length - 1][graph[0].length - 1]); // Return minimal path
    }

    // Find not marked neighbours
    private List<Vertex> getNeighbors(Vertex v){
        List<Vertex> list = new ArrayList<>();
        if(v.x > 0 && !graph[v.x - 1][v.y].flag){
            list.add(graph[v.x - 1][v.y]);
        }
        if(v.y > 0 && !graph[v.x][v.y - 1].flag){
            list.add(graph[v.x][v.y - 1]);
        }
        if(v.x < graph.length - 1 && !graph[v.x + 1][v.y].flag){
            list.add(graph[v.x + 1][v.y]);
        }
        if(v.y < graph[0].length - 1 && !graph[v.x][v.y + 1].flag){
            list.add(graph[v.x][v.y + 1]);
        }
        return list;
    }

    // Create graph for this array
    private void createGraph(int[][] array){
        graph = new Vertex[array.length][array[0].length];
        for(int i = 0; i < graph.length; i++){
            for(int j = 0; j < graph[0].length; j++){
                graph[i][j] = new Vertex(i, j, array[i][j]);
            }
        }
    }

    // Build minimal path
    private Deque<Vertex> minPath(Vertex vertex){
        ArrayDeque<Vertex> path = new ArrayDeque<>();
        while (vertex != null){
            path.addFirst(vertex);
            vertex = vertex.prev;
        }
        return path;
    }

}
