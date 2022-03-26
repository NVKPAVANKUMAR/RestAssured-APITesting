package pojo;

/**
 * Represents pojo class to store the Request and Response
 * for the BookingDates object.<br>
 * <p>
 * This class uses the fluent design pattern.
 *
 * @author NVK PAVANKUMAR
 * @version 1.0
 */
public class BookingDates {

    /**
     * checkin and checkout date
     */
    private String checkin;
    private String checkout;

    public BookingDates() {
    }

    /**
     * This method initialize the
     * members values while creating the object.
     *
     * @param checkin  -> checkin date of booking.
     * @param checkout -> checkout date of booking.
     */
    public BookingDates(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    /**
     * Get the value of checkin date.
     *
     * @return checkin -> return checkin date
     */
    public String getCheckin() {
        return checkin;
    }

    /**
     * Set the value of checkin date.
     *
     * @param checkin -> checkin date of booking
     * @return BookingDates -> return current object
     */
    public BookingDates setCheckin(String checkin) {
        this.checkin = checkin;
        return this;
    }

    /**
     * Get the value of checkout date.
     *
     * @return checkout -> return checkout date
     */
    public String getCheckout() {
        return checkout;
    }

    /**
     * Set the value of checkout date.
     *
     * @param checkout -> checkout date of booking.
     * @return BookingDates -> return current object
     */
    public BookingDates setCheckout(String checkout) {
        this.checkout = checkout;
        return this;
    }
}
