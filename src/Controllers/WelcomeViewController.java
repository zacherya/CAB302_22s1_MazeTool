package Controllers;

import Views.WelcomeView;

public class WelcomeViewController implements Runnable {

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