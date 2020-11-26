package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbershop.Model.User;

public class EditBarberActivity extends AppCompatActivity {

    EditText name = null;
    EditText phoneNumber = null;
    EditText email = null;
    EditText userName = null;
    Button submitBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barber);

        name = findViewById(R.id.editTxtBarberName);
        phoneNumber = findViewById(R.id.editTxtBarberPhone);
        email = findViewById(R.id.editTxtBarberEmail);
        userName = findViewById(R.id.editTxtBarberUserName);
        submitBtn = findViewById(R.id.btnSubmitBarber);

        final User user = new User();
        if (getIntent().getSerializableExtra("User") != null) {
            User userTmp = (User) getIntent().getSerializableExtra("User");
            user.setName(userTmp.getName());
            name.setText(userTmp.getName(), TextView.BufferType.EDITABLE);
            email.setText(userTmp.getEmail(), TextView.BufferType.EDITABLE);
            phoneNumber.setText(userTmp.getPhoneNumber(), TextView.BufferType.EDITABLE);
            userName.setText(userTmp.getUsername(), TextView.BufferType.EDITABLE);
            user.setRole_id(2);
            user.setUser_id(userTmp.getUser_id());
        } else {
            user.setName(name.getText().toString());
            user.setEmail(email.getText().toString());
            user.setPassword(phoneNumber.getText().toString());
            user.setRole_id(2);
            user.setUsername(userName.getText().toString());
        }

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User userCreate = new User();
                userCreate.setUser_id(user.getUser_id());
                //userCreate.setPassword("");
                userCreate.setEmail(email.getText().toString());
                userCreate.setUsername(userName.getText().toString());
                userCreate.setName(name.getText().toString());
                userCreate.setPhoneNumber(phoneNumber.getText().toString());
                userCreate.setRole_id(user.getRole_id());

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                if (userCreate.getUser_id() != null) {
                    db.updateUser(userCreate);
                    Toast.makeText(view.getContext(), "User : " + userCreate.getName() + " Updated", Toast.LENGTH_SHORT).show();
                } else {
                    db.createUser(userCreate);
                    Toast.makeText(view.getContext(), "User : " + userCreate.getName() + " Created", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(EditBarberActivity.this, ManageBarberActivity.class);
                startActivity(intent);
            }
        });


    }
}