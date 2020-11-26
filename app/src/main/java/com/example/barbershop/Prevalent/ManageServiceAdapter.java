package com.example.barbershop.Prevalent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbershop.DatabaseHelper;
import com.example.barbershop.EditBarberActivity;
import com.example.barbershop.EditServiceActivity;
import com.example.barbershop.Model.Service;
import com.example.barbershop.Model.User;
import com.example.barbershop.R;

import java.util.List;

public class ManageServiceAdapter extends BaseAdapter {

    private List<Service> services;
    private Context context;

    public ManageServiceAdapter(Context context, List<Service> services) {
        this.context = context;
        this.services = services;
    }

    @Override
    public int getCount() {
        return services.size();
    }

    @Override
    public Object getItem(int position) {
        return services.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_barber_layout, viewGroup, false);
        }

        TextView txtViewBarberId = view.findViewById(R.id.txtViewBarberId);
        TextView txtViewBarberName = view.findViewById(R.id.txtViewBarberName);
        Button btnDeleteBarber = view.findViewById(R.id.btnBarberDelete);
        Button btnEditBarber = view.findViewById(R.id.btnBarberUpdate);

        final DatabaseHelper databaseHelper = new DatabaseHelper(context);

        final int itemClicked = i;
        btnEditBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditServiceActivity.class);
               intent.putExtra("Service", services.get(itemClicked));
                view.getContext().startActivity(intent);
            }
        });
        btnDeleteBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Service service = services.get(itemClicked);
                databaseHelper.deleteService(service.getService_id());
                services.remove(itemClicked);
                Toast.makeText(view.getContext(), "Service : " + service.getService_name() + " Deleted", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        //Set the text for each item in the listview
        txtViewBarberId.setText(String.valueOf((services.get(i).getService_name())));
        txtViewBarberName.setText(services.get(i).getDescription());
        return view;
    }

}
