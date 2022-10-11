package ui.modifydata.subclasses.leftSideSubclasses;

import databases.Reader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class CreateListFromFileData extends JPanel {
    private ArrayList<ArrayList<String>> currentFileDataList = new ArrayList<>();
    private final ArrayList<JLabel> currentFileDataLabelsList = new ArrayList<>();
    private final Reader reader = new Reader();

    public CreateListFromFileData(String filePath, MouseListener mouseListener) throws Exception {
        this.setLayout(new GridLayout(1,1));
        this.setBorder(new EmptyBorder(5,0,10,0));
        this.populateNavSectionByDataFileContent(filePath, mouseListener);
    }

    public void populateNavSectionByDataFileContent(String filePath, MouseListener mouseListener) throws Exception {
        this.currentFileDataList.clear();
        this.currentFileDataLabelsList.clear();
        this.currentFileDataList = this.reader.getDataList("src\\main\\java\\databases\\data\\" + filePath);
        this.removeAll();

        JPanel tempContainerPanel = new JPanel(new GridLayout(this.currentFileDataList.size(),1));
        for (ArrayList<String> strings : this.currentFileDataList) {
            JLabel englishCaption = new JLabel(strings.get(0));
            this.currentFileDataLabelsList.add(englishCaption);
            tempContainerPanel.setBorder(new EmptyBorder(2,5,0,0));
            tempContainerPanel.add(englishCaption);
            englishCaption.addMouseListener(mouseListener);
        }

        JScrollPane scrollPane = new JScrollPane(tempContainerPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        this.add(scrollPane);
        this.revalidate();
        this.repaint();
    }

    public ArrayList<JLabel> getCurrentFileDataLabelsList() {
        return currentFileDataLabelsList;
    }
}
