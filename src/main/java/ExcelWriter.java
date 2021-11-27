import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ExcelWriter {

    public void saveToExcel(Map<String, Integer> map, FileOutputStream fileOutputStream)  {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Count Occurences");

        sheet.setColumnWidth(0,6000);
        sheet.setColumnWidth(1,6000);

        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        cellStyle.getFont().setBold(true);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);


        Row header = sheet.createRow(0);

        Cell headerCell = header.createCell(0);
        headerCell.setCellValue("Words");


        headerCell = header.createCell(1);
        headerCell.setCellValue("Occurrences");


        Set<String> keySet = map.keySet();

        int rowNumber = 1;

        for (Map.Entry<String, Integer> entry: map.entrySet()){

            Row row = sheet.createRow(rowNumber++);
            row.createCell(0).setCellValue(entry.getKey());
            row.createCell(1).setCellValue(entry.getValue());
        }

        Iterator<Row> rowIterator =  sheet.rowIterator();

        while(rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                cell.setCellStyle(cellStyle);
            }
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
