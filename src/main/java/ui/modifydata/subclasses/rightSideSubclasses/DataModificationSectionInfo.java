package ui.modifydata.subclasses.rightSideSubclasses;

import databases.Reader;

import java.awt.event.MouseListener;
import java.util.ArrayList;

public class DataModificationSectionInfo extends DataDisplaySection {
    private Reader reader = new Reader();
    private ArrayList<ArrayList<String>> currentFileDataList;
    private int dataIndex;

    public DataModificationSectionInfo(int dataIndex, String dataFileName, MouseListener mouseListener) throws Exception {
        super(mouseListener);
        this.dataIndex = dataIndex;
        this.currentFileDataList = reader.getDataList("src\\main\\java\\databases\\data\\" + dataFileName);

        this.conceptTextArea.setText(this.currentFileDataList.get(this.dataIndex).get(0));
        this.definitionTextArea.setText(this.currentFileDataList.get(this.dataIndex).get(1));
        this.exampleOfUsageTextArea.setText(this.currentFileDataList.get(this.dataIndex).get(2));
        this.amountOfProperAnswersTextField.setText(this.currentFileDataList.get(this.dataIndex).get(4));
        this.amountOfWrongAnswersTextField.setText(this.currentFileDataList.get(this.dataIndex).get(5));
        this.dateOfLastProperAnswerTextField.setText(this.currentFileDataList.get(this.dataIndex).get(3));
        this.dateOfLastProperAnswerTextField.setEditable(false);
        this.statisticAboutGuessesTextField.setText(this.currentFileDataList.get(this.dataIndex).get(6));
        this.statisticAboutGuessesTextField.setEditable(false);
    }

    public DataModificationSectionInfo(MouseListener mouseListener) {
        super(mouseListener);
    }

    public ArrayList<String> createNewDataToUpdateFile() {
        ArrayList<String> tempList = new ArrayList<>();

        String[] tempStringArray = {this.conceptTextArea.getText(), this.definitionTextArea.getText(), this.exampleOfUsageTextArea.getText()
                ,this.dateOfLastProperAnswerTextField.getText(), this.amountOfProperAnswersTextField.getText()
                ,this.amountOfWrongAnswersTextField.getText(), this.statisticAboutGuessesTextField.getText()};

        for (int iterator = 0; iterator < this.currentFileDataList.get(this.dataIndex).size(); iterator++) {
            this.currentFileDataList.get(this.dataIndex).set(iterator, tempStringArray[iterator]);
        }

        for (int iterator = 0; iterator < this.currentFileDataList.size(); iterator++) {
            String tempStringVariable = "";
            for (int subIterator = 0; subIterator < this.currentFileDataList.get(iterator).size(); subIterator++) {
                tempStringVariable = tempStringVariable + this.currentFileDataList.get(iterator).get(subIterator);
                if (subIterator != this.currentFileDataList.get(iterator).size() - 1) {
                    tempStringVariable = tempStringVariable + ";";
                }
            }
            tempList.add(tempStringVariable);
        }
        return tempList;
    }
}
