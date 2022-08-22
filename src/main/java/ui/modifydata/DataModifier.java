package ui.modifydata;

import databases.Reader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DataModifier extends JPanel implements MouseListener {
    private ArrayList<ArrayList<String>> currentFileDataList = new ArrayList<ArrayList<String>>();
    private ArrayList<JLabel> currentFileDataLabelsList = new ArrayList<JLabel>();

    private JPanel upperSectionMenuPanel = new JPanel();
    private JPanel navSectionDataListPanel = new JPanel(new GridLayout(30,1));

    private final Reader reader = new Reader();
    private final String[] dataOptions = this.reader.readTxtFile("src\\main\\java\\databases\\DataBases.txt");
    private final JComboBox dataOptionsComboBox = new JComboBox(this.dataOptions);

    public DataModifier() {
        this.setSize(800,500);
        this.setLayout(new BorderLayout());

        this.dataOptionsComboBox.addActionListener(e -> {
            try {
                this.populateNavSectionByDataFileContent();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        this.upperSectionMenuPanel.add(this.dataOptionsComboBox);

        this.add(this.upperSectionMenuPanel, BorderLayout.PAGE_START);
        this.add(this.navSectionDataListPanel, BorderLayout.LINE_START);
    }

    private void populateNavSectionByDataFileContent() throws Exception {
        this.currentFileDataList.clear();
        this.currentFileDataLabelsList.clear();
        this.currentFileDataList = this.reader.getDataList("src\\main\\java\\databases\\data\\" + this.dataOptionsComboBox.getSelectedItem());

        this.navSectionDataListPanel.removeAll();

        for (int i = 0; i < this.currentFileDataList.size(); i++) {
            JLabel englishCaption = new JLabel(this.currentFileDataList.get(i).get(0));
            englishCaption.addMouseListener(this);
            this.currentFileDataLabelsList.add(englishCaption);
            this.navSectionDataListPanel.add(englishCaption);
            if (i == 30) {
                break;
            }
        }

        this.navSectionDataListPanel.revalidate();
        this.navSectionDataListPanel.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.currentFileDataLabelsList.contains(e.getSource())) {
            System.out.println(this.currentFileDataList.get(this.currentFileDataLabelsList.indexOf(e.getSource())).get(0));
            System.out.println(this.currentFileDataList.get(this.currentFileDataLabelsList.indexOf(e.getSource())).get(1));
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
