package Views;

import Controllers.OpenViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

public class OpenView extends DefaultView<OpenViewController> {

    public OpenView(OpenViewController controller) {
        super("Open Maze", 750, 450);

        _controller = controller;

        readyFrame(this::addFrameElements);
    }

    private void addFrameElements() {
        // Create panels
        panels.put("topPanel", new JPanel());

        buttons.put("button1",createButton("←", 55, 30, this));
        buttons.put("button2",createButton("hello", 55, 30, null));
        buttons.put("button3",createButton("asd", 55, 30, null));

        definePanelGrid("topPanel", 3,1,0,0);
        addButtonsToPanel(panels.get("topPanel"), "button1", "button3", "button2");

        getContentPane().add(panels.get("topPanel"), BorderLayout.PAGE_START);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
