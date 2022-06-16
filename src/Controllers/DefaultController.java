package Controllers;

import Views.*;
import Modals.*;
import java.awt.event.ActionListener;

/**
 * Default controller class to provide standard functionality to child controllers.
 * @param <M> modal
 * @param <V> view
 * @author Zac Adams
 */
public abstract class DefaultController<M,V> implements IController, Runnable{

    protected V _view;
    protected M _modal;

    /**
     * The default controller constructor that calls standardised controller methods
     * required by all child controllers.
     * @author Zac Adams
     */
    public DefaultController() {}

    /**
     * Can return the modal to method outside of parent child context.
     * @return Defined polymorphic type of the inherited Modal
     * @author Zac Adams
     */
    public M getModal() {
        return _modal;
    }

    /**
     * Can return the view to method outside of parent child context.
     * @return Defined polymorphic type of the inherited View
     * @author Zac Adams
     */
    public V getView() {
        return _view;
    }

    @Override
    public void run() {

    }
}
