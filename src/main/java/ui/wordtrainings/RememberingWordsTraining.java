package ui.wordtrainings;

import databases.Reader;
import ui.wordtrainings.subclasses.leftpanelandclasses.*;
import ui.wordtrainings.subclasses.rightpanelandclasses.CreateAnswerListSummaryPanel;
import ui.wordtrainings.subclasses.rightpanelandclasses.CreateBottomNavigationPanel;
import ui.wordtrainings.subclasses.rightpanelandclasses.CreateInfoRightSectionPanel;
import ui.wordtrainings.subclasses.wordinformationwindow.CreateWordInformationWindow;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Objects;

public class RememberingWordsTraining extends JPanel implements MouseListener {
    private final Reader reader = new Reader();
    private final String[] wordsLearningOptions = this.reader.readTxtFile("src\\main\\java\\databases\\DataBases.txt");
    private final JComboBox wordsLearningOptionsComboBox = new JComboBox(this.wordsLearningOptions);
    private String filePath = "src\\main\\java\\databases\\data\\VerbsDataBase.csv";

    private DrawnWords drawnWords = new DrawnWords();


    //  Left Section Panel
    private final CreateCorrectWordPanel createCorrectWordPanel = new CreateCorrectWordPanel();
    private final CreateWordsGameMainPanel createWordsGameMainPanel = new CreateWordsGameMainPanel();
    private final CreateResultAndChangeWordsOptionPanel createResultAndChangeWordsOptionPanel = new CreateResultAndChangeWordsOptionPanel(this.wordsLearningOptionsComboBox);;
    private final CreateLeftSidePanel createLeftSidePanel = new CreateLeftSidePanel();

    //  Right section Panel
    private final CreateBottomNavigationPanel createBottomNavigationPanel = new CreateBottomNavigationPanel(this);
    private final CreateAnswerListSummaryPanel createAnswerListSummaryPanel = new CreateAnswerListSummaryPanel();

    //  Before refactoring
    private ArrayList<ArrayList<String>> listOfDrawnWords = new ArrayList<ArrayList<String>>();
    private ArrayList<JButton> listOfButtonsWithDrawnWords = new ArrayList<JButton>();

    private final ArrayList<JPanel> listOfPanelsAnswerSummary = new ArrayList<JPanel>();
    private final ArrayList<ArrayList<String>> listOfAnswer = new ArrayList<ArrayList<String>>();


    private final int amountOfWordsInSummaryList = 20;
    private int wordsInSummaryListCount = 0;
    private int currentWordsListPanelIndex;

    private final ArrayList<JPanel> wordsListPanelContainer = new ArrayList<JPanel>();

    //  HERE
    private final JPanel rightSidePanel = new JPanel(new BorderLayout());

    private JPanel translatedWordListPanel = new JPanel(new GridLayout(this.amountOfWordsInSummaryList,2));

    public RememberingWordsTraining() throws Exception {
        this.setSize(new Dimension(800,500));
        this.setLayout(new GridLayout(1,2));

        CreateInfoRightSectionPanel createInfoRightSectionPanel = new CreateInfoRightSectionPanel();

        this.createLeftSidePanel.add(this.createCorrectWordPanel, BorderLayout.PAGE_START);
        this.createLeftSidePanel.add(this.createWordsGameMainPanel, BorderLayout.CENTER);
        this.createLeftSidePanel.add(this.createResultAndChangeWordsOptionPanel, BorderLayout.PAGE_END);

        //  Before refactored
        this.createButtonsWithDrawnWords();

        this.rightSidePanel.setBorder(new EmptyBorder(5,10,5,10));

        this.translatedWordListPanel.setBorder(new EmptyBorder(5,0,10,0));

        this.wordsListPanelContainer.add(this.translatedWordListPanel);
        //  Right section panel
        this.rightSidePanel.add(this.wordsListPanelContainer.get(this.currentWordsListPanelIndex), BorderLayout.CENTER);
        this.rightSidePanel.add(createInfoRightSectionPanel, BorderLayout.PAGE_START);
        this.rightSidePanel.add(this.createBottomNavigationPanel, BorderLayout.PAGE_END);

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

        this.add(this.createLeftSidePanel);
        this.add(this.rightSidePanel);
    }

    //  Main section - word game functions (left Panel)
    public void createButtonsWithDrawnWords() throws Exception {
        this.drawnWords.setCorrectAnswerIndex();
        this.listOfButtonsWithDrawnWords.clear();
        this.listOfDrawnWords.clear();
        this.listOfDrawnWords = this.drawnWords.getDrawnWords(this.filePath);
        this.createCorrectWordPanel.updateCorrectAnswerLabel(this.listOfDrawnWords.get(this.drawnWords.getCorrectAnswerIndex()).get(1));
        this.listOfButtonsWithDrawnWords = this.createWordsGameMainPanel.createButtonsWithDrawnWords(this.filePath, this, this.listOfDrawnWords);
    }

