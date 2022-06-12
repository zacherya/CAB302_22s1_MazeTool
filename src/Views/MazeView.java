package Views;

import Controllers.MazeViewController;
import Controllers.WelcomeViewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

/** The MazeView Class */
public class MazeView extends DefaultView<MazeViewController> {

    private static final int BOTTOM_BTN_WIDTH = 0;
    private static final int BOTTOM_BTN_HEIGHT = 40;
    private static final int RIGHT_BTN_WIDTH = 175;
    private static final int RIGHT_BTN_HEIGHT = 30;

    // File selectors
    private JFileChooser logoImageSelector = new JFileChooser();
    private JFileChooser entryImageSelector = new JFileChooser();
    private JFileChooser exitImageSelector = new JFileChooser();

    /** Constructs and configures the view for Maze
     * @param controller The views controller access parameter
     * @authors Aaron Nolan, Zac Adams
     */
    public MazeView(MazeViewController controller) {
        // JFrame setup
        super("New Maze", 750,450);
        configureFrame();

        //Set Controller
        _controller = controller;

        readyFrame(this::addElements);

    }

    /**
     * Creates and adds the elements to the view's frame
     * @authors Aaron Nolan, Zac Adams
     */
    private void addElements() {
        // Create panels
        panels.put("primary", new JPanel());
        panels.put("topPanel", new JPanel());
        panels.put("bottomPanel", new JPanel());
        panels.put("rightPanel", new JPanel());
        panels.put("centrePanel", new JPanel());
        panels.put("leftPanel", new JPanel());

        // Define panel custom attributes
        panels.get("centrePanel").setBackground(Color.WHITE);

        // Create adn configure components for top panel
        textAreas.put("titleText",new JTextArea(1,57));
        textAreas.get("titleText").setText("Give your new maze a creative title..");

        buttons.put("backBtn",createButton("←", 55, 30, this::backBtnAction));

        // Create components for bottom panel
        buttons.put("saveBtn",createButton("Save", BOTTOM_BTN_WIDTH, BOTTOM_BTN_HEIGHT, this::saveBtnAction));
        buttons.put("exportBtn",createButton("Export", BOTTOM_BTN_WIDTH, BOTTOM_BTN_HEIGHT, this::exportBtnAction));

        // Create components for right panel
        buttons.put("solutionBtn",createButton("Show solution", RIGHT_BTN_WIDTH, RIGHT_BTN_HEIGHT, this::solutionBtnAction));
        buttons.put("logoImageBtn",createButton("Select logo image", RIGHT_BTN_WIDTH, RIGHT_BTN_HEIGHT, this::logoImageBtnAction));
        buttons.put("entryImageBtn",createButton("Select entry image", RIGHT_BTN_WIDTH, RIGHT_BTN_HEIGHT, this::entryImageBtnAction));
        buttons.put("exitImageBtn",createButton("Select exit image", RIGHT_BTN_WIDTH, RIGHT_BTN_HEIGHT, this::exitImageBtnAction));

        // Define Panel grids
        definePanelGrid("primary",1,1,0,0);
//        definePanelGrid("topPanel",1,2,5,0);
        definePanelGrid("bottomPanel",1,2,5,0);
        definePanelGrid("rightPanel",6,1,0,5);

        // Add elements to panels
        addButtonsToPanel(panels.get("topPanel"),"backBtn");
        addTextAreasToPanel(panels.get("topPanel"),"titleText");

        addButtonsToPanel(panels.get("rightPanel"),"solutionBtn","logoImageBtn","entryImageBtn","exitImageBtn");

        addButtonsToPanel(panels.get("bottomPanel"),"saveBtn","exportBtn");

        // Add panels to the JFrame
        getContentPane().add(panels.get("topPanel"), BorderLayout.PAGE_START);
        getContentPane().add(panels.get("bottomPanel"), BorderLayout.PAGE_END);
        getContentPane().add(panels.get("rightPanel"), BorderLayout.LINE_END);
        getContentPane().add(panels.get("leftPanel"), BorderLayout.LINE_START);
        getContentPane().add(panels.get("centrePanel"), BorderLayout.CENTER);
        getContentPane().add(panels.get("primary"));
    }
    public void insertMazeFrame(BufferedImage graphic) {
        JLabel mazePic = new JLabel(new ImageIcon(graphic));
        panels.get("primary").add(mazePic);
        panels.get("primary").repaint();
        repaint();
    }

    /** Event for a back button
     * @param actionEvent An ActionEvent
     * @authors Aaron Nolan
     */
    private void backBtnAction(ActionEvent actionEvent) {
        System.out.println("Back button clicked");
        _controller.disposeView();
        WelcomeViewController newView = new WelcomeViewController();
    }

    /** Event for a solution button
     * @param actionEvent An ActionEvent
     * @authors Aaron Nolan */
    private void solutionBtnAction(ActionEvent actionEvent) {
        System.out.println("Show solution button clicked");
        _controller.showMazeSolution();

    }

    /** Event for a save button
     * @param actionEvent An ActionEvent
     * @authors Aaron Nolan */
    private void saveBtnAction(ActionEvent actionEvent) { System.out.println("Save button clicked"); }

    /** Event for an export button
     * @param actionEvent An ActionEvent
     * @authors Aaron Nolan */
    private void exportBtnAction(ActionEvent actionEvent) { System.out.println("Export button clicked"); }

    /** Event for a logo image button
     * @param actionEvent An ActionEvent
     * @authors Aaron Nolan*/
    private void logoImageBtnAction(ActionEvent actionEvent) {
        System.out.println("Select logo image button clicked");
        logoImageSelector.showOpenDialog(this);
    }

    /** Event for an entry image button
     * @param actionEvent An ActionEvent
     * @authors Aaron Nolan */
    private void entryImageBtnAction(ActionEvent actionEvent) {
        System.out.println("Select entry image button clicked");
        entryImageSelector.showOpenDialog(this);
    }

    /** Event for an exit image button
     * @param actionEvent An ActionEvent
     * @authors Aaron Nolan */
    private void exitImageBtnAction(ActionEvent actionEvent) {
        System.out.println("Select exit image button clicked");
        exitImageSelector.showOpenDialog(this);
    }

    @Override
    public void run() {

    }
}