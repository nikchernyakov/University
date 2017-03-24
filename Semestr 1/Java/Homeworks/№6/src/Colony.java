import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Colony {
    public ArrayList<Bacterium> bacts = new ArrayList<>(); //массив всех бактерий, которые в данный момент у нас есть
    public MCor minX, minY; //граница левого верхнего угла матрицы
    public MCor maxX, maxY; //граница правого нижнего угла матрицы

    //конструктор для начального состояния
    public Colony(boolean[][] mas) {
        minX = new MCor();
        maxX = new MCor();
        minY = new MCor();
        maxY = new MCor();

        //переводим матрицу в массив, т.к. нам не нужны пустые клетки
        for(int i = 0; i < mas.length; i++)
            for(int j = 0; j < mas[i].length; j++)
                if(mas[i][j]){  //если бактерия есть в этой клетке
                    this.bacts.add(new Bacterium(i,j));
                }

        updateBorders(); //обновляем границы
    }

    /**
     * проверяет не является ли координата минимальной или максимальной
     */
    public void updateBorder(int i, int j){
        if(!minX.flag || i < minX.n ){
            minX.n = i;
            minX.flag = true;
        }

        if(!maxX.flag || i > maxX.n ){
            maxX.n = i;
            maxX.flag = true;
        }

        if(!minY.flag || j < minY.n ){
            minY.n = j;
            minY.flag = true;
        }

        if(!maxY.flag || j > maxY.n ){
            maxY.n = j;
            maxY.flag = true;
        }
    }

    /**
     * проверяет границы для всех живых бактерий
     */
    public void updateBorders(){
        for(Bacterium b : bacts){
            updateBorder(b.x, b.y);
        }
    }

    /**
     * осуществляет одношаговое преобразование
     */
    public void step(){
        HashMap<Bacterium, Integer> newBact = new HashMap<>(); //новые потанцеальные бактерии
        ArrayList<Integer> mustDel = new ArrayList<>(); //те бактерии, которые умрут на этом шагу

        for(int id = 0; id < bacts.size(); id++){
            Bacterium b = bacts.get(id);
            int t = 0;  //счетчик соседей текущей бактерии

            //элегантный, как мне кажется, просмотр всех соседей бактерии
            for(int i = -1; i <= 1; i++)
                for(int j = -1; j <= 1; j++) {
                    if (i == 0 && j == 0) { continue; } //сама бактерия

                    Bacterium newB = new Bacterium(b.x + i, b.y + j); //бактерия сосед в будущем

                    if (bacts.contains(newB)) {  //если тут уже есть бактерия
                        t++;
                        continue;
                    }

                    if (newBact.get(newB) == null) {  //если бактерии в этой клетке нет
                        newBact.put(newB, 0);   //добавляем ее в мапу потенциальных бактерий
                    }
                    newBact.put(newB, newBact.get(newB) + 1); //теперь говорим, что у этой клетке добавился сосед

                }

            if(t != 2 && t != 3){  //условие вымирания бактерии
                //System.out.println("LOL " + b.x + " " + b.y);
                mustDel.add(id);  //говорим, что эту бактерию нужно удалить
            }
        }

        //удаляем всех бактерий, которые должны умереть
        for(int i = 0; i < mustDel.size(); i++){
            if(bacts.get(mustDel.get(i) - i).x == minX.n){
                minX.flag = false;
            }
            if(bacts.get(mustDel.get(i) - i).x == maxX.n){
                maxX.flag = false;
            }
            if(bacts.get(mustDel.get(i) - i).y == minY.n){
                minY.flag = false;
            }
            if(bacts.get(mustDel.get(i) - i).y == maxY.n){
                maxY.flag = false;
            }

            bacts.remove(mustDel.get(i) - i);
            //System.out.println("LOL " + bacts.get(id).x + " " + bacts.get(id).y);
        }

        //из потенциальных бактерий выбираем тех, кто действительно появятся
        for(Map.Entry<Bacterium,Integer> entry : newBact.entrySet()) {
            if(entry.getValue() == 3){
                bacts.add(entry.getKey());
                //updateBorder(entry.getKey().x, entry.getKey().y);
            }
            //System.out.println(entry.getKey().x + " " + entry.getKey().y + " " + entry.getValue());
        }

        updateBorders();  //обновляем границы
    }

    /**
     * многошаговое преобразование
     */
    public void step(int n){
        for(int i = 0; i < n; i++)
            step();
    }

    /**
     * проверяем, есть ли еще бактерии
     */
    public boolean isEmpty(){
        return bacts.isEmpty();
    }

    /**
     * проверяем, будут ли бактерии через n шагов
     */
    public boolean isEmpty(int n){
        ArrayList<Bacterium> copyOfBacts = new ArrayList<>();
        copyOfBacts.addAll(bacts);
        step(n);  //моделируем, что произойдет через n шагов
        boolean tr = isEmpty();
        bacts = new ArrayList<>();
        bacts.addAll(copyOfBacts);
        return tr;
    }

    /**
     * выводит матрицу бактерий
     * @param out куда нужно выводить матрицу
     */
    public void print(PrintStream out){
        //генерируем матрицу имя массив бактерий
        boolean[][] table = new boolean[maxX.n - minX.n + 1][maxY.n - minY.n + 1]; //зная, координаты крайних бактерий, делаем таблицу
        //System.out.println(table.length +" "+ table[0].length);

        //сортировка массива бактерий, чтобы удобно было считывать
        //но я поздно понял, что она оказывается не нужна мне вовсе
        //пускай будет здесь пока
        /*bacts.sort((p1, p2) ->
                p1.x < p2.x ? -1 :
                        p1.x > p2.x ? 1 :
                                p1.y < p2.y ? -1 :
                                        p1.y > p2.y ? 1 : 0);
        */

        //расстановка бактерий
        for(Bacterium b : bacts){
            //out.println(b.x + " " + b.y);
            table[b.x - minX.n][b.y - minY.n] = true;
        }

        //вывод матрицы в out
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                out.print(table[i][j] ? "*" : ".");
            }
            out.println();
        }
    }
}