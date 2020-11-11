package com.example.barbershop.Prevalent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.barbershop.Model.Service;
import com.example.barbershop.Model.User;
import com.example.barbershop.R;

import java.util.List;

public class ServiceAdapter extends BaseAdapter {
    List<Service> services;

    public ServiceAdapter(List<Service> services)
    {
        this.services=services;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null){
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            view = layoutInflater.inflate(R.layout.item_view,viewGroup,false);
        }

        TextView textView1 = view.findViewById(R.id.textView1);

        textView1.setText(services.get(position).getService_name());



        return view;
    }
}
