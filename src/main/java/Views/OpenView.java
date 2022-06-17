package Views;

import Controllers.OpenViewController;
import Controllers.WelcomeViewController;
import DataAccess.DtoModels.MazeDto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.List;

public class OpenView extends DefaultView<OpenViewController> {

    JTable table;
    DefaultTableModel tableModel;
    ArrayList<MazeDto> mazes;
    /**
     * Initialises the configuration and objects for the Open View frame.
     * @param controller
     * @author Zac Adams
     */
    public OpenView(OpenViewController controller) {
        super("Open Maze", 750, 450);

        _controller = controller;

        readyFrame(this::addFrameElements);
    }

    /**
     * Adds and configures the elements for the view's frame.
     * @author Zac Adams
     * TODO: 1/05/2022 Refactor and implement this method properly (Make GUI relevant).
     */
    private void addFrameElements() {
        // Create panels
        panels.put("topPanel", new JPanel());
        panels.put("middlePanel", new JPanel());
        panels.put("bottomPanel", new JPanel());

        buttons.put("backBtn",createButton("←", 55, 30, this::backBtnAction));

        definePanelGrid("topPanel", 1,3,0,0);
        definePanelGrid("middlePanel", 1,1,0,0);

        String[] columnNames = { "Maze Name", "Author" };
        mazes = _controller.getMazes();
        if(mazes.size() == 0) {
            panels.get("middlePanel").add(new JLabel("There are no mazes in the database"));
        } else {
            tableModel = new DefaultTableModel(columnNames, mazes.size());
            tableModel.removeRow(0);
            for (MazeDto maze: mazes) {
                tableModel.addRow(new Object[] {maze.GetMazeName(), maze.GetMazeAuthor()});
            }
            table = new JTable(tableModel){
                public boolean isCellEditable(int row, int column) {
                    return false;
                };
            };

            table.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent mouseEvent) {
                    JTable table =(JTable) mouseEvent.getSource();
                    Point point = mouseEvent.getPoint();
                    int row = table.rowAtPoint(point);
                    if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                        // your valueChanged overridden method
                        openMaze(row);
                    }
                }
            });


            JScrollPane sp = new JScrollPane(table);
            panels.get("middlePanel").add(sp);
        }


        addButtonsToPanel(panels.get("topPanel"), "backBtn");


        getContentPane().add(panels.get("topPanel"), BorderLayout.PAGE_START);
        getContentPane().add(panels.get("middlePanel"), BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    /**
     * Returns the user to the welcome view.
     * @param actionEvent the button to listen for an action on
     * @author Alex Hannah, Aaron Nolan
     */
    private void backBtnAction(ActionEvent actionEvent) {
        System.out.println("Back button clicked");
        _controller.disposeView();
        WelcomeViewController newView = new WelcomeViewController();
    }

    private void openMaze(int row) {
        System.out.println(mazes.get(row).GetMazeName() + " clicked");

    }
}
