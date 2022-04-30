package Controllers;

import Views.WelcomeView;

import javax.swing.*;

public class WelcomeViewController implements IController, Runnable {

    private WelcomeView view;

    public WelcomeViewController() {
        view = new WelcomeView(this);
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