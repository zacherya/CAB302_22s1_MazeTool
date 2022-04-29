package Views;

import Controllers.MazeViewController;
import Controllers.WelcomeViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MazeView extends JFrame implements Runnable {

    private MazeViewController controller;

    private static final int WIDTH = 750;
    private static final int HEIGHT = 450;
    private static final int ROWS = 2;
    private static final int COLS = 2;

    private JPanel buttonPanel;
    private JButton homeBtn;
    private JButton solutionBtn;
    private JButton saveBtn;
    private JButton exportBtn;

    public MazeView(MazeViewController controller) {
        this.controller = controller;

        // JFrame setup
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
        setTitle("New Maze");

        // Create panels
        JPanel topPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPnl = new JPanel();
        JPanel centrePanel = new JPanel();
        centrePanel.setBackground(Color.WHITE);
        buttonPanel = new JPanel();

        // Create buttons
        homeBtn = createButton("Home", this::homeBtnAction);
        solutionBtn = createButton("Show solution", this::solutionBtnAction);
        saveBtn = createButton("Save", this::saveBtnAction);
        exportBtn = createButton("Export", this::exportBtnAction);

        // Add panels to the JFrame
        getContentPane().add(topPanel, BorderLayout.PAGE_START);
        getContentPane().add(leftPanel, BorderLayout.LINE_START);
        getContentPane().add(rightPnl, BorderLayout.LINE_END);
        getContentPane().add(centrePanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        // Resize the main frame to fit its components
        pack();

        // Center on screen
        setLocationRelativeTo(null);

        // Configure the button panel
        layoutButtonPanel();

        // Make the window visible
        setVisible(true);
    }

    private void layoutButtonPanel() {

        buttonPanel.setLayout(new GridLayout(ROWS, COLS));
        buttonPanel.add(homeBtn);
        buttonPanel.add(solutionBtn);
        buttonPanel.add(saveBtn);
        buttonPanel.add(exportBtn);

    }

    private JButton createButton(String withText, ActionListener actionListener) {

        JButton button = new JButton();
        button.setText(withText);
        button.addActionListener(actionListener);

        return button;
    }

    private void homeBtnAction(ActionEvent actionEvent) {
        System.out.println("Home button clicked");
        controller.disposeView();
        WelcomeViewController newView = new WelcomeViewController();
    }

    private void solutionBtnAction(ActionEvent actionEvent) { System.out.println("Show solution button clicked"); }

    private void saveBtnAction(ActionEvent actionEvent) { System.out.println("Save button clicked"); }

    private void exportBtnAction(ActionEvent actionEvent) { System.out.println("Export button clicked"); }

    @Override
    public void run() {

    }
}