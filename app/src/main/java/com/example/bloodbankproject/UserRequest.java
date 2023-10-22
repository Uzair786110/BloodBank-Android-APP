package com.example.bloodbankproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class UserRequest extends AppCompatActivity {
    String Id;
    RecyclerView recyclerView;
    List<userreq> listList;
    RecyclerView.Adapter adapter;

    //Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request);


        Intent d = getIntent();
        Id = d.getStringExtra("userId");

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

        recyclerView = findViewById(R.id.user_rv);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listList = new ArrayList<>();
        getData();


        // getData();

    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://personable-bubble.000webhostapp.com/ViewBloodRequest.php?id=" + Id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("all");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                userreq list = new userreq(object.getString("R_id"),
                                        object.getString("Name"),
                                        object.getString("Contact"),
                                        object.getString("Address"),
                                        object.getString("City"),
                                        object.getString("Blood"),
                                        object.getString("Date"),
                                        object.getString("Bags"),
                                        object.getString("UserIdKey"));
                                listList.add(list);
                            }
                            adapter = new UserCustomAdapter(listList, getApplicationContext());
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }


        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
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

