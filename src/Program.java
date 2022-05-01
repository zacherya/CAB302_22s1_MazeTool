import Controllers.WelcomeViewController;

import javax.swing.*;

/** The Program Class - MazeTool Entry Point */
public class Program {

    /** Main entry point for the program.
     * @param args Arguments passed into the runtime environment */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new WelcomeViewController());
    }

}