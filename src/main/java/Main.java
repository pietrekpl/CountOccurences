import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.zip.ZipOutputStream;

public class Main {

    public Main() throws FileNotFoundException {
    }

    public static void main(String[] args) throws IOException {
        // setting the destination of a test file
        final String FILE = "C:\\Users\\Lenovo\\Desktop\\test.txt";
        // setting the destination to output txt file
        final String OUTPUT_TXT = "C:\\Users\\Lenovo\\Desktop\\output.txt";

        // setting excel destination down below in class, to write in line 75
        FileOutputStream EXCEL_FILE_INPUT_STREAM;
        // setting zip archive destination down below in class, to write in line 80
        FileOutputStream ZIP_OUTPUT_STREAM;

        ZipOutputStream zos;

        Path path = Paths.get(FILE);

        //checking is entry file has a .txt extension
        checkFileExtension(path);

        if (calculateFileSizeMb(path) > 5) {
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

                    // writing map to .txt file
                    TxtWriter txtWriter = new TxtWriter();
                    txtWriter.saveToTxtFile(map, OUTPUT_TXT);

                    File file = new File(OUTPUT_TXT);

                    // if above OUTPUT_TXT file exist, should be processed for rest of operations : saving to excel and zip
                    if (file.exists()) {

                        // saving map to excel in .xlsx format
                        EXCEL_FILE_INPUT_STREAM = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\output.xlsx");
                        ExcelWriter excelWriter = new ExcelWriter();
                        excelWriter.saveToExcel(map, EXCEL_FILE_INPUT_STREAM);

                        // saving OUTPUT_TXT as zip
                        ZIP_OUTPUT_STREAM = new FileOutputStream("C:\\Users\\Lenovo\\Desktop\\result.zip");
                        zos = new ZipOutputStream(ZIP_OUTPUT_STREAM);
                        FileToZip fileToZip = new FileToZip();
                        fileToZip.zipFile(OUTPUT_TXT, zos);
                        zos.close();
                        ZIP_OUTPUT_STREAM.close();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // method to calculate file size from bytes to Mb
    public static double calculateFileSizeMb(Path path) {

        double fileSizeMb = 0;

        try {
            fileSizeMb = (double) (Files.size(path) / 1024) / 1024;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileSizeMb;

    }

    public static void checkFileExtension(Path path) {
        if (!path.toFile().getName().endsWith(".txt")) {
            throw new IllegalArgumentException("File should be in .txt file");
        }

    }

}
