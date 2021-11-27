import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
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

    public void saveToExcel(Map<String, Integer> map, FileOutputStream fileOutputStream)  {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet countOccurrencesSheet = workbook.createSheet("Count Occurences");

        countOccurrencesSheet.setColumnWidth(0,6000);
        countOccurrencesSheet.setColumnWidth(1,6000);

        Row header = countOccurrencesSheet.createRow(0);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Words");

        headerCell = header.createCell(1);
        headerCell.setCellValue("Occurrences");

        Set<String> keySet = map.keySet();

        int rowNumber = 1;

        for (Map.Entry<String, Integer> entry: map.entrySet()){

            Row row = countOccurrencesSheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue());

        }

        try {
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            System.out.println("Excel Sheet generated successfully");
        } catch (IOException e) {
            e.printStackTrace();
        }







    }
}
