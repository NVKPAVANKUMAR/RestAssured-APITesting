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
 * Represents API - PartialUpdateBooking testing activities
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class PartialUpdateBooking extends BaseTest {

    /**
     * Test the PartialUpdateBooking api using pojo
     * 1. Collects the data from excelsheet
     * 2. Create new Booking ID
     * 3. Call the PartialUpdateBooking APi for created ID
     * 4. Verify the response and status code
     *
     * @version 1.0
     */
    @Test(description = "Update the partial booking")
    public void partialUpdateBooking(Map<String, String> testData) {

        BookingDetails bookingDetails = getBookingDetails(testData);

        //Created a new booking
        Response getResponse = createBooking(bookingDetails);
        String cookieValue = ApplicationConstants.cookieValue + createAuthToken();
        bookingDetails = getResponse.as(BookingDetails.class);
        String setValue = "{\"firstname\":\"sam\"}";


        //Sending the PATCH request
        Response patchResponse = Api.patchAPI(ApplicationConstants.updateBooking(bookingId), setValue, cookieValue, ApplicationConstants.jsonContentType);
        patchResponse.then().assertThat().statusCode(ApplicationConstants.statusCode200);
        Log.info("PartialUpdateBooking - Booking id updated with success code.");
        BookingDetails putBookingDetails = patchResponse.as(BookingDetails.class);

        Assert.assertEquals(putBookingDetails.getFirstname(), "sam");
        Assert.assertEquals(putBookingDetails.getLastname(), testData.get("lastname"));
        Assert.assertEquals(putBookingDetails.getTotalprice(), Integer.parseInt(testData.get("totalprice")));
        Assert.assertEquals(putBookingDetails.getBookingdates().getCheckin(), testData.get("checkin"));
        Assert.assertEquals(putBookingDetails.getBookingdates().getCheckout(), testData.get("checkout"));
        writeRequestAndResponseInReport(writer.toString(), patchResponse.prettyPrint());
        Log.info("PartialUpdateBooking - Partially Booking is updated successfully");
    }

}
