import Helpers.Difficulty;
import Modals.Image;

/** The Random Maze Class*/
public class RandomMaze {


    public Difficulty difficulty;

    /** Constructor
     * @authors Alex Hannah */
    public void RandomMaze(){

    }

    /** Overloaded Constructor
     * @param difficulty Enum Helpers.Difficulty setting for the Maze
     * @param startPoint Integer cell location of the starting point for the maze
     * @param endPoint Integer cell location of the ending point of the maze
     * @param logo An image being used for the included logo
     * @authors Alex Hannah */
    public void RandomMaze(Difficulty difficulty, int startPoint, int endPoint, Image logo){
        this.difficulty = difficulty;
    }

    /** Method to generate the Random Maze
     * @authors Alex Hannah */
    public void generateMaze(){

    }
}
