package Helpers;

import Helpers.Difficulty;
import Modals.Image;
import Views.StdDraw;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Stack;
import java.util.ArrayList;

/** The Random Maze Class*/
public class RandomMaze {

    private int height, length;
    private Room[][] rooms;
    private Door[][] vDoors, hDoors;

//    public Difficulty difficulty;
//
//    /** Constructor
//     * @authors Alex Hannah */
//    public void RandomMaze(){
//
//    }
//
//    /** Overloaded Constructor
//     * @param difficulty Enum Helpers.Difficulty setting for the Maze
//     * @param startPoint Integer cell location of the starting point for the maze
//     * @param endPoint Integer cell location of the ending point of the maze
//     * @param logo An image being used for the included logo
//     * @authors Alex Hannah */
//    public void RandomMaze(Difficulty difficulty, int startPoint, int endPoint, Image logo){
//        this.difficulty = difficulty;
//    }
//
//    /** Method to generate the Random Maze
//     * @authors Alex Hannah */
//    public void generateMaze(){
//
//    }
public RandomMaze(int h, int l, double openDoorChance){

        this.height = h;
        this.length = l;

        this.rooms = new Room[h][l];
        this.vDoors = new Door[h][l-1];
        this.hDoors = new Door[h-1][l];

        for(int i = 0; i < h; i++){
            for(int j = 0; j < l; j++){
                this.rooms[i][j] = new Room();
            }
        }

        for(int i = 0; i < h; i++){
            for(int j = 0; j < l-1; j++){
                this.vDoors[i][j] = new Door();
            }
        }
        for(int i = 0; i < h-1; i++){
            for(int j = 0; j < l; j++){
                this.hDoors[i][j] = new Door();
            }
        }

        this.generate();

        for(int i = 0; i < h; i++){
            for(int j = 0; j < l-1; j++){
                if (Math.random() < openDoorChance) this.vDoors[i][j].open();
            }
        }
        for(int i = 0; i < h-1; i++){
            for(int j = 0; j < l; j++){
                if (Math.random() < openDoorChance) this.hDoors[i][j].open();
            }
        }

    }

    public void generate(){
        Stack<int[]> chain = new Stack<int[]>();
        int[] start = {0,0};

        chain.push(start);
        this.rooms[0][0].gen();

        while (!chain.empty()){
            int[] currentRoom = chain.peek();
            int[][] neighbours = ungeneratedNeighbours(currentRoom[0], currentRoom[1]);
            if (neighbours.length == 0){
                chain.pop();
                //System.out.println("back");
                continue;
            }

            int[] nextRoom = neighbours[(int) (Math.random() * neighbours.length)];
            if (nextRoom[0] != currentRoom[0]){
                if (nextRoom[0] < currentRoom[0]){
                    this.hDoors[nextRoom[0]][nextRoom[1]].open(); //advancing up
                    //System.out.println("up");
                } else{
                    this.hDoors[currentRoom[0]][currentRoom[1]].open(); //advancing down
                    //System.out.println("down");
                }
            } else{
                if (nextRoom[1] < currentRoom[1]){
                    this.vDoors[nextRoom[0]][nextRoom[1]].open(); //advancing left
                    //System.out.println("left");
                } else{
                    this.vDoors[currentRoom[0]][currentRoom[1]].open(); //advancing right
                    //System.out.println("right");
                }
            }

            this.rooms[nextRoom[0]][nextRoom[1]].gen();
            int[] newCopy = {nextRoom[0],nextRoom[1]};
            chain.push(newCopy);

        }

    }

    public int[][] ungeneratedNeighbours(int y, int x){
        int[][] neighbours = new int[4][2];
        int count = 0;
        if (y > 0 && !this.rooms[y-1][x].generated()){
            neighbours[count][0] = y-1;
            neighbours[count][1] = x;
            count++;
        }
        if (x > 0 && !this.rooms[y][x-1].generated()){
            neighbours[count][0] = y;
            neighbours[count][1] = x-1;
            count++;
        }
        if (y < this.height - 1 && !this.rooms[y+1][x].generated()){
            neighbours[count][0] = y+1;
            neighbours[count][1] = x;
            count++;
        }
        if (x < this.length - 1 && !this.rooms[y][x+1].generated()){
            neighbours[count][0] = y;
            neighbours[count][1] = x+1;
            count++;
        }

        int[][] cutNeighbours = new int[count][2]; //remove unused spots
        for(int i = 0; i < count; i++){
            cutNeighbours[i] = neighbours[i];
        }
        return cutNeighbours;
    }

    @Override
    public String toString(){
        String mazeString = "";

        //top outer wall
        for(int i = 0; i < 2 * this.length + 1; i++){
            mazeString += "X";
        }
        mazeString += "\n";


        for(int i = 0; i < 2 * this.height - 1; i++){
            String lineString = "X"; //left outer wall

            if (i % 2 == 0){
                for(int j = 0; j < this.length - 1; j++){
                    lineString += (this.vDoors[i/2][j].isOpen())? "  " : " X";
                }
                lineString += " X";
            } else{
                for(int j = 0; j < this.length; j++){
                    lineString += (this.hDoors[i/2][j].isOpen())? " X" : "XX";
                }
            }
            mazeString += lineString + "\n";
        }

        //bottom outer wall
        for(int i = 0; i < 2 * this.length + 1; i++){
            mazeString += "X";
        }
        mazeString += "\n";

        return mazeString;
    }

    public BufferedImage draw(){
        double scaler = 900.0 / Math.max(this.length * 2 + 1, this.height * 2 + 1); //Change the double value to your preferred maximum dimension in pixels
//        StdDraw.setCanvasSize((int) (scaler *(this.length * 2 + 1)), (int) (scaler *(this.height * 2 + 1)));
        StdDraw.setCanvasSize(100,100);

        StdDraw.setYscale(- (this.height * 2 - 1) - 0.5, 1.5);
        StdDraw.setXscale(-1.5, (this.length * 2 - 1) + 0.5);
        StdDraw.enableDoubleBuffering();

        //top/bottom wall
        for(int j = -1; j < this.length * 2; j++){
            StdDraw.filledSquare(j, 1, 0.5);
            StdDraw.filledSquare(j, - this.height * 2 + 1, 0.5);
        }

        for(int i = 0; i < 2 * this.height - 1; i++){
            StdDraw.filledSquare(-1, -i, 0.5);

            if (i % 2 == 0){
                for(int j = 0; j < this.length - 1; j++){
                    if (this.vDoors[i/2][j].isOpen()){
                        //StdDraw.filledSquare(j * 2 + 1, -i, 0.1);
                    } else{
                        StdDraw.filledSquare(j * 2 + 1, -i, 0.5);
                    }
                }

            } else{
                for(int j = 0; j < this.length; j++){
                    if (this.hDoors[i/2][j].isOpen()){
                        //StdDraw.filledSquare(j * 2, -i, 0.1);
                        StdDraw.filledSquare(j * 2 + 1, -i, 0.5);
                    }
                    else {
                        StdDraw.filledSquare(j * 2, -i, 0.5);
                        StdDraw.filledSquare(j * 2 + 1, -i, 0.5);
                    }
                }

            }

            StdDraw.filledSquare(this.length * 2 - 1, -i, 0.5);
        }


        int[] currentRoom = {this.height - 1, this.length - 1};
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.006);

        //Draw start triangle
        StdDraw.line(-.3,.3,.3,.3);
        StdDraw.line(.3,.3,0,-.3);
        StdDraw.line(0,-.3,-.3,.3);
        //Draw end triangle
        StdDraw.line(currentRoom[1] * 2 - 0.3, - (currentRoom[0] * 2 - 0.3), currentRoom[1] * 2 + 0.3,  - (currentRoom[0] * 2 - 0.3));
        StdDraw.line(currentRoom[1] * 2 - 0.3, - (currentRoom[0] * 2 - 0.3), currentRoom[1] * 2,  - (currentRoom[0] * 2 + 0.3));
        StdDraw.line(currentRoom[1] * 2 + 0.3, - (currentRoom[0] * 2 - 0.3), currentRoom[1] * 2,  - (currentRoom[0] * 2 + 0.3));
        //StdDraw.show();
        return StdDraw.extractGraphic();
    }

    public void drawSolution(){ //Dijkstra
        this.rooms[0][0].setDistance(0);//distance from source to source is always 0
        this.rooms[0][0].visit();

        ArrayList<int[]> loRooms = new ArrayList<int[]>();
        int hiDistance = 1;
        ArrayList<int[]> hiRooms = new ArrayList<int[]>();

        int[] zz = {0,0};
        loRooms.add(zz);

        while (loRooms.size() > 0 && this.rooms[this.height-1][this.length-1].getDistance() == -1){
            for(int[] coords : loRooms){
                int[][] adjacents = unvisitedAdjacents(coords[0], coords[1]);
                for(int[] newCoords : adjacents){
                    this.rooms[newCoords[0]][newCoords[1]].visit();
                    this.rooms[newCoords[0]][newCoords[1]].setDistance(hiDistance);
                    hiRooms.add(newCoords);
                }
            }
            hiDistance++;

            loRooms = hiRooms;
            hiRooms = new ArrayList<int[]>();
        }

        //Only colours the path to the end
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.005);

        int[] currentRoom = {this.height - 1, this.length - 1};
        for(int d = this.rooms[this.height - 1][this.length - 1].getDistance() - 1; d > 0; d--){

            if (currentRoom[0] > 0 && this.rooms[currentRoom[0] - 1][currentRoom[1]].getDistance() == d && this.hDoors[currentRoom[0]-1][currentRoom[1]].isOpen()){
                currentRoom[0]--;
                //StdDraw.filledSquare (currentRoom[1] * 2, - (currentRoom[0] * 2), 0.1);
                StdDraw.line(currentRoom[1] * 2 - 0.3, - (currentRoom[0] * 2 - 0.3), currentRoom[1] * 2 + 0.3,  - (currentRoom[0] * 2 - 0.3));
                StdDraw.line(currentRoom[1] * 2 - 0.3, - (currentRoom[0] * 2 - 0.3), currentRoom[1] * 2,  - (currentRoom[0] * 2 + 0.3));
                StdDraw.line(currentRoom[1] * 2 + 0.3, - (currentRoom[0] * 2 - 0.3), currentRoom[1] * 2,  - (currentRoom[0] * 2 + 0.3));
                continue;
            }
            if (currentRoom[1] > 0 && this.rooms[currentRoom[0]][currentRoom[1] - 1].getDistance() == d && this.vDoors[currentRoom[0]][currentRoom[1]-1].isOpen()){
                currentRoom[1]--;
                //StdDraw.filledSquare (currentRoom[1] * 2, - (currentRoom[0] * 2), 0.2);
                StdDraw.line(currentRoom[1] * 2 - 0.3, - (currentRoom[0] * 2 - 0.3), currentRoom[1] * 2 - 0.3,  - (currentRoom[0] * 2 + 0.3));
                StdDraw.line(currentRoom[1] * 2 - 0.3, - (currentRoom[0] * 2 - 0.3), currentRoom[1] * 2 + 0.3,  - (currentRoom[0] * 2));
                StdDraw.line(currentRoom[1] * 2 - 0.3, - (currentRoom[0] * 2 + 0.3), currentRoom[1] * 2 + 0.3,  - (currentRoom[0] * 2));
                continue;
            }
            if (currentRoom[0] < this.height - 1 && this.rooms[currentRoom[0] + 1][currentRoom[1]].getDistance() == d && this.hDoors[currentRoom[0]][currentRoom[1]].isOpen()){
                currentRoom[0]++;
                //StdDraw.filledSquare (currentRoom[1] * 2, - (currentRoom[0] * 2), 0.3);
                StdDraw.line(currentRoom[1] * 2 - 0.3, - (currentRoom[0] * 2 + 0.3), currentRoom[1] * 2 + 0.3,  - (currentRoom[0] * 2 + 0.3));
                StdDraw.line(currentRoom[1] * 2 - 0.3, - (currentRoom[0] * 2 + 0.3), currentRoom[1] * 2,  - (currentRoom[0] * 2 - 0.3));
                StdDraw.line(currentRoom[1] * 2 + 0.3, - (currentRoom[0] * 2 + 0.3), currentRoom[1] * 2,  - (currentRoom[0] * 2 - 0.3));
                continue;
            }
            if (currentRoom[1] < this.length - 1 && this.rooms[currentRoom[0]][currentRoom[1] + 1].getDistance() == d && this.vDoors[currentRoom[0]][currentRoom[1]].isOpen()){
                currentRoom[1]++;
                //StdDraw.filledSquare (currentRoom[1] * 2, - (currentRoom[0] * 2), 0.4);
                StdDraw.line(currentRoom[1] * 2 + 0.3, - (currentRoom[0] * 2 - 0.3), currentRoom[1] * 2 + 0.3,  - (currentRoom[0] * 2 + 0.3));
                StdDraw.line(currentRoom[1] * 2 + 0.3, - (currentRoom[0] * 2 - 0.3), currentRoom[1] * 2 - 0.3,  - (currentRoom[0] * 2));
                StdDraw.line(currentRoom[1] * 2 + 0.3, - (currentRoom[0] * 2 + 0.3), currentRoom[1] * 2 - 0.3,  - (currentRoom[0] * 2));
                continue;
            }
        }
        StdDraw.show();

    }

    public int[][] unvisitedAdjacents(int y, int x){ //yeah i know code recycling and all that
        int[][] neighbours = new int[4][2];
        int count = 0;
        if (y > 0 && !this.rooms[y-1][x].visited() && this.hDoors[y-1][x].isOpen()){
            neighbours[count][0] = y-1;
            neighbours[count][1] = x;
            count++;
        }
        if (x > 0 && !this.rooms[y][x-1].visited() && this.vDoors[y][x-1].isOpen()){
            neighbours[count][0] = y;
            neighbours[count][1] = x-1;
            count++;
        }
        if (y < this.height - 1 && !this.rooms[y+1][x].visited() && this.hDoors[y][x].isOpen()){
            neighbours[count][0] = y+1;
            neighbours[count][1] = x;
            count++;
        }
        if (x < this.length - 1 && !this.rooms[y][x+1].visited() && this.vDoors[y][x].isOpen()){
            neighbours[count][0] = y;
            neighbours[count][1] = x+1;
            count++;
        }

        int[][] cutNeighbours = new int[count][2]; //remove unused spots
        for(int i = 0; i < count; i++){
            cutNeighbours[i] = neighbours[i];
        }
        return cutNeighbours;
    }

    public static void main(String[] args){
        double odc = 0; //openDoorChance
        boolean help = false; //
        boolean draw = false;
        boolean print = false;

        int[] dimensions = new int[2];
        int dimIndex = 0;

        for(int i = 0; i < args.length; i++){
            String current = args[i];
            if (current.charAt(0) == '-'){ //option
                if (current.length() != 2){
                    System.err.println("No combining options! Invalid option: " + current);
                    System.exit(-1);
                }
                switch(current.charAt(1)){
                    case 'h':
                        System.out.println("Usage: java Maze h w [-h] [-o ODC] [-d] [-t] [-p]");
                        System.out.println("-h Shows help");
                        System.out.println("-o Opens doors to create loops in the otherwise perfect maze, probability of ODC");
                        System.out.println("-d Draws the solution with triangles");
                        System.out.println("-p If present, prints the maze to stdout instead of drawing it");
                        System.out.println("h w Height and Width of the maze, in squares.");
                        System.exit(0);

                    case 'o':
                        try{
                            odc = Double.parseDouble(args[++i]);
                        }
                        catch(NumberFormatException e){
                            System.err.println("Invalid open door chance: " + args[i]);
                            System.exit(-2);
                        }
                        catch(ArrayIndexOutOfBoundsException e){
                            System.err.println("No open door chance specified");
                            System.exit(-3);
                        }
                        break;

                    case 'd':
                        draw = true;
                        break;

                    case 'p':
                        print = true;
                        break;

                    default:
                        System.err.println("Invalid option: " + current);
                        System.exit(-4);
                }
            }

            else{ //dimension
                if (dimIndex < 2){
                    try{
                        dimensions[dimIndex] = Integer.parseInt(current);
                    }
                    catch(NumberFormatException e){
                        System.err.println("Dimensions come first! Invalid maze dimensions: " + current);
                        System.exit(3);
                    }
                    if (dimensions[dimIndex++] < 1){
                        System.err.println("Dimensions come first! Invalid maze dimensions: " + current);
                        System.exit(4);
                    }
                }
            }
        }

        if (dimensions[0] == 0 || dimensions[1] == 0){
            System.err.println("Unspecified maze dimensions");
            System.exit(5);
        }

        RandomMaze myMaze = new RandomMaze(dimensions[0], dimensions[1], odc);

        if (print) System.out.println(myMaze);
        else{
            myMaze.draw();
            if (draw) myMaze.drawSolution();
        }

    }

}
