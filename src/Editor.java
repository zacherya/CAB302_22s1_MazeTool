import Modals.Maze;

/** The Editor Class*/
public class Editor {


    public int editingWindowHeight;
    public int editingWindowWidth;

    /** Constructor*/
    public void Editor(){

    }

    /** Overloaded Constructor
     * @param editingWindowHeight Integer for editing the cell height of a Maze
     * @param editingWindowWidth Integer for editing the cell width of a Maze*/
    public void Editor(int editingWindowHeight, int editingWindowWidth){
        this.editingWindowHeight = editingWindowHeight;
        this.editingWindowWidth = editingWindowWidth;
    }

    /** Method for drawing the lines on a Maze
     * @param x Integer for the x coordinate of a line being placed on the Maze
     * @param y Integer for the y coordinate of a line being placed on the Maze*/
    public void drawLine(int x, int y){

    }

    /** Method for placing an image onto the maze at a specific location
     * @param x Integer for the x coordinate of the image being placed on the Maze
     * @param y Integer for the y coordinate of the image being placed on the Maze*/
    public void placeItem(int x, int y){

    }

    /** Toggles view of the line solution for the Maze
     * @param maze A Maze object*/
    public void toggleSolution(Maze maze){

    }


}
