/**
 * Created by Никита on 02.05.2016.
 */
public class Kva {
    private int ind;
    private Kva nextKva = null;
    public Kva(int ind){
        this.ind = ind;
    }

    public int getInd() {
        return ind;
    }

    public void setInd(int ind) {
        this.ind = ind;
    }

    public Kva getNextKva() {
        return nextKva;
    }

    public void setNextKva(Kva nextKva) {
        this.nextKva = nextKva;
    }
}
