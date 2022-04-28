public class Maze extends Image {



    public String name;
    public String author;
    public java.time.LocalDate created;
    public java.time.LocalDate lastEdited;
    public int startPoint;
    public int endPoint;
    public Image logo;



    public void Maze(){

    }

    public void Maze(String name, String author, java.time.LocalDate created, java.time.LocalDate lastEdited, int startPoint, int endPoint, Image logo){
        this.name = name;
        this.author = author;
        this.created = created;
        this.lastEdited = lastEdited;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.logo = logo;
    }

    public boolean isValid(Maze maze){
        return true;
    }

    public boolean isSolvable(Maze maze){
        return true;
    }

    public void calculateSolution(Maze maze){

    }


}
