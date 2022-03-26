package pojo.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class DataProviderHelper {
    @DataProvider(name = "DataFromExcel")
    public Object[] data(Method method) throws IOException {
        List<Map<String, String>> testcaselist = ExcelSheetHelper.getExcelContent(
                method.getDeclaringClass().getSimpleName());
        System.out.println("============ : " + testcaselist.size());
        return testcaselist.toArray();
    }

}
