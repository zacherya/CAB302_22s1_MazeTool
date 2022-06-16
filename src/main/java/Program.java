import Controllers.WelcomeViewController;

import javax.swing.*;

/** The Program Class - MazeTool Entry Point */
public class Program {

    /** Main entry point for the program.
     * @param args Arguments passed into the runtime environment */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        SwingUtilities.invokeLater(new WelcomeViewController());
    }

}