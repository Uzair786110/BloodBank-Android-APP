package com.example.bloodbankproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class BecomeDonor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_become_donor);
        EditText edD_Name, edD_Contact, edD_Address, edD_City, edD_Age, edD_Blood;
        String D_URL = "https://personable-bubble.000webhostapp.com/AddBloodDonor.php";
        Button btnD_Submit;

        edD_Name = findViewById(R.id.D_Name);
        edD_Contact = findViewById(R.id.D_Contact);
        edD_Address = findViewById(R.id.D_Location);
        edD_City = findViewById(R.id.D_City);
        edD_Age = findViewById(R.id.D_Age);
        edD_Blood = findViewById(R.id.D_Blood);
        btnD_Submit = findViewById(R.id.D_Submit);

        btnD_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                String Name = edD_Name.getText().toString();
                String Contact = edD_Contact.getText().toString();
                String Address = edD_Address.getText().toString();
                String City = edD_City.getText().toString();
                String Age = edD_Age.getText().toString();
                String Blood = edD_Blood.getText().toString();

                Toast.makeText(getApplicationContext(), currentDate + currentTime + Name + Contact + Address + Age + City + Blood, Toast.LENGTH_SHORT).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, D_URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "Data inserted", Toast.LENGTH_LONG).show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }

                ) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> map = new HashMap();
                        map.put("Date", currentDate);
                        map.put("Time", currentTime);
                        map.put("Name", Name);
                        map.put("Address", Address);
                        map.put("Contact", Contact);
                        map.put("City", City);
                        map.put("Age", Age);
                        map.put("BloodGroup", Blood);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


            }
        });

    }
}