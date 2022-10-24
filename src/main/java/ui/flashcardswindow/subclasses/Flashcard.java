package ui.flashcardswindow.subclasses;

import databases.Reader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class Flashcard extends JPanel {

    private final Random random = new Random();
    private int flashCardIndex;
    private ArrayList<ArrayList<String>> conceptsList;

    private final Reader reader = new Reader();

    private final JPanel questionPanel = new JPanel(new GridLayout(1, 1));

    private final JPanel answerPanel = new JPanel(new GridLayout(3, 1));
    private final FlashcardsActionButtonsPanel flashcardsActionButtonsPanel;

    public Flashcard(MouseListener mouseListener, String dictionary) throws Exception {
        this.setLayout(new BorderLayout());
        this.answerPanel.setBorder(new EmptyBorder(10, 0, 10, 10));
        this.flashcardsActionButtonsPanel = new FlashcardsActionButtonsPanel(mouseListener);
        this.newQuestionSetUp(dictionary);
    }

    public void showAnswer() {
        this.removeAll();
        this.add(this.answerPanel, BorderLayout.CENTER);
        this.add(this.flashcardsActionButtonsPanel, BorderLayout.PAGE_END);
        this.flashcardsActionButtonsPanel.changeButtonsStatus();
        this.revalidate();
        this.repaint();
    }

    public void newQuestionSetUp(String dictionary) throws Exception {
        this.removeAll();
        this.questionPanel.removeAll();
        this.answerPanel.removeAll();

        System.out.println();

        this.conceptsList = reader.getDataList("src\\main\\java\\databases\\data\\" + dictionary);
        this.flashCardIndex = random.nextInt(this.conceptsList.size());

        JLabel questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Serif", Font.BOLD, 16));
        questionLabel.setText(this.conceptsList.get(flashCardIndex).get(0));
        this.questionPanel.add(questionLabel);

        this.setUpAnswerPanel(this.conceptsList.get(flashCardIndex));

        this.flashcardsActionButtonsPanel.changeButtonsStatus();
        this.add(this.questionPanel, BorderLayout.CENTER);
        this.add(this.flashcardsActionButtonsPanel, BorderLayout.PAGE_END);
        this.revalidate();
        this.repaint();
    }

    public FlashcardsActionButtonsPanel getFlashcardsActionButtonsPanel() {
        return this.flashcardsActionButtonsPanel;
    }

    private void setUpAnswerPanel(ArrayList<String> conceptInformationList) {
        String[] captions = {"Question", "Answer", "Example"};
        for (int iterator = 0; iterator < 3; iterator++) {
            if (conceptInformationList.get(iterator).equals("-")) {break;}
            JPanel tempContainer = new JPanel(new BorderLayout());
            JTextArea label = new JTextArea(captions[iterator]);
            label.setFont(new Font("Serif", Font.BOLD, 12));
            label.setBorder(new MatteBorder(4, 0, 0, 0, Color.decode("#525861")));
            label.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 0, 0), new EtchedBorder()));
            label.setLineWrap(true);
            label.setEditable(false);
            tempContainer.add(label, BorderLayout.PAGE_START);

            JTextArea label2 = new JTextArea(conceptInformationList.get(iterator));
            label2.setFont(new Font("Serif", Font.BOLD, 16));
            label2.setBorder(new MatteBorder(4, 0, 0, 0, Color.decode("#525861")));
            label2.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 0, 0), new EtchedBorder()));
            label2.setLineWrap(true);
            label2.setEditable(false);
            tempContainer.add(label2, BorderLayout.CENTER);
            this.answerPanel.add(tempContainer);
        }
    }
}

