package ui.wordtrainings;

import databases.Reader;
import ui.wordtrainings.subclasses.leftpanelandclasses.*;
import ui.wordtrainings.subclasses.rightpanelandclasses.CreateAnswerListSummaryPanel;
import ui.wordtrainings.subclasses.rightpanelandclasses.CreateInfoRightSectionPanel;
import ui.wordtrainings.subclasses.rightpanelandclasses.CreateRightSidePanel;
import ui.wordtrainings.subclasses.wordinformationwindow.CreateWordInformationWindow;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class RememberingWordsTraining extends JPanel implements MouseListener {
    private final Reader reader = new Reader();
    private final String[] wordsLearningOptions = this.reader.readTxtFile("src\\main\\java\\databases\\DataBases.txt");
    private final JComboBox wordsLearningOptionsComboBox = new  JComboBox(this.wordsLearningOptions);
    private String filePath = "src\\main\\java\\databases\\data\\" + this.wordsLearningOptionsComboBox.getSelectedItem();

    private final DrawnWords drawnWords = new DrawnWords();

    private final CreateWordsGameMainPanel createWordsGameMainPanel = new CreateWordsGameMainPanel(this, this.drawnWords.getDrawnWords(this.filePath));
    private final CreateResultAndChangeWordsOptionPanel createResultAndChangeWordsOptionPanel = new CreateResultAndChangeWordsOptionPanel(this.wordsLearningOptionsComboBox);
    private final CreateCorrectWordPanel createCorrectWordPanel = new CreateCorrectWordPanel(this.createWordsGameMainPanel.getCorrectAnswer().get(1));

    private final CreateAnswerListSummaryPanel createAnswerListSummaryPanel = new CreateAnswerListSummaryPanel(this);

    public RememberingWordsTraining() throws Exception {
        this.setSize(new Dimension(800,500));
        this.setLayout(new GridLayout(1,2));

        CreateInfoRightSectionPanel createInfoRightSectionPanel = new CreateInfoRightSectionPanel();

        CreateLeftSidePanel createLeftSidePanel = new CreateLeftSidePanel();
        createLeftSidePanel.add(this.createCorrectWordPanel, BorderLayout.PAGE_START);
        createLeftSidePanel.add(this.createWordsGameMainPanel, BorderLayout.CENTER);
        createLeftSidePanel.add(this.createResultAndChangeWordsOptionPanel, BorderLayout.PAGE_END);

        CreateRightSidePanel createRightSidePanel = new CreateRightSidePanel();
        createRightSidePanel.add(this.createAnswerListSummaryPanel, BorderLayout.CENTER);
        createRightSidePanel.add(createInfoRightSectionPanel, BorderLayout.PAGE_START);

        this.wordsLearningOptionsComboBox.addActionListener(e -> {
            this.filePath = "src\\main\\java\\databases\\data\\"+this.wordsLearningOptionsComboBox.getSelectedItem();
            try {
                this.createWordsGameMainPanel.createButtonsWithDrawnWords(this, this.drawnWords.getDrawnWords(this.filePath));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.createCorrectWordPanel.updateCorrectAnswerLabel(this.createWordsGameMainPanel.getCorrectAnswer().get(1));
        });

        this.add(createLeftSidePanel);
        this.add(createRightSidePanel);
    }

    private boolean evaluateAnswer(JButton button) {
        return Objects.equals(button.getText(), this.createWordsGameMainPanel.getCorrectAnswer().get(0));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.createWordsGameMainPanel.getListOfButtonsWithDrawnWords().contains(e.getSource())) {
            this.createAnswerListSummaryPanel.addSummaryOfYourAnswer(this.createWordsGameMainPanel.getCorrectAnswer(), this.evaluateAnswer((JButton) e.getSource()), this);
            this.createResultAndChangeWordsOptionPanel.updateStatistic(this.evaluateAnswer((JButton) e.getSource()));
            try {
                this.createWordsGameMainPanel.createButtonsWithDrawnWords(this, this.drawnWords.getDrawnWords(this.filePath));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.createCorrectWordPanel.updateCorrectAnswerLabel(this.createWordsGameMainPanel.getCorrectAnswer().get(1));
        }

        if (e.getSource() == this.createAnswerListSummaryPanel.getCreateBottomNavigationPanelNextButton() || e.getSource() == this.createAnswerListSummaryPanel.getCreateBottomNavigationPanelPreviousButton()) {
            this.createAnswerListSummaryPanel.nextPreviousWordsListSummaryPanel((JButton) e.getSource());
        }

        if (this.createAnswerListSummaryPanel.getListOfPanelAnswer().contains(e.getSource())) {
            int index = this.createAnswerListSummaryPanel.getListOfPanelAnswer().indexOf(e.getSource());
            new CreateWordInformationWindow(this.createAnswerListSummaryPanel.getListOfAnswers().get(index));
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
        if (this.createAnswerListSummaryPanel.getListOfPanelAnswer().contains(e.getSource())) {
            int index = this.createAnswerListSummaryPanel.getListOfPanelAnswer().indexOf(e.getSource());
            this.createAnswerListSummaryPanel.getListOfPanelAnswer().get(index).setBorder(new LineBorder(Color.WHITE, 1));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (this.createAnswerListSummaryPanel.getListOfPanelAnswer().contains(e.getSource())) {
            int index = this.createAnswerListSummaryPanel.getListOfPanelAnswer().indexOf(e.getSource());
            this.createAnswerListSummaryPanel.getListOfPanelAnswer().get(index).setBorder(javax.swing.BorderFactory.createEmptyBorder());
        }
    }
}