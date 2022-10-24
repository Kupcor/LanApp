package ui.wordtrainings.subclasses.wordinformationwindow;

import ui.templates.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class WordInformationWindow extends Window {

    public WordInformationWindow(ArrayList<String> wordInformationList) {
        super(500,100);
        assert wordInformationList != null : "The word information was not provided.";

        this.setTitle("\"" + wordInformationList.get(0) + "\"" + " info");
        this.createWordFlashcard(wordInformationList);
    }

    private void createWordFlashcard(ArrayList<String> wordInformationList) {
        String[] captions = {"English word:", "Translated into polish:", "Example:", "Additional info:"};

        JPanel wordInformationPanel = new JPanel(new GridLayout(wordInformationList.size(),1));
        for (int iterator = 0; iterator < wordInformationList.size(); iterator++){
            JPanel tempPanelForCaptionAndWordInformation = new JPanel(new BorderLayout());

            JLabel captionLabel = new JLabel(captions[iterator]);
            JLabel singleInformationLabel = new JLabel(wordInformationList.get(iterator));

            captionLabel.setFont(new Font("Serif", Font.BOLD, 14));
            singleInformationLabel.setFont(new Font("Serif", Font.BOLD, 14));

            captionLabel.setBorder(new EmptyBorder(5,5,5,5));
            singleInformationLabel.setBorder(new EmptyBorder(5,5,5,5));

            tempPanelForCaptionAndWordInformation.add(captionLabel, BorderLayout.LINE_START);
            tempPanelForCaptionAndWordInformation.add(singleInformationLabel, BorderLayout.LINE_END);

            wordInformationPanel.add(tempPanelForCaptionAndWordInformation);
        }
        this.add(wordInformationPanel);
    }
}