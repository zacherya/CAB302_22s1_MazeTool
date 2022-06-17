package DataAccess.DtoModels;


import Modals.Maze;

import javax.imageio.ImageIO;
import javax.naming.CannotProceedException;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Time;

public class MazeDto {

    private int id;
    private String name;
    private String author;
    private byte[] mazeMain;
    private byte[] mazeSolution;
    public MazeDto(String name, String author, byte[] main, byte[] solution) {
        this.mazeMain = main;
        this.mazeSolution = solution;
        this.name = name;
        this.author = author;
    }
    public MazeDto(int id, String name, String author, byte[] main, byte[] solution) {
        this.mazeMain = main;
        this.mazeSolution = solution;
        this.id = id;
        this.name = name;
        this.author = author;
    }
    public MazeDto(Maze mazeObj) throws IOException {
        mazeMain = toByteArray(mazeObj.draw());
        mazeSolution = toByteArray(mazeObj.drawSolution(true));
    }

    /**
     * Constructor for the MazeDto.
     */
    public MazeDto() {
    }

    // convert byte[] to BufferedImage
    private static BufferedImage toBufferedImage(byte[] bytes)
            throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }

    public String GetMazeName() {
        return name;
    }
    public String GetMazeAuthor() {
        return author;
    }

    public byte[] getMazeAsBytes(Boolean solution) {
        if (solution) {
            return this.mazeSolution;
        } else {
            return this.mazeMain;
        }
    }

    public BufferedImage getMazeAsBufferedImage(Boolean solution) throws IOException {
        if(solution) {
            return toBufferedImage(mazeSolution);
        } else {
            return toBufferedImage(mazeMain);
        }
    }

    // convert BufferedImage to byte[]
    public static byte[] toByteArray(BufferedImage bi)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "png", baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }

}
