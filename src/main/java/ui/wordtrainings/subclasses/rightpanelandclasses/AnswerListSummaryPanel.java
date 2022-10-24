package ui.wordtrainings.subclasses.rightpanelandclasses;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class AnswerListSummaryPanel extends JPanel {
    private final int maxAmountOfAnswersInOneSummaryPanel = 20;
    private int amountOfAnswersInOneSummaryPanel = 0;
    private int currentSummaryPanelIndex = 0;
    private final ArrayList<JPanel> summaryPanelList = new ArrayList<>();
    private final ArrayList<ArrayList<String>> listOfAnswers = new ArrayList<>();
    private final ArrayList<JPanel> listOfPanelAnswer = new ArrayList<>();
    private final BottomNavigationPanel bottomNavigationPanel;

    public AnswerListSummaryPanel(MouseListener mouseListener) {
        JPanel oneSummaryPanel = new JPanel(new GridLayout(this.maxAmountOfAnswersInOneSummaryPanel, 1));
        oneSummaryPanel.setBorder(new EmptyBorder(0,0,10,0));
        this.bottomNavigationPanel = new BottomNavigationPanel(mouseListener);
        this.setLayout(new BorderLayout());
        this.summaryPanelList.add(oneSummaryPanel);
        this.add(summaryPanelList.get(this.currentSummaryPanelIndex), BorderLayout.CENTER);
    }

    public void addSummaryOfYourAnswer(ArrayList<String> correctAnswerInformation, Boolean evaluation, MouseListener mouseListener) {
        if (this.amountOfAnswersInOneSummaryPanel > maxAmountOfAnswersInOneSummaryPanel - 1) {
            if (this.summaryPanelList.size() == 1) this.createBottomNavigationPanel();
            this.addAnotherWordsListSummaryPanel();
            this.amountOfAnswersInOneSummaryPanel = 0;
        }

        JLabel currentEnglishWord = new JLabel(correctAnswerInformation.get(0));
        JLabel currentPolishWord = new JLabel(correctAnswerInformation.get(1));
        JPanel tempContainerForWordsPair = new JPanel(new GridLayout(1,2));
        this.amountOfAnswersInOneSummaryPanel++;

        if (evaluation) {
            currentEnglishWord.setForeground(Color.GREEN);
            currentPolishWord.setForeground(Color.GREEN);
        } else {
            currentEnglishWord.setForeground(Color.RED);
            currentPolishWord.setForeground(Color.RED);
        }

        tempContainerForWordsPair.add(currentEnglishWord);
        tempContainerForWordsPair.add(currentPolishWord);
        tempContainerForWordsPair.addMouseListener(mouseListener);

        this.summaryPanelList.get(this.currentSummaryPanelIndex).add(tempContainerForWordsPair);
        this.listOfAnswers.add(correctAnswerInformation);
        this.listOfPanelAnswer.add(tempContainerForWordsPair);
    }

    private void createBottomNavigationPanel() {
        this.add(bottomNavigationPanel, BorderLayout.PAGE_END);
        bottomNavigationPanel.initialize();
    }

    private void addAnotherWordsListSummaryPanel() {
        JPanel oneSummaryPanel = new JPanel(new GridLayout(this.maxAmountOfAnswersInOneSummaryPanel, 1));
        oneSummaryPanel.setBorder(new EmptyBorder(0,0,10,0));
        this.remove(this.summaryPanelList.get(this.bottomNavigationPanel.getWordsTranslatedListPanelNumber()));
        this.summaryPanelList.add(oneSummaryPanel);

        this.currentSummaryPanelIndex++;
        this.bottomNavigationPanel.setWordsTranslatedListPanelNumberLabel(this.currentSummaryPanelIndex);

        this.add(summaryPanelList.get(this.currentSummaryPanelIndex));
        this.revalidate();
        this.repaint();
    }

    public void nextPreviousWordsListSummaryPanel(JButton button) {
        this.remove(this.summaryPanelList.get(this.bottomNavigationPanel.getWordsTranslatedListPanelNumber()));
        this.bottomNavigationPanel.updateTranslatedListPanelNumberLabel(button,this.summaryPanelList.size());

        this.add(this.summaryPanelList.get(this.bottomNavigationPanel.getWordsTranslatedListPanelNumber()));
        this.revalidate();
        this.repaint();
    }

    public JButton getCreateBottomNavigationPanelNextButton() {
        return this.bottomNavigationPanel.getNextButton();
    }

    public JButton getCreateBottomNavigationPanelPreviousButton() {
        return this.bottomNavigationPanel.getPreviousButton();
    }

    public ArrayList<ArrayList<String>> getListOfAnswers() {
        return listOfAnswers;
    }

    public ArrayList<JPanel> getListOfPanelAnswer() {
        return listOfPanelAnswer;
    }
}
