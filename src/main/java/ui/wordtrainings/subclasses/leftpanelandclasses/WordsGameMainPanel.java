package ui.wordtrainings.subclasses.leftpanelandclasses;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class WordsGameMainPanel extends JPanel {
    private final ArrayList<JButton> listOfButtonsWithDrawnWords = new ArrayList<>();
    private ArrayList<String> correctAnswerInformation;

    public WordsGameMainPanel(MouseListener mouseListener, ArrayList<ArrayList<String>> drawnWordList){
        this.setLayout(new GridLayout(10,1));
        this.setBorder(new EmptyBorder(5,0,5,0));
        this.createButtonsWithDrawnWords(mouseListener, drawnWordList);
    }

    public void createButtonsWithDrawnWords(MouseListener mouseListener, ArrayList<ArrayList<String>> drawnWordList){
        this.removeAll();
        this.listOfButtonsWithDrawnWords.clear();
        for (int iterator = 0; iterator < 10; iterator++) {
            JButton wordButton = new JButton(drawnWordList.get(iterator).get(0));
            wordButton.addMouseListener(mouseListener);

            this.add(wordButton);
            this.listOfButtonsWithDrawnWords.add(wordButton);
        }
        this.setCorrectAnswerInformation(drawnWordList);
    }

    public void setCorrectAnswerInformation(ArrayList<ArrayList<String>> drawnWordsList) {
        this.correctAnswerInformation = drawnWordsList.get((int) (Math.random() * 10));
    }

    public ArrayList<JButton> getListOfButtonsWithDrawnWords() {
        return listOfButtonsWithDrawnWords;
    }

    public ArrayList<String> getCorrectAnswer() {
        return this.correctAnswerInformation;
    }
}
