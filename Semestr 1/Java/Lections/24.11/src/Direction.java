public enum  Direction {
    LEFT("left"), RIGTH("right");

    private final String name;

    Direction(String name){
        this.name = name;
    }
    @Override
    public String toString(){
        if(this == LEFT) return "left"; else return "right";
    }
}
