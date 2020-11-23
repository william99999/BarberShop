package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.barbershop.Model.Booking;
import com.example.barbershop.Model.Service;
import com.example.barbershop.Model.Shift;
import com.example.barbershop.Model.User;
import com.example.barbershop.Model.User_Role;
import com.example.barbershop.Model.Users;
import com.example.barbershop.Prevalent.BarberAdapter;
import com.example.barbershop.Prevalent.Prevalent;
import com.example.barbershop.Prevalent.ServiceAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.paperdb.Paper;

import static android.R.layout.simple_spinner_dropdown_item;

public class serviceActivity extends AppCompatActivity {
    Button logoutBtn;
    DatabaseHelper db;
    DatePicker datePicker;

    EditText editTextDate;

    Button btnDateSelect;
    Button btnTimeSelect;
    Spinner spinnerName;
    Spinner spinnerServices;
    Spinner spinnerTime;
    Button btnFinalize;
    Button btnAddService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);




        btnAddService = findViewById(R.id.btnAddService);
        logoutBtn = findViewById(R.id.btnLogout);
        editTextDate = findViewById(R.id.editTextDateSelect);
        btnDateSelect = findViewById(R.id.btnSelectDate);
        btnTimeSelect = findViewById(R.id.btnSelectTime);


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Intent intent = new Intent(serviceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        try {
            int day = getIntent().getExtras().getInt("day");
            int month = getIntent().getExtras().getInt("month");
            int year = getIntent().getExtras().getInt("year");
            String date;
            if (day != 0 && month != 0 && year != 0) {
                date = (day + "/" + month + "/" + year);
                editTextDate.setText(date);
            }
        } catch (Exception e) {
            Log.e("serice Activity", "there is eroor in intent one");
        }


        btnDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(serviceActivity.this, CalenderActivity.class);
                startActivity(intent);


                // editTextDate.setText();

            }
        });


        db = new DatabaseHelper(getApplicationContext());
//
//        User_Role role1 = new User_Role(1, "customer");
//        User_Role role2 = new User_Role(2, "barber");
//        User_Role role3 = new User_Role(3, "admin");
//
//        User user1 = new User("Mark", "mark@gmail.com", "113355", "113355", 1);
//        User user2 = new User("Henry", "henryk@gmail.com", "224466", "224466", 1);
//        User user3 = new User("John", "john@gmail.com", "335577", "335577", 1);
//
//        User barber1 = new User("Barron", "barron@gmail.com", "446688", "446688", 2);
//        User barber2 = new User("Bryan", "bryan@gmail.com", "557799", "557799", 2);
//
//
//        Service service1 = new Service(1, "Hair cut", "each type of hair cut is available", 25);
//        Service service2 = new Service(2, "Beard", "setting the beard, trimming and neck shaving", 18);
//        Service service3 = new Service(3, "Hair Color", "various colors to choose depending on look and style", 80);
//
//
//        Shift shift1 = new Shift(1, "446688", "11/10/2020", "10", 0);
//        Shift shift2 = new Shift(2, "446688", "11/10/2020", "12", 0);
//        Shift shift3 = new Shift(3, "446688", "11/10/2020", "17", 0);
//        Shift shift4 = new Shift(4, "557799", "11/10/2020", "10", 0);
//
//        Booking booking1 = new Booking(1,"113355", "446688", 1, 1);
//        Booking booking2 = new Booking( 2,"224466", "446688", 2, 2);
//        Booking booking3 = new Booking( 3,"335577", "557799", 4, 3);
//
//
//        db.createRole(role1);
//        db.createRole(role2);
//        db.createRole(role3);
//
//
//        db.createUser(user1);
//        db.createUser(user2);
//        db.createUser(user3);
//        db.createUser(barber1);
//        db.createUser(barber2);
//
//        db.createService(service1);
//        db.createService(service2);
//        db.createService(service3);
//
//        db.createShift(shift1);
//        db.createShift(shift2);
//        db.createShift(shift3);
//        db.createShift(shift4);
//
//        db.createBooking(booking1);
//        db.createBooking(booking2);
//        db.createBooking(booking3);
//



        ArrayList<User> barbers = (ArrayList<User>) db.getAllBarbers();
        ArrayList<Service> services = (ArrayList<Service>) db.getAllServices();

        spinnerTime = findViewById(R.id.spinnerShift);
        spinnerName = findViewById(R.id.spinnerBarberName);
        spinnerServices = findViewById(R.id.spinnerBarberServices);
        btnFinalize = findViewById(R.id.btnSubmitBooking);

        BarberAdapter adapter = new BarberAdapter(barbers);
        spinnerName.setAdapter(adapter);


        ServiceAdapter serviceAdapter = new ServiceAdapter(services);
        spinnerServices.setAdapter(serviceAdapter);

        ArrayList<Integer> shifttime = new ArrayList<>();
        int startshiftBook = 7;

        for (int a = 1; a <= 10; a++) {
            shifttime.add(startshiftBook);
            startshiftBook++;
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, simple_spinner_dropdown_item, shifttime);
        spinnerTime.setAdapter(arrayAdapter);

        btnFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(serviceActivity.this, ReviewActivity.class);
                startActivity(intent);
            }
        });

        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User barber = (User) spinnerName.getSelectedItem();
                Service service = (Service) spinnerServices.getSelectedItem();
                String date = editTextDate.getText().toString();
                String time = spinnerTime.getSelectedItem().toString();
//                String barberID = db.getOneBarberID(barberName);
//                Log.d("service activity", "barber name  = " + barberName);
//                Log.d("service activity", "barber id  = " + barberID);
                List<Shift> shifts = db.getAllShiftFromOneBarber(barber.getUser_id());
                boolean existing = false;

                for (Shift shift : shifts) {
                    if (shift.getDate() == date && shift.getTime() == time) {
                        existing = true;
                        Toast.makeText(serviceActivity.this, "This time is booked, please select different time or date.", Toast.LENGTH_SHORT).show();
                    }
                }

                if (existing == false) {
                    int shiftId = (int) db.createShift(barber.getUser_id(), date, time);
//                    String user_id = Paper.book().read(Prevalent.userPhoneKey);
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(serviceActivity.this);
                    String user_id = sharedPreferences.getString("phonenum",null);
                    Log.e("service activity " , "user_id " + user_id);
                    int serviceId = service.getService_id();
                    db.createBooking(user_id, barber.getUser_id(), shiftId, serviceId);
                }

            }

        });

    }
}