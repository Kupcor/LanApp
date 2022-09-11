package ui.wordtrainings.subclasses.leftpanelandclasses;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CreateResultAndChangeWordsOptionPanel extends JPanel {
    private int amountOfGuessWords;
    private int amountOfCorrectAnswer;
    private int amountOfWrongAnswer;
    private final JPanel resultsSummaryPanel = new JPanel();

    public CreateResultAndChangeWordsOptionPanel(JComboBox comboBox) {
        this.setLayout(new BorderLayout());
        this.amountOfCorrectAnswer = 0;
        this.amountOfWrongAnswer = 0;
        this.amountOfGuessWords = 0;

        this.add(comboBox, BorderLayout.PAGE_START);
        this.initializeOrUpdateStatistic();
    }

    private void initializeOrUpdateStatistic() {
        this.resultsSummaryPanel.removeAll();
        String[] statisticsCaptions = {"<html><center>Total attempts</center><html>", "Correct answers", "Wrong answers"};
        JLabel[] statistics = {
                new JLabel(String.valueOf(this.amountOfGuessWords), SwingConstants.CENTER),
                new JLabel(String.valueOf(this.amountOfCorrectAnswer), SwingConstants.CENTER),
                new JLabel(String.valueOf(this.amountOfWrongAnswer), SwingConstants.CENTER),
        };

        for (int iterator = 0; iterator < 3; iterator++) {
            JPanel tempPanel = new JPanel(new GridLayout(2,1));
            tempPanel.setBorder(new EmptyBorder(0,15,0,10));

            JLabel label = new JLabel(statisticsCaptions[iterator], SwingConstants.CENTER);
            label.setFont(new Font("Serif", Font.BOLD, 12));

            tempPanel.add(label);
            tempPanel.add(statistics[iterator]);
            this.resultsSummaryPanel.add(tempPanel);
        }
        this.resultsSummaryPanel.setBorder(new EmptyBorder(5,0,0,0));
        this.resultsSummaryPanel.revalidate();
        this.resultsSummaryPanel.repaint();
        this.add(this.resultsSummaryPanel, BorderLayout.PAGE_END);
    }

    public void updateStatistic(boolean isAnswerEvaluationIsCorrect) {
        if (isAnswerEvaluationIsCorrect) this.amountOfCorrectAnswer++;
        else this.amountOfWrongAnswer++;
        ++this.amountOfGuessWords;
        this.initializeOrUpdateStatistic();
    }
}
