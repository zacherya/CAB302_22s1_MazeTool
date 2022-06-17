package Controllers;

import DataAccess.DtoModels.MazeDto;
import DataAccess.Providers.MazeDataProvider;
import Modals.Open;
import Views.IView;
import Views.OpenView;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Initiates and controls associated functionality for retrieving, displaying and interacting with existing maze files.
 * @author Aaron Nolan
 */
public class OpenViewController extends DefaultController<Open, OpenView> {

    private MazeDataProvider _provider;

    /**
     *Initialise a new OpenViewController to initiate a new View, Modal and data provider
     */
    public OpenViewController() {
        _provider = new MazeDataProvider();
        _view = new OpenView(this);
        _modal = new Open();
    }

    public ArrayList<MazeDto> getMazes() {
        ArrayList<MazeDto> mazes = new ArrayList<>();
        try {
            mazes = _provider.GetMazes();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(_view, "There was an issue fetching the mazes! "+ex.getLocalizedMessage());
        }
        return mazes;
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
}
