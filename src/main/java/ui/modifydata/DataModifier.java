package ui.modifydata;

import databases.Reader;
import ui.modifydata.subclasses.leftSideSubclasses.AdditionalModificationOptionPanel;
import ui.modifydata.subclasses.leftSideSubclasses.DataListFromFile;
import ui.modifydata.subclasses.leftSideSubclasses.NewFileCreator;
import ui.modifydata.subclasses.leftSideSubclasses.LeftPanel;
import ui.modifydata.subclasses.rightSideSubclasses.DataCreationSectionInfo;
import ui.modifydata.subclasses.rightSideSubclasses.DataModificationSectionInfo;
import ui.modifydata.subclasses.rightSideSubclasses.RightPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class DataModifier extends JPanel implements MouseListener {
    private JPanel leftSidePanel = new LeftPanel();
    private DataListFromFile dataListFromFile;
    private AdditionalModificationOptionPanel additionalModificationOptionPanel= new AdditionalModificationOptionPanel(this);

    private JPanel rightSidePanel = new RightPanel();
    private DataCreationSectionInfo dataCreationSectionInfo = new DataCreationSectionInfo(this);
    private DataModificationSectionInfo dataModificationSectionInfo = new DataModificationSectionInfo(this);

    private final Reader reader = new Reader();
    private final String[] dataOptions = this.reader.readTxtFile("src\\main\\java\\databases\\DataBases.txt");
    private final JComboBox dataOptionsComboBox = new JComboBox(this.dataOptions);

    public DataModifier() throws Exception {
        this.setSize(800,500);
        this.setLayout(new BorderLayout());

        JPanel fileChosePanel = new JPanel(new GridLayout(1,1));
        fileChosePanel.add(this.dataOptionsComboBox);
        this.dataListFromFile = new DataListFromFile((String) this.dataOptionsComboBox.getSelectedItem(), this);

        this.leftSidePanel.add(fileChosePanel, BorderLayout.PAGE_START);
        this.leftSidePanel.add(dataListFromFile, BorderLayout.CENTER);
        this.leftSidePanel.add(additionalModificationOptionPanel, BorderLayout.PAGE_END);

        this.add(leftSidePanel, BorderLayout.LINE_START);
        this.add(rightSidePanel, BorderLayout.CENTER);

        this.dataOptionsComboBox.addActionListener(e -> {
            try {
                this.dataListFromFile.populateNavSectionByDataFileContent((String) this.dataOptionsComboBox.getSelectedItem(), this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void rightSidePanelMainSectionRepaint(JPanel newMainJPanel) {
        this.rightSidePanel.removeAll();
        this.rightSidePanel.add(newMainJPanel);
        this.rightSidePanel.repaint();
        this.rightSidePanel.revalidate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.dataListFromFile.getCurrentFileDataLabelsList().contains(e.getSource())) {
            int dataIndex = this.dataListFromFile.getCurrentFileDataLabelsList().indexOf(e.getSource());
            try {
                this.dataModificationSectionInfo = new DataModificationSectionInfo(dataIndex, (String) this.dataOptionsComboBox.getSelectedItem(), this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.rightSidePanelMainSectionRepaint(this.dataModificationSectionInfo);
        }
        if (this.additionalModificationOptionPanel.getCreateNewFileButton() == e.getSource()) {
            new NewFileCreator();
        }
        if (this.additionalModificationOptionPanel.getCreateNewDataButton() == e.getSource()) {
            this.dataCreationSectionInfo = new DataCreationSectionInfo(this);
            this.rightSidePanelMainSectionRepaint(this.dataCreationSectionInfo);
        }

        if (this.dataModificationSectionInfo.getSaveButton() == e.getSource()) {
            try {
                this.reader.saveNewDataOnFile((String) this.dataOptionsComboBox.getSelectedItem(),this.dataModificationSectionInfo.createNewDataToUpdateFile());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (this.dataCreationSectionInfo.getSaveButton() == e.getSource()) {
            try {
                this.dataCreationSectionInfo.saveNewData((String) this.dataOptionsComboBox.getSelectedItem());
                this.dataListFromFile.populateNavSectionByDataFileContent((String) this.dataOptionsComboBox.getSelectedItem(), this);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
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
        if (this.dataListFromFile.getCurrentFileDataLabelsList().contains(e.getSource())) {
            int index = this.dataListFromFile.getCurrentFileDataLabelsList().indexOf(e.getSource());
            this.dataListFromFile.getCurrentFileDataLabelsList().get(index).setBorder(new LineBorder(Color.white, 1));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (this.dataListFromFile.getCurrentFileDataLabelsList().contains(e.getSource())) {
            int index = this.dataListFromFile.getCurrentFileDataLabelsList().indexOf(e.getSource());
            this.dataListFromFile.getCurrentFileDataLabelsList().get(index).setBorder(new EmptyBorder(0,0,0,0));
        }
    }
}
