package ui;

import ui.modifydata.DataModifier;
import ui.templates.Window;
import ui.wordtrainings.RememberingWordsTraining;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainUIContainer extends Window implements MouseListener {
    private JPanel navSectionPanel = new JPanel();
    private JPanel mainSectionPanel = new JPanel();
    private JPanel bottomSectionPanel = new JPanel();

    private RememberingWordsTraining rememberingWordsTraining;
    private DataModifier dataModifier;

    private JLabel openRememberingWordsTrainingSection = new JLabel("Remembering words training");
    private JLabel openDataModifierSection = new JLabel("Modify data");

    public MainUIContainer(int width, int height) throws Exception {
        super(width, height);
        this.setStyleToMainPanels();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.navSectionPanel.setPreferredSize(new Dimension(200,500));

        this.setLayout(new BorderLayout());
        this.mainSectionPanel.setBackground(Color.BLUE);

        this.openRememberingWordsTrainingSection.addMouseListener(this);
        this.openDataModifierSection.addMouseListener(this);


        this.navSectionPanel.add(this.openRememberingWordsTrainingSection);
        this.navSectionPanel.add(this.openDataModifierSection);

        this.add(this.navSectionPanel, BorderLayout.LINE_START);
        this.add(this.mainSectionPanel, BorderLayout.CENTER);
        this.add(this.bottomSectionPanel, BorderLayout.PAGE_END);
        }

    private void setNewPanelToMainSection(JPanel panel) {
        assert panel != null : "Panel cannot be null.";
        this.remove(this.mainSectionPanel);
        this.mainSectionPanel = panel;
        this.add(this.mainSectionPanel, BorderLayout.CENTER);
        this.add(this.navSectionPanel, BorderLayout.LINE_START);
        this.revalidate();
        this.repaint();
    }

    private void setStyleToMainPanels() {
        this.navSectionPanel.setBackground(Color.decode("#232dde"));
        this.navSectionPanel.setForeground(Color.decode("#000000"));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.openRememberingWordsTrainingSection) {
            try {
                this.rememberingWordsTraining = new RememberingWordsTraining();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.setNewPanelToMainSection(this.rememberingWordsTraining);
        }

        if (e.getSource() == this.openDataModifierSection) {
            this.dataModifier = new DataModifier();
            this.setNewPanelToMainSection(this.dataModifier);
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
