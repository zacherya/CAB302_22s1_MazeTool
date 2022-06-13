package Controllers;

import DataAccess.Providers.MazeDataProvider;
import Views.MazeView;
import Modals.Maze;

public class MazeViewController extends DefaultController<Modals.Maze, MazeView>  {
    private MazeDataProvider _provider;
    private boolean showSolution = false;

    public Boolean solutionShowing() {
        return showSolution;
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
            _modal = new Maze(20,20,0);
            _view.insertMazeFrame(_modal.draw());

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
        showSolution = !showSolution;
        _modal.toggleSolution(showSolution);
        updateView();
    }

}