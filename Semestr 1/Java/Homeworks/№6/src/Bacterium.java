/**
 * Одна бактерия, у которой задаем координаты в таблице
 */
public class Bacterium {
    int x, y; // координаты бактерии

    //конструктор бактерии
    public Bacterium(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bacterium)) return false;

        Bacterium bacterium = (Bacterium) o;

        if (x != bacterium.x) return false;
        return y == bacterium.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
