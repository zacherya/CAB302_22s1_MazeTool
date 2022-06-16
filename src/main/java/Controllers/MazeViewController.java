package Controllers;

import DataAccess.Providers.MazeDataProvider;
import Exceptions.MazeCreationException;
import Views.MazeView;
import Modals.Maze;

import javax.swing.*;

/**
 * Initiates and controls associated functionality for a maze, manipulating the view as required.
 * @author Zac Adams, Aaron Nolan
 */
public class MazeViewController extends DefaultController<Modals.Maze, MazeView>  {

    private MazeDataProvider _provider;
    private boolean showSolution = false;

    /**
     * Get method that indicates if the solution to a maze is currently being displayed.
     * @return true if showing, false if hidden
     * @author Zac Adams, Aaron Nolan
     */
    public Boolean solutionShowing() {
        return showSolution;
    }

    /**
     * Initialise a new MazeViewController to initiate a new View and modal.
     * @param generateRandom where the maze modal should have preset, automatically
     *                       generated data
     * @author Aaron Nolan, Zac Adams
     */
    public MazeViewController(boolean generateRandom) {
        _provider = new MazeDataProvider();
        _view = new MazeView(this);
        if(generateRandom) {
            // Configure maze to be random here
            try {
                _modal = new Maze(20,20,0);
                _view.insertMazeFrame(_modal.draw());
            } catch (MazeCreationException e) {
                JOptionPane.showMessageDialog(_view, "There was an issue generating the maze!");
                disposeView();
                new WelcomeViewController();
            }
        } else {
            _modal = new Maze();
        }
    }

    /**
     * Update the view's UI changes
     * @author Aaron Nolan
     */
    public void updateView() {
        // TODO
        _view.repaint();
    }

    /**
     * Dispose of the current view
     * @author Aaron Nolan
     */
    public void disposeView() {
        _view.dispose();
    }

    /**
     * Toggles the maze solution to display on and off.
     * @author Zac Adams, Aaron Nolan
     */
    public void toggleMazeSolution() {
        showSolution = !showSolution;
        _modal.toggleSolution(showSolution);
        updateView();
    }

}