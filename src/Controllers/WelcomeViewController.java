package Controllers;

import Views.WelcomeView;

import javax.swing.*;

public class WelcomeViewController extends DefaultController<Object, WelcomeView> {

    /**
     * Initalise a new instance of the WelcomeViewController and initiate the WelcomeView.
     * Modal is not required as this view requies interaction only. MVC requires a modal, thus
     * it contains a generic, non-initalised object for manipulation where required.
     * @authors Zac Adams
     */
    public WelcomeViewController() {
        _view = new WelcomeView(this);
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