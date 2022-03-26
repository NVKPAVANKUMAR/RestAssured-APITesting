package testUtilities;

import constants.ApplicationConstants;
import io.restassured.response.Response;
import org.junit.Assert;
import pojo.BookingDates;
import pojo.BookingDetails;
import reporting.Log;
import pojo.utilities.Api;
import pojo.utilities.CommonHelper;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a application layer to do API Testing
 * It uses the rest assured reusable functionality to
 * perform application level api testing.
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class ApplicationHelper {

    public String bookingId;

    public static String encode(String string) {
        return Base64.getEncoder().encodeToString(string.getBytes());
    }

    public static String decode(String string) {
        return new String(Base64.getDecoder().decode(string));
    }

    /**
     * Creates the Authentication payload.
     *
     * @return returns the authentication payload.
     * @author NVK PAVANKUMAR
     */
    private Map<String, String> getAuthPayload() {
        Map<String, String> authPayload = new HashMap<String, String>();
        String username = CommonHelper.readConfigurationFile("username");
        String password = CommonHelper.readConfigurationFile("password");
        authPayload.put("username", username);
        authPayload.put("password", password);
        return authPayload;
    }

    /**
     * Creates the Authentication token to do further api actions.
     *
     * @return returns the authentication token.
     * @author NVK PAVANKUMAR
     */
    public String createAuthToken() {

        /*******************************************************
         * Send a POST request to /auth and check that the response has HTTP
         * status code 200
         ******************************************************/

        Map<String, String> authPayload = getAuthPayload();

        Response response = Api.postAPI(ApplicationConstants.postAuth, authPayload);
        Assert.assertEquals("Authentication Token is created successfully.", response.statusCode(),
                ApplicationConstants.statusCode200);
        String token = response.then().extract().path("token");
        Log.info("ApplicationHelper - Token : " + token);
        return token;
    }

    /**
     * Calls the GetBooking API call.
     *
     * @param bookingID -> booking id to get the booking details
     * @return response -> response of GetBooking API call.
     * @author NVK PAVANKUMAR
     */
    public Response getBooking(String bookingID) {
        Response response = Api.getAPI(ApplicationConstants.getBookingId(bookingId));
        response.then().assertThat().statusCode(200);
        Log.info("ApplicationHelper - Booking id received successfully");
        return response;
    }

    /**
     * Calls the API to create the CreateBooking API call.
     * 1. It call the Post method to create the booking
     * 2. Get the Booking ID and check the success message
     * 3. Using same booking id, get the response for
     * verification of data
     *
     * @param bodyPayload -> data(Body) to create the booking
     * @return response -> response of CreateBooking API call.
     * @author NVK PAVANKUMAR
     */
    public Response createBooking(Object bodyPayload) {

        //Create booking
        Response response = Api.postAPI(ApplicationConstants.createBooking, bodyPayload);
        Assert.assertEquals("CreateBooking ID status code is 200.", response.statusCode(),
                ApplicationConstants.statusCode200);
        Log.info("ApplicationHelper - CreateBooking ID status code is 200.");

        //Get newly created booking id
        bookingId = response.then().extract().path("bookingid").toString();
        Log.info("ApplicationHelper - Newly created Id is : " + bookingId);

        //Verify created booking id by calling GetBooking
        response = Api.getAPI(ApplicationConstants.getBookingId(bookingId));
        Assert.assertEquals("New Booking is created successfully.",
                response.statusCode(), ApplicationConstants.statusCode200);
        Log.info("ApplicationHelper - Booking is created successfully");
        return response;
    }

    /**
     * Store data from map to BookingDetail object
     *
     * @param testData -> data which need to map into object
     * @return return the BookingDetails object.
     * @author NVK PAVANKUMAR
     */
    public BookingDetails getBookingDetails(Map<String, String> testData) {
        BookingDates bookingDates = new BookingDates()
                .setCheckin(testData.get("checkin"))
                .setCheckout(testData.get("checkout"));
        return new BookingDetails()
                .setFirstname(testData.get("firstname"))
                .setLastname(testData.get("lastname"))
                .setTotalprice(Integer.parseInt(testData.get("totalprice")))
                .setDepositpaid(Boolean.parseBoolean(testData.get("depositpaid")))
                .setAdditionalneeds(testData.get("additionalneeds"))
                .setBookingdates(bookingDates);
    }

}
