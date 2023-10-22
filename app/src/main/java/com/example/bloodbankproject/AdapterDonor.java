package com.example.bloodbankproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterDonor extends RecyclerView.Adapter<AdapterDonor.ViewHolder> {
    List<BloodDonorFetch> list;
    Context context;

    public AdapterDonor(List<BloodDonorFetch> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public AdapterDonor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.blooddonation, null);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(AdapterDonor.ViewHolder holder, int position) {
        final BloodDonorFetch list1 = list.get(position);
        holder.tvName.setText(list1.getName());
        holder.tvAddress.setText(list1.getAddress());
        holder.tvCity.setText(list1.getCity());
        holder.tvBlood.setText(list1.getBlood());
        holder.DonBtn.setOnClickListener(new View.OnClickListener() {
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvAddress, tvCity, tvBlood;
        Button DonBtn;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.donName);
            tvAddress = itemView.findViewById(R.id.donAddress);
            tvCity = itemView.findViewById(R.id.donCity);
            tvBlood = itemView.findViewById(R.id.donbloodgroup);
            DonBtn = itemView.findViewById(R.id.doncallbtn);


        }

    }
}
