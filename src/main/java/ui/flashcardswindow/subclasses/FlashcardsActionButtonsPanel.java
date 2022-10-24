package ui.flashcardswindow.subclasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class FlashcardsActionButtonsPanel extends JPanel {

    private final JButton correctAnswerButton = new JButton("Correct");
    private final JButton wrongAnswerButton = new JButton("Wrong");
    private final JButton showAnswerButton = new JButton("ShowAnswer");
    private boolean isNowShowAnswer = false;

    public FlashcardsActionButtonsPanel(MouseListener mouseListener) {
        this.setLayout(new GridLayout(1,2));
        this.showAnswerButton.addMouseListener(mouseListener);
        this.correctAnswerButton.addMouseListener(mouseListener);
        this.wrongAnswerButton.addMouseListener(mouseListener);
        this.add(showAnswerButton);
    }

    public void changeButtonsStatus() {
        this.removeAll();
        if (this.isNowShowAnswer) {
            this.add(this.wrongAnswerButton);
            this.add(this.correctAnswerButton);
        } else {
            this.add(this.showAnswerButton);
        }
        this.isNowShowAnswer = !this.isNowShowAnswer;
        this.revalidate();
        this.repaint();
    }

    public JButton getWrongAnswerButton() {
        return this.wrongAnswerButton;
    }

    public JButton getCorrectAnswerButton() {
        return this.correctAnswerButton;
    }

    public JButton getShowAnswerButton() {
        return this.showAnswerButton;
    }
}
