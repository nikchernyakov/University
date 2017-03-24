import javafx.geometry.Pos;

import java.util.Arrays;

public class Position {
    public final static int QUEENS = 8;

    private int queens = 0;
    private int[] position = new int[QUEENS];

    @Override
    public String toString(){
        return Arrays.toString(position);
    }

    public boolean set(int h){
        position[queens++] = h;
        for(int i = 0; i < queens - 1; i++){
            if(position[i] == h) return false;
            if(Math.abs(h - position[i]) == queens - i - 1) return false;
        }
        return true;
    }

    public Position(){}

    public Position(Position p){
        this.queens = p.queens;
        this.position = Arrays.copyOf(p.position, QUEENS);

    }

    public static Position findPosition(Position p){
        if(p.queens == QUEENS) return p;
        for(int h = 0; h < QUEENS; h++){
            Position newPos = new Position(p);
            if(newPos.set(h)){
                Position finalPos = findPosition(newPos);
                if(finalPos != null) return finalPos;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(findPosition(new Position()));
    }
}
