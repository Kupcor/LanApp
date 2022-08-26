package ui.wordtrainings.subclasses.leftpanelandclasses;

import databases.Reader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CreateWordsGameMainPanel extends JPanel {

    public CreateWordsGameMainPanel() {
        this.setLayout(new GridLayout(10,1));
        this.setBorder(new EmptyBorder(5,0,5,0));
    }

    public ArrayList<JButton> createButtonsWithDrawnWords(String filePath, MouseListener mouseListener, ArrayList<ArrayList<String>> drawnWordList) throws Exception {
        this.removeAll();
        ArrayList<JButton> listOfButtonsWithDrawnWords = new ArrayList<JButton>();


        for (int iterator = 0; iterator < 10; iterator++) {
            JButton wordButton = new JButton(drawnWordList.get(iterator).get(0));
            wordButton.addMouseListener(mouseListener);

            this.add(wordButton);
            listOfButtonsWithDrawnWords.add(wordButton);
        }

        return listOfButtonsWithDrawnWords;
    }
}
