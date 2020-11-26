package com.example.barbershop.Model;

public class Booking {

    private int booking_id;
    private String customer_id;
    private String barber_id;
    private int shift_id;
    private int service_id;

//    (booking_id INTEGER PRIMARY KEY, customer_id INTEGER, barber_id INTEGER, shift_id INTEGER, service_id INTEGER, comment TEXT)";

    public Booking(int booking_id, String customer_id, String barber_id, int shift_id, int service_id) {
        this.booking_id = booking_id;
        this.customer_id = customer_id;
        this.barber_id = barber_id;
        this.shift_id = shift_id;
        this.service_id = service_id;
    }
    public Booking( String customer_id, String barber_id, int shift_id, int service_id) {

        this.customer_id = customer_id;
        this.barber_id = barber_id;
        this.shift_id = shift_id;
        this.service_id = service_id;
    }

    public Booking() {
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getBarber_id() {
        return barber_id;
    }

    public void setBarber_id(String barber_id) {
        this.barber_id = barber_id;
    }

    public int getShift_id() {
        return shift_id;
    }

    public void setShift_id(int shift_id) {
        this.shift_id = shift_id;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }


}
