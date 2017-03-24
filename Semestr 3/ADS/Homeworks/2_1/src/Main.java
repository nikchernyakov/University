import java.util.ArrayList;
import java.util.List;

/**
 * Алгоритмы и структуры данных
 * 3 семестр
 * Домашняя работа №2_1
 * Черняков Никита
 * А3201
 *
 * Алгоритм находит в заданом графе для вершины Эксцентриситенту и Диаметр графа
 * Алгоритм:
 * 1) Находим Эксцентриситенту для каждой веришины (Наибольший кратчайший путь до вершины)
 * 2) Находим среди Эксцентриситент Диаметр
 */
public class Main {
    public static void main(String[] args) {
        int n = 10;
        int vertex = 7;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(2, 5));
        edges.add(new Edge(5, 0));
        edges.add(new Edge(5, 8));
        edges.add(new Edge(0, 1));
        edges.add(new Edge(1, 8));
        edges.add(new Edge(3, 8));
        edges.add(new Edge(1, 7));
        edges.add(new Edge(0, 4));
        edges.add(new Edge(3, 7));
        edges.add(new Edge(6, 7));
        edges.add(new Edge(4, 6));
        edges.add(new Edge(4, 9));
        edges.add(new Edge(3, 9));
        Graph graph = new Graph(n, edges);

        graph.findDia(vertex);
        System.out.println("Eccentricity of the Vertex " + vertex + " : " + graph.getEccentricity());
        System.out.println("Graph Diameter: " + graph.getDia());
    }
}

/*  Example 1
        int vertex = 1;
        int n = 6;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 5));
        edges.add(new Edge(0, 2));
        edges.add(new Edge(0, 3));
        edges.add(new Edge(1, 4));
        edges.add(new Edge(1, 2));
        edges.add(new Edge(4, 5));
        edges.add(new Edge(3, 5));
*/

/*  Example 2
        int vertex = 7;
        int n = 9;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1));
        edges.add(new Edge(0, 8));
        edges.add(new Edge(2, 5));
        edges.add(new Edge(3, 8));
        edges.add(new Edge(4, 6));
        edges.add(new Edge(4, 7));
        edges.add(new Edge(5, 7));
        edges.add(new Edge(5, 8));
        edges.add(new Edge(7, 0));

 */

/* Example 3
        int n = 3;
        int vertex = 1;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1));
        edges.add(new Edge(1, 2));
*/
/*  Example 4
        int vertex = 2;
        int n = 5;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1));
        edges.add(new Edge(0, 2));
        edges.add(new Edge(1, 3));
        edges.add(new Edge(2, 3));
        edges.add(new Edge(3, 4));
*/

/* Example 5
int vertex = 0;
        int n = 10;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(2, 5));
        edges.add(new Edge(5, 0));
        edges.add(new Edge(5, 8));
        edges.add(new Edge(0, 1));
        edges.add(new Edge(1, 8));
        edges.add(new Edge(3, 8));
        edges.add(new Edge(1, 7));
        edges.add(new Edge(0, 4));
        edges.add(new Edge(3, 7));
        edges.add(new Edge(6, 7));
        edges.add(new Edge(4, 6));
        edges.add(new Edge(4, 9));
        edges.add(new Edge(3, 9));
 */