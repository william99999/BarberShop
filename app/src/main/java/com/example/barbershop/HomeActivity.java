package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.barbershop.Model.User;
import com.example.barbershop.Model.User_Role;
import com.example.barbershop.Prevalent.BarberAdapter;

import java.util.List;

import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity {

    private Button logoutBtn;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//
//        logoutBtn = findViewById(R.id.logout_btn);
//
//        logoutBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Paper.book().destroy();
//                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        db = new DatabaseHelper(getApplicationContext());
//
//        User user1 = new User("123456789", "Mark","mark@gmail.com", "123456789",2);
//        User user2 = new User("123456789", "Mark", "mark@gmail.com", "123456789",1);
//        User user3 = new User("123456789", "Mark", "mark@gmail.com", "123456789",2);
//
//        User_Role role1 = new User_Role(1,"user");
//        User_Role role2 = new User_Role(2,"barber");
//        User_Role role3 = new User_Role(3,"admin");
//
//        db.createUser(user1);
//        db.createUser(user2);
//        db.createUser(user3);
//
//        db.createRole(role1);
//        db.createRole(role2);
//        db.createRole(role3);
//
//        List<User> users = db.getAllBarbers();
//
//
//        ListView listView = findViewById(R.id.listView);
//        BarberAdapter adapter = new BarberAdapter(users);
//        listView.setAdapter(adapter);


    }
}