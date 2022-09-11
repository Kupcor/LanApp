package ui.modifydata.subclasses.leftSideSubclasses;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class AdditionalModificationOptionPanel extends JPanel {
    private final JButton createNewFileButton = new JButton("<html><center>Create new file</center><html>");
    private final JButton createNewDataButton = new JButton("<html><center>Add new information</center><html>");
    private final JButton sortDataButton = new JButton("<html><center>Sort data in file</center><html>");

    public AdditionalModificationOptionPanel(MouseListener mouseListener) {
        this.setLayout(new GridLayout(2,1));
        this.setBorder(new EmptyBorder(0,0,5,0));

        this.createNewDataButton.addMouseListener(mouseListener);
        this.createNewFileButton.addMouseListener(mouseListener);

        JPanel upperTempPanel = new JPanel(new GridLayout(1,2));
        upperTempPanel.add(this.createNewDataButton);
        upperTempPanel.add(this.sortDataButton);

        this.add(upperTempPanel);
        this.add(this.createNewFileButton);
    }

    public JButton getCreateNewFileButton() {
        return createNewFileButton;
    }

    public JButton getCreateNewDataButton() {
        return createNewDataButton;
    }

    public JButton getSortDataButton() {
        return sortDataButton;
    }
}
