package pojo;

/**
 * Represents pojo class to store the request and response
 * for the BookingDetails object.<br>
 * <p>
 * This class uses the fluent design pattern.
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class BookingDetails {

    private String firstname;
    private String lastname;
    private int totalprice;
    private Boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;

    public BookingDetails() {
    }

    /**
     * This method initialize the members values while creating the object for
     * booking details.
     *
     * @param firstname       -> firstname of booking.
     * @param lastname        -> lastname of booking.
     * @param totalprice      -> totalprice of booking.
     * @param depositpaid     -> Whether deposit paid or not.
     * @param additionalneeds -> Any additional needs of booking.
     * @param bookingDates    -> represent the bookingDates object.
     * @author NVK PAVANKUMAR
     */
    public BookingDetails(String firstname, String lastname, int totalprice, Boolean depositpaid, String additionalneeds, BookingDates bookingDates) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.additionalneeds = additionalneeds;
        this.bookingdates = bookingDates;
    }

    /**
     * Get the firstname of booking.
     *
     * @return firstname -> firstname of booking.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Set the firstname in bookingDetails
     *
     * @param firstname -> firstname for booking.
     * @return BookingDetails
     * -> return bookingDetails as same object.
     */
    public BookingDetails setFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    /**
     * Get the lastname of booking.
     *
     * @return lastname -> lastname of booking.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Set the lastname in bookingDetails.
     *
     * @param lastname -> lastname for booking.
     * @return BookingDetails
     * -> return bookingDetails as same object.
     */
    public BookingDetails setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    /**
     * Get the totalprice of booking.
     *
     * @return totalprice -> totalprice of booking.
     */
    public int getTotalprice() {
        return totalprice;
    }

    /**
     * Set the totalprice in bookingDetails.
     *
     * @param totalprice -> totalprice for booking.
     * @return BookingDetails
     * -> return bookingDetails as same object.
     */
    public BookingDetails setTotalprice(int totalprice) {
        this.totalprice = totalprice;
        return this;
    }

    /**
     * Get the status of depositpaid or not for booking.
     *
     * @return depositpaid -> status of depositpaid.
     */
    public Boolean getDepositpaid() {
        return depositpaid;
    }

    /**
     * Set the depositpaid in bookingDetails.
     *
     * @param depositpaid -> depositpaid for booking.
     * @return BookingDetails
     * -> return bookingDetails as same object.
     */
    public BookingDetails setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
        return this;
    }

    /**
     * Get the bookingdates as object.
     *
     * @return bookingdates -> bookingdates as object.
     */
    public BookingDates getBookingdates() {
        return bookingdates;
    }

    /**
     * Set the bookingdates in bookingDetails.
     *
     * @param bookingdates -> bookingdates for booking.
     * @return BookingDetails
     * -> return bookingDetails as same object.
     */
    public BookingDetails setBookingdates(BookingDates bookingdates) {
        this.bookingdates = bookingdates;
        return this;
    }

    /**
     * Get the additionalneeds if required for booking.
     *
     * @return additionalneeds -> additionalneeds of booking.
     */
    public String getAdditionalneeds() {
        return additionalneeds;
    }

    /**
     * Set the additionalneeds in bookingDetails.
     *
     * @param additionalNeeds -> additionalneeds for booking.
     * @return BookingDetails
     * -> return bookingDetails as same object.
     */
    public BookingDetails setAdditionalneeds(String additionalNeeds) {
        this.additionalneeds = additionalNeeds;
        return this;
    }
}
