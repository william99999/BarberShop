package com.example.barbershop.Model;

public class Booking {

    private int booking_id;
    private int customer_id;
    private int barber_id;
    private int shift_id;
    private int service_id;
    private String comment;

//    (booking_id INTEGER PRIMARY KEY, customer_id INTEGER, barber_id INTEGER, shift_id INTEGER, service_id INTEGER, comment TEXT)";

    public Booking(int booking_id, int customer_id, int barber_id, int shift_id, int service_id, String comment) {
        this.booking_id = booking_id;
        this.customer_id = customer_id;
        this.barber_id = barber_id;
        this.shift_id = shift_id;
        this.service_id = service_id;
        this.comment = comment;
    }

    public Booking() {
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getBarber_id() {
        return barber_id;
    }

    public void setBarber_id(int barber_id) {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
