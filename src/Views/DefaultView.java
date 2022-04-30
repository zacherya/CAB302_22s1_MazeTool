package Views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public abstract class DefaultView<T> extends JFrame implements IView, Runnable, ActionListener {
    protected T _controller;
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
     * Initalise the default view with a title and size
     * @param frameTitle
     * @param width
     * @param height
     */
    public DefaultView(String frameTitle, int width, int height) {
        super(frameTitle);
        viewWidth = width;
        viewHeight = height;

        configureFrame();
    }
    public void definePanelGrid(String panelId, int rows, int cols) {
        GridLayout layout = new GridLayout(rows,cols);
        panels.get(panelId).setLayout(layout);
    }
    public void addPanelsToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(panels.get(objectId));
        }
    }
    public void addButtonsToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(buttons.get(objectId));
        }
        // Alt method but adds all elements
//        for (JButton btn: buttons.values()) {
//            panel.add(btn);
//        }
    }
    public void addTextAreasToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(textAreas.get(objectId));
        }
    }
    public void addTextFieldsToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(textFields.get(objectId));
        }
    }
    public void addFileChoosersToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(fileChoosers.get(objectId));
        }
    }
    public void addComboBoxesToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(comboBoxes.get(objectId));
        }
    }
    public void addCheckBoxesToPanel(JPanel panel, String... objectIds) {
        for (String objectId: objectIds) {
            panel.add(checkBoxes.get(objectId));
        }
    }


    /**
     * Configures the frame with default variables
     */
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
     * @return A new button */
    @Override
    public JButton createButton(String withText, int width, int height, ActionListener actionListener) {
        JButton button = new JButton();
        button.setText(withText);
        button.setPreferredSize(new Dimension(width, height));
        button.addActionListener(actionListener);

        return button;
    }



    /**
     * Global process method for catching all button events.
     * Can run common methods on all actions.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

    }
}
