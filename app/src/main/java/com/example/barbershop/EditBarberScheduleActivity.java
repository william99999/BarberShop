package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditBarberScheduleActivity extends AppCompatActivity {

    Button btnDateSelect = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barber_schedule);
        btnDateSelect = findViewById(R.id.btnSelectDate);
        btnDateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditBarberScheduleActivity.this, CalenderActivity.class);
                startActivity(intent);
                // editTextDate.setText();

            }
        });

    }
}