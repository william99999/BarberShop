package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.barbershop.Model.Service;
import com.example.barbershop.Model.User;
import com.example.barbershop.Model.User_Role;
import com.example.barbershop.Prevalent.BarberAdapter;
import com.example.barbershop.Prevalent.ServiceAdapter;

import java.util.ArrayList;

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
        Button btnSubmit;
        Spinner ampm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);


        logoutBtn=findViewById(R.id.btnLogout);
        editTextDate=findViewById(R.id.editTextDateSelect);
        ampm=findViewById(R.id.spinnerAmPm);
        btnDateSelect=findViewById(R.id.btnSelectDate);
        btnTimeSelect=findViewById(R.id.btnSelectTime);


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Intent intent = new Intent(serviceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        try {
            int day=getIntent().getExtras().getInt("day");
            int month=getIntent().getExtras().getInt("month");
            int year=getIntent().getExtras().getInt("year");
            String date;
            if(day!=0 && month!=0 && year!=0)
            {
                date=(day+"/"+month+"/"+year);
                editTextDate.setText(date);
            }
        }catch (Exception e)
        {
            Log.e("serice Activity","there is eroor in intent one");
        }




        btnDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(serviceActivity.this, CalenderActivity.class);
                startActivity(intent);



               // editTextDate.setText();

            }
        });


        db = new DatabaseHelper(getApplicationContext());
        User user1 = new User(1, "Mark", "M", "mark@gmail.com", "123456789", "123456789",2);
        User user2 = new User(2, "Mark", "M", "mark@gmail.com", "123456789", "123456789",1);
        User user3 = new User(3, "henry", "M", "mark@gmail.com", "123456789", "123456789",2);

        Service service1=new Service();
        service1.setService_id(1);
        service1.setService_name("Hair cut");
        service1.setDescription("each type of hair cut is available");
        service1.setRate(25);
        Service service2=new Service();
        service2.setService_id(2);
        service2.setService_name("Beard");
        service2.setDescription("setting the beard, trimming and neck shaving");
        service2.setRate(18);
        Service service3=new Service();
        service3.setService_id(3);
        service3.setService_name("Hair Color");
        service3.setDescription("various colors to choose depending on look and style");
        service3.setRate(80);




        User_Role role1 = new User_Role(1,"user");
        User_Role role2 = new User_Role(2,"barber");
        User_Role role3 = new User_Role(3,"admin");

        db.createUser(user1);
        db.createUser(user2);
        db.createUser(user3);

        db.createService(service1);
        db.createService(service2);
        db.createService(service3);

        db.createRole(role1);
        db.createRole(role2);
        db.createRole(role3);
        ArrayList<User> users = (ArrayList<User>) db.getAllBarbers();
        ArrayList<Service> services= (ArrayList<Service>) db.getAllServices();
        spinnerTime=findViewById(R.id.spinnerShift);
        spinnerName=findViewById(R.id.spinnerBarberName);
        spinnerServices=findViewById(R.id.spinnerBarberServices);
        btnSubmit=findViewById(R.id.btnSubmitBooking);

        BarberAdapter adapter=new BarberAdapter(users);
        spinnerName.setAdapter(adapter);

        ServiceAdapter serviceAdapter=new ServiceAdapter(services);
        spinnerServices.setAdapter(serviceAdapter);

        ArrayList<Integer> shifttime=new ArrayList<>();
        int startshiftBook=7;
        for(int a=1;a<=10;a++){
            if(startshiftBook>12)
            {
                startshiftBook=1;
                shifttime.add(startshiftBook);
            }else {
                shifttime.add(startshiftBook);
            }
            startshiftBook++;

        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,simple_spinner_dropdown_item,shifttime);
        spinnerTime.setAdapter(arrayAdapter);

    btnSubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent intent=new Intent(serviceActivity.this,ReviewActivity.class);
            Bundle myBundle = new Bundle();

            myBundle.putString("barberName",spinnerName.getSelectedItem().toString());
            myBundle.putString("barberService",spinnerServices.getSelectedItem().toString());
            myBundle.putString("date",editTextDate.getText().toString());
            myBundle.putInt("time",Integer.parseInt(spinnerTime.getSelectedItem().toString()));
            myBundle.putInt("ampm",ampm.getSelectedItemPosition());
            intent.putExtras(myBundle);
            startActivity(intent);
        }
    });




       /* btnTimeSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker.setVisibility(View.VISIBLE);
                timePicker.setIs24HourView(true);
                SimpleDateFormat f12hours=new SimpleDateFormat("hh:mm aa");
                try {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        int hour=timePicker.getHour();
                        int minute=timePicker.getMinute();
                        editTextTime.setText(hour+minute);
                    }

                }catch (Exception e)
                {
                    e.getMessage();
                }


            }
        });*/

    }
}