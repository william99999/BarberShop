package com.example.barbershop.Model;

public class Service {

    private int service_id;
    private String service_name;
    private String description;
    private double rate;
//    String service_table = "CREATE TABLE " + SERVICE_TABLE + " (service_id INTEGER PRIMARY KEY, service_name TEXT, description TEXT, rate REAL)";


    public Service() {
    }

    public Service(int service_id, String service_name, String description, double rate) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.description = description;
        this.rate = rate;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
