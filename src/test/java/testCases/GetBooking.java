package testCases;

import constants.ApplicationConstants;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.BookingDetails;
import reporting.Log;

import java.util.Map;

/**
 * Represents API - GetBooking testing activities
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class GetBooking extends BaseTest {

    /**
     * Test the GetBooking api using pojo
     * 1. Collects the data from excelsheet
     * 2. Create new Booking ID
     * 3. Call the GetBooking APi for created ID
     * 4. Verify the response and status code
     *
     * @author NVK PAVANKUMAR
     * @version 1.0
     */
    @Test(description = "Get Booking ID")
    public void getBookingIDs(Map<String, String> testData) {

        BookingDetails bookingDetailsPost = getBookingDetails(testData);

        Response response = createBooking(bookingDetailsPost);
        Log.info("GetBooking - Booking id is fetched with status code : " + response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), ApplicationConstants.statusCode200, "Successfully get Booking ID.");
        BookingDetails responseBookingDetails = response.as(BookingDetails.class);

        Assert.assertEquals(responseBookingDetails.getFirstname(), testData.get("firstname"));
        Assert.assertEquals(responseBookingDetails.getLastname(), testData.get("lastname"));
        Assert.assertEquals(responseBookingDetails.getTotalprice(), Integer.parseInt(testData.get("totalprice")));
        Assert.assertEquals(responseBookingDetails.getBookingdates().getCheckin(), testData.get("checkin"));
        Assert.assertEquals(responseBookingDetails.getBookingdates().getCheckout(), testData.get("checkout"));
        writeRequestAndResponseInReport(writer.toString(), response.prettyPrint());
        Log.info("GetBooking - Booking is fetched successfully.");
    }
}