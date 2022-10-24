import com.formdev.flatlaf.FlatDarculaLaf;
import ui.MainUIContainer;

import javax.swing.*;

public class Main {

    static public void main(String[] args) throws Exception {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch(Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new MainUIContainer(1000, 500);
    }
}
