package Controllers;

import Modals.Open;
import Views.MazeView;
import Modals.Maze;
import Views.OpenView;

public class MazeViewController extends DefaultController<Maze, MazeView>  {

    public MazeViewController(boolean generateRandom) {
        _view = new MazeView(this);
        if(generateRandom) {
            // Configure maze to be random here
            _modal = new Maze();
        } else {
            _modal = new Maze();
        }

    }

    public void updateView() {
        // TODO
        _view.repaint();
    }

    public void disposeView() {
        _view.dispose();
    }

}