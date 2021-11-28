import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TxtWriter {
    // saving map to txt
    public void saveToTxtFile(Map<String, Integer> map, String outputFilePath) {

        File outputFile = new File(outputFilePath);

        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(new FileWriter(outputFile));

            for (Map.Entry<String, Integer> entry : map.entrySet()) {

                bufferedWriter.write(entry.getKey() + ": " + entry.getValue());

                bufferedWriter.newLine();

            }
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
