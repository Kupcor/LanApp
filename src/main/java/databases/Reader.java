package databases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
}
