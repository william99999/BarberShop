package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.barbershop.Model.Service;
import com.example.barbershop.Model.Shift;
import com.example.barbershop.Model.User;
import com.example.barbershop.Prevalent.ManageScheduleAdapter;

import java.util.List;

public class ManageSchedule2Activity extends AppCompatActivity {

    TextView textView = null;
    Button addSchedule = null;
    ListView listViewManageSchedule = null;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_schedule2);

        textView = findViewById(R.id.txtViewManageSchedule);
        addSchedule = findViewById(R.id.btnAddSchedule2);
        listViewManageSchedule = findViewById(R.id.listViewManageSchedule2);

        //get the barber clicked
        User user = null;
        if (getIntent().getSerializableExtra("User") != null) {
            user = (User) getIntent().getSerializableExtra("User");
            textView.setText("Manage Schedule for : " + user.getName());
        }
        final User tmp = user;
        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditBarberScheduleActivity.class);
                intent.putExtra("User", tmp);
                view.getContext().startActivity(intent);
            }
        });
        //get all the barbers from the database
        db = new DatabaseHelper(getApplicationContext());

        List<Shift> shifts = db.getAllShiftFromOneBarber(tmp.getUser_id());
        listViewManageSchedule.setAdapter(new ManageScheduleAdapter(getApplicationContext(),shifts));
    }
}