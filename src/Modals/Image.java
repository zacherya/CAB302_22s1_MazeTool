package Modals;
/** Class for Images */
public class Image {


    public String fileName;
    public int height;
    public int width;

    /** Constructor
     * @authors Alex Hannah */
    public void Image(){

    }
    /** Overloaded Constructor
     * @param fileName file location as a String
     * @authors Alex Hannah */
    public void Image(String fileName){
        this.fileName = fileName;
    }

    /** Scales the cell size of an image
     * @authors Alex Hannah */
    public void scale(){

    }
    /** Tells if an image is contained in a block of cells or not
     * @return true if contained within cells, false otherwise
     * @authors Alex Hannah */
    public boolean isContained(){
        return false;
    }
}
