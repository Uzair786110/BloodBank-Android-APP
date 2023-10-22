package com.example.bloodbankproject;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    List<userreq> list;
    Context context;

    public CustomAdapter(List<userreq> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bloodrequest, null);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(CustomAdapter.ViewHolder holder, int position) {
        final userreq list1 = list.get(position);
        holder.tvDate.setText(list1.getDate());
        holder.tvName.setText(list1.getName());
        holder.tvAddress.setText(list1.getAddress());
        holder.tvCity.setText(list1.getCity());
        holder.tvBlood.setText(list1.getBlood());
        holder.tvStatus.setText(list1.getBags());
        holder.Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", list1.getContact(), null));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static void filterList(ArrayList<userreq> filteredList) {
        // list=filteredList;


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate, tvName, tvAddress, tvCity, tvBlood, tvStatus;
        Button Call;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.reqDate);
            tvName = itemView.findViewById(R.id.reqName);
            tvAddress = itemView.findViewById(R.id.reqAddress);
            tvCity = itemView.findViewById(R.id.reqCity);
            tvBlood = itemView.findViewById(R.id.reqbloodgroup);
            tvStatus = itemView.findViewById(R.id.reqstatus);
            Call = itemView.findViewById(R.id.callbtn);


        }

    }
}
