package Controllers;

import Views.MazeView;

public class MazeViewController implements Runnable {

    private MazeView view;

    public MazeViewController() {
        view = new MazeView(this);
    }

    public void updateView() {
        // TODO
    }

    public void disposeView() {
        view.dispose();
    }

    @Override
    public void run() {

    }
}