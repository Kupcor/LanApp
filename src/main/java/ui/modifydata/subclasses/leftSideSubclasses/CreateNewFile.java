package ui.modifydata.subclasses.leftSideSubclasses;

import databases.Reader;
import ui.templates.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CreateNewFile extends Window implements MouseListener {
    private final JTextField fileNameTextArea = new JTextField();
    private final JButton cancelButton = new JButton("<html><center>Cancel</center></html>");
    private final JButton acceptButton = new JButton("<html><center>Accept</center></html>");
    private final Reader reader = new Reader();

    public CreateNewFile() {
        super(500, 120);
        this.setLayout(new GridLayout(1,1));
        this.setTitle("Creating new file window");

        JPanel tempContainerPanel = new JPanel(new GridLayout(3,1));
        tempContainerPanel.setBorder(new EmptyBorder(0,5,0,5));
        JPanel tempButtonContainerPanel = new JPanel(new GridLayout(1,2));
        tempButtonContainerPanel.setBorder(new EmptyBorder(5,0,0,0));

        this.cancelButton.addMouseListener(this);
        this.acceptButton.addMouseListener(this);
        tempButtonContainerPanel.add(this.cancelButton);
        tempButtonContainerPanel.add(this.acceptButton);

        tempContainerPanel.add(new JLabel("Provide new file name..."));
        tempContainerPanel.add(this.fileNameTextArea);
        tempContainerPanel.add(tempButtonContainerPanel);

        this.add(tempContainerPanel);
    }

    private String modifyFileNameString(String fileName) {
        String finalFileName = "";
        String[] subStrings = fileName.split(" ");
        for (String singleString : subStrings) {
            String firstCharacter = String.valueOf(singleString.charAt(0));
            singleString = singleString.replaceFirst(firstCharacter, firstCharacter.toUpperCase());
            finalFileName = finalFileName.concat(singleString);
        }
        return finalFileName;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.cancelButton.equals(e.getSource())) {
            this.dispose();
        }
        if (this.acceptButton.equals(e.getSource())) {
            this.reader.createCSVFile(this.modifyFileNameString(this.fileNameTextArea.getText()));
            this.dispose();
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
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }
}
