package com.example.bloodbankproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText username, usermail, contact, password, c_password,address,city;
    Spinner spblood;
    String addstu_url = "https://personable-bubble.000webhostapp.com/UserRegistration.php",donor="1";
    String Blood;
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkdonor:
                if (checked)
                {
                donor="1";
                }
                else
                {
                    donor="0";
                }
                break;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        usermail = findViewById(R.id.usermail);
        contact = findViewById(R.id.contact);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.C_password);
        spblood = findViewById(R.id.spblood);
        address=findViewById(R.id.Address);
        city=findViewById(R.id.City);



        List<String> item = new ArrayList<>();
        item.add("A+");
        item.add("A-");
        item.add("B+");
        item.add("B-");
        item.add("O+");
        item.add("O-");
        item.add("AB+");
        item.add("AB-");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, item);
        spblood.setAdapter(adapter);
        spblood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Blood = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Button Register = findViewById(R.id.registerbtn);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty() || usermail.getText().toString().isEmpty() || contact.getText().toString().isEmpty() || password.getText().toString().isEmpty() || c_password.getText().toString().isEmpty()|| city.getText().toString().isEmpty()|| address.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Fill All Fields", Toast.LENGTH_SHORT).show();
                } else if (password.getText().toString().equals(c_password.getText().toString())) {
                    register(username.getText().toString(), usermail.getText().toString(), contact.getText().toString(), password.getText().toString(), Blood,address.getText().toString(),city.getText().toString());
                } else {
                    Toast.makeText(getApplicationContext(), "Password Missmatch", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void register(final String username, final String usermail, final String contact, final String password, final String bloodgroup, final String address, final String city) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, addstu_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                if (response.trim().equals("Insert")) {
                    Intent i = new Intent(MainActivity.this, loginpage.class);
                    startActivity(i);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> obj = new HashMap<>();
                obj.put("Name", username);
                obj.put("Email", usermail);
                obj.put("Contact", contact);
                obj.put("Password", password);
                obj.put("BloodGroup", bloodgroup);
                obj.put("Don",donor);
                obj.put("Address",address);
                obj.put("City",city);
                return obj;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }


}