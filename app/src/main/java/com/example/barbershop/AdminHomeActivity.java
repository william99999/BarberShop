package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHomeActivity extends AppCompatActivity {

    private Button managerBarberBtn;
    private Button managerServices;
    private Button managerSchedule;
    private ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        loadingBar = new ProgressDialog(this);

        managerBarberBtn = findViewById(R.id.mangeBarberBtn);
        managerServices = findViewById(R.id.mangeServicesBtn);
        managerSchedule = findViewById(R.id.mangeScheduleBtn);

        managerBarberBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBar.setTitle("Please wait, fetching barbers");
                loadingBar.setMessage("Please wait, fetching barbers");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                Intent intent = new Intent(AdminHomeActivity.this, ManageBarberActivity.class);
                startActivity(intent);
                loadingBar.dismiss();
            }
        });

        managerServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBar.setTitle("Please wait, fetching Services");
                loadingBar.setMessage("Please wait, fetching Services");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                Intent intent = new Intent(AdminHomeActivity.this, ManageServiceActivity.class);
                startActivity(intent);
                loadingBar.dismiss();
            }
        });

        managerSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingBar.setTitle("Please wait, fetching schedule");
                loadingBar.setMessage("Please wait, fetching schedule");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
                Intent intent = new Intent(AdminHomeActivity.this, ManageScheduleActivity.class);
                startActivity(intent);
                loadingBar.dismiss();
            }
        });
    }
}