import javax.swing.*;
import java.awt.*;

public class Program extends JFrame implements Runnable {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Program());
    }

    @Override
    public void run() {
        // Terminate the program if the user closes the main frame
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Maze Designer Tool");
        // Add components to the frame
        try {
            this.getContentPane().add(new JButton("Hey"));
        } catch (Exception e) {
            //catch can never be reached here;
            //Exception only if *this* is null
            e.printStackTrace();
        }
        // Resize the main frame to fit its components
        this.pack();
        // Make the simulation visible
        this.setVisible(true);
    }
}
