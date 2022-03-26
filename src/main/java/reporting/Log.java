package reporting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents the helper functions to log messages
 * with contextual information about the current line.
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class Log {

    private static final Logger log = LogManager.getLogger(Log.class);

    /**
     * Report status as info with message in extent report and log file.
     *
     * @param message -> The message which needs to log.
     */
    public static void info(String message) {
        ExtentManager.getExtentTest().info(message);
        log.info(message);
    }

    /**
     * Report status as fail with message in report and log file.<br>
     *
     * @param message which need to log.
     */
    public static void fail(String message) {
        ExtentManager.getExtentTest().fail(message);
        log.error(message);
    }

    /**
     * Report status as fail with stack trace message in extent report and log file.
     *
     * @param message -> report the message.
     */
    public static void fail(Throwable message) {
        ExtentManager.getExtentTest().fail(message);
        log.error(message);
    }

    /**
     * WARN-level log output in extent report and log file.
     *
     * @param message -> warning message which wanted to log.
     */
    public static void warn(String message) {
        ExtentManager.getExtentTest().warning(message);
        log.warn(message);
    }

    /**
     * Report status as skip with exception and screenshot in extent report.
     *
     * @param message which need to log.
     */
    public static void skip(Throwable message) {
        ExtentManager.getExtentTest().skip(message);
        log.error(message);
    }

    /**
     * Report information message in log file only.
     *
     * @param message which need to log.
     */
    public static void logInfo(String message) {
        log.info(message);
    }

}