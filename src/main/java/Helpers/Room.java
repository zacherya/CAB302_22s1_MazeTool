package Helpers;

// Room = edges in graph. This is inspired by an adaptation for Prim's algorithm:
// Source: https://github.com/eugenp/tutorials/blob/master/algorithms-modules/algorithms-miscellaneous-5/src/main/java/com/baeldung/algorithms/prim/Edge.java

/**
 * Class for managing each room in a maze, otherwise referred to as the edges in a graph.
 */
public class Room {

    private int distanceFromStart;
    private boolean generated;
    private boolean visited;

    /**
     * Constructor for the room.
     */
    public Room(){
        this.distanceFromStart = -1;
        this.generated = false;
        this.visited = false;
    }

    /**
     * Getter method for returning the distance the room is from the beginning of the maze.
     * @return the distance
     */
    public int getDistance(){
        return this.distanceFromStart;
    }

    /**
     * Getter method for returning whether a room has been generated.
     * @return true if generated, false if not
     */
    public boolean generated(){
        return this.generated;
    }

    /**
     * Sets the generated field to true.
     */
    public void gen(){
        this.generated = true;
    }

    /**
     * Sets the distance to the value provided.
     * @param d distance
     */
    public void setDistance(int d){
        this.distanceFromStart = d;
    }

    /**
     * Sets the visited value to true.
     */
    public void visit(){
        this.visited = true;
    }

    /**
     * Getter method for the visited value.
     * @return true if visited, false if not
     */
    public boolean visited(){
        return this.visited;
    }
}
