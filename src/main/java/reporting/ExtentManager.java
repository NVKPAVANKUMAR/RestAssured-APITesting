package reporting;

import com.aventstack.extentreports.ExtentTest;

import java.util.Objects;

/**
 * To set, get and remove ExtentTest and manage Thread safety.
 * <br>
 * Class is final to avoid extend.
 * <br><br>
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public final class ExtentManager {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private ExtentManager() {
    }

    /**
     * Get ExtentTest instance.
     *
     * @return ExtentTest
     */
    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    /**
     * Set ExtentTest instance
     *
     * @param extentTestReference ExtentTest reference variable.
     */
    static void setExtentTest(ExtentTest extentTestReference) {
        if (Objects.nonNull(extentTestReference))
            extentTest.set(extentTestReference);
    }

    /**
     * Removes the ExtentTest instance.
     */
    static void unload() {
        extentTest.remove();
    }
}


