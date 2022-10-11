package ui.modifydata.subclasses.leftSideSubclasses;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;

public class AdditionalModificationOptionPanel extends JPanel {
    private final JButton createNewFileButton = new JButton("<html><center>Create new file</center><html>");
    private final JButton deleteFileButton = new JButton("<html><center>Delete file</center><html>");
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

        JPanel bottomTempPanel = new JPanel(new GridLayout(1,2));
        bottomTempPanel.add(this.createNewFileButton);
        bottomTempPanel.add(this.deleteFileButton);

        this.add(upperTempPanel);
        this.add(bottomTempPanel);
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
