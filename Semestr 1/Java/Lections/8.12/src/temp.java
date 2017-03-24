import java.util.Arrays;

class Point{
    private double x,y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}

class Segment{
    private Point start,finish;


    public Segment(Point start, Point finish) {
        this.start = start;
        this.finish = finish;
    }

    private double getLength(){
        return Math.sqrt((start.getX() - start.getX())*(start.getX() - start.getX()) + (start.getY() - start.getY())*(start.getY() - start.getY()));
    }
}

class Triagle{
    private Segment a,b,c;
    public Triagle{Segment a, Segment b, Segment c){
        this.a;
    }

}
class temp {
    Arrays.sort(array, new Comp);
}
