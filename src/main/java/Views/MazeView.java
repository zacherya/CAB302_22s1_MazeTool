package Views;

import Controllers.MazeViewController;
import Controllers.WelcomeViewController;
import DataAccess.DtoModels.MazeDto;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static java.lang.String.format;

/** The MazeView Class */
public class MazeView extends DefaultView<MazeViewController> {

    public final MazeDto viewingMaze;
    private static final int BOTTOM_BTN_WIDTH = 0;
    private static final int BOTTOM_BTN_HEIGHT = 40;
    private static final int RIGHT_BTN_WIDTH = 175;
    private static final int RIGHT_BTN_HEIGHT = 30;

    // File selectors
    // TODO: Clean-up and refactor this implementation
    private final String USER_DIRECTORY = System.getProperty("user.home");
    private final String PATH = "/Desktop";
    private final FileNameExtensionFilter FILTER = new FileNameExtensionFilter("Image Files", "jpg", "png", "jpeg");
    private JFileChooser logoImageSelector = new JFileChooser(USER_DIRECTORY + PATH);
    private JFileChooser entryImageSelector = new JFileChooser(USER_DIRECTORY + PATH);
    private JFileChooser exitImageSelector = new JFileChooser(USER_DIRECTORY + PATH);

    /**
     * Constructs and configures the view for Maze
     *
     * @param controller  The views controller access parameter
     * @author Aaron Nolan, Zac Adams
     */
    public MazeView(MazeViewController controller) {
        // JFrame setup
        super("New Maze", 750,450);
        this.viewingMaze = null;
        configureFrame();

        // Set Controller
        _controller = controller;
        readyFrame(this::addElements);
    }

    public MazeView(MazeViewController controller, MazeDto mazeToShow) {

        // JFrame setup
        super("Viewing Maze", 750,450);
        configureFrame();

        viewingMaze = mazeToShow;

        // Set Controller
        _controller = controller;
        readyFrame(this::addElements);
        textAreas.get("authorText").setText(viewingMaze.GetMazeName());
        textAreas.get("titleText").setText(viewingMaze.GetMazeAuthor());
        viewingMazeSolutionToggle(false);
        makeViewUneditable();
    }

    /**
     * Creates and adds the elements to the view's frame
     * @author Aaron Nolan, Zac Adams
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

        // Create and configure components for top panel
        textAreas.put("titleText",new JTextArea(1,57));
        textAreas.get("titleText").setText("Give your new maze a creative title..");
        textAreas.put("authorText",new JTextArea(1,12));
        textAreas.get("authorText").setText("Who made this maze?");
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
//      definePanelGrid("topPanel",1,2,5,0);
        definePanelGrid("bottomPanel",1,2,5,0);
        definePanelGrid("rightPanel",6,1,0,5);

        // Add elements to panels
        addButtonsToPanel(panels.get("topPanel"),"backBtn");
        addTextAreasToPanel(panels.get("topPanel"),"authorText");
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

    private void makeViewUneditable() {
        buttons.get("saveBtn").setEnabled(false);
        buttons.get("logoImageBtn").setEnabled(false);
        buttons.get("entryImageBtn").setEnabled(false);
        buttons.get("exitImageBtn").setEnabled(false);
        textAreas.get("authorText").setEnabled(false);
        textAreas.get("titleText").setEnabled(false);
        repaint();
    }

    public void viewingMazeSolutionToggle(Boolean solutionShow) {
        JLabel mazePic = new JLabel(new ImageIcon(viewingMaze.getMazeAsBytes(solutionShow)));
        panels.get("primary").removeAll();
        panels.get("primary").revalidate();
        panels.get("primary").repaint();
        panels.get("primary").add(mazePic);
        panels.get("primary").repaint();
        repaint();
    }

    public String getMazeName() {
        return textAreas.get("titleText").getText();
    }
    public String getMazeAuthor() {
        return textAreas.get("authorText").getText();
    }

    public void insertMazeFrame(BufferedImage graphic) {
        JLabel mazePic = new JLabel(new ImageIcon(graphic));
        panels.get("primary").add(mazePic);
        panels.get("primary").repaint();
        repaint();
    }

    /** Event for a back button
     * @param actionEvent An ActionEvent
     * @author Aaron Nolan
     */
    private void backBtnAction(ActionEvent actionEvent) {
        System.out.println("Back button clicked");
        _controller.disposeView();
        WelcomeViewController newView = new WelcomeViewController();
    }

    /** Event for a solution button
     * @param actionEvent An ActionEvent
     * @author Aaron Nolan */
    private void solutionBtnAction(ActionEvent actionEvent) {
        System.out.println("Show solution button clicked");
        _controller.toggleMazeSolution();
        if(_controller.solutionShowing()) {
            buttons.get("solutionBtn").setText("Hide solution");
        } else {
            buttons.get("solutionBtn").setText("Show solution");
        }
        repaint();
    }

    /** Event for a save button
     * @param actionEvent An ActionEvent
     * @author Aaron Nolan */
    private void saveBtnAction(ActionEvent actionEvent) {
        System.out.println("Save button clicked");
        if(_controller.saveMaze()){
            this.setTitle("Viewing Maze");
            makeViewUneditable();

            JOptionPane.showMessageDialog(this, getMazeName() + " by " + getMazeAuthor() + " has been saved successfully!");
        }
    }

    /** Event for an export button
     * @param actionEvent An ActionEvent
     * @author Aaron Nolan */
    private void exportBtnAction(ActionEvent actionEvent) { System.out.println("Export button clicked"); }

    /** Event for a logo image button
     * @param actionEvent An ActionEvent
     * @author Aaron Nolan*/
    private void logoImageBtnAction(ActionEvent actionEvent) {
        File selectedFile = getFile(logoImageSelector);
        System.out.println("Logo image: " + selectedFile);
    }

    /** Event for an entry image button
     * @param actionEvent An ActionEvent
     * @author Aaron Nolan */
    private void entryImageBtnAction(ActionEvent actionEvent) {
        File selectedFile = getFile(entryImageSelector);
        System.out.println("Entry image: " + selectedFile);
    }

    /** Event for an exit image button
     * @param actionEvent An ActionEvent
     * @author Aaron Nolan */
    private void exitImageBtnAction(ActionEvent actionEvent) {
        File selectedFile = getFile(exitImageSelector);
        System.out.println("Exit image: " + selectedFile);
    }

    /**
     * Handles the logic for selecting and returning a valid file
     * @param jFileChooser A JFileChooser
     * @return An image file selected by the user
     * @author Aaron Nolan */
    private File getFile(JFileChooser jFileChooser) {
        jFileChooser.setFileFilter(FILTER);
        int selectedOption = jFileChooser.showOpenDialog(this);
        if (selectedOption == JFileChooser.APPROVE_OPTION) {
            return new File(jFileChooser.getSelectedFile().getAbsolutePath());
        }
        return null;
    }

    @Override
    public void run() {

    }
}