package testCases;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.BookingDetails;
import reporting.Log;

import java.util.Map;

/**
 * Represents API - CreateBooking testing activities
 *
 * @author NVK PAVANKUMAR
 * @author https://github.com/NisVek-Automation
 * @version 1.0
 */
public class CreateBooking extends BaseTest {

    public static String newID = "";

    /**
     * Test the CreateBooking api using pojo
     * 1. Collects the data from excelsheet
     * 2. Create new Booking ID
     * 3. Verify the response and status code
     *
     * @author NVK PAVANKUMAR
     * @version 1.0
     */
    @Test(description = "Create the booking")
    public void createNewBooking(Map<String, String> testData) {
        BookingDetails bookingDetailsPost = getBookingDetails(testData);
        //Created a new booking
        Response getResponse = createBooking(bookingDetailsPost);
        writeRequestAndResponseInReport(writer.toString(), getResponse.prettyPrint());
        Log.info("CreateBooking - Booking is created successfully.");
    }
}
