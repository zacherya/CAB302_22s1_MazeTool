package Views;

import Controllers.MazeViewController;
import Controllers.OpenViewController;
import Controllers.WelcomeViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** The WelcomeView Class*/
public class WelcomeView extends DefaultView<WelcomeViewController>  {

    private WelcomeViewController controller;

    /** Displays the Welcome View
     * @param controller A WelcomeViewController*/
    public WelcomeView(WelcomeViewController controller) {
        super("Create-a-Maze!",400,200);
        this.controller = controller;

        readyFrame(this::addElements);
    }
    
    private void addElements() {
        //create main panel
        panels.put("primary", new JPanel());
        panels.put("buttons", new JPanel());
        panels.put("newAndOpen", new JPanel());
        panels.put("randomMaze", new JPanel());
        
        // Create buttons
        buttons.put("newBtn", createButton("Create new maze", this::newBtnAction));
        buttons.put("randomBtn", createButton("Generate random maze", this::randomBtnAction));
        buttons.put("openBtn", createButton("Open existing maze", this::openBtnAction));

        // Define grids
        definePanelGrid("primary",1,1,0,0);
        definePanelGrid("buttons",2,1,0,10);
        definePanelGrid("newAndOpen",1,2,10,0);
        definePanelGrid("randomMaze",1,1,0,0);

        addPanelsToPanel(panels.get("primary"),"buttons");
        addPanelsToPanel(panels.get("buttons"),"newAndOpen");
        addPanelsToPanel(panels.get("buttons"),"randomMaze");

        addButtonsToPanel(panels.get("newAndOpen"),"newBtn","openBtn");
        addButtonsToPanel(panels.get("randomMaze"),"randomBtn");

        getContentPane().add(panels.get("primary"));
    }
    
    /** Creates a button
     * @param withText A String of text for the button
     * @param actionListener An actionListener*/
    private JButton createButton(String withText, ActionListener actionListener) {

        JButton button = new JButton();
        button.setText(withText);
        button.addActionListener(actionListener);

        return button;
    }

    /** Event for an action button
     * @param actionEvent - An ActionEvent*/
    private void newBtnAction(ActionEvent actionEvent) {
        System.out.println("New button clicked");
        controller.disposeView();
        MazeViewController newView = new MazeViewController(false);
    }

    /** Event for a random button
     * @param actionEvent - An ActionEvent*/
    private void randomBtnAction(ActionEvent actionEvent) {
        System.out.println("Random button clicked");
        controller.disposeView();
        MazeViewController newView = new MazeViewController(true);
    }

    /** Event for an open button
     * @param actionEvent - An ActionEvent*/
    private void openBtnAction(ActionEvent actionEvent) {
        System.out.println("Open button clicked");
        controller.disposeView();
        OpenViewController newView = new OpenViewController();
    }

    @Override
    public void run() {

    }
}
