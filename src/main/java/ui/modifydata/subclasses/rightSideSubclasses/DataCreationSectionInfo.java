package ui.modifydata.subclasses.rightSideSubclasses;

import databases.Reader;

import java.awt.event.MouseListener;
import java.io.IOException;

public class DataCreationSectionInfo extends DataDisplaySection{
    private Reader reader = new Reader();

    public DataCreationSectionInfo(MouseListener mouseListener) {
        super(mouseListener);
        this.conceptTextArea.setText("Idea/Concept/Word/Question...");
        this.definitionTextArea.setText("Definition of concept / word translation ...");
        this.exampleOfUsageTextArea.setText("Example of usage / additional information");
        this.amountOfProperAnswersTextField.setText("0");
        this.amountOfProperAnswersTextField.setEditable(false);
        this.amountOfWrongAnswersTextField.setText("0");
        this.amountOfWrongAnswersTextField.setEditable(false);
        this.dateOfLastProperAnswerTextField.setText("-");
        this.dateOfLastProperAnswerTextField.setEditable(false);
        this.statisticAboutGuessesTextField.setText("-");
        this.statisticAboutGuessesTextField.setEditable(false);
        this.saveButton.setText("Create");
    }

    private String getNewDataArrayListAsString() {
        String newDataInformation =
                this.conceptTextArea.getText() + ";" + this.definitionTextArea.getText() + ";" + this.exampleOfUsageTextArea.getText()
                + ";" + this.dateOfLastProperAnswerTextField.getText() + ";" + this.amountOfProperAnswersTextField.getText()
                + ";" + this.amountOfWrongAnswersTextField.getText() + ";" + this.statisticAboutGuessesTextField.getText();
        return newDataInformation;
    }

    public void saveNewData(String filePath) throws IOException {
        this.reader.createDataRecord(this.getNewDataArrayListAsString(), filePath);
    }
}
