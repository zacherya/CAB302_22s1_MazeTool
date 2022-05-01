package Controllers;

import Views.*;
import Modals.*;

import java.awt.event.ActionListener;

public abstract class DefaultController<M,V> implements IController, Runnable{
    protected V _view;
    protected M _modal;

    /**
     * The default controller constructor that calls standardised controller methods
     * required by all child controllers.
     * @authors Zac Adams
     */
    public DefaultController() {

    }

    /**
     * Can return the modal to method outside of parent child context
     * @return Defined polymorphic type of the inheritied Modal
     * @authors Zac Adams
     */
    public M getModal() {
        return _modal;
    }

    /**
     * Can return the view to method outside of parent child context
     * @return Defined polymorphic type of the inheritied View
     * @authors Zac Adams
     */
    public V getView() {
        return _view;
    }


    @Override
    public void run() {

    }
}
