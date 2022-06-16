package Helpers;

public class Door{

    private boolean isOpen;

    public Door(){
        this.isOpen = false;
    }

    public boolean isOpen(){
        return this.isOpen;
    }

    public void open(){
        this.isOpen = true;
    }

}
