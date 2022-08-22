package databases;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ReaderTest {

    @Test
    public void testGetWordsList() {
        Reader reader = new Reader();
        ArrayList<ArrayList<String>> testArrayList = new ArrayList<ArrayList<String>>();
        String pathToTestFile = "src\\test\\java\\databases\\testFile.csv";
        int helpVarTestNumber = 1;
        
        try {
            reader.getDataList(null);
            Assertions.fail("Data from null file loaded. Unexpected behaviour.");
        } catch (Exception e) {
            //  Work correctly
        }
        
        try {
            testArrayList = reader.getDataList(pathToTestFile);
        } catch (Exception e) {
            Assertions.fail("Data from file have not been loaded.");
        }

        Assertions.assertFalse(testArrayList.isEmpty());
        for (ArrayList<String> entity : testArrayList) {
            Assertions.assertFalse(entity.isEmpty());
            Assertions.assertEquals(entity.get(0), ("englishWordTest" + helpVarTestNumber));
            Assertions.assertEquals(entity.get(1), ("polishWordTest" + helpVarTestNumber));
            Assertions.assertEquals(entity.get(2), ("sentenceTest" + helpVarTestNumber));
            ++helpVarTestNumber;
        }
    }


}
