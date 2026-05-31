package api.utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;

public class XLUtilities {

    public static FileInputStream inputStream;
    public static XSSFWorkbook workbook;
    public static XSSFSheet excelSheet;
    public static XSSFRow row;
    public static XSSFCell cell;

    public static String getCellValue(String fileName, String sheetName, int rowNo, int colNo) {
        String data = "";

        try {
            inputStream = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(inputStream);
            excelSheet = workbook.getSheet(sheetName);
            cell = excelSheet.getRow(rowNo).getCell(colNo);

            DataFormatter formatter = new DataFormatter();
            data = formatter.formatCellValue(cell);

            workbook.close();

        } catch (Exception e) {
            return "";
        }

        return data;
    }

    public static int getRowCount(String fileName, String sheetName) {
        try {
            inputStream = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(inputStream);
            excelSheet = workbook.getSheet(sheetName);

            int ttlRow = 0;

            for (Row row : excelSheet) {
                if (row != null && row.getPhysicalNumberOfCells() > 0) {
                    ttlRow++;
                }
            }

            workbook.close();

            System.out.println("Total no. of rows: " + ttlRow);

            return ttlRow;


        } catch (Exception e) {
            return 0;
        }
    }

    public static int getColCount(String fileName, String sheetName) {
        try {
            inputStream = new FileInputStream(fileName);
            workbook = new XSSFWorkbook(inputStream);
            excelSheet = workbook.getSheet(sheetName);

            int ttlCol = 0;

            Row headerRow = excelSheet.getRow(0);

            for (Cell cell : headerRow) {
                if (cell != null && !cell.toString().trim().isEmpty()) {
                    ttlCol++;
                }
            }

            System.out.println("Total no. of cols: " + ttlCol);

            workbook.close();

            return ttlCol;


        } catch (Exception e) {
            return 0;
        }
    }

}
