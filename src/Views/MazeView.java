package Views;

import Controllers.MazeViewController;
import Controllers.WelcomeViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** The MazeView Class */
public class MazeView extends DefaultView {

    private MazeViewController controller;

    private static final int WIDTH = 750;
    private static final int HEIGHT = 450;
    private static final int BOTTOM_BTN_WIDTH = 0;
    private static final int BOTTOM_BTN_HEIGHT = 40;
    private static final int RIGHT_BTN_WIDTH = 175;
    private static final int RIGHT_BTN_HEIGHT = 30;

    // Top panel + child components
    private JPanel topPanel;
    private JTextArea titleText;
    private JButton backBtn;

    // Bottom panel + child components
    private JPanel bottomPanel;
    private JButton saveBtn;
    private JButton exportBtn;

    // Right panel + child components
    private JPanel rightPanel;
    private JButton solutionBtn;
    private JButton logoImageBtn;
    private JButton entryImageBtn;
    private JButton exitImageBtn;

    // File selectors
    private JFileChooser logoImageSelector = new JFileChooser();
    private JFileChooser entryImageSelector = new JFileChooser();
    private JFileChooser exitImageSelector = new JFileChooser();

    /** Generates the view of a Maze
     * @param controller A MazeViewController */
    public MazeView(MazeViewController controller) {
        // JFrame setup
        super("New Maze", 750,450);
        configureFrame();

        //Set Controller
        this.controller = controller;


        // Create panels
        topPanel = new JPanel();
        bottomPanel = new JPanel();
        rightPanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel centrePanel = new JPanel();
        centrePanel.setBackground(Color.WHITE);

        // Create components for top panel
        titleText = new JTextArea(1,57);
        titleText.setText("Give your new maze a creative title..");
        backBtn = createButton("←", 55, 30, this::backBtnAction);

        // Create components for bottom panel
        saveBtn = createButton("Save", BOTTOM_BTN_WIDTH, BOTTOM_BTN_HEIGHT, this::saveBtnAction);
        exportBtn = createButton("Export", BOTTOM_BTN_WIDTH, BOTTOM_BTN_HEIGHT,  this::exportBtnAction);

        // Create components for right panel
        solutionBtn = createButton("Show solution", RIGHT_BTN_WIDTH, RIGHT_BTN_HEIGHT, this::solutionBtnAction);
        logoImageBtn = createButton("Select logo image", RIGHT_BTN_WIDTH, RIGHT_BTN_HEIGHT, this::logoImageBtnAction);
        entryImageBtn = createButton("Select entry image", RIGHT_BTN_WIDTH, RIGHT_BTN_HEIGHT, this::entryImageBtnAction);
        exitImageBtn = createButton("Select exit image", RIGHT_BTN_WIDTH, RIGHT_BTN_HEIGHT, this::exitImageBtnAction);

        // Add panels to the JFrame
        getContentPane().add(topPanel, BorderLayout.PAGE_START);
        getContentPane().add(bottomPanel, BorderLayout.PAGE_END);
        getContentPane().add(rightPanel, BorderLayout.LINE_END);
        getContentPane().add(leftPanel, BorderLayout.LINE_START);
        getContentPane().add(centrePanel, BorderLayout.CENTER);

        // Resize the main frame to fit its components
        pack();

        // Center on screen
        setLocationRelativeTo(null);

        // Configure the custom panels
        layoutTopPanel();
        layoutBottomPanel();
        layoutRightPanel();

        // Make the window visible
        setVisible(true);
    }

    /** Components for the top panel */
    private void layoutTopPanel() {
        topPanel.add(backBtn);
        topPanel.add(titleText);
    }

    /** Components for the bottom panel */
    private void layoutBottomPanel() {
        bottomPanel.setLayout(new GridLayout(1, 2));
        bottomPanel.add(saveBtn);
        bottomPanel.add(exportBtn);
    }

    /** Components for the right panel */
    private void layoutRightPanel() {
        rightPanel.setLayout(new GridLayout(6, 1));
        rightPanel.add(solutionBtn);
        rightPanel.add(logoImageBtn);
        rightPanel.add(entryImageBtn);
        rightPanel.add(exitImageBtn);
    }

    /** Event for a back button
     * @param actionEvent An ActionEvent */
    private void backBtnAction(ActionEvent actionEvent) {
        System.out.println("Back button clicked");
        controller.disposeView();
        WelcomeViewController newView = new WelcomeViewController();
    }

    /** Event for a solution button
     * @param actionEvent An ActionEvent */
    private void solutionBtnAction(ActionEvent actionEvent) { System.out.println("Show solution button clicked"); }

    /** Event for a save button
     * @param actionEvent An ActionEvent */
    private void saveBtnAction(ActionEvent actionEvent) { System.out.println("Save button clicked"); }

    /** Event for an export button
     * @param actionEvent An ActionEvent */
    private void exportBtnAction(ActionEvent actionEvent) { System.out.println("Export button clicked"); }

    /** Event for a logo image button
     * @param actionEvent An ActionEvent */
    private void logoImageBtnAction(ActionEvent actionEvent) {
        System.out.println("Select logo image button clicked");
        logoImageSelector.showOpenDialog(this);
    }

    /** Event for an entry image button
     * @param actionEvent An ActionEvent */
    private void entryImageBtnAction(ActionEvent actionEvent) {
        System.out.println("Select entry image button clicked");
        entryImageSelector.showOpenDialog(this);
    }

    /** Event for an exit image button
     * @param actionEvent An ActionEvent */
    private void exitImageBtnAction(ActionEvent actionEvent) {
        System.out.println("Select exit image button clicked");
        exitImageSelector.showOpenDialog(this);
    }

    @Override
    public void run() {

    }
}