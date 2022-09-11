package ui.wordtrainings.subclasses.leftpanelandclasses;

import javax.swing.*;
import java.awt.*;

public class CreateCorrectWordPanel extends JPanel {

    public CreateCorrectWordPanel(String correctAnswer) {
        this.setLayout(new GridLayout(1,1));
        this.updateCorrectAnswerLabel(correctAnswer);
    }

    public void updateCorrectAnswerLabel(String correctAnswer) {
        this.removeAll();
        JLabel correctAnswerLabel = new JLabel(correctAnswer, SwingConstants.CENTER);
        correctAnswerLabel.setFont(new Font("Serif", Font.BOLD, 16));
        this.add(correctAnswerLabel);
        this.revalidate();
        this.repaint();
    }
}
