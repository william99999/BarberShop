package com.example.barbershop.Prevalent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.barbershop.DatabaseHelper;
import com.example.barbershop.EditBarberActivity;
import com.example.barbershop.LoginActivity;
import com.example.barbershop.ManageBarberActivity;
import com.example.barbershop.Model.User;
import com.example.barbershop.R;

import java.util.List;

public class ManageBarberAdapter extends BaseAdapter {

    private List<User> users;
    private Context context;

    public ManageBarberAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
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
                Intent intent = new Intent(view.getContext(), EditBarberActivity.class);
                intent.putExtra("User", users.get(itemClicked));
                view.getContext().startActivity(intent);
            }
        });
        btnDeleteBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User deleteUser = users.get(itemClicked);
                databaseHelper.deleteUser(deleteUser.getUser_id());
                users.remove(itemClicked);
                Toast.makeText(view.getContext(), "User : " + deleteUser.getName() + " Deleted", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        //Set the text for each item in the listview
        txtViewBarberId.setText(String.valueOf((users.get(i).getUser_id())));
        txtViewBarberName.setText(users.get(i).getName());
        return view;
    }

}
