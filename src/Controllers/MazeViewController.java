package Controllers;

import Views.MazeView;
import Modals.Maze;

public class MazeViewController implements IController {

    private Maze modal;
    private MazeView view;

    public MazeViewController(boolean generateRandom) {
        view = new MazeView(this);
        if(generateRandom) {
            // Configure maze to be random here
            modal = new Maze();
        } else {
            modal = new Maze();
        }

    }

    public void updateView() {
        // TODO
        view.repaint();
    }

    public void disposeView() {
        view.dispose();
    }

}