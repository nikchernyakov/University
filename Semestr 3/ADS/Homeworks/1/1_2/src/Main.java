import java.util.List;
import java.util.Random;

/**
 * Chernyakov Nikita
 * A3201
 * Homework Algorithms 1_2
 */
public class Main {
    public static void main(String[] args) {

        // For testing
        int n = 5, m = 5;
        Random random = new Random();
        int[][] array = new int[n][m];

        for(int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = random.nextInt(10);
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        Dijkstra algo = new Dijkstra();
        for(Dijkstra.Vertex v : algo.dijkstra(array)){
            System.out.println("Vertex (" + v.x + ", " + v.y + "): distance = " + v.distance);
        }
    }
}
