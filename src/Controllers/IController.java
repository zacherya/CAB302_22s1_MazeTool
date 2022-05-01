package Controllers;
import Views.*;
public interface IController {
    /**
     * The default method for updating or refreshing a view in the current thread.
     * Should be used when UI changes are required and must be rendered.
     * @authors Zac Adams
     */
    public void updateView();

    /**
     * The default method for disposing of a view from the users interactable context.
     * @authors Zac Adams
     */
    public void disposeView();
}
