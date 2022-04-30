package Views;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Function;

public interface IView {
    public void configureFrame();

    public void readyFrame(Runnable callback);

    public JButton createButton(String withText, int width, int height, ActionListener actionListener);
    public void actionPerformed(ActionEvent e);

}