    private boolean evaluateAnswer(JButton button) {
        return Objects.equals(button.getText(), this.listOfDrawnWords.get(this.drawnWords.getCorrectAnswerIndex()).get(0));
    }

    private void createNextRound() {
        try {
            this.createButtonsWithDrawnWords();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //  Right side panel
    private void addSummaryOfYourAnswer(JButton button) {
        JLabel currentEnglishWord = new JLabel(this.listOfDrawnWords.get(this.drawnWords.getCorrectAnswerIndex()).get(0));
        JLabel currentPolishWord = new JLabel(this.listOfDrawnWords.get(this.drawnWords.getCorrectAnswerIndex()).get(1));
        JPanel tempContainerForWordsPair = new JPanel(new GridLayout(1,2));
        this.wordsInSummaryListCount++;

        if (this.evaluateAnswer(button)) {
            currentEnglishWord.setForeground(Color.GREEN);
            currentPolishWord.setForeground(Color.GREEN);
        } else {
            currentEnglishWord.setForeground(Color.RED);
            currentPolishWord.setForeground(Color.RED);
        }

        if (this.wordsInSummaryListCount > this.amountOfWordsInSummaryList) {
            this.wordsInSummaryListCount = 1;
            this.addAnotherWordsListSummaryPanel();
        }

        tempContainerForWordsPair.add(currentEnglishWord);
        tempContainerForWordsPair.add(currentPolishWord);
        tempContainerForWordsPair.addMouseListener(this);


        this.listOfPanelsAnswerSummary.add(tempContainerForWordsPair);
        this.listOfAnswer.add(this.listOfDrawnWords.get(this.drawnWords.getCorrectAnswerIndex()));


        this.wordsListPanelContainer.get(this.currentWordsListPanelIndex).add(tempContainerForWordsPair);
        this.wordsListPanelContainer.get(this.currentWordsListPanelIndex).revalidate();
        this.wordsListPanelContainer.get(this.currentWordsListPanelIndex).repaint();

        this.createResultAndChangeWordsOptionPanel.updateStatistic(this.evaluateAnswer(button));
        if (this.createResultAndChangeWordsOptionPanel.getAmountOfGuessWords() > this.amountOfWordsInSummaryList) {
            this.createBottomNavigationPanel.initialize();
        }
    }

    private void addAnotherWordsListSummaryPanel() {
        this.translatedWordListPanel = new JPanel(new GridLayout(this.amountOfWordsInSummaryList,2));
        this.wordsListPanelContainer.add(this.translatedWordListPanel);
        this.rightSidePanel.remove(this.wordsListPanelContainer.get(this.currentWordsListPanelIndex));

        this.currentWordsListPanelIndex++;

        this.createBottomNavigationPanel.updateTranslatedListPanelNumberLabel(this.createBottomNavigationPanel.getNextButton(), wordsListPanelContainer.size());
        this.rightSidePanel.add(this.wordsListPanelContainer.get(this.currentWordsListPanelIndex));
        this.rightSidePanel.revalidate();
        this.rightSidePanel.repaint();
    }

    private void nextWordsListSummaryPanel(JButton button) {
        this.rightSidePanel.remove(this.wordsListPanelContainer.get(this.createBottomNavigationPanel.getWordsTranslatedListPanelNumber()));
        this.rightSidePanel.add(this.wordsListPanelContainer.get(this.createBottomNavigationPanel.getWordsTranslatedListPanelNumber()));
        this.createBottomNavigationPanel.updateTranslatedListPanelNumberLabel(button, wordsListPanelContainer.size());
        this.rightSidePanel.revalidate();
        this.rightSidePanel.repaint();
    }

    private void previousWordsListSummaryPanel(JButton button) {
        this.rightSidePanel.remove(this.wordsListPanelContainer.get(this.createBottomNavigationPanel.getWordsTranslatedListPanelNumber()));
        this.rightSidePanel.add(this.wordsListPanelContainer.get(this.createBottomNavigationPanel.getWordsTranslatedListPanelNumber()));
        this.createBottomNavigationPanel.updateTranslatedListPanelNumberLabel(button, wordsListPanelContainer.size());
        this.rightSidePanel.revalidate();
        this.rightSidePanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.listOfButtonsWithDrawnWords.contains(e.getSource())) {
            this.addSummaryOfYourAnswer((JButton) e.getSource());
            this.createNextRound();
        }

        if (e.getSource() == this.createBottomNavigationPanel.getNextButton()) {
            this.nextWordsListSummaryPanel((JButton) e.getSource());
        }

        if (e.getSource() == this.createBottomNavigationPanel.getPreviousButton()) {
            this.previousWordsListSummaryPanel((JButton) e.getSource());
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
            this.listOfPanelsAnswerSummary.get(index).setBorder(javax.swing.BorderFactory.createEmptyBorder());
        }
    }
}

//  Refactory status 330 lines
//  Refactory status 325 lines
//  Refactory status 293
//  Refactory status 277
//  Refactory status 254
//  Refactory status 236
//  Refactory status 229