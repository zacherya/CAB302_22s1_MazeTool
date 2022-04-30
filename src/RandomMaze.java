import Modals.Image;

/** The Random Maze Class*/
public class RandomMaze {


    public Difficulty difficulty;

    /** Constructor*/
    public void RandomMaze(){

    }

    /** Overloaded Constructor
     * @param difficulty Enum Difficulty setting for the Maze
     * @param startPoint Integer cell location of the starting point for the maze
     * @param endPoint Integer cell location of the ending point of the maze
     * @param logo An image being used for the included logo*/
    public void RandomMaze(Difficulty difficulty, int startPoint, int endPoint, Image logo){
        this.difficulty = difficulty;
    }

    /** Method to generate the Random Maze*/
    public void generateMaze(){

    }
}
