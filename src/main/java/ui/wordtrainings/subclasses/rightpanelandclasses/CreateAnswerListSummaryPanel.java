package ui.wordtrainings.subclasses.rightpanelandclasses;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateAnswerListSummaryPanel extends JPanel {
    private final int amountOfAnswersInOneSummaryPanel = 20;
    private int currentSummaryPanelIndex = 0;
    private ArrayList<JPanel> summaryPanelList = new ArrayList<JPanel>();

    public CreateAnswerListSummaryPanel() {
        JPanel oneSummaryPanel = new JPanel(new GridLayout(this.amountOfAnswersInOneSummaryPanel, 1));
        this.summaryPanelList.add(oneSummaryPanel);
        this.add(summaryPanelList.get(this.currentSummaryPanelIndex));
    }
}
