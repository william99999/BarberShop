package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.barbershop.Model.User;

import java.util.List;

public class BarberManageActivity extends AppCompatActivity {

    EditText editText;
    Button editBarberButton;
    Button updateBarberButton;
    Button deleteBarberButton;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_manage);

        editText = findViewById(R.id.editTextBarberName);
        editBarberButton = findViewById(R.id.btnBarberUpdate);
        //updateBarberButton = findViewById(R.id.btnUpdateBarber);
        deleteBarberButton = findViewById(R.id.btnDelBarber);
        listView = findViewById(R.id.listViewBarber);

        //get all the barbers from the database
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        List<User> user = db.getAllBarbers();
        //adapter
        ArrayAdapter adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_single_choice,user);

        listView.setAdapter(adapter);

    }
}