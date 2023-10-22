package com.example.bloodbankproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bloodreq extends AppCompatActivity {
    String  Id,Searches;
    RecyclerView recyclerView;
    List<userreq> listList;
    RecyclerView.Adapter adapter;


    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bloodreq);
        recyclerView = findViewById(R.id.rv);



        Spinner Searchblood =findViewById(R.id.searchblood);
        List<String> item = new ArrayList<>();
        item.add("Search By Blood");
        item.add("A");
        item.add("B");
        item.add("O");
        item.add("AB");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(bloodreq.this, R.layout.support_simple_spinner_dropdown_item, item);
        Searchblood.setAdapter(adapter);
        Searchblood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!"Search By Blood".equals(parent.getItemAtPosition(position).toString())) {
                    Searches = parent.getItemAtPosition(position).toString();
                    Toast.makeText(getApplicationContext(), Searches + " filtered", Toast.LENGTH_LONG).show();
                } else {
                    Searches = "";
                }

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                listList = new ArrayList<>();
                getData();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE}, PackageManager.PERMISSION_GRANTED);

        drawerLayout = findViewById(R.id.DrawableLayout);
        navigationView = findViewById(R.id.navigationview);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent obj = getIntent();
        Id = obj.getStringExtra("MyId");


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this::onNavigationItemSelected);

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Request:
                Intent obj = new Intent(getApplicationContext(), bloodreq.class);
                obj.putExtra("MyId", Id);
                startActivity(obj);
               // finish();
                return true;

            case R.id.update:
                Intent upd = new Intent(getApplicationContext(), Dashboard.class);
                upd.putExtra("updId", Id);
                startActivity(upd);
                finish();
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
//               Intent ii=new Intent(Dashboard.this,gethospital.class);
//               startActivity(ii);
                break;

        }
        return true;
    }

    private void getData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,"https://personable-bubble.000webhostapp.com/ViewBloodRequestFilter.php?blood="+Searches,
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
                            adapter = new CustomAdapter(listList, getApplicationContext());
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

}

