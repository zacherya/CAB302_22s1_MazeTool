package Controllers;
import Views.*;
public interface IController {
    /**
     * The default method for updating or refreshing a view in the current thread.
     * Should be used when UI changes are required and must be rendered.
     * @author Zac Adams
     */
    public void updateView();

    /**
     * The default method for disposing a view from the user's context.
     * @author Zac Adams
     */
    public void disposeView();
}
