import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ExcelWriter {





    // to complete the rest of a class
    public void saveToExcel(Map<String, Integer> map, FileOutputStream fileOutputStream) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet countOccurencesSheet = workbook.createSheet("Count occurrences");

        Set<String> keySet = map.keySet();

        int rowNumber = 0;

        for (Map.Entry<String, Integer> entry: map.entrySet()){

            Row row = countOccurencesSheet.createRow(rowNumber++);

            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue());
        }


        workbook.write(fileOutputStream);
        fileOutputStream.close();

        System.out.println("Succesfuly written");




    }
}
