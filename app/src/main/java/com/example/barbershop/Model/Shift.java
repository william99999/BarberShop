package com.example.barbershop.Model;

import java.util.Date;

public class Shift {

    private int shift_id;
    private String barber_id;
    private String date;
    private String time;

//    (shift_id INTEGER PRIMARY KEY, barber_id INTEGER, start_time TEXT, end_time TEXT)
    public Shift() {
    }

    public Shift(int shift_id, String barber_id, String date, String time, int status) {
        this.shift_id = shift_id;
        this.barber_id = barber_id;
        this.date = date;
        this.time = time;
    }



    public int getShift_id() {
        return shift_id;
    }

    public void setShift_id(int shift_id) {
        this.shift_id = shift_id;
    }

    public String getBarber_id() {
        return barber_id;
    }

    public void setBarber_id(String barber_id) {
        this.barber_id = barber_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
