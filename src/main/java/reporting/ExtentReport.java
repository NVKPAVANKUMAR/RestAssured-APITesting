package reporting;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import constants.FrameworkConstants;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Preparing and setting up Extent report instances.
 *
 * <br>
 * Class is final to avoid extend.
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public final class ExtentReport {

    private static ExtentReports extentReports;

    /**
     * Private constructor to avoid external instantiation
     */
    private ExtentReport() {
    }

    /**
     * Setting up the test report information.
     */
    public static void initReports() {
        if (Objects.isNull(extentReports)) {
            // Setting the view order for the tabs in report
            ExtentSparkReporter spark = new ExtentSparkReporter(FrameworkConstants.NEW_REPORT_PATH)
                    .viewConfigurer()
                    .viewOrder().as(new ViewName[]{ViewName.DASHBOARD, ViewName.TEST})
                    .apply();

            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle(FrameworkConstants.REPORT_TITLE);
            spark.config().setReportName(FrameworkConstants.REPORT_NAME);
            spark.config().setEncoding(String.valueOf(StandardCharsets.UTF_8));
            spark.config().setTimeStampFormat(FrameworkConstants.DATE_TIME_FORMAT);

            // Setting up information
            extentReports = new ExtentReports();
            try {
                extentReports.setSystemInfo("Testing", "API Testing Demo");
                extentReports.setSystemInfo("Username", System.getProperty("user.name"));
                extentReports.setSystemInfo("OS", System.getProperty("os.name"));
                extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
                extentReports.setSystemInfo("ENV", "QA");
                extentReports.setSystemInfo("Java", System.getProperty("java.version"));
                extentReports.setAnalysisStrategy(AnalysisStrategy.TEST);
                extentReports.attachReporter(spark);
                Log.logInfo("Extent report is initialized successfully.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Setting up the ExtentTest instance.
     *
     * @param testCaseName current test case name.
     */
    public static void createTests(String testCaseName) {
        ExtentTest extentTest = extentReports.createTest(testCaseName);
        ExtentManager.setExtentTest(extentTest);
    }

    /**
     * Removing the ExtentReport instance.
     */
    public static void flushReport() {
        if (Objects.nonNull(extentReports)) {
            extentReports.flush();
            ExtentManager.unload();
            Log.logInfo("Extent report flushed successfully.");
        }
    }

    /**
     * Get the report path.
     *
     * @return Path of the execution report.
     */
    public static String getReportPath() {
        return FrameworkConstants.NEW_REPORT_PATH;
    }

}
