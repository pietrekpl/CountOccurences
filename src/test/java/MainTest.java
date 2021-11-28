import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void calculateFileSizeMb() {
        // given
        // put a path to txt file
        File file = new File(getClass().getResource("10mb.txt").getPath());
        Path path = file.toPath();
        // when
        double actualFileSize = Main.calculateFileSizeMb(path);
        // then
        assertEquals(10, actualFileSize);

    }


    @Test()
    void shouldOccurException() {
        //given
        File file = new File("/sample.docx");
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            //when , then
             Main.checkFileExtension(file.toPath());
        });


    }

}