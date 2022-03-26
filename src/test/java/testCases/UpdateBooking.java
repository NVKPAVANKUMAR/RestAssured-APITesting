package testCases;

import constants.ApplicationConstants;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import pojo.BookingDetails;
import reporting.Log;
import pojo.utilities.Api;

import java.util.Map;

/**
 * Represents API - UpdateBooking testing activities
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class UpdateBooking extends BaseTest {

    /**
     * Test the UpdateBooking api using pojo
     * 1. Collects the data from excelsheet
     * 2. Create new Booking ID
     * 3. Call the UpdateBooking APi for created ID
     * 4. Verify the response and status code
     *
     * @version 1.0
     */
    @Test(dataProvider = "DataFromExcel", description = "Update the booking")
    public void updateBooking(Map<String, String> testData) {

        BookingDetails bookingDetailsPost = getBookingDetails(testData);

        //Created a new booking
        Response getResponse = createBooking(bookingDetailsPost);
        String cookieValue = ApplicationConstants.cookieValue + createAuthToken();

        BookingDetails bookingDetails = getResponse.as(BookingDetails.class);
        bookingDetails.setLastname(testData.get("UpdateLastName"));

        //Sending the PUT request
        Response putResponse = Api.putAPI(ApplicationConstants.updateBooking(bookingId), bookingDetails, cookieValue, ApplicationConstants.jsonContentType);
        putResponse.then().assertThat().statusCode(ApplicationConstants.statusCode200);
        Log.info("UpdateBooking - Booking id updated with success code.");
        BookingDetails putBookingDetails = putResponse.as(BookingDetails.class);

        //Verify the received body data
        Assert.assertEquals(putBookingDetails.getFirstname(), testData.get("firstname"));
        Assert.assertEquals(putBookingDetails.getLastname(), testData.get("UpdateLastName"));
        Assert.assertEquals(putBookingDetails.getTotalprice(), Integer.parseInt(testData.get("totalprice")));
        Assert.assertEquals(putBookingDetails.getBookingdates().getCheckin(), testData.get("checkin"));
        Assert.assertEquals(putBookingDetails.getBookingdates().getCheckout(), testData.get("checkout"));
        writeRequestAndResponseInReport(writer.toString(), putResponse.prettyPrint());
        Log.info("UpdateBooking - Booking is updated successfully");

    }

}
