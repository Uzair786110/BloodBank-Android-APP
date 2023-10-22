package com.example.bloodbankproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapterfetchuser extends RecyclerView.Adapter<ViewHolder> {


    List<fetchuserprofile> list;
    Context context;

    public Adapterfetchuser(List<fetchuserprofile> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userprofiledata, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        fetchuserprofile mylist = list.get(position);
        holder.NAME.setText(mylist.getId());
        holder.Contact.setText(mylist.getContact());
        holder.Blood.setText(mylist.getBloodgroup());
        holder.Email.setText(mylist.getEmail());
        holder.Add.setText(mylist.getAddress());
        holder.Add.setText(mylist.getCity());
        if (mylist.getDon().equals("0")) {
            holder.Don.setText("Not A Donor");
        } else {
            holder.Don.setText("You Are Donor");
        }
        holder.Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Profileupd = new Intent(context, UpdateProfile.class);
                String Nameupd = mylist.getName();
                Profileupd.putExtra("updName", Nameupd);
                String Idupd = mylist.getId();
                Profileupd.putExtra("updId", Idupd);
                String Emailupd = mylist.getEmail();
                Profileupd.putExtra("updEmail", Emailupd);
                String Contactupd = mylist.getContact();
                Profileupd.putExtra("updContact", Contactupd);
                String Blood = mylist.getBloodgroup();
                Profileupd.putExtra("updBlood", Blood);
                String Don = mylist.getDon();
                Profileupd.putExtra("updDon", Don);
                String Add = mylist.getAddress();
                Profileupd.putExtra("updAdd", Add);
                String City = mylist.getCity();
                Profileupd.putExtra("updCity", City);
                Profileupd.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Profileupd);

            }
        });


    }

    @Override
    public int getItemCount() {
        return 1;
    }
}

class ViewHolder extends RecyclerView.ViewHolder {
    TextView NAME, Contact, Blood, Email, Don, Add, City;
    Button Update;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        Email = itemView.findViewById(R.id.profilename);
        Contact = itemView.findViewById(R.id.profileemail);
        Blood = itemView.findViewById(R.id.profilecontact);
        NAME = itemView.findViewById(R.id.profilebloodgroup);
        Update = itemView.findViewById(R.id.updatebtnss);
        Don = itemView.findViewById(R.id.profileDonor);
        Add = itemView.findViewById(R.id.profileAddress);
        City = itemView.findViewById(R.id.profileCity);

    }
}

