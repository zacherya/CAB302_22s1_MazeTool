package Helpers;

/**
 * Helper class to handle door functionality for the maze.
 */
public class Door{

    private boolean isOpen;

    /**
     * Constructor method.
     * Sets isOpen to false by default.
     */
    public Door(){
        this.isOpen = false;
    }

    /**
     * Getter method for the isOpen value.
     * @return true if the door is open, otherwise false
     */
    public boolean isOpen(){
        return this.isOpen;
    }

    /**
     * Sets the isOpen value for a door to true.
     */
    public void open(){
        this.isOpen = true;
    }

}