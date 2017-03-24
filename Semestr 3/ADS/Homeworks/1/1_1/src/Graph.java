/**
    Chernyakov Nikita
    A3201
    Task № 1_1
 */

import java.util.*;


public class Graph {
    /**
     * Vertex in graph
     */
    private class Train{
        public int count = 0; // Count trains that we already used in our Line
        public List<Integer> nexts; // Next trains that this train can connect

        Train(List<Integer> nexts){
            this.nexts = nexts;
        }

        // Check count of trains
        public boolean canUse(){
            return count < 2;
        }

    }

    private int n; // Count of trains
    private Train[] trains; // Array of trains

    /*Graph(List<Integer>[] array){
        n = array.length;
        trains = new Train[n];
        for(int i = 0; i < n; i++){
            trains[i] = new Train(array[i]);
        }
    }*/


    /**
        DFS
     */
    public Deque<Integer> traverse(List<Integer>[] array){
        n = array.length;
        trains = new Train[n];
        for(int i = 0; i < n; i++){
            trains[i] = new Train(array[i]);
        }

        Deque<Integer> result;
        for(int ind = 0 ; ind < n; ind++ ){
            // For Debug
            //System.out.println("Start " + ind);
            result = traverse(new ArrayDeque<>(), ind);
            if(result != null) {
                return result;
            }
        }
        return null;
    }

    private Deque<Integer> traverse(Deque<Integer> deq, int ind){
        // For Debug
        // System.out.println(deq + " " + ind);

        Deque<Integer> result;
        trains[ind].count++;
        deq.addFirst(ind);
        if(deq.size() == 2 * n){
            return deq;
        }

        for(Integer nextInd : trains[ind].nexts){
            if(trains[nextInd].canUse()) {
                result = traverse(deq, nextInd);
                if(result != null) {
                    return result;
                }
            }
        }
        deq.removeFirst();
        trains[ind].count--;
        return null;
    }

    /*public static void main(String[] args) {
        ArrayList<Integer>[] array = new ArrayList[3];
        array[0] = new ArrayList<>();
        array[1] = new ArrayList<>();
        array[2] = new ArrayList<>();

        array[0].add(1);
        array[1].add(1); array[1].add(2);
        array[2].add(0);

        Graph graph = new Graph();
        //System.out.println(array.length);
        System.out.println(graph.traverse(array));
    }*/
}
