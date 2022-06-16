package Helpers;

//Room = edges in graph. This is inspired by an adaptation for Prim's algorithm:
//https://github.com/eugenp/tutorials/blob/master/algorithms-modules/algorithms-miscellaneous-5/src/main/java/com/baeldung/algorithms/prim/Edge.java
public class Room {

    private int distanceFromStart;
    private boolean generated;
    private boolean visited;

    public Room(){
        this.distanceFromStart = -1;
        this.generated = false;
        this.visited = false;
    }

    public int getDistance(){
        return this.distanceFromStart;
    }

    public boolean generated(){
        return this.generated;
    }

    public void gen(){
        this.generated = true;
    }

    public void setDistance(int d){
        this.distanceFromStart = d;
    }

    public void visit(){
        this.visited = true;
    }

    public boolean visited(){
        return this.visited;
    }
}
