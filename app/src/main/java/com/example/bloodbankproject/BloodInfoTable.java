package com.example.bloodbankproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class BloodInfoTable extends AppCompatActivity {
    //Menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String Id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_info_table);

        Intent G = getIntent();
        Id = G.getStringExtra("Id");

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
                // finish();
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