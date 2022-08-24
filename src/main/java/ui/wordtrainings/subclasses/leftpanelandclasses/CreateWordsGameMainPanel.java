package ui.wordtrainings.subclasses.leftpanelandclasses;

import databases.Reader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class CreateWordsGameMainPanel extends JPanel {

    public CreateWordsGameMainPanel() {
        this.setLayout(new GridLayout(10,1));
        this.setBorder(new EmptyBorder(5,0,5,0));
    }

    private void createButtonsWithDrawnWords(String filePath) throws Exception {
        ArrayList<ArrayList<String>> finalDrawnWordsList = this.getDrawnWords(filePath);
        int properAnswerIndex = (int) (Math.random() * 10);

    }

    public ArrayList<ArrayList<String>> getDrawnWords(String filePath) throws Exception {
        Reader reader = new Reader();
        ArrayList<ArrayList<String>> wordsList = reader.getDataList(filePath);
        ArrayList<ArrayList<String>> finalDrawnWordsList = new ArrayList<ArrayList<String>>();
        ArrayList<String> drawnWord;

        for (int iterator = 0; iterator < 10; iterator++) {
            do {
                drawnWord = wordsList.get((int) (Math.random() * wordsList.size()));
            } while (finalDrawnWordsList.contains(drawnWord));
            finalDrawnWordsList.add(drawnWord);
        }

        return finalDrawnWordsList;
    }
}
