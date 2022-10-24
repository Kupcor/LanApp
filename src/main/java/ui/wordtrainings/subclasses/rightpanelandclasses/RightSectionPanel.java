package ui.wordtrainings.subclasses.rightpanelandclasses;

import javax.swing.*;
import java.awt.*;

public class RightSectionPanel extends JPanel {

    public RightSectionPanel() {
        this.setLayout(new GridLayout(1,1));

        JLabel yourAnswersCaption = new JLabel("Your answers", SwingConstants.CENTER);
        yourAnswersCaption.setFont(new Font("Serif", Font.BOLD, 16));
        this.add(yourAnswersCaption);
    }
}
