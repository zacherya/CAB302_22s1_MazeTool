package Controllers;

import Views.*;
import Modals.*;

import java.awt.event.ActionListener;

public abstract class DefaultController<M,V> implements IController{
    protected V _view;
    protected M _modal;
    public DefaultController() {

    }

    public M getModal() {
        return _modal;
    }
    public V getView() {
        return _view;
    }
}
