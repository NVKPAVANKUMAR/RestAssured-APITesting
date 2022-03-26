package pojo.utilities;

import constants.FrameworkConstants;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExcelSheetHelper {

    private ExcelSheetHelper() {
    }

    /**
     * This method reads each cell data from ExcelSheet and store all the rows as maps in the list.
     *
     * @param sheetName -> sheetName from ExcelSheet.
     * @return testDataList -> It returns list of map.
     */
    public static List<Map<String, String>> getExcelContent(String sheetName) throws FileNotFoundException, IOException {
        Sheet sheet = getExcelSheet(sheetName);
        int lastRowNum = sheet.getLastRowNum();
        short lastColNum = sheet.getRow(0).getLastCellNum();

        List<Map<String, String>> testDataList = new ArrayList<>();
        Map<String, String> testDataMap = null;

        for (int row = 1; row < lastRowNum + 1; row++) {
            testDataMap = new HashMap<>();
            for (int col = 0; col < lastColNum; col++) {
                String key = sheet.getRow(0).getCell(col).getStringCellValue();
                String value = sheet.getRow(row).getCell(col).getStringCellValue();
                testDataMap.put(key, value);
            }
            testDataList.add(testDataMap);
        }
        return testDataList;
    }

    /**
     * This method provides the data sheet from workbook.
     *
     * @param sheetName -> name of the sheet
     * @return Sheet -> It returns the sheet object from workbook.
     */
    private static Sheet getExcelSheet(String sheetName) {
        Workbook workbook = null;
        Sheet sheet;
        try (FileInputStream fileStream = new FileInputStream(FrameworkConstants.DATASHEET_PATH)) {
            workbook = WorkbookFactory.create(fileStream);
            sheet = workbook.getSheet(sheetName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Excel file not found to read the data.");
        } catch (Exception e) {
            throw new RuntimeException("Exception while working with Excel file.");
        }

        return sheet;
    }
	
	
	/*
	static boolean isConditionsMatching(Method method, List<Map<String, String>> alldatalist, int i) {
		return isTestCaseNameMatching(method, alldatalist, i) && isRunnable(alldatalist, i);
	}

	private static boolean isRunnable(List<Map<String, String>> alldatalist, int i) {
		return alldatalist.get(i).get("execute").equalsIgnoreCase("yes");
	}

	private static boolean isTestCaseNameMatching(Method m, List<Map<String, String>> alldatalist, int i) {
		return alldatalist.get(i).get("testname").equalsIgnoreCase(m.getName());
	}
	*/

}
