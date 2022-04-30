package Controllers;

import Views.WelcomeView;

import javax.swing.*;

public class WelcomeViewController extends DefaultController<Object, WelcomeView> {

    public WelcomeViewController() {
        _view = new WelcomeView(this);
    }

    public void updateView() {
        // TODO
    }

    public void disposeView() {
        _view.dispose();
    }

}