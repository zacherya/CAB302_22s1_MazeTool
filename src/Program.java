import Controllers.WelcomeViewController;

import javax.swing.*;

/** The Program Class*/
public class Program {

    /** main method
     * @param args String array*/
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new WelcomeViewController());
    }

}