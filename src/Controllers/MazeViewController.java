package Controllers;

import DataAccess.Providers.MazeDataProvider;
import Views.MazeView;
import Modals.Maze;
import Helpers.RandomMaze;

public class MazeViewController extends DefaultController<Maze, MazeView>  {
    private MazeDataProvider _provider;

    RandomMaze myMaze;
    private boolean toggleStatus = false;

    public boolean getToggleStatus() {
        return toggleStatus;
    }

    public void setToggleStatus(Boolean newStatus) {
        this.toggleStatus = newStatus;
    }

    /**
     * Initalise a new MazeViewController to initiate a new View and modal
     * @param generateRandom Where the maze modal should have preset, automatically
     *                       generated data
     * @authors Aaron Nolan, Zac Adams
     */
    public MazeViewController(boolean generateRandom) {
        _provider = new MazeDataProvider();
        _view = new MazeView(this);
        if(generateRandom) {
            // Configure maze to be random here
            _modal = new Maze();
            myMaze = new RandomMaze(20,20,0);
            _view.insertMazeFrame(myMaze.draw());

        } else {
            _modal = new Maze();
        }
    }

    /**
     * Update the view's UI changes
     * @authors Aaron Nolan
     */
    public void updateView() {
        // TODO
        _view.repaint();
    }

    /**
     * Dispose of the current view
     * @authors Aaron Nolan
     */
    public void disposeView() {
        _view.dispose();
    }

    public void toggleMazeSolution() {
        if (!toggleStatus) {
            myMaze.toggleSolution(true);
        } else {
            myMaze.toggleSolution(false);
        }
        updateView();
    }

}