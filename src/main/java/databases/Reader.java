package databases;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Reader {

    public Reader() {}

    public ArrayList<ArrayList<String>> getDataList(String filePath) throws Exception {
        assert filePath != null : "File path cannot be empty!";

        ArrayList<ArrayList<String>> allDataList = new ArrayList<ArrayList<String>>();
        ArrayList<String> oneDataSet = new ArrayList<String>();
        String line = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] characters = line.split(";");
                oneDataSet = new ArrayList<String>(Arrays.asList(characters));
                allDataList.add(oneDataSet);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDataList;
    }

    public String[] readTxtFile(String filePath) {
        assert filePath != null : "File path cannot be empty!";

        String[] allDataBasesList = new String[0];
        String line = null;

        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                allDataBasesList = line.split(";");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allDataBasesList;
    }

    public void saveNewDataOnFile(String filePath, ArrayList<String> tempDataArrayList) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\java\\databases\\data\\" + filePath));
        for (int iterator = 0; iterator < tempDataArrayList.size(); iterator++) {
            bufferedWriter.write(tempDataArrayList.get(iterator));
            if (iterator != tempDataArrayList.size() - 1) {
                bufferedWriter.write("\n");
            }
        }
        bufferedWriter.close();
    }

    public void createDataRecord(String newInformation, String filePath) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src\\main\\java\\databases\\data\\" + filePath, true));
        bufferedWriter.append("\n");
        bufferedWriter.append(newInformation);
        bufferedWriter.close();
    }

    public void createCSVFile(String filePath) {
        try {
            FileWriter fileWriter = new FileWriter("src\\main\\java\\databases\\data\\" + filePath +".csv");
            fileWriter.close();

            FileWriter appendWriter = new FileWriter("src\\main\\java\\databases\\DataBases.txt", true);
            BufferedWriter bw = new BufferedWriter(appendWriter);
            bw.write(";"+filePath + ".csv");
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
