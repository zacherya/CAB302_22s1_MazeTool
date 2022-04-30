package Views;

import Controllers.MazeViewController;
import Controllers.WelcomeViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** The WelcomeView Class*/
public class WelcomeView extends JFrame implements Runnable  {

    private WelcomeViewController controller;

    private JButton newBtn;
    private JButton randomBtn;
    private JButton openBtn;

    private static final int WIDTH = 400;
    private static final int HEIGHT = 200;
    private static final int ROWS = 3;
    private static final int COLS = 2;

    /** Displays the Welcome View
     * @param controller A WelcomeViewController*/
    public WelcomeView(WelcomeViewController controller) {
        this.controller = controller;

        // JFrame setup
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(ROWS, COLS));
        setResizable(false);
        setTitle("Create-a-Maze!");

        // Create buttons
        newBtn = createButton("New", this::newBtnAction);
        randomBtn = createButton("Random", this::randomBtnAction);
        openBtn = createButton("Open", this::openBtnAction);

        // Add buttons to the JFrame
        add(newBtn);
        add(randomBtn);
        add(openBtn);

        // Resize the main frame to fit its components
        pack();

        // Center on screen
        setLocationRelativeTo(null);

        // Make the window visible
        setVisible(true);
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
        MazeViewController newView = new MazeViewController();
    }

    /** Event for a random button
     * @param actionEvent - An ActionEvent*/
    private void randomBtnAction(ActionEvent actionEvent) { System.out.println("Random button clicked"); }

    /** Event for an open button
     * @param actionEvent - An ActionEvent*/
    private void openBtnAction(ActionEvent actionEvent) {
        System.out.println("Open button clicked");
    }

    @Override
    public void run() {

    }
}
