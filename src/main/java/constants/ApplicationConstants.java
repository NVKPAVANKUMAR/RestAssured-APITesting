package constants;

/**
 * Represents Project specific property/constant values.
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class ApplicationConstants {


    public static String baseURI = "https://restful-booker.herokuapp.com";

    //API Resources to make call
    public static String getBookingIds = "/booking";
    public static String postAuth = "/auth";
    public static String createBooking = "/booking";
    public static String jsonContentType = "application/json";
    public static String cookieValue = "token=";
    //Status code
    public static int statusCode200 = 200;
    public static int statusCode201 = 201;

    public static String getBookingId(String id) {
        return "/booking/" + id;
    }

    public static String updateBooking(String id) {
        return "/booking/" + id;
    }

    public static String deleteBooking(String id) {
        return "/booking/" + id;
    }

}
