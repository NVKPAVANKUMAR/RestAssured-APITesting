package testCases;

import constants.ApplicationConstants;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.BookingDetails;
import reporting.Log;
import pojo.utilities.Api;

import java.util.Map;

/**
 * Represents API - DeleteBooking testing activities
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class DeleteBooking extends BaseTest {

    /**
     * Test the DeleteBooking api using pojo
     * 1. Collects the data from excelsheet
     * 2. Create new Booking ID
     * 3. Call the DeleteBooking APi for created ID
     * 4. Verify the response and status code
     *
     * @version 1.0
     */
    @Test(description = "Delete the booking")
    public void deleteBookingIDs(Map<String, String> testData) {


        /*******************************************************
         * Send a DELETE request to /booking/{id}
         * and check that the response has HTTP status code 200
         ******************************************************/
        BookingDetails bookingDetailsPost = getBookingDetails(testData);
        //Created a new booking
        Response response = createBooking(bookingDetailsPost);
        String cookieValue = ApplicationConstants.cookieValue + createAuthToken();

        //Delete the booking
        response = Api.deleteAPI(ApplicationConstants.deleteBooking(bookingId), cookieValue, ApplicationConstants.jsonContentType);
        response.then().assertThat().statusCode(200);
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
        Log.info("DeleteBooking - Booking is deleted successfully");

    }
}
