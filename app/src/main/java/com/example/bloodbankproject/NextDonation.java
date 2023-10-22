package com.example.bloodbankproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;

public class NextDonation extends AppCompatActivity {
    TextView LastDonation, NextDonation;
    Button Date;
    int y, m, d;
    //Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_donation);

        Intent Next = getIntent();
        Id = Next.getStringExtra("Id");

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
        LastDonation = findViewById(R.id.LastDonation);
        NextDonation = findViewById(R.id.futureDonation);
        Date = findViewById(R.id.EnterDate);
        Calendar c = Calendar.getInstance();
        Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                y = c.get(Calendar.YEAR);
                m = c.get(Calendar.MONTH);
                d = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog L = new DatePickerDialog(NextDonation.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        LastDonation.setText(dayOfMonth + "-" + Integer.valueOf(month + 1) + "-" + year);
                        String dt = year + "-" + Integer.valueOf(month + 1) + "-" + dayOfMonth;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar c = Calendar.getInstance();
                        try {
                            c.setTime(sdf.parse(dt));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        c.add(Calendar.DATE, 120);  // number of days to add, can also use Calendar.DAY_OF_MONTH in place of Calendar.DATE
                        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
                        String output = sdf1.format(c.getTime());
                        NextDonation.setText(output);
                    }
                }, y, m, d);
                L.show();


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
                finish();
                return true;
            case R.id.Next:
                Intent Next = new Intent(getApplicationContext(), NextDonation.class);
                Next.putExtra("Id", Id);
                startActivity(Next);
                // finish();
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