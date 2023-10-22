package com.example.bloodbankproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AddBloodRequest extends AppCompatActivity {
    //Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String Id, Blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_blood_request);

        EditText edR_Name, edR_Contact, edR_Address, edR_City, edR_Bag;
        Spinner edR_Blood;
        String R_URL = "https://personable-bubble.000webhostapp.com/UserReqBlood.php";
        Button btnR_Submit;

        edR_Name = findViewById(R.id.R_Name);
        edR_Contact = findViewById(R.id.R_Contact);
        edR_Address = findViewById(R.id.R_Location);
        edR_City = findViewById(R.id.R_City);
        edR_Blood = findViewById(R.id.R_Blood);
        edR_Bag = findViewById(R.id.R_Bags);
        btnR_Submit = findViewById(R.id.R_Submit);

        Intent req = getIntent();
        Id = req.getStringExtra("RequserId");

        //Menu
        drawerLayout = findViewById(R.id.DrawableLayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);
//Menu end

        List<String> item = new ArrayList<>();
        item.add("A+");
        item.add("A-");
        item.add("B+");
        item.add("B-");
        item.add("O+");
        item.add("O-");
        item.add("AB+");
        item.add("AB-");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(AddBloodRequest.this, R.layout.support_simple_spinner_dropdown_item, item);
        edR_Blood.setAdapter(adapter);
        edR_Blood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Blood = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnR_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                String Name = edR_Name.getText().toString();
                String Contact = edR_Contact.getText().toString();
                String Address = edR_Address.getText().toString();
                String City = edR_City.getText().toString();
                String Bag = edR_Bag.getText().toString();

                Toast.makeText(getApplicationContext(), currentDate + currentTime + Name + Contact + Address + City + Blood, Toast.LENGTH_SHORT).show();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, R_URL, new Response.Listener<String>() {
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
                        map.put("UserId", Id);
                        map.put("Address", Address);
                        map.put("Contact", Contact);
                        map.put("City", City);
                        map.put("BloodGroup", Blood);
                        map.put("Bags", Bag);
                        return map;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


            }
        });

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Request:
                Intent obj = new Intent(getApplicationContext(), bloodreq.class);
                obj.putExtra("MyId", Id);
                startActivity(obj);
                finish();
                return true;

            case R.id.update:
                Intent upd = new Intent(getApplicationContext(), Dashboard.class);
                upd.putExtra("updId", Id);
                startActivity(upd);
                //  finish();
                return true;
            case R.id.Next:
                Intent Next = new Intent(getApplicationContext(), NextDonation.class);
                Next.putExtra("Id", Id);
                startActivity(Next);
                finish();
                return true;
            case R.id.Add:
                Intent req = new Intent(getApplicationContext(), AddBloodRequest.class);
                req.putExtra("RequserId", Id);
                startActivity(req);
                finish();
                return true;
            case R.id.UpdateReq:
                Intent d = new Intent(getApplicationContext(), UserRequest.class);
                d.putExtra("userId", Id);
                startActivity(d);
                finish();
                return true;
            case R.id.BloodInfo:
                Intent G = new Intent(getApplicationContext(), BloodInfoTable.class);
                G.putExtra("Id", Id);
                startActivity(G);
                finish();
                return true;
            case R.id.FindDonor:
                Intent F = new Intent(getApplicationContext(), BloodDonor.class);
                F.putExtra("Id", Id);
                startActivity(F);
                finish();
                return true;
            case R.id.About:
                Toast.makeText(getApplicationContext(), "App is Develop for SE project :)", Toast.LENGTH_LONG).show();
//                finish();
                return true;
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences("loginAuth", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().commit();
                Intent intent = new Intent(getApplicationContext(), loginpage.class);
                startActivity(intent);
                finish();
                return true;

        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(getApplicationContext());
        menuInflater.inflate(R.menu.mymenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.About:
                Toast.makeText(getApplicationContext(), "App is Develop for SE project :)", Toast.LENGTH_LONG).show();
                break;
            case R.id.logout:
                SharedPreferences sharedPreferences = getSharedPreferences("loginAuth", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().commit();
                Intent intent = new Intent(getApplicationContext(), loginpage.class);
                startActivity(intent);
                break;

        }
        return true;
    }
}