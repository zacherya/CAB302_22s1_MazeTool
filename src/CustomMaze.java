import Modals.Image;

/** The Custom Maze Class*/
public class CustomMaze {


    int mazeWidth;
    int mazeHeight;
    boolean incLogo;
    Image image;

    /** Constructor
     * @authors Alex Hannah */
    public void CustomMaze(){

    }

    /** Overloaded Constructor
     * @param mazeWidth Integer with the dimensions for the width of the Maze
     * @param mazeHeight Integer with the dimensions for the height of the Maze
     * @param incLogo A boolean that tells if the maze has an included logo or not
     * @param image The image used when a logo is included
     * @authors Alex Hannah */

    public CustomMaze(int mazeWidth, int mazeHeight, boolean incLogo, Image image){
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;
        this.incLogo = incLogo;
        this.image = image;
    }

    /** Generates A maze with the custom options
     * @authors Alex Hannah */
    public void generateMaze(){

    }


}
