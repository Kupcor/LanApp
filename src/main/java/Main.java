import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLightLaf;
import ui.MainUIContainer;
import ui.wordtrainings.RememberingWordsTraining;
import ui.wordtrainings.subclasses.leftpanelandclasses.CreateWordsGameMainPanel;

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
