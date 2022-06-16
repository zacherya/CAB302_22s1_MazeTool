package Views;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;

public interface IView {
    /**
     * Configures the frame defaults and constraints.
     * It should include at a minimum: Default close operation,
     *      minimum size, layout and resizeable.
     * This method is called upon construction of the view, before
     * any frame content is added.
     * @author Zac Adams
     */
    void configureFrame();

    /**
     * Adds the frame content using a method defined in the view
     * and calls methods to render, paint, set location and make visible.
     * The callback method is executed before any rendering of the Frame.
     * @param callback A method defined in the view, called before any rendering.
     * @author Zac Adams
     */
    void readyFrame(Runnable callback);

    /**
     * A simple create button method that all views should implement
     * according to the frames particular styling requirements
     * @param withText The text displayed on the button
     * @param width The fixed width of the button
     * @param height The fixed height of the button
     * @param actionListener The action callback when clicking the button.
     *                       Set to 'this' for the button to be handled by the generic
     *                       actionPerformed event listener.
     * @return An instance of a JButton with the configured properties
     * @author Zac Adams, Aaron Nolan
     */
    JButton createButton(String withText, int width, int height, ActionListener actionListener);

    /**
     * A generic action event listener method that requires implementation from ActionListener
     * to function. If an event callback method is defined as 'this', the button
     * will default to this method.
     * @param e The event fired as a result of the action performed
     * @author Zac Adams
     */
    void actionPerformed(ActionEvent e);

}
