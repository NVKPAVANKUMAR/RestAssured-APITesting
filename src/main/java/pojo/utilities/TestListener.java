package pojo.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporting.ExtentReport;
import reporting.Log;


/**
 * Represents a Listener derived from ITestListener.
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class TestListener implements ITestListener {

    private static String testcaseName;

    // Before all Test case started, this method is called.
    @Override
    public void onStart(ITestContext iTestContext) {
        Log.logInfo("======================================================================");
        Log.logInfo("============ Starting the Suite : " + iTestContext.getName() + " ============");
        ExtentReport.initReports();
        //ExtentTestManager.startTest(iTestContext.getName(), "Started");
    }

    // After all Test case executed, this method is called.
    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.logInfo("============ Ending the Suite : " + iTestContext.getName() + "=============");
        Log.logInfo("======================================================================");
        ExtentReport.flushReport();
        //ExtentManager.getExtentReports().flush();
    }

    // When Test case started, this method is called.
    @Override
    public void onTestStart(ITestResult iTestResult) {
        testcaseName = iTestResult.getMethod().getMethodName();
        ExtentReport.createTests(testcaseName);
        Log.info("************* TestCase is started : " + testcaseName + " *************");
    }

    // When Test case get passed, this method is called.
    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info("************* TestCase is succeed : " + testcaseName + " *************");
    }

    // When Test case get failed, this method is called.
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.fail(iTestResult.getThrowable().getMessage());
        Log.fail(iTestResult.getThrowable());
        Log.fail("********** Testcase is failed : " + testcaseName + " *************");
    }

    // When Test case get Skipped, this method is called.
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.skip(iTestResult.getThrowable());
        Log.info("************* TestCase is skipped : " + testcaseName + " *************");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Log.info("Test failed but it is in defined success ratio " + testcaseName);
    }
}
