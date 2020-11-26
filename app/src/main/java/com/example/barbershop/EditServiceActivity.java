package com.example.barbershop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbershop.Model.Service;
import com.example.barbershop.Model.User;

public class EditServiceActivity extends AppCompatActivity {
    EditText serviceName = null;
    EditText serviceDescription = null;
    EditText serviceRate = null;
    Button serviceSubmit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_service);

        serviceName = findViewById(R.id.editTxtServiceName);
        serviceDescription = findViewById(R.id.editTxtServiceDescription);
        serviceRate = findViewById(R.id.editTxtServiceRate);

        serviceSubmit = findViewById(R.id.btnServiceSubmit);

        final Service service = new Service();
        if (getIntent().getSerializableExtra("Service") != null) {
            Service serviceTmp = (Service) getIntent().getSerializableExtra("Service");
            serviceName.setText(serviceTmp.getService_name(), TextView.BufferType.EDITABLE);
            serviceDescription.setText(serviceTmp.getDescription(), TextView.BufferType.EDITABLE);
            serviceRate.setText(String.valueOf(serviceTmp.getRate()), TextView.BufferType.EDITABLE);
            service.setService_id(serviceTmp.getService_id());

        }

        serviceSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Service serviceCreate = new Service();
                serviceCreate.setService_id(service.getService_id());
                serviceCreate.setService_name(serviceName.getText().toString());
                serviceCreate.setDescription(serviceDescription.getText().toString());
                serviceCreate.setRate(Double.valueOf(serviceRate.getText().toString()));

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                if (serviceCreate.getService_id() == null) {
                    db.createService(serviceCreate);
                    Toast.makeText(view.getContext(), "Service : " + serviceCreate.getService_name() + " Created", Toast.LENGTH_SHORT).show();
                } else {
                    db.updateService(serviceCreate);
                    Toast.makeText(view.getContext(),"Service : " + serviceCreate.getService_name() +  " Updated", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(EditServiceActivity.this, ManageServiceActivity.class);
                startActivity(intent);
            }
        });


    }
}