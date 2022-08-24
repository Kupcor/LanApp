package ui;

import databases.Reader;
import ui.wordtrainings.subclasses.wordinformationwindow.CreateWordInformationWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

public class CopyOfRememberingWordsTraining extends JPanel implements MouseListener {
    private final ArrayList<ArrayList<String>> listOfDrawnWords = new ArrayList<ArrayList<String>>();
    private final ArrayList<JButton> listOfButtonsWithDrawnWords = new ArrayList<JButton>();

    private final ArrayList<JPanel> listOfPanelsAnswerSummary = new ArrayList<JPanel>();
    private final ArrayList<ArrayList<String>> listOfAnswer = new ArrayList<ArrayList<String>>();

    private int currentCorrectAnswerIndex;
    private int amountOfGuessWords = 0;
    private int amountOfCorrectAnswer = 0;
    private int amountOfWrongAnswer = 0;

    private final int amountOfWordsInSummaryList = 20;
    private int wordsInSummaryListCount = 0;
    private int currentWordsListPanelIndex;
    private int visibleWordsListPanelIndex;

    private boolean nextPreviousButtonVisibility = false;

    private ArrayList<JPanel> wordsListPanelContainer = new ArrayList<JPanel>();

    //  HERE
    private final JPanel leftSectionPanel = new JPanel(new BorderLayout());
    private final JPanel rightSidePanel = new JPanel(new BorderLayout());

    private final JPanel wordsGameMainPanel = new JPanel(new GridLayout(10,1 ));
    private final JPanel guessWordPanel = new JPanel(new GridLayout(1,1));
    private final JPanel resultsAndChangeWordsOptionPanel = new JPanel(new BorderLayout());

    private final JPanel resultsWordsOptionPanel = new JPanel(new GridLayout(1, 3));

    private JPanel translatedWordListPanel = new JPanel(new GridLayout(this.amountOfWordsInSummaryList,2));
    private final JPanel infoRightSectionPanel = new JPanel(new GridLayout(1,1));
    private final JPanel bottomNavigationRightSectionPanel = new JPanel(new GridLayout(1,3));

    private final JLabel correctAnswerLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel amountOfGuessWordsLabel = new JLabel(String.valueOf(this.amountOfGuessWords), SwingConstants.CENTER);
    private final JLabel amountOfCorrectAnswerLabel = new JLabel(String.valueOf(this.amountOfCorrectAnswer), SwingConstants.CENTER);
    private final JLabel amountOfWrongAnswerLabel = new JLabel(String.valueOf(this.amountOfWrongAnswer), SwingConstants.CENTER);
    private final JLabel wordsTranslatedListPanelNumberLabel = new JLabel("Current page: " + (this.visibleWordsListPanelIndex + 1), SwingConstants.CENTER);

    private final JButton rightPanelNextButton = new JButton("<html><center>Next</center><html>");
    private final JButton rightPanelPreviousButton = new JButton("<html><center>Previous</center><html>");

    private final Reader reader = new Reader();
    private final String[] wordsLearningOptions = this.reader.readTxtFile("src\\main\\java\\databases\\DataBases.txt");
    private final JComboBox wordsLearningOptionsComboBox = new JComboBox(this.wordsLearningOptions);
    private String filePath = "src\\main\\java\\databases\\data\\VerbsDataBase.csv";

    public CopyOfRememberingWordsTraining() throws Exception {
        this.setSize(new Dimension(800,500));
        this.setLayout(new GridLayout(1,2));

        this.correctAnswerLabel.setFont(new Font("Serif", Font.BOLD, 16));

        this.createButtonsWithDrawnWords();
        this.addObjectsToResultAndChangeWordsOptionPanel();

        //  HERE
        this.guessWordPanel.add(this.correctAnswerLabel);

        this.rightSidePanel.setBorder(new EmptyBorder(5,10,5,10));

        //  HERE
        this.leftSectionPanel.setBorder(new EmptyBorder(5,20,5,10));


        JLabel yourAnswersCaption = new JLabel("Your answers", SwingConstants.CENTER);
        yourAnswersCaption.setFont(new Font("Serif", Font.BOLD, 16));
        this.infoRightSectionPanel.add(yourAnswersCaption);

        this.rightPanelPreviousButton.addMouseListener(this);
        this.rightPanelNextButton.addMouseListener(this);

        this.wordsGameMainPanel.setBorder(new EmptyBorder(5,0,5,0));
        this.translatedWordListPanel.setBorder(new EmptyBorder(5,0,10,0));

        //  HERE
        this.leftSectionPanel.add(this.guessWordPanel, BorderLayout.PAGE_START);
        this.leftSectionPanel.add(this.wordsGameMainPanel, BorderLayout.CENTER);
        this.leftSectionPanel.add(resultsAndChangeWordsOptionPanel, BorderLayout.PAGE_END);

        this.wordsListPanelContainer.add(this.translatedWordListPanel);
        this.rightSidePanel.add(this.wordsListPanelContainer.get(this.currentWordsListPanelIndex), BorderLayout.CENTER);
        this.rightSidePanel.add(infoRightSectionPanel, BorderLayout.PAGE_START);
        this.rightSidePanel.add(this.bottomNavigationRightSectionPanel, BorderLayout.PAGE_END);

        this.wordsLearningOptionsComboBox.addActionListener(e -> {
            if ("VerbsDataBase.csv".equals(this.wordsLearningOptionsComboBox.getSelectedItem())) {
                this.filePath = "src\\main\\java\\databases\\data\\VerbsDataBase.csv";
            } else if ("NounsDataBase.csv".equals(this.wordsLearningOptionsComboBox.getSelectedItem())) {
                this.filePath = "src\\main\\java\\databases\\data\\NounsDataBase.csv";
            } else if ("AdjectivesDataBase.csv".equals(this.wordsLearningOptionsComboBox.getSelectedItem())) {
                this.filePath = "src\\main\\java\\databases\\data\\AdjectivesDataBase.csv";
            }
            createNextRound();
        });

        this.add(this.leftSectionPanel);
        this.add(this.rightSidePanel);
    }

    //  Main section - word game functions (left Panel)
    public void createButtonsWithDrawnWords() throws Exception {
        int properAnswerIndex = (int) (Math.random() * 10);
        this.listOfButtonsWithDrawnWords.clear();
        this.getDrawnWords();

        for (int iterator = 0; iterator < 10; iterator++) {
            JButton wordButton = new JButton(this.listOfDrawnWords.get(iterator).get(0));
            wordButton.addMouseListener(this);

            this.wordsGameMainPanel.add(wordButton);
            this.listOfButtonsWithDrawnWords.add(wordButton);

            if (iterator == properAnswerIndex) {
                this.currentCorrectAnswerIndex = iterator;
                this.setCorrectAnswerLabel(iterator);
            }
        }
    }

    private void getDrawnWords() throws Exception {
        this.listOfDrawnWords.clear();
        Reader reader = new Reader();
        ArrayList<ArrayList<String>> wordsList = reader.getDataList(this.filePath);
        ArrayList<String> drawnWord;

        for (int iterator = 0; iterator < 10; iterator++) {
            do {
                drawnWord = wordsList.get((int) (Math.random() * wordsList.size()));
            } while (this.listOfDrawnWords.contains(drawnWord));
            this.listOfDrawnWords.add(drawnWord);
        }
    }

    private void setCorrectAnswerLabel(int index) {
        this.correctAnswerLabel.setText(this.listOfDrawnWords.get(index).get(1));
        this.guessWordPanel.revalidate();
        this.guessWordPanel.repaint();
    }

    private boolean evaluateAnswer(JButton button) {
        return Objects.equals(button.getText(), this.listOfDrawnWords.get(this.currentCorrectAnswerIndex).get(0));
    }

    private void createNextRound() {
        this.wordsGameMainPanel.removeAll();
        try {
            this.createButtonsWithDrawnWords();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        this.wordsGameMainPanel.revalidate();
        this.wordsGameMainPanel.repaint();
    }

    //  Right side panel
    private void addSummaryOfYourAnswer(JButton button) {
        JLabel currentEnglishWord = new JLabel(this.listOfDrawnWords.get(this.currentCorrectAnswerIndex).get(0));
        JLabel currentPolishWord = new JLabel(this.listOfDrawnWords.get(this.currentCorrectAnswerIndex).get(1));
        JPanel tempContainerForWordsPair = new JPanel(new GridLayout(1,2));
        this.amountOfGuessWords++;
        this.wordsInSummaryListCount++;

        if (!this.nextPreviousButtonVisibility && this.amountOfGuessWords > this.amountOfWordsInSummaryList) this.addNextPreviousButtons();

        if (this.evaluateAnswer(button)) {
            currentEnglishWord.setForeground(Color.GREEN);
            currentPolishWord.setForeground(Color.GREEN);
            this.amountOfCorrectAnswer++;
        } else {
            currentEnglishWord.setForeground(Color.RED);
            currentPolishWord.setForeground(Color.RED);
            this.amountOfWrongAnswer++;
        }

        if (this.wordsInSummaryListCount > this.amountOfWordsInSummaryList) {
            this.wordsInSummaryListCount = 1;
            this.addAnotherWordsListSummaryPanel();
        }

        tempContainerForWordsPair.add(currentEnglishWord);
        tempContainerForWordsPair.add(currentPolishWord);
        tempContainerForWordsPair.addMouseListener(this);


        this.listOfPanelsAnswerSummary.add(tempContainerForWordsPair);
        this.listOfAnswer.add(this.listOfDrawnWords.get(this.currentCorrectAnswerIndex));


        this.wordsListPanelContainer.get(this.currentWordsListPanelIndex).add(tempContainerForWordsPair);
        this.wordsListPanelContainer.get(this.currentWordsListPanelIndex).revalidate();
        this.wordsListPanelContainer.get(this.currentWordsListPanelIndex).repaint();

        this.repaintStatistic();
    }

    private void addAnotherWordsListSummaryPanel() {
        this.translatedWordListPanel = new JPanel(new GridLayout(this.amountOfWordsInSummaryList,2));
        this.wordsListPanelContainer.add(this.translatedWordListPanel);
        this.rightSidePanel.remove(this.wordsListPanelContainer.get(this.currentWordsListPanelIndex));

        this.currentWordsListPanelIndex++;
        this.visibleWordsListPanelIndex++;

        this.updateWordsTranslatedListPanelNumberLabel();
        this.rightSidePanel.add(this.wordsListPanelContainer.get(this.currentWordsListPanelIndex));
        this.rightSidePanel.revalidate();
        this.rightSidePanel.repaint();
    }

    private void nextWordsListSummaryPanel() {
        this.rightSidePanel.remove(this.wordsListPanelContainer.get(this.visibleWordsListPanelIndex));
        this.visibleWordsListPanelIndex++;
        if (this.visibleWordsListPanelIndex > this.wordsListPanelContainer.size() - 1) this.visibleWordsListPanelIndex = 0;
        this.rightSidePanel.add(this.wordsListPanelContainer.get(this.visibleWordsListPanelIndex));
        this.updateWordsTranslatedListPanelNumberLabel();
        this.rightSidePanel.revalidate();
        this.rightSidePanel.repaint();
    }

    private void previousWordsListSummaryPanel() {
        this.rightSidePanel.remove(this.wordsListPanelContainer.get(this.visibleWordsListPanelIndex));
        this.visibleWordsListPanelIndex--;
        if (this.visibleWordsListPanelIndex < 0) this.visibleWordsListPanelIndex = this.wordsListPanelContainer.size() - 1;
        this.rightSidePanel.add(this.wordsListPanelContainer.get(this.visibleWordsListPanelIndex));
        this.updateWordsTranslatedListPanelNumberLabel();
        this.rightSidePanel.revalidate();
        this.rightSidePanel.repaint();
    }

    private void repaintStatistic() {
        this.amountOfWrongAnswerLabel.setText(String.valueOf(this.amountOfWrongAnswer));
        this.amountOfCorrectAnswerLabel.setText(String.valueOf(this.amountOfCorrectAnswer));
        this.amountOfGuessWordsLabel.setText(String.valueOf(this.amountOfGuessWords));
        this.resultsAndChangeWordsOptionPanel.revalidate();
        this.resultsAndChangeWordsOptionPanel.repaint();
    }

    private void addObjectsToResultAndChangeWordsOptionPanel() {
        this.resultsAndChangeWordsOptionPanel.add(this.wordsLearningOptionsComboBox, BorderLayout.PAGE_START);
        String[] statisticsCaptions = {"<html><center>Total attempts</center><html>", "Correct answers", "Wrong answers"};
        JLabel[] statistics = {this.amountOfGuessWordsLabel, this.amountOfCorrectAnswerLabel, this.amountOfWrongAnswerLabel};

        for (int iterator = 0; iterator < 3; iterator++) {
            JPanel tempPanel = new JPanel(new GridLayout(2,1));
            JLabel label = new JLabel(statisticsCaptions[iterator], SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 12));
            tempPanel.add(label);
            tempPanel.add(statistics[iterator]);
            this.resultsWordsOptionPanel.add(tempPanel);
        }
        this.resultsWordsOptionPanel.setBorder(new EmptyBorder(5,0,0,0));
        this.resultsAndChangeWordsOptionPanel.add(this.resultsWordsOptionPanel, BorderLayout.PAGE_END);
    }

    private void addNextPreviousButtons() {
        this.nextPreviousButtonVisibility = true;
        this.bottomNavigationRightSectionPanel.add(this.rightPanelPreviousButton);
        this.bottomNavigationRightSectionPanel.add(this.wordsTranslatedListPanelNumberLabel);
        this.bottomNavigationRightSectionPanel.add(this.rightPanelNextButton);
    }

    private void updateWordsTranslatedListPanelNumberLabel() {
        this.wordsTranslatedListPanelNumberLabel.setText("Current page: " + (this.visibleWordsListPanelIndex + 1));
        this.bottomNavigationRightSectionPanel.revalidate();
        this.bottomNavigationRightSectionPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.listOfButtonsWithDrawnWords.contains(e.getSource())) {
            this.addSummaryOfYourAnswer((JButton) e.getSource());
            this.createNextRound();
        }

        if (e.getSource() == this.rightPanelNextButton) {
            this.nextWordsListSummaryPanel();
        }

        if (e.getSource() == this.rightPanelPreviousButton) {
            this.previousWordsListSummaryPanel();
        }

        if (this.listOfPanelsAnswerSummary.contains(e.getSource())) {
            int index = this.listOfPanelsAnswerSummary.indexOf(e.getSource());
            CreateWordInformationWindow createWordInformationWindow = new CreateWordInformationWindow(this.listOfAnswer.get(index));
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
        if (this.listOfPanelsAnswerSummary.contains(e.getSource())) {
            int index = this.listOfPanelsAnswerSummary.indexOf(e.getSource());
            this.listOfPanelsAnswerSummary.get(index).setBorder(new LineBorder(Color.WHITE, 1));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (this.listOfPanelsAnswerSummary.contains(e.getSource())) {
            int index = this.listOfPanelsAnswerSummary.indexOf(e.getSource());
            this.listOfPanelsAnswerSummary.get(index).setBorder(BorderFactory.createEmptyBorder());
        }
    }
}
