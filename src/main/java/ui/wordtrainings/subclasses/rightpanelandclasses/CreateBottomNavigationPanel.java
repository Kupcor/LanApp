package ui.wordtrainings.subclasses.rightpanelandclasses;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class CreateBottomNavigationPanel extends JPanel {
    private int wordsTranslatedListPanelNumber;
    private final JLabel wordsTranslatedListPanelNumberLabel;
    private final JButton previousButton = new JButton("<html><center>Previous</center><html>");
    private final JButton nextButton = new JButton("<html><center>Next</center><html>");

    public CreateBottomNavigationPanel(MouseListener mouseListener) {
        this.setLayout(new GridLayout(1,3));
        this.wordsTranslatedListPanelNumber = 0;
        this.wordsTranslatedListPanelNumberLabel = new JLabel(String.valueOf(this.wordsTranslatedListPanelNumber + 1), SwingConstants.CENTER);
        this.previousButton.addMouseListener(mouseListener);
        this.nextButton.addMouseListener(mouseListener);
    }

    public void initialize() {
        this.add(this.previousButton);
        this.add(this.wordsTranslatedListPanelNumberLabel);
        this.add(this.nextButton);
    }

    public void updateTranslatedListPanelNumberLabel(JButton button, int length) {
        if (button == this.nextButton) {
            if (this.wordsTranslatedListPanelNumber + 1 > length - 1) this.wordsTranslatedListPanelNumber = 0;
            else this.wordsTranslatedListPanelNumber++;
        }
        else {
            if (this.wordsTranslatedListPanelNumber - 1 < 0) this.wordsTranslatedListPanelNumber = length - 1;
            else this.wordsTranslatedListPanelNumber--;
        }
        this.wordsTranslatedListPanelNumberLabel.setText(String.valueOf(this.wordsTranslatedListPanelNumber+1));
        this.revalidate();
        this.repaint();
    }

    public JButton getPreviousButton() {
        return previousButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }

    public int getWordsTranslatedListPanelNumber() {
        return wordsTranslatedListPanelNumber;
    }
}
