/**
 * Chernyakov Nikita
 * A3201
 *
 * Algorithms and Data Structure
 * Extra Task 3
 *
 * Algorithm:
 * 1) Start dfs from all verteces
 * 2) If dfs found the cycle return the path
 *
 * Speed the same with speed of DFS: O(N+M)
 */

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> result = findCycle(test2()); // Upload test graph and find the answer
        // if result is null that graph don't have a cycle
        if(result == null){
            System.out.println("Graph don't have a cycle");
            return;
        }
        // Else print the path
        System.out.println("Graph cycle:");
        for(Integer it: result){
            System.out.println(it);
        }
    }

    /**
     * This function ask from the task
     * Function return path of cycle in graph(if it has)
     * @param graph source graph
     * @return The path of Cycle
     */
    private static LinkedList<Integer> findCycle(Graph graph){
        // For all verteces
        for(int i = 0; i < graph.size(); i++){
            if(graph.getColor(i) == -1) continue; // Skip if this vertex was already visited
            graph = dfs(graph, i); // Try to avoid the graph from this vertex and find the cycle
            // If path isn't empty it's mean that graph has a cycle
            if(!graph.cycle.isEmpty()){
                break;
            }
        }
        return graph.cycle.isEmpty() ? null : graph.cycle; // Return cycle if graph has it
    }

    /**
     * Avoid the graph
     * Use the DFS algorithm to this avoid. If we visit vertex with color 1,
     * it's mean that we already visited this vertex in that avoid => it's cycle
     * @param graph
     * @param ind start vertex
     * @return new Graph
     */
    private static Graph dfs(Graph graph, int ind){
        graph.setColor(ind, 1); // Set color 1, that we visit this vertex
        graph.cycle.add(ind); // Add vertex to path
        // For all adjacent verteces
        for(Integer it : graph.getVerteces(ind)){
            // If vertex has color 0 we never been there
            if (graph.getColor(it) == 0) {
                graph = dfs(graph, it); // Avoid from this vertex
                // If cycle has a new index in path => we found the cycle
                if(graph.cycle.getLast() != ind){
                    return graph;
                }
            }
            // if we came in vertex with color 1 => we found the cycle
            else if(graph.getColor(it) == 1){
                graph.cycle.add(it); // Add to path
                return graph;
            }
        }
        graph.setColor(ind, -1);
        graph.cycle.removeLast(); // Remove if we didn't found the cycle
        return graph;
    }

    // Answer: Yes
    private static Graph test1(){
        Graph graph = new Graph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        return graph;
    }

    // Answer: No
    private static Graph test2(){
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(2, 1);
        graph.addEdge(3, 2);
        graph.addEdge(3, 0);
        return graph;
    }
}
