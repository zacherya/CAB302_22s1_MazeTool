package Controllers;

import Modals.Open;
import Views.IView;
import Views.OpenView;

public class OpenViewController extends DefaultController<Open, OpenView> {

    public OpenViewController() {
        _view = new OpenView(this);
        _modal = new Open();
    }

    @Override
    public void updateView() {

    }

    @Override
    public void disposeView() {

    }
}
