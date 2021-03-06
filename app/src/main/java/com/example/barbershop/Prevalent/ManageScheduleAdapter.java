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
import com.example.barbershop.EditBarberScheduleActivity;
import com.example.barbershop.EditServiceActivity;
import com.example.barbershop.Model.Service;
import com.example.barbershop.Model.Shift;
import com.example.barbershop.Model.User;
import com.example.barbershop.R;

import java.util.List;

public class ManageScheduleAdapter extends BaseAdapter {

    private List<Shift> shifts;
    private Context context;

    public ManageScheduleAdapter(Context context, List<Shift> shifts) {
        this.context = context;
        this.shifts = shifts;
    }

    @Override
    public int getCount() {
        return shifts.size();
    }

    @Override
    public Object getItem(int position) {
        return shifts.get(position);
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
                Intent intent = new Intent(view.getContext(), EditBarberScheduleActivity.class);
                intent.putExtra("Shift", shifts.get(itemClicked));
                view.getContext().startActivity(intent);
            }
        });
        btnDeleteBarber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Shift shift = shifts.get(itemClicked);
                databaseHelper.deleteShift(shift.getShift_id());
                shifts.remove(itemClicked);
                Toast.makeText(view.getContext(), "Schedule deleted", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        //Set the text for each item in the listview
        txtViewBarberId.setText(String.valueOf(shifts.get(i).getBarber_id()));
        txtViewBarberName.setText(String.valueOf(shifts.get(i).getShift_id()));
        return view;
    }


}
