import java.util.*;

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

class Graph {

    private List<List<Integer>> graph;  // Список смежности
    private boolean[] flags;  // Отметки посещаемости в вершине
    private int dia;  // Значение диаметра
    private int eccentricity;  // Эксцентриситента
    private int[] distances;  // Кратчайшие расстояния до каждой вершины

    /**
     * Создание списка смежности через список ребер
     * @param n количество вершин
     * @param edges список ребер
     */
    Graph(int n, List<Edge> edges){
        graph = new ArrayList<>(n);
        for(int i = 0; i < n; ++i){
            graph.add(new ArrayList<>());
        }

        // Добавляем ребра к каждой вершине
        for(Edge edge : edges){
            graph.get(edge.from).add(edge.to);
            graph.get(edge.to).add(edge.from);
        }
    }

    /**
     * Нахождение экцентриситенты и диаметра графа по заданой вершине
     * @param index индекс вершины
     */
     void findDia(int index){
         // Находим эксцентриситету для каждой вершины и выбираем максимум для нее
        for(int i = 0; i < graph.size(); ++i) {
            findDistances(i);
            int currentEccent = findMaxDist();  // Эксцентриситента для текущей вершины
            if (i == index) eccentricity = currentEccent;  // Если это вершина, для которой нужно найти эксцентриситенту
            dia = Math.max(currentEccent, dia);
        }
    }

    int getDia(){
        return dia;
    }

    int getEccentricity(){
        return eccentricity;
    }

    /**
     * Нахождение индекса самой дальней вершины в массиве distances
     * @return индекс вершины
     */
    private int findMaxDist(){
        int max = 0;
        for(int i = 0; i < distances.length; i++){
            max = Math.max(distances[i], max);
        }
        return max;
    }


    /**
     * Нахождение кратчайших расстояний от заданой вершины до всех остальных
     * @param ind индекс вершины
     */
    private void findDistances(int ind){
        updatePath(); // Обнуляем все нужные данные
        bfs(ind);
    }

    /**
     * Алгоритм обхода в ширину для нахождения кратчайших путей
     * @param ind индекс вершины
     */
    private void bfs(int ind){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(ind);
        flags[ind] = true;
        distances[ind] = 0;
        while(!queue.isEmpty()){
            for(int v : graph.get(queue.peek())){
                if(!flags[v]) {
                    queue.add(v); // Добавляю очередную вершину
                    flags[v] = true; // Отмечаем, что посетили
                    distances[v] = distances[queue.peek()] + 1;
                }
            }
            queue.poll(); // Удаляем посещенную
        }
    }

    /**
     * Обновление всех параметров для нахождения кратчайших путей
     */
    private void updatePath(){
        flags = new boolean[graph.size()];
        distances = new int[graph.size()];
        for(int i = 0; i < graph.size(); i++){
            distances[i] = Integer.MAX_VALUE;
        }
    }
}
