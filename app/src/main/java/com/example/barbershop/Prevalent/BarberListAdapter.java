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
import com.example.barbershop.ManageSchedule2Activity;
import com.example.barbershop.ManageScheduleActivity;
import com.example.barbershop.Model.User;
import com.example.barbershop.R;

import java.util.List;

public class BarberListAdapter extends BaseAdapter {


    private List<User> users;
    private Context context;

    public BarberListAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
    }

    @Override
    public int getCount() {
        return users.size() + 1;
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
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_layout, viewGroup, false);
        }

        TextView txtViewBarberId = view.findViewById(R.id.txtView1);
        TextView txtViewBarberName = view.findViewById(R.id.txtView2);
        TextView txtViewBarberEmail = view.findViewById(R.id.txtView3);

        if (i == 0) {
            txtViewBarberId.setText("Id");
            txtViewBarberName.setText("Name");
            txtViewBarberEmail.setText("Email");
        } else {
            final int itemClicked = i-1;
            txtViewBarberId.setText(String.valueOf((users.get(itemClicked).getUser_id())));
            txtViewBarberName.setText(users.get(itemClicked).getName());
            txtViewBarberEmail.setText(users.get(itemClicked).getEmail());

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ManageSchedule2Activity.class);
                    intent.putExtra("User", users.get(itemClicked));
                    view.getContext().startActivity(intent);
                }
            });
        }
        return view;
    }
}
