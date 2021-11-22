import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    static final String FILE = "C:\\Users\\Lenovo\\Desktop\\test.txt";

    public static void main(String[] args) {

        Path path = Paths.get(FILE);

        double fileSizeMb = 0;

        try {
            fileSizeMb = (double) (Files.size(path) / 1024) / 1024;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileSizeMb > 5) {
            throw new IllegalArgumentException("File size should be max 5 MB");
        } else {

            Map<String, Integer> map = new LinkedHashMap<>();


            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE))) {
                StringBuilder builder = new StringBuilder();
                // preparing file input, change all words to lower case, eliminate dots and comas
                String line = bufferedReader.readLine().toLowerCase().replaceAll("\\.", "").replaceAll(",", "");


                //counting words and putting them into map
                while (line != null) {
                    String[] words = line.split(" ");
                    for (int i = 0; i < words.length; i++) {
                        if (map.get(words[i]) == null) {
                            map.put(words[i], 1);
                        } else {
                            int value = Integer.parseInt(String.valueOf(map.get(words[i])));
                            value++;
                            map.put(words[i], value);
                        }
                    }
                    builder.append(System.lineSeparator());
                    line = bufferedReader.readLine();

                    // sorting in descending order
                    map = map.entrySet().stream().sorted(Comparator.comparing(
                            Map.Entry<String, Integer>::getValue).reversed()).collect(
                            LinkedHashMap<String, Integer>::new,
                            (map1, e) -> map1.put(e.getKey(), e.getValue()),
                            LinkedHashMap::putAll);

                    map.forEach((k, v) -> System.out.println(k + ": " + v));

                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
