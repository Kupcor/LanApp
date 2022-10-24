package ui.wordtrainings;

import databases.Reader;
import ui.wordtrainings.subclasses.leftpanelandclasses.*;
import ui.wordtrainings.subclasses.rightpanelandclasses.AnswerListSummaryPanel;
import ui.wordtrainings.subclasses.wordinformationwindow.WordInformationWindow;
import ui.wordtrainings.subclasses.rightpanelandclasses.RightSectionPanel;
import ui.wordtrainings.subclasses.rightpanelandclasses.RightPanel;

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

    private final WordsGameMainPanel wordsGameMainPanel = new WordsGameMainPanel(this, this.drawnWords.getDrawnWords(this.filePath, 10));
    private final ResultAndChangeWordsOptionPanel resultAndChangeWordsOptionPanel = new ResultAndChangeWordsOptionPanel(this.wordsLearningOptionsComboBox);
    private final CorrectWordPanel correctWordPanel = new CorrectWordPanel(this.wordsGameMainPanel.getCorrectAnswer().get(1));

    private final AnswerListSummaryPanel answerListSummaryPanel = new AnswerListSummaryPanel(this);

    public RememberingWordsTraining() throws Exception {
        this.setSize(new Dimension(800,500));
        this.setLayout(new GridLayout(1,2));

        RightSectionPanel rightSectionPanel = new RightSectionPanel();

        LeftPanel leftPanel = new LeftPanel();
        leftPanel.add(this.correctWordPanel, BorderLayout.PAGE_START);
        leftPanel.add(this.wordsGameMainPanel, BorderLayout.CENTER);
        leftPanel.add(this.resultAndChangeWordsOptionPanel, BorderLayout.PAGE_END);

        RightPanel rightPanel = new RightPanel();
        rightPanel.add(this.answerListSummaryPanel, BorderLayout.CENTER);
        rightPanel.add(rightSectionPanel, BorderLayout.PAGE_START);

        this.wordsLearningOptionsComboBox.addActionListener(e -> {
            this.filePath = "src\\main\\java\\databases\\data\\"+this.wordsLearningOptionsComboBox.getSelectedItem();
            try {
                this.wordsGameMainPanel.createButtonsWithDrawnWords(this, this.drawnWords.getDrawnWords(this.filePath, 10));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.correctWordPanel.updateCorrectAnswerLabel(this.wordsGameMainPanel.getCorrectAnswer().get(1));
        });

        this.add(leftPanel);
        this.add(rightPanel);
    }

    private boolean evaluateAnswer(JButton button) {
        return Objects.equals(button.getText(), this.wordsGameMainPanel.getCorrectAnswer().get(0));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.wordsGameMainPanel.getListOfButtonsWithDrawnWords().contains(e.getSource())) {
            this.answerListSummaryPanel.addSummaryOfYourAnswer(this.wordsGameMainPanel.getCorrectAnswer(), this.evaluateAnswer((JButton) e.getSource()), this);
            this.resultAndChangeWordsOptionPanel.updateStatistic(this.evaluateAnswer((JButton) e.getSource()));
            try {
                this.wordsGameMainPanel.createButtonsWithDrawnWords(this, this.drawnWords.getDrawnWords(this.filePath, 10));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.correctWordPanel.updateCorrectAnswerLabel(this.wordsGameMainPanel.getCorrectAnswer().get(1));
        }

        if (e.getSource() == this.answerListSummaryPanel.getCreateBottomNavigationPanelNextButton() || e.getSource() == this.answerListSummaryPanel.getCreateBottomNavigationPanelPreviousButton()) {
            this.answerListSummaryPanel.nextPreviousWordsListSummaryPanel((JButton) e.getSource());
        }

        if (this.answerListSummaryPanel.getListOfPanelAnswer().contains(e.getSource())) {
            int index = this.answerListSummaryPanel.getListOfPanelAnswer().indexOf(e.getSource());
            new WordInformationWindow(this.answerListSummaryPanel.getListOfAnswers().get(index));
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
        if (this.answerListSummaryPanel.getListOfPanelAnswer().contains(e.getSource())) {
            int index = this.answerListSummaryPanel.getListOfPanelAnswer().indexOf(e.getSource());
            this.answerListSummaryPanel.getListOfPanelAnswer().get(index).setBorder(new LineBorder(Color.WHITE, 1));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (this.answerListSummaryPanel.getListOfPanelAnswer().contains(e.getSource())) {
            int index = this.answerListSummaryPanel.getListOfPanelAnswer().indexOf(e.getSource());
            this.answerListSummaryPanel.getListOfPanelAnswer().get(index).setBorder(javax.swing.BorderFactory.createEmptyBorder());
        }
    }
}