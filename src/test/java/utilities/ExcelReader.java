
package utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {  

	

    public static Map<String, Map<String, String>> getDataForSheet(String sheetName) {


        Map<String, Map<String, String>> dataMap = new HashMap<>();

        try {
        	String testDataFilePath = ConfigReader.getProperty("test_data_file_path"); 
            FileInputStream fis = new FileInputStream(testDataFilePath);

            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheet(sheetName);

            Row headerRow = sheet.getRow(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = headerRow.getPhysicalNumberOfCells();

            for (int i = 1; i < rowCount; i++) {
                Row currentRow = sheet.getRow(i);
                if (currentRow == null) continue;

                Map<String, String> rowData = new HashMap<>();

                for (int j = 1; j < colCount; j++) {

                    Cell keyCell = headerRow.getCell(j);
                    if (keyCell == null) continue;

                    String key = keyCell.toString().trim();
                    Cell valueCell = currentRow.getCell(j);

                    String value = getCellValue(valueCell);

                    rowData.put(key, value);
                }
                Cell firstCell = currentRow.getCell(0);//

                if (firstCell == null) {
                    System.out.println("Skipping row " + i + " because key column is empty");
                    continue;
                }

                String rowKey = firstCell.toString().trim();

                dataMap.put(rowKey, rowData);
            }

            workbook.close();
            fis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return dataMap;
    }
    
    public static Map<String, Map<String, String>> getLoginData() {
    	return getDataForSheet(ConfigReader.getProperty("login_data_sheet_name"));
    }
    public static Map<String, Map<String, String>> getEditorData() {
    	return getDataForSheet(ConfigReader.getProperty("editor_data_sheet_name"));
    }
    public static Map<String, Map<String, String>> getArraydataData() {
    	return getDataForSheet(ConfigReader.getProperty("ArrayData_data_sheet_name"));
    }

    public static Map<String, Map<String, String>> getArrayTryData() {
    	return getDataForSheet(ConfigReader.getProperty("ArrayTry_data_sheet_name"));
    }


    private static String getCellValue(Cell cell) {

        if (cell == null)
            return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();

            case NUMERIC:
                return String.valueOf((int) cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            default:
                return "";
        }
    }

}
