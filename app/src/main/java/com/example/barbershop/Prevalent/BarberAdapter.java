package com.example.barbershop.Prevalent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.barbershop.Model.User;
import com.example.barbershop.R;

import java.util.List;

public class BarberAdapter extends BaseAdapter {

    List<User> users;

    public BarberAdapter(List<User> users) {
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
        if (view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            view = layoutInflater.inflate(R.layout.item_view,viewGroup,false);
        }

        TextView textView1 = view.findViewById(R.id.textView1);
        textView1.setText(users.get(i).getName());
        TextView textView2 = view.findViewById(R.id.textView2);
        textView2.setText(users.get(i).getUsername());
        return view;
    }
}
