package com.example.barbershop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.barbershop.Model.Booking;
import com.example.barbershop.Model.Service;
import com.example.barbershop.Model.Shift;
import com.example.barbershop.Model.User;
import com.example.barbershop.Model.User_Role;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "barbers_app";
    private static final String USER_TABLE = "user";
    private static final String USER_ROLE_TABLE = "user_role";
    private static final String BOOKING_TABLE = "booking";
    private static final String SERVICE_TABLE = "service";
    private static final String SHIFT_TABLE = "shift";
    private static final String USER_SERVICE_TABLE = "user_service";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        String user_role_table = "CREATE TABLE " + USER_ROLE_TABLE + " (role_id INTEGER PRIMARY KEY, role_type TEXT)";
        String user_table = "CREATE TABLE " + USER_TABLE + " (user_id TEXT PRIMARY KEY, name TEXT, email TEXT, phonenumber TEXT, password TEXT, role_id INTEGER, FOREIGN KEY (role_id) REFERENCES user_role_table(role_id))";
        String service_table = "CREATE TABLE " + SERVICE_TABLE + " (service_id INTEGER PRIMARY KEY AUTOINCREMENT, service_name TEXT, description TEXT, rate REAL)";
        String shift_table = "CREATE TABLE " + SHIFT_TABLE + " (shift_id INTEGER PRIMARY KEY AUTOINCREMENT, barber_id TEXT, date TEXT, time TEXT)";
        String booking_table = "CREATE TABLE " + BOOKING_TABLE + " (booking_id INTEGER PRIMARY KEY AUTOINCREMENT, customer_id TEXT, barber_id TEXT, shift_id INTEGER, service_id INTEGER, FOREIGN KEY (customer_id) REFERENCES user_table(user_id),FOREIGN KEY (barber_id) REFERENCES user_table(user_id), FOREIGN KEY (shift_id) REFERENCES shift_table(shift_id), FOREIGN KEY (service_id) REFERENCES service_table(service_id) )";
        String user_service_table = "CREATE TABLE " + USER_SERVICE_TABLE + " (user_service_id INTEGER PRIMARY KEY, user_id TEXT, service_id INTEGER, FOREIGN KEY (user_id) REFERENCES user_table(user_id), FOREIGN KEY (service_id) REFERENCES service_table(service_id))";

        db.execSQL(user_table);
        db.execSQL(user_role_table);
        db.execSQL(booking_table);
        db.execSQL(service_table);
        db.execSQL(shift_table);
        db.execSQL(user_service_table);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_SERVICE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BOOKING_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_ROLE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SERVICE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SHIFT_TABLE);

    }


    // CREATE


    public long createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("user_id", user.getUser_id());
        values.put("role_id", user.getRole_id());
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("phonenumber", user.getPhoneNumber());
        values.put("password", user.getPassword());


        // insert row
        long user_id = db.insert("user", null, values);

        return user_id;
    }

    public long createRole(User_Role role) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("role_id", role.getRole_id());
        values.put("role_type", role.getRole_type());


        // insert row
        long role_id = db.insert("user_role", null, values);

        return role_id;
    }



    public long createShift (String barberId, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("barber_id", barberId);
        values.put("date", date);
        values.put("time",time);

        // insert row
        long shift_id = db.insert("shift", null, values);

        return shift_id;
    }


    public int getServiceId(String serviceName) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT service_id FROM " + SERVICE_TABLE + " WHERE " + " service.service_name = ?" ;
        String[] param = new String[]{serviceName};
        Cursor c = db.rawQuery(selectQuery,param);
        int serviceId = c.getInt(c.getColumnIndex("service_id"));
        return serviceId;

    }

    public long createShift (Shift shift) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("shift_id", shift.getShift_id());
        values.put("barber_id", shift.getBarber_id());
        values.put("date", shift.getDate());
        values.put("time", shift.getTime());


        // insert row
        long shift_id = db.insert("shift", null, values);

        return shift_id;
    }

    public long createBooking (Booking booking) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("booking_id", booking.getBooking_id());
        values.put("customer_id", booking.getCustomer_id());
        values.put("barber_id", booking.getBarber_id());
        values.put("shift_id", booking.getShift_id());
        values.put("service_id", booking.getService_id());

        // insert row
        long booking_id = db.insert("booking", null, values);

        return booking_id;
    }

    public long createBooking (String userId, String barberID, int shiftID, int serviceID) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("customer_id",userId);
        values.put("barber_id", barberID);
        values.put("shift_id", shiftID);
        values.put("service_id", serviceID);

        // insert row
        long booking_id = db.insert("booking", null, values);

        return booking_id;
    }

    public long createService (Service service) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("service_id", service.getService_id());
        values.put("service_name", service.getService_name());
        values.put("description", service.getDescription());
        values.put("rate", service.getRate());


        // insert row
        long service_id = db.insert("service", null, values);

        return service_id;
    }


    // READ

    public List<User> getAllBarbers(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> users = new ArrayList<User>();

        String selectQuery = "SELECT  DISTINCT * FROM " + USER_TABLE + " WHERE " + " user.role_id = 2";

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                User user = new User();
                user.setUser_id(c.getString((c.getColumnIndex("user_id"))));
                user.setRole_id(c.getInt((c.getColumnIndex("role_id"))));
                user.setName((c.getString(c.getColumnIndex("name"))));
                user.setEmail(c.getString(c.getColumnIndex("email")));
                user.setPhoneNumber(c.getString(c.getColumnIndex("phonenumber")));
                user.setPassword(c.getString(c.getColumnIndex("password")));

                // adding to todo list
                users.add(user);
            } while (c.moveToNext());
        }
        return users;
    }

    public String getOneBarberID(String barberName){
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT name FROM " + USER_TABLE + " WHERE " + " user.name = ?;";
        String[] params = new String[]{barberName};

        Cursor c = db.rawQuery(selectQuery,params);
        User user = new User();

        if (c.moveToFirst()) {

                user.setUser_id(c.getString((c.getColumnIndex("user_id"))));
                user.setRole_id(c.getInt((c.getColumnIndex("role_id"))));
                user.setName((c.getString(c.getColumnIndex("name"))));
                user.setEmail(c.getString(c.getColumnIndex("email")));
                user.setPhoneNumber(c.getString(c.getColumnIndex("phonenumber")));
                user.setPassword(c.getString(c.getColumnIndex("password")));
                return user.getUser_id();
        } else {
            return null;
        }


    }

    public List<User> getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<User> users = new ArrayList<User>();

        String selectQuery = "SELECT  * FROM " + USER_TABLE + " WHERE " + " user.role_id = 1";

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                User user = new User();
                user.setUser_id(c.getString((c.getColumnIndex("user_id"))));
                user.setRole_id(c.getInt((c.getColumnIndex("role_id"))));
                user.setName((c.getString(c.getColumnIndex("name"))));
                user.setEmail(c.getString(c.getColumnIndex("email")));
                user.setPhoneNumber(c.getString(c.getColumnIndex("phonenumber")));
                user.setPassword(c.getString(c.getColumnIndex("password")));

                // adding to todo list
                users.add(user);
            } while (c.moveToNext());
        }
        return users;
    }

    public List<Service> getAllServices(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Service> services = new ArrayList<Service>();

        String selectQuery = "SELECT  * FROM " + SERVICE_TABLE ;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Service service = new Service();
                service.setService_id(c.getInt((c.getColumnIndex("service_id"))));
                service.setService_name(c.getString((c.getColumnIndex("service_name"))));
                service.setDescription((c.getString(c.getColumnIndex("description"))));
                service.setRate(c.getDouble(c.getColumnIndex("rate")));

                // adding to todo list
                services.add(service);
            } while (c.moveToNext());
        }
        return services;
    }

    public List<Shift> getAllShiftFromOneBarber(String barber_id){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Shift> shifts = new ArrayList<Shift>();

        String selectQuery = "SELECT  * FROM " + SHIFT_TABLE + " WHERE shift.barber_id = ?;";
        String[] param = new String[]{barber_id};

        Cursor c = db.rawQuery(selectQuery, param);
        if (c.moveToFirst()) {
            do {
                Shift shift = new Shift();
                shift.setShift_id(c.getInt((c.getColumnIndex("shift_id"))));
                shift.setBarber_id(c.getString((c.getColumnIndex("barber_id"))));
                shift.setDate((c.getString(c.getColumnIndex("date"))));
                shift.setTime(c.getString(c.getColumnIndex("time")));

                // adding to todo list
                shifts.add(shift);
            } while (c.moveToNext());
        }
        return shifts;
    }

    public List<Booking> getAllBookingsFromOneUser(String user_id){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Booking> bookings = new ArrayList<Booking>();

        String selectQuery = "SELECT  * FROM " + BOOKING_TABLE + " WHERE booking.customer_id = ?;" ;
        String[] param = new String[]{user_id};
        Log.d("Database helper", "current userid is : " + user_id);

        Cursor c = db.rawQuery(selectQuery, param);
        if (c.moveToFirst()) {
            do {
                Booking booking = new Booking();
                booking.setBooking_id(c.getInt((c.getColumnIndex("booking_id"))));
                booking.setCustomer_id(c.getString((c.getColumnIndex("customer_id"))));
                booking.setBarber_id(c.getString((c.getColumnIndex("barber_id"))));
                booking.setShift_id(c.getInt((c.getColumnIndex("shift_id"))));
                booking.setService_id(c.getInt((c.getColumnIndex("service_id"))));


                // adding to todo list
                bookings.add(booking);
            } while (c.moveToNext());
        }
        return bookings;
    }

    public List<Booking> getAllBookingsFromOneBarber(String user_id){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Booking> bookings = new ArrayList<Booking>();

        String selectQuery = "SELECT  * FROM " + BOOKING_TABLE + " WHERE booking.barber_id = " + user_id ;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Booking booking = new Booking();
                booking.setBooking_id(c.getInt((c.getColumnIndex("booking_id"))));
                booking.setCustomer_id(c.getString((c.getColumnIndex("customer_id"))));
                booking.setBarber_id(c.getString((c.getColumnIndex("barber_id"))));
                booking.setShift_id(c.getInt((c.getColumnIndex("shift_id"))));
                booking.setService_id(c.getInt((c.getColumnIndex("service_id"))));


                // adding to todo list
                bookings.add(booking);
            } while (c.moveToNext());
        }
        return bookings;
    }

    public List<Booking> getAllBookings(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Booking> bookings = new ArrayList<Booking>();

        String selectQuery = "SELECT  * FROM " + BOOKING_TABLE ;

        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Booking booking = new Booking();
                booking.setBooking_id(c.getInt((c.getColumnIndex("booking_id"))));
                booking.setCustomer_id(c.getString((c.getColumnIndex("customer_id"))));
                booking.setBarber_id(c.getString((c.getColumnIndex("barber_id"))));
                booking.setShift_id(c.getInt((c.getColumnIndex("shift_id"))));
                booking.setService_id(c.getInt((c.getColumnIndex("service_id"))));
               

                bookings.add(booking);
            } while (c.moveToNext());
        }
        return bookings;
    }

    public Shift getOneShift(int shift_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + SHIFT_TABLE + " WHERE "
                + "shift_id " + " = " + shift_id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Shift shift = new Shift();
        shift.setShift_id(c.getInt((c.getColumnIndex("shift_id"))));
        shift.setBarber_id(c.getString((c.getColumnIndex("barber_id"))));
        shift.setDate((c.getString(c.getColumnIndex("date"))));
        shift.setTime(c.getString(c.getColumnIndex("time")));

        return shift;
    }

    public Service getOneService(int service_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + SERVICE_TABLE + " WHERE "
                + " service_id " + " = " + service_id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Service service = new Service();
        service.setService_id(c.getInt((c.getColumnIndex("service_id"))));
        service.setService_name(c.getString((c.getColumnIndex("service_name"))));
        service.setDescription((c.getString(c.getColumnIndex("description"))));
        service.setRate(c.getDouble(c.getColumnIndex("rate")));

        return service;
    }


    public Booking getOneBooking(int booking_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + BOOKING_TABLE + " WHERE "
                + " booking_id " + " = " + booking_id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Booking booking = new Booking();
        booking.setBooking_id(c.getInt((c.getColumnIndex("booking_id"))));
        booking.setCustomer_id(c.getString((c.getColumnIndex("customer_id"))));
        booking.setBarber_id(c.getString((c.getColumnIndex("barber_id"))));
        booking.setShift_id(c.getInt((c.getColumnIndex("shift_id"))));
        booking.setService_id(c.getInt((c.getColumnIndex("service_id"))));

        return booking;
    }

    public User getOneUser(String user_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + USER_TABLE + " WHERE "
                + " user_id " + " = " + user_id;


        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        User user = new User();
        user.setUser_id(c.getString((c.getColumnIndex("user_id"))));
        user.setRole_id(c.getInt((c.getColumnIndex("role_id"))));
        user.setName((c.getString(c.getColumnIndex("name"))));
        user.setEmail(c.getString(c.getColumnIndex("email")));
        user.setPhoneNumber(c.getString(c.getColumnIndex("phonenumber")));
        user.setPassword(c.getString(c.getColumnIndex("password")));

        return user;
    }





    // UPDATE shift to status 1: means already booked or 0 means available
    public int updateShift(Shift shift) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("shift_id", shift.getShift_id());
        values.put("barber_id", shift.getBarber_id());
        values.put("date", shift.getDate());
        values.put("time", shift.getTime());
        values.put("status", shift.getBarber_id());

        // updating row
        return db.update(SHIFT_TABLE, values, "shift_id = ?",
                new String[] { String.valueOf(shift.getShift_id()) });
    }

    public int updateBooking(Booking booking) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("booking_id", booking.getBooking_id());
        values.put("customer_id", booking.getCustomer_id());
        values.put("barber_id", booking.getBarber_id());
        values.put("shift_id", booking.getShift_id());
        values.put("service_id", booking.getService_id());

        // updating row
        return db.update(BOOKING_TABLE, values, "booking_id = ?",
                new String[] { String.valueOf(booking.getBooking_id()) });
    }

    public int updateService(Service service) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("service_id", service.getService_id());
        values.put("service_name", service.getService_name());
        values.put("description", service.getDescription());
        values.put("rate", service.getRate());

        // updating row
        return db.update(SERVICE_TABLE, values, "service_id = ?",
                new String[] { String.valueOf(service.getService_id()) });
    }

    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("user_id", user.getUser_id());
        values.put("role_id", user.getRole_id());
        values.put("name", user.getName());
        values.put("email", user.getEmail());
        values.put("phonenumber", user.getPhoneNumber());
        values.put("password", user.getPassword());

        // updating row
        return db.update(USER_TABLE, values, "user_id = ?",
                new String[] { String.valueOf(user.getUser_id()) });
    }

    public void deleteService (int service_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SERVICE_TABLE,   "service_id = ?",
                new String[] { String.valueOf(service_id) });
    }

    public void deleteBooking (int booking_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BOOKING_TABLE,   "booking_id = ?",
                new String[] { String.valueOf(booking_id) });
    }

    public void deleteShift (int shift_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(SHIFT_TABLE,   "shift_id = ?",
                new String[] { String.valueOf(shift_id) });
    }



}
