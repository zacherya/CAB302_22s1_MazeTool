package Modals;
import DataAccess.DtoModels.*;

/** The Maze class*/
public class Maze extends Image {
    public String name;
    public String author;
    public java.time.LocalDate created;
    public java.time.LocalDate lastEdited;
    public int startPoint;
    public int endPoint;
    public Image logo;


    /** Constructor
     * @authors Alex Hannah */
    public void Maze(){

    }

    /** Overloaded Constructor
     * @authors Zac Adams */
    public void Maze(MazeDto dto) {

    }

    /** Overloaded Constructor
     * @param name String with the name of the maze
     * @param author String with the name of the author of the maze
     * @param created The time and date when the maze was created in hh:mm:ss, dd:mm:yyyy format
     * @param lastEdited The time and date when the maze was last edited in hh:mm:ss, dd:mm:yyyy format
     * @param startPoint The cell location of the starting point for the maze
     * @param endPoint The cell location of the ending point of the maze
     * @param logo The logo image being used in the maze
     * @authors Alex Hannah
     * */
    public void Maze(String name, String author, java.time.LocalDate created, java.time.LocalDate lastEdited, int startPoint, int endPoint, Image logo){
        this.name = name;
        this.author = author;
        this.created = created;
        this.lastEdited = lastEdited;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.logo = logo;
    }

    /** Checks if the maze contains a name, author, time created, time last edited, a start point, an end point and a logo
     * @param maze A maze object
     * @return true if maze contains relevant information, otherwise false
     * @authors Alex Hannah */
    public boolean isValid(Maze maze){
        return true;
    }

    /** Checks if the maze is solvable
     * @param maze A maze object
     * @return true if maze is solvable, otherwise false
     * @authors Alex Hannah */
    public boolean isSolvable(Maze maze){
        return true;
    }

    /** Calculates the most efficient route through the maze from beginning to end
     * @param maze A maze object
     * @authors Alex Hannah */
    public void calculateSolution(Maze maze){

    }


}
