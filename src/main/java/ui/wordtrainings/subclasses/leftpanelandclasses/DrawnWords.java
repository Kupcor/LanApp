package ui.wordtrainings.subclasses.leftpanelandclasses;

import databases.Reader;

import java.util.ArrayList;

public class DrawnWords {
    private int correctAnswerIndex = (int) (Math.random() * 10);

    public ArrayList<ArrayList<String>> getDrawnWords(String filePath) throws Exception {
        Reader reader = new Reader();
        ArrayList<ArrayList<String>> wordsList = reader.getDataList(filePath);
        ArrayList<ArrayList<String>> finalDrawnWordsList = new ArrayList<ArrayList<String>>();
        ArrayList<String> drawnWord;

        for (int iterator = 0; iterator < 10; iterator++) {
            do {
                drawnWord = wordsList.get((int) (Math.random() * wordsList.size()));
            } while (finalDrawnWordsList.contains(drawnWord));
            finalDrawnWordsList.add(drawnWord);
        }

        return finalDrawnWordsList;
    }

    public void setCorrectAnswerIndex() {
        this.correctAnswerIndex = (int) (Math.random() * 10);;
    }

    public int getCorrectAnswerIndex() {return this.correctAnswerIndex;}
}
