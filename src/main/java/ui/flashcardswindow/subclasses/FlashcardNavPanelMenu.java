package ui.flashcardswindow.subclasses;

import databases.Reader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FlashcardNavPanelMenu extends JPanel {
    private final Reader reader = new Reader();
    private final String[] flashCardLearningOption = this.reader.readTxtFile("src\\main\\java\\databases\\DataBases.txt");
    private final JComboBox flashCardLearningOptionComboBox = new  JComboBox(this.flashCardLearningOption);

    public FlashcardNavPanelMenu() {
        this.setLayout(new GridLayout(1, 5));
        JLabel flashCardsListLabel = new JLabel("Flash cards list: ");
        flashCardsListLabel.setBorder(new EmptyBorder(0, 10, 0 ,0));
        flashCardsListLabel.setFont(new Font("Serif", Font.BOLD, 12));
        this.setBorder(new EmptyBorder(10, 0, 10, 0));

        this.add(flashCardsListLabel);
        this.add(flashCardLearningOptionComboBox);
        for (int i = 0; i < 3; i++) { this.add(new JLabel());}

    }

    public JComboBox getFlashCardLearningOptionComboBox() {
        return this.flashCardLearningOptionComboBox;
    }
}
