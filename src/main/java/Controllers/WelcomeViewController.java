package Controllers;

import Views.WelcomeView;

import javax.swing.*;

/**
 * Initiates and controls associated functionality for the landing view of the application.
 * @author Zac Adams, Aaron Nolan
 */
public class WelcomeViewController extends DefaultController<Object, WelcomeView> {

    /**
     * Initialise a new instance of the WelcomeViewController and initiate the WelcomeView.
     * Modal is not required as this view requires interaction only. MVC requires a modal, thus
     * it contains a generic, uninitialised object for manipulation where required.
     * @author Zac Adams
     */
    public WelcomeViewController() {
        _view = new WelcomeView(this);
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