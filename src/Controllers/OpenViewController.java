package Controllers;

import DataAccess.Providers.MazeDataProvider;
import Modals.Open;
import Views.IView;
import Views.OpenView;

public class OpenViewController extends DefaultController<Open, OpenView> {

    private MazeDataProvider _provider;

    /**
     *Initalise a new OpenViewController to intitiate a new View, Modal and data provider
     */
    public OpenViewController() {
        _provider = new MazeDataProvider();
        _view = new OpenView(this);
        _modal = new Open();
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
}
