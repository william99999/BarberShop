package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.barbershop.Model.Booking;
import com.example.barbershop.Model.Service;
import com.example.barbershop.Model.Shift;
import com.example.barbershop.Model.User;
import com.example.barbershop.Prevalent.Prevalent;
import com.example.barbershop.Prevalent.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class ReviewActivity extends AppCompatActivity {

    private ArrayList<String> mBarbers = new ArrayList<>();
    private ArrayList<String> mServices = new ArrayList<>();
    private ArrayList<String> mDates = new ArrayList<>();
    private ArrayList<String> mTimes = new ArrayList<>();
    DatabaseHelper db;
    Button logoutBtn;
    Button payBtn;
    List<Booking> listBookings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        db = new DatabaseHelper(getApplicationContext());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(ReviewActivity.this);
        String user_id = sharedPreferences.getString("phonenum",null);
        Log.d("Review Activity", "current userid is : " + user_id);
        listBookings = db.getAllBookingsFromOneUser(user_id);

//         public Booking(int booking_id, String customer_id, String barber_id, int shift_id, int service_id, String comment) {
////            this.booking_id = booking_id;
////            this.customer_id = customer_id;
////            this.barber_id = barber_id;
////            this.shift_id = shift_id;
////            this.service_id = service_id;
////            this.comment = comment;
////        }

        for(Booking booking : listBookings) {
            String barberId = booking.getBarber_id();
            Log.d("Review Activity", "current barberid is : " + barberId);
            User user = db.getOneUser(barberId);
            Log.d("Review Activity", "current user name is : " + user.getName());
            String barberName = user.getName();
            mBarbers.add(barberName);
            int serviceId = booking.getService_id();
            Service service = db.getOneService(serviceId);
            mServices.add(service.getService_name());
            int shiftId = booking.getShift_id();
            Shift shift = db.getOneShift(shiftId);
            String startDate = shift.getDate();
            String startTime =  shift.getTime();
            mDates.add(startDate);
            mTimes.add(startTime);
        }

        initRecyclerView();

        logoutBtn = findViewById(R.id.btnLogout);
        payBtn = findViewById(R.id.btnPayNow);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Paper.book().destroy();
                Intent intent = new Intent(ReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReviewActivity.this, "move to pay page", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initRecyclerView(){
        RecyclerView recyclerView =  findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mBarbers, mServices, mDates, mTimes);
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position) {
                Booking deletedBooking = listBookings.get(position);
                db.deleteBooking(deletedBooking.getBooking_id());
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}