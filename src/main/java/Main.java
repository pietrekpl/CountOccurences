import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    static final String FILE = "C:\\Users\\Lenovo\\Desktop\\10mb.txt";

    public static void main(String[] args) {

        Path path = Paths.get(FILE);

        double fileSizeMb = 0;

        try {
             fileSizeMb = (double) (Files.size(path)/ 1024 ) / 1024;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileSizeMb > 5) {
            throw new IllegalArgumentException("File size should be max 5 MB");
        }
        else {
            /// to do counting staff
        }
    }
}
