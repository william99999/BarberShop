package com.example.barbershop;

import android.content.Intent;
import android.os.Bundle;

import com.example.barbershop.Model.Service;
import com.example.barbershop.Model.User;
import com.example.barbershop.Prevalent.ManageBarberAdapter;
import com.example.barbershop.Prevalent.ManageServiceAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ManageServiceActivity extends AppCompatActivity {

    Button addService = null;
    ListView listViewService = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_service);
        addService = findViewById(R.id.btnAddService);
        listViewService = findViewById(R.id.listViewManageService);

        //get all the barbers from the database
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManageServiceActivity.this, EditServiceActivity.class);
                startActivity(intent);
            }
        });
        List<Service> services = db.getAllServices();
        listViewService.setAdapter(new ManageServiceAdapter(this.getApplicationContext(), services));

    }
}