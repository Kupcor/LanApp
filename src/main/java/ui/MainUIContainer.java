package ui;

import ui.flashcardswindow.FlashCardsMainPanel;
import ui.modifydata.DataModifier;
import ui.templates.Window;
import ui.wordtrainings.RememberingWordsTraining;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainUIContainer extends Window implements MouseListener {
    private final JPanel navSectionPanel = new JPanel(new GridLayout(20,1));
    private JPanel mainSectionPanel = new JPanel();

    private RememberingWordsTraining rememberingWordsTraining;
    private DataModifier dataModifier;
    private FlashCardsMainPanel flashCardsMainPanel;

    private final JLabel openRememberingWordsTrainingSection = new JLabel("Remembering words training");
    private final JLabel openFlashCardsSection = new JLabel("Flashcards");
    private final JLabel openDataModifierSection = new JLabel("Modify data");

    public MainUIContainer(int width, int height) throws Exception {
        super(width, height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.navSectionPanel.setPreferredSize(new Dimension(200,500));
        this.navSectionPanel.setBorder(new MatteBorder(0,0,0,4,Color.decode("#525861")));

        this.setLayout(new BorderLayout());

        this.addNewButtonToNavPanel(this.openRememberingWordsTrainingSection);
        this.addNewButtonToNavPanel(this.openFlashCardsSection);
        this.addNewButtonToNavPanel(this.openDataModifierSection);

        this.add(this.navSectionPanel, BorderLayout.LINE_START);
        this.add(this.mainSectionPanel, BorderLayout.CENTER);
        JPanel bottomSectionPanel = new JPanel();
        this.add(bottomSectionPanel, BorderLayout.PAGE_END);
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

    private void addNewButtonToNavPanel(JLabel buttonLabel) {
        JPanel panel = new JPanel();
        buttonLabel.setForeground(Color.decode("#98a3ad"));
        panel.add(buttonLabel);
        panel.setBorder(new EmptyBorder(0,0,0,0));
        buttonLabel.addMouseListener(this);
        this.navSectionPanel.add(panel);
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
            try {
                this.dataModifier = new DataModifier();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.setNewPanelToMainSection(this.dataModifier);
        }

        if (e.getSource() == this.openFlashCardsSection) {
            try {
                this.flashCardsMainPanel = new FlashCardsMainPanel();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            this.setNewPanelToMainSection(this.flashCardsMainPanel);
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
        if (e.getSource() == this.openRememberingWordsTrainingSection) {
            this.openRememberingWordsTrainingSection.setForeground(Color.WHITE);
        }
        if (e.getSource() == this.openFlashCardsSection) {
            this.openFlashCardsSection.setForeground(Color.WHITE);
        }
        if (e.getSource() == this.openDataModifierSection) {
            this.openDataModifierSection.setForeground(Color.WHITE);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == this.openRememberingWordsTrainingSection) {
            this.openRememberingWordsTrainingSection.setForeground(Color.decode("#98a3ad"));
        }
        if (e.getSource() == this.openFlashCardsSection) {
            this.openFlashCardsSection.setForeground(Color.decode("#98a3ad"));
        }
        if (e.getSource() == this.openDataModifierSection) {
            this.openDataModifierSection.setForeground(Color.decode("#98a3ad"));
        }
    }
}
