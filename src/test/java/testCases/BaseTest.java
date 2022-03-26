package testCases;

import com.aventstack.extentreports.Status;
import constants.FrameworkConstants;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.apache.commons.io.output.WriterOutputStream;
import reporting.ExtentManager;
import reporting.Log;
import testUtilities.ApplicationHelper;
import pojo.utilities.Api;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Represent TestCases Parent class.
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class BaseTest extends ApplicationHelper {
    protected StringWriter writer;
    protected PrintStream printStrObj;

    // Initialization to start API testing
    @BeforeClass
    public void initSpecification() {
        Log.logInfo("BaseTest - Initializing the Request Specification.");
        Api.configReqestSpecification();
    }

    @BeforeMethod
    public void setUp() {
        writer = new StringWriter();
        printStrObj = new PrintStream(new WriterOutputStream(writer), true);
    }

    /*
     * Format the api string and log in Extent Report
     * @author : NVK PAVANKUMAR
     * @param  : content
     */
    protected void formatAPIAndLogInReport(String content) {
        String prettyPrint = content.replace("\n", "<br>");
        ExtentManager.getExtentTest().log(Status.INFO,"<pre>" + prettyPrint + "</pre>");
    }

    /*
     * Read the json file and convert to String
     * @author : NVK PAVANKUMAR
     * @param  : filepath
     */

    public String generateStringFromResource(String path) throws IOException {
        String temp = "";
        try {
            temp = new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    public void writeRequestAndResponseInReport(String request, String response) {
        ExtentManager.getExtentTest().log(Status.INFO, "---- Request ---");
        formatAPIAndLogInReport(request);
        ExtentManager.getExtentTest().log(Status.INFO,"---- Response ---");
        formatAPIAndLogInReport(response);
    }

    @AfterSuite
    public void afterSuite() throws IOException {
        File htmlFile = new File(FrameworkConstants.NEW_REPORT_PATH);
        Desktop.getDesktop().browse(htmlFile.toURI());
    }
}
