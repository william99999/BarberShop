package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.barbershop.Model.User;
import com.example.barbershop.Prevalent.BarberListAdapter;

import java.util.List;

public class ManageScheduleActivity extends AppCompatActivity {

    ListView listViewBarber = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_schedule);

        listViewBarber = findViewById(R.id.listViewManageSchedule);
        //get all the barbers from the database
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        List<User> users = db.getAllBarbers();
        listViewBarber.setAdapter(new BarberListAdapter(this.getApplicationContext(), users));

    }
}