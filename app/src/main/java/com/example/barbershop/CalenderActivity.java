package com.example.barbershop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalenderActivity extends AppCompatActivity {

    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        calendarView=findViewById(R.id.calendarViewDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


                Intent intent=new Intent(CalenderActivity.this, serviceActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("year",year);
                bundle.putInt("month",month);
                bundle.putInt("day",dayOfMonth);

                intent.putExtras(bundle);
//                intent.putExtra("year",year);
//                intent.putExtra("month",month);
//                intent.putExtra("day",dayOfMonth);
                startActivity(intent);

            }
        });
    }
}