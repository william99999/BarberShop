package com.example.barbershop.Model;

import java.util.Date;

public class Shift {

    private int shift_id;
    private int barber_id;
    private String start_time;
    private String end_time;
    private int status;

//    (shift_id INTEGER PRIMARY KEY, barber_id INTEGER, start_time TEXT, end_time TEXT)
    public Shift() {
    }

    public Shift(int shift_id, int barber_id, String start_time, String end_time, int status) {
        this.shift_id = shift_id;
        this.barber_id = barber_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getShift_id() {
        return shift_id;
    }

    public void setShift_id(int shift_id) {
        this.shift_id = shift_id;
    }

    public int getBarber_id() {
        return barber_id;
    }

    public void setBarber_id(int barber_id) {
        this.barber_id = barber_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
