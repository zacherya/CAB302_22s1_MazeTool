package Controllers;

import DataAccess.DtoModels.MazeDto;
import DataAccess.Providers.MazeDataProvider;
import Exceptions.MazeCreationException;
import Views.MazeView;
import Modals.Maze;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Base64;

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
        try {
            MazeDataProvider mdp = new MazeDataProvider();
            ArrayList<MazeDto> mazes = mdp.GetMazes();
            for (MazeDto maze:mazes) {
                System.out.println(maze.GetMazeName());
            }
        } catch(SQLException sqle) {
            JOptionPane.showMessageDialog(_view, "There was an issue fetching the maze! "+sqle.getLocalizedMessage());
        }

    }

    public Boolean saveMaze() {
        //MazeDto(String name, String author, byte[] main, byte[] solution)
        try {
            byte[] main = MazeDto.toByteArray(_modal.draw());
            byte[] solution = MazeDto.toByteArray(_modal.drawSolution(true));
            MazeDto mazeToSave = new MazeDto(_view.getMazeName(),_view.getMazeAuthor(),main,solution);
            _provider.InsertMaze(mazeToSave);
            return true;
        } catch (Exception ex) {
            if(ex.getLocalizedMessage().contains("SQLITE_CONSTRAINT_UNIQUE")) {
                JOptionPane.showMessageDialog(_view, "You can't create a maze with the same name as one that already exists!");
            } else {
                JOptionPane.showMessageDialog(_view, "There was an issue converting the maze! "+ex.getLocalizedMessage());
            }
            return false;
        }


    }

}