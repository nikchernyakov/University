/**
 * Chernyakov Nikita
 * A3201
 *
 * Algorithms and Data Structure
 * Hometask 2_2
 *
 * This program find MST (minimal spanning tree) and print edges of this tree.
 * First, algo put each vertex on there tree and default edges list of graph sort.
 * Next, iterate all edges. If current vertexs in different tree,
 * then program union this trees and add in answer this edge.
 * At the end of program all vertexs will be in one tree.
 *
 * The speed of algorithm:
 * 1) Sort edges - 0(M log M) and O(M log N) in bad way
 * 2) Check that two vertexs belong one tree - O(1)
 * 3) Union two trees - 0(N)
 * 4) Third option will be run N-1 time - O(N ^ 2)
 *
 * The result speed is O(M log N + N ^ 2);
 */

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int n; // Count of vertex in graph
    ArrayList<Edge> edges; // Edge list
    int[] treeId; // Tree id that show in what tree vertex is

    // Constructor
    Graph(int n, ArrayList<Edge> edges){
        this.n = n;
        this.edges = edges;
        this.treeId = new int[n];
    }

    /**
     * Algorithm to find MST in graph
     * @return
     */
    List<Edge> findMst(){
        List<Edge> result = new ArrayList<>(); // Answer
        // Put all vertex in there trees
        for(int i = 0; i < n; ++i){
            treeId[i] = i;
        }

        edges.stream().sorted().forEach(edge -> {
            // If vertex's in different trees
            if(treeId[edge.from] != treeId[edge.to]){
                result.add(edge); // Add to answer
                int oldId = treeId[edge.from]; // Save index of old tree, that help to find other vertexs in old tree
                // Put vertex's from old tree to new
                for(int i = 0; i < n; ++i){
                    if(treeId[i] == oldId){
                        treeId[i] = treeId[edge.to];
                    }
                }
            }
            /* For debug
            System.out.println("Edge: " + edge.from + " - " + edge.to);
            for(int i = 0; i < n; ++i){
                System.out.print(treeId[i] + " ");
            }
            System.out.println(); */
        });

        return result;
    }
}
