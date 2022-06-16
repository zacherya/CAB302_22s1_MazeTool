package Views;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public abstract class DefaultView<C> extends JFrame implements IView, Runnable, ActionListener {

    protected C _controller;
    private static int viewWidth;
    private static int viewHeight;

    protected HashMap<String, JPanel> panels = new HashMap<String, JPanel>();
    protected HashMap<String, JButton> buttons = new HashMap<String, JButton>();
    protected HashMap<String, JTextArea> textAreas = new HashMap<String, JTextArea>();
    protected HashMap<String, JTextField> textFields = new HashMap<String, JTextField>();
    protected HashMap<String, JFileChooser> fileChoosers = new HashMap<String, JFileChooser>();
    protected HashMap<String, JComboBox> comboBoxes = new HashMap<String, JComboBox>();
    protected HashMap<String, JCheckBox> checkBoxes = new HashMap<String, JCheckBox>();

    /**
     * Initialise the default view with a title and size
     * @param frameTitle The view's title that appears in the OS's frame, taskbar and task manager
     * @param width The view's default starting width
     * @param height The view's default starting height
     * @author Zac Adams
     */
    public DefaultView(String frameTitle, int width, int height) {
        super(frameTitle);
        viewWidth = width;
        viewHeight = height;

        configureFrame();
    }

    /**
     * Get an existing panel and define a grid layout for it with rows and cols. Gap between
     * elements for x and y can be specified else set to 0.
     *
     * @param panelId The panel key string defined in the views 'panels' lookup object
     * @param rows The amount of rows the grid should render available
     * @param cols The amount of columns the grid should render available
     * @param hGap The gap on the x-axis between columns
     * @param vGap The gap on the y-axis between rows
     * @author Zac Adams
     */
    public void definePanelGrid(String panelId, int rows, int cols, int hGap, int vGap) {
        GridLayout layout = new GridLayout(rows,cols);
        layout.setVgap(vGap);
        layout.setVgap(vGap);
        layout.setHgap(hGap);
        panels.get(panelId).setBorder(new EmptyBorder(5, 5, 5, 5));
        panels.get(panelId).setLayout(layout);
    }

    /**
     * Method to add a single or multiple panels to a particular panel
     * @param panel The panel the objects to should be rendered in
     * @param objectIds The corresponding object key defined in the panels' lookup variable
     * @author Zac Adams
     * TODO: 1/05/2022 Add complimenting method to accept panelId
     */
    public void addPanelsToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(panels.get(objectId));
        }
    }
    /**
     * Method to add a single or multiple buttons to a particular panel
     * @param panel The panel the objects to should be rendered in
     * @param objectIds The corresponding object key defined in the buttons' lookup variable.
     * @author Zac Adams
     * TODO: 1/05/2022 Add complimenting method to accept panelId
     */
    public void addButtonsToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(buttons.get(objectId));
        }
        // Alt method but adds all elements
//        for (JButton btn: buttons.values()) {
//            panel.add(btn);
//        }
    }
    /**
     * Method to add a single or multiple textAreas to a particular panel
     * @param panel The panel the objects to should be rendered in
     * @param objectIds The corresponding object key defined in the textAreas lookup variable.
     * @author Zac Adams
     * TODO: 1/05/2022 Add complimenting method to accept panelId
     */
    public void addTextAreasToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(textAreas.get(objectId));
        }
    }
    /**
     * Method to add a single or multiple textFields to a particular panel.
     * @param panel The panel the objects to should be rendered in.
     * @param objectIds The corresponding object key defined in the textFields lookup variable.
     * @author Zac Adams
     * TODO: 1/05/2022 Add complimenting method to accept panelId
     */
    public void addTextFieldsToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(textFields.get(objectId));
        }
    }
    /**
     * Method to add a single or multiple fileChoosers to a particular panel
     * @param panel The panel the objects to should be rendered in
     * @param objectIds The corresponding object key defined in the fileChoosers lookup variable
     * @deprecated This method is obsolete as it does not fit the requirements nor function as
     *      originally expected. FileChoosers have no explicit UI.
     * @author Zac Adams
     * TODO: 1/05/2022 Ensure method can be removed safely
     */
    @Deprecated void addFileChoosersToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(fileChoosers.get(objectId));
        }
    }
    /**
     * Method to add a single or multiple comboBoxes to a particular panel
     * @param panel The panel the objects to should be rendered in
     * @param objectIds The corresponding object key defined in the comboBoxes lookup variable
     * @author Zac Adams
     * TODO: 1/05/2022 Add complimenting method to accept panelId
     */
    public void addComboBoxesToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(comboBoxes.get(objectId));
        }
    }
    /**
     * Method to add a single or multiple checkBoxes to a particular panel
     * @param panel The panel the objects to should be rendered in
     * @param objectIds The corresponding object key defined in the checkBoxes lookup variable
     * @author Zac Adams
     * TODO: 1/05/2022 Add complimenting method to accept panelId
     */
    public void addCheckBoxesToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(checkBoxes.get(objectId));
        }
    }

    @Override
    public void configureFrame() {
        setMinimumSize(new Dimension(viewWidth, viewHeight));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    @Override
    public void readyFrame(Runnable callback) {
        callback.run();

        // Resize the main frame to fit its components
        pack();

        // Center on screen
        setLocationRelativeTo(null);

        // Make the window visible
        setVisible(true);
    }

    /** Creates buttons
     * @param withText String of text for the button
     * @param actionListener An ActionListener
     * @param width Preferred width of the button
     * @param height Preferred height of the button
     * @return A new button
     * @author Zac Adams, Aaron Nolan
     */
    @Override
    public JButton createButton(String withText, int width, int height, ActionListener actionListener) {
        JButton button = new JButton();
        button.setText(withText);
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(actionListener);

        return button;
    }

    /**
     * Global process method for catching all button events. Define the callback method to 'this' for
     * this method to execute.
     * Implement common action events here (e.g Go Home)
     * @param e The event fired as a result of the action performed
     * @author Zac Adams
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }
}
