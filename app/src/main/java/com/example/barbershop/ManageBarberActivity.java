package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.barbershop.Model.User;
import com.example.barbershop.Prevalent.ManageBarberAdapter;

import java.util.List;

public class ManageBarberActivity extends AppCompatActivity {

    Button addBarber = null;
    ListView listViewBarber = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_barber);

        addBarber = findViewById(R.id.btnAddBarber);
        listViewBarber = findViewById(R.id.listViewManageBarber);

        //get all the barbers from the database
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        addBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageBarberActivity.this, EditBarberActivity.class);
                startActivity(intent);
            }
        });
        List<User> users = db.getAllBarbers();
        listViewBarber.setAdapter(new ManageBarberAdapter(this.getApplicationContext(), users));
    }
}