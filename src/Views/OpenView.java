package Views;

import Controllers.OpenViewController;
import Controllers.WelcomeViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

public class OpenView extends DefaultView<OpenViewController> {

    /**
     * Initalises the configuration and objects for the Open View frame
     * @param controller
     * @authors Zac Adams
     */
    public OpenView(OpenViewController controller) {
        super("Open Maze", 750, 450);

        _controller = controller;

        readyFrame(this::addFrameElements);
    }

    /**
     * Adds and configures the elements for the view's frame
     * @authors Zac Adams
     * @// TODO: 1/05/2022 Refactor and implement this method properly (Make gui relevant). 
     */
    private void addFrameElements() {
        // Create panels
        panels.put("topPanel", new JPanel());

        buttons.put("button1",createButton("←", 55, 30, this::backBtnAction));
        buttons.put("button2",createButton("hello", 55, 30, null));
        buttons.put("button3",createButton("asd", 55, 30, null));

        definePanelGrid("topPanel", 3,1,0,0);
        addButtonsToPanel(panels.get("topPanel"), "button1", "button3", "button2");

        getContentPane().add(panels.get("topPanel"), BorderLayout.PAGE_START);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void backBtnAction(ActionEvent actionEvent) {
        System.out.println("Back button clicked");
        _controller.disposeView();
        WelcomeViewController newView = new WelcomeViewController();
    }
}
