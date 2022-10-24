package ui.flashcardswindow;

import ui.flashcardswindow.subclasses.Flashcard;
import ui.flashcardswindow.subclasses.FlashcardNavPanelMenu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FlashCardsMainPanel extends JPanel implements MouseListener {

    private Flashcard flashcard;
    private final FlashcardNavPanelMenu flashcardNavPanelMenu = new FlashcardNavPanelMenu();

    public FlashCardsMainPanel() throws Exception {
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10,10,10,10));

        this.flashcard = new Flashcard(this, (String) this.flashcardNavPanelMenu.getFlashCardLearningOptionComboBox().getSelectedItem());

        this.setUpFlashCardMainPanel();

        this.flashcardNavPanelMenu.getFlashCardLearningOptionComboBox().addActionListener(e -> {
            try {
                this.flashcard = new Flashcard(this, (String) this.flashcardNavPanelMenu.getFlashCardLearningOptionComboBox().getSelectedItem());
                this.setUpFlashCardMainPanel();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void setUpFlashCardMainPanel() {
        this.removeAll();
        this.add(this.flashcardNavPanelMenu, BorderLayout.PAGE_START);
        this.add(this.flashcard, BorderLayout.CENTER);
        this.revalidate();
        this.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.flashcard.getFlashcardsActionButtonsPanel().getShowAnswerButton()) {
            this.flashcard.showAnswer();
        }

        if (e.getSource() == this.flashcard.getFlashcardsActionButtonsPanel().getWrongAnswerButton()) {
            try {
                this.flashcard.newQuestionSetUp( (String) this.flashcardNavPanelMenu.getFlashCardLearningOptionComboBox().getSelectedItem());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == this.flashcard.getFlashcardsActionButtonsPanel().getCorrectAnswerButton()) {
            try {
                this.flashcard.newQuestionSetUp((String) this.flashcardNavPanelMenu.getFlashCardLearningOptionComboBox().getSelectedItem());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
