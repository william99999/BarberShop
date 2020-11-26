package com.example.barbershop.Prevalent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.barbershop.EditBookingActivity;
import com.example.barbershop.R;
import com.example.barbershop.ReviewActivity;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {



    private static final String TAG = "Recycler View Adapter";

    private ArrayList<String> mBarberNames = new ArrayList<>();
    private ArrayList<String> mServices = new ArrayList<>();
    private ArrayList<String> mDates = new ArrayList<>();
    private ArrayList<String> mTimes = new ArrayList<>();

    private Context mContext;

    public RecyclerViewAdapter(Context context,ArrayList<String> mBarberNames, ArrayList<String> mServices, ArrayList<String> mDates, ArrayList<String> mTimes) {
        this.mBarberNames = mBarberNames;
        this.mServices = mServices;
        this.mDates = mDates;
        this.mTimes = mTimes;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        ViewHolder holder = new ViewHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtItemBarber.setText(mBarberNames.get(position));
        holder.txtItemService.setText(mServices.get(position));
        holder.txtItemDate.setText(mDates.get(position));
        holder.txtItemTime.setText(mTimes.get(position));

    }

    @Override
    public int getItemCount() {
        return mBarberNames.size();
    }

    private OnItemClickListener mListener;
    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }




    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtItemBarber, txtItemService, txtItemDate, txtItemTime;
        Button btnItemDelete;
        LinearLayout parentLayout;
        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            txtItemBarber = itemView.findViewById(R.id.txtItemBarber);
            txtItemService = itemView.findViewById(R.id.txtItemService);
            txtItemDate = itemView.findViewById(R.id.txtItemDate);
            txtItemTime = itemView.findViewById(R.id.txtItemTime);
            btnItemDelete = itemView.findViewById(R.id.btnItemDelete);
            parentLayout = itemView.findViewById(R.id.parentLinearLayout);

            btnItemDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!= null) {
                        int position  = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });


        }
    }

}
