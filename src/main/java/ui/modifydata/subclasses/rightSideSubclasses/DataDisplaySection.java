package ui.modifydata.subclasses.rightSideSubclasses;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class DataDisplaySection extends JPanel {
    protected JTextArea conceptTextArea = new JTextArea();
    protected JTextArea definitionTextArea = new JTextArea();
    protected JTextArea exampleOfUsageTextArea = new JTextArea();

    protected JPanel informationContainerPanelNo1 = new JPanel(new GridLayout(3,0));
    protected JPanel informationContainerPanelNo2 = new JPanel(new BorderLayout());
    protected JPanel informationContainerPanelNo2SubPanel1 = new JPanel(new GridLayout(2,2));
    protected JPanel informationContainerPanelNo2SubPanel2 = new JPanel(new GridLayout(1,5));

    protected JTextField dateOfLastProperAnswerTextField = new JTextField(5);
    protected JTextField amountOfProperAnswersTextField = new JTextField(5);
    protected JTextField amountOfWrongAnswersTextField = new JTextField(5);
    protected JTextField statisticAboutGuessesTextField = new JTextField(5);

    protected JButton saveButton = new JButton("Save");

    protected DataDisplaySection(MouseListener mouseListener) {
        this.setLayout(new BorderLayout());

        this.setInformationContainerPanelNo1();
        this.setInformationContainerPanelNo2();

        this.informationContainerPanelNo2SubPanel2.setBorder(new EmptyBorder(15,0,0,0));
        this.saveButton.addMouseListener(mouseListener);
        this.informationContainerPanelNo2SubPanel2.add(this.saveButton);

        this.informationContainerPanelNo1.setBorder(new EmptyBorder(5,5,5,5));
        this.informationContainerPanelNo2.setBorder(new EmptyBorder(5,5,5,5));

        this.informationContainerPanelNo2.add(this.informationContainerPanelNo2SubPanel1, BorderLayout.CENTER);
        this.informationContainerPanelNo2.add(this.informationContainerPanelNo2SubPanel2, BorderLayout.PAGE_END);

        this.add(this.informationContainerPanelNo1, BorderLayout.CENTER);
        this.add(this.informationContainerPanelNo2, BorderLayout.PAGE_END);
    }

    private void setInformationContainerPanelNo1() {
        this.conceptTextArea.setLineWrap(true);
        this.definitionTextArea.setLineWrap(true);
        this.exampleOfUsageTextArea.setLineWrap(true);

        JTextArea[] tempJTextAreasContainer = {this.conceptTextArea, this.definitionTextArea, this.exampleOfUsageTextArea};
        String[] tempStringsContainer = {"Concept", "Definition", "Example of usage"};
        for (int iterator = 0; iterator < 3; iterator++) {
            JPanel tempJPanel = new JPanel(new BorderLayout());
            tempJPanel.setBorder(new EmptyBorder(5,5,5,5));
            JLabel tempJLabel = new JLabel(tempStringsContainer[iterator]);
            tempJLabel.setFont(new Font("Serif", Font.BOLD, 16));
            tempJPanel.add(tempJLabel, BorderLayout.PAGE_START);
            tempJPanel.add(tempJTextAreasContainer[iterator], BorderLayout.CENTER);
            this.informationContainerPanelNo1.add(tempJPanel);
        }
    }

    private void setInformationContainerPanelNo2() {
        JTextField[] tempJTextFieldsContainer = {this.dateOfLastProperAnswerTextField, this.amountOfProperAnswersTextField,
                this.statisticAboutGuessesTextField, this.amountOfWrongAnswersTextField};
        String[] tempStringsContainer = {"Proper answer date", "Proper answers", "Rate of success", "Wrong answers"};

        for (int iterator = 0; iterator < 4; iterator++) {
            JPanel tempPanel = new JPanel(new GridLayout(1,2));
            tempPanel.setBorder(new EmptyBorder(0,2,0,2));
            JLabel tempJLabel = new JLabel(tempStringsContainer[iterator]);
            tempJLabel.setFont(new Font("Serif", Font.BOLD, 12));
            tempPanel.add(tempJLabel);
            tempPanel.add(tempJTextFieldsContainer[iterator]);
            this.informationContainerPanelNo2SubPanel1.add(tempPanel);
            this.informationContainerPanelNo2SubPanel2.add(new JLabel());
        }

    }

    public JButton getSaveButton() {
        return saveButton;
    }
}
