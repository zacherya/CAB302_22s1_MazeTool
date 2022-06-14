package Modals;

import DataAccess.DtoModels.MazeDto;
import Exceptions.MazeCreationException;
import Helpers.Door;
import Helpers.Room;
import Views.StdDraw;

import java.awt.image.BufferedImage;
import java.util.Stack;
import java.util.ArrayList;

/** The Random Maze Class*/
public class Maze {

    private int height, length;
    private Room[][] rooms;
    private Door[][] vDoors, hDoors;

    public Maze() {

    }
    public Maze(MazeDto mazeDbObject) {
        // unwrap maze from db here
    }
    public Maze(int h, int l, double openDoorChance) throws MazeCreationException {
        try {
            this.newMaze(h,l,openDoorChance);
        } catch (IllegalArgumentException iae) {
            throw new MazeCreationException(iae.getMessage());
        }

    }

    public void newMaze(int h, int l, double openDoorChance) throws IllegalArgumentException {
        if(h < 5 || l < 5) {
            throw new IllegalArgumentException("The maze must be at least 5x5");
        }
        if(openDoorChance < 0) {
            throw new IllegalArgumentException("The maze must be have an open door chance greater or equal to 0");
        }
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

    public BufferedImage draw(){
        //Change the double value to your preferred maximum dimension in pixels
        double scaler = 155.0 / Math.max(this.length * 2 + 1, this.height * 2 + 1);
        StdDraw.setCanvasSize((int) (scaler *(this.length * 2 + 1)), (int) (scaler *(this.height * 2 + 1)));
//        StdDraw.setCanvasSize(275,155);

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
        StdDraw.setPenRadius(0.001);

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

    public void toggleSolution(Boolean display){ //Dijkstra
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

        // Only colours the path to the end
        if (display) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius(0.0012);
        } else {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.setPenRadius(0.0025);
        }

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

}
