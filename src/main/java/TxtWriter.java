import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TxtWriter {

    private Map<String, Integer> map = new HashMap<>();

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

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
