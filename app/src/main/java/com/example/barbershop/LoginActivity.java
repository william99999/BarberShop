package com.example.barbershop;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbershop.Model.Users;
import com.example.barbershop.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText inputNumber, inputPassword;
    private Button loginButton;
    private ProgressDialog loadingBar;
    private String parentDbName = "Users";
    private CheckBox chkBoxRememberMe;
    private TextView adminLink;
    private TextView notAdminLink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.login_btn);
        inputNumber = findViewById(R.id.login_phone_number_input);
        inputPassword= findViewById(R.id.login_password_input);
        adminLink = findViewById(R.id.admin_panel_link);
        notAdminLink = findViewById(R.id.not_admin_panel_link);

        loadingBar = new ProgressDialog(this);
        chkBoxRememberMe = findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        adminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Login Admin");
                adminLink.setVisibility(View.INVISIBLE);
                notAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";
            }
        });

        notAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginButton.setText("Login");
                adminLink.setVisibility(View.VISIBLE);
                notAdminLink.setVisibility(View.INVISIBLE);
                parentDbName = "Users";
            }
        });
    }

    private void loginUser(){
        String phone = inputNumber.getText().toString();
        String password = inputPassword.getText().toString();
        if(TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please write your phone number...", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }
        else {
            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait, while we are checking the credentials.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();
            allowAccessToAccount(phone, password);
        }
    }


    private void allowAccessToAccount(String phone, final String password) {
        final String phoneNum = phone;
        final String pass = password;

        if(chkBoxRememberMe.isChecked() && parentDbName=="Users"){
            Paper.book().write(Prevalent.userPhoneKey, phoneNum);
            Paper.book().write(Prevalent.userPasswordKey, pass);
            Toast.makeText(LoginActivity.this, "papaer userphonekey = " + Paper.book().read(Prevalent.userPhoneKey), Toast.LENGTH_SHORT).show();
        }



        final DatabaseReference rootRef;
        rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDbName).child(phoneNum).exists()) {
                    Users usersData = snapshot.child(parentDbName).child(phoneNum).getValue(Users.class);
                    if(usersData.getPhone().equals(phoneNum)) {
                        if (usersData.getPassword().equals(pass)){

                            if (parentDbName.equals("Admins")) {
                                Toast.makeText(LoginActivity.this, "Welcome admin, log in successfully", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("phonenum", phoneNum);
                                editor.commit();

                            } else if ( parentDbName.equals("Users")){

                                Toast.makeText(LoginActivity.this, "Login successfully.", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("phonenum", phoneNum);
                                editor.commit();

                                Intent intent = new Intent(LoginActivity.this, serviceActivity.class);
                                startActivity(intent);
                            }


                        } else{
                            Toast.makeText(LoginActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();
                        }
                    }
                } else{
                    Toast.makeText(LoginActivity.this, "Account with this " + phoneNum+" does not exist.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                    Toast.makeText(LoginActivity.this, "You need to create a new account.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}