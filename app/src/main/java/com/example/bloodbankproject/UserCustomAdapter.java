package com.example.bloodbankproject;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

public class UserCustomAdapter extends RecyclerView.Adapter<UserCustomAdapter.ViewHolder> {
    List<userreq> list;
    Context context;
    String URL;

    public UserCustomAdapter(List<userreq> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public UserCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.userbloodrequest, null);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(UserCustomAdapter.ViewHolder holder, int position) {
        final userreq list1 = list.get(position);
        holder.tvDate.setText(list1.getDate());
        holder.tvName.setText(list1.getName());
        holder.tvAddress.setText(list1.getAddress());
        holder.tvCity.setText(list1.getCity());
        holder.tvBlood.setText(list1.getBlood());
        holder.tvStatus.setText(list1.getBags());
        holder.tvContact.setText(list1.getContact());
        holder.Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Requestupd = new Intent(context, UpdateWork.class);
                String Idupd = list1.getR_id();
                Requestupd.putExtra("updId", Idupd);
                String Nameupd = list1.getName();
                Requestupd.putExtra("updName", Nameupd);
                String Contactupd = list1.getContact();
                Requestupd.putExtra("updContact", Contactupd);
                String Address = list1.getAddress();
                Requestupd.putExtra("updAddress", Address);
                String City = list1.getCity();
                Requestupd.putExtra("updCity", City);
                String Blood = list1.getBlood();
                Requestupd.putExtra("updBlood", Blood);
                String Req = list1.getBags();
                Requestupd.putExtra("updBag", Req);
                String user = list1.getUserid();
                Requestupd.putExtra("upduser", user);
                Requestupd.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(Requestupd);
            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                URL = "https://personable-bubble.000webhostapp.com/DeleteBloodRequest.php?id=" + list1.getR_id();
                Deletedata();
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

        TextView tvDate, tvName, tvAddress, tvCity, tvBlood, tvStatus, tvContact;
        Button Update, Delete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.ureqDate);
            tvName = itemView.findViewById(R.id.ureqName);
            tvAddress = itemView.findViewById(R.id.ureqAddress);
            tvCity = itemView.findViewById(R.id.ureqCity);
            tvBlood = itemView.findViewById(R.id.ureqbloodgroup);
            tvStatus = itemView.findViewById(R.id.ureqstatus);
            tvContact = itemView.findViewById(R.id.ureqcontact);
            Update = itemView.findViewById(R.id.requpdatebtn);
            Delete = itemView.findViewById(R.id.Deletebtn);


        }

    }

    public void Deletedata() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context, response, Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }
}
