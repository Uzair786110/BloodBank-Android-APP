package com.example.bloodbankproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class loginpage extends AppCompatActivity {
    String Id, URL = "https://personable-bubble.000webhostapp.com/Login.php",
            EmailtoIdURL = "https://personable-bubble.000webhostapp.com/EmailToId.php";
    EditText edEmail, edPass;
    TextView tvId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        edEmail = findViewById(R.id.loginuser);
        edPass = findViewById(R.id.loginpass);
        tvId = findViewById(R.id.Idbatao);

        Button login = findViewById(R.id.loginbtn);
        Button regis = findViewById(R.id.signupbtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailintent = edEmail.getText().toString();
                EmailtoId(emailintent);
                Thread timerThread = new Thread() {
                    public void run() {
                        try {
                            sleep(1500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {

                            Login(edEmail.getText().toString(), edPass.getText().toString());
                        }
                    }
                };
                timerThread.start();
//                Intent i = new Intent(getApplicationContext(),Admin.class);
//                startActivity(i);

            }
        });
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);

            }
        });
    }


    public void Login(String Email, String Password) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("0")) {
                    Toast.makeText(getApplicationContext(), "Admin", Toast.LENGTH_LONG).show();
                } else if (response.equals("1")) {
                    Toast.makeText(getApplicationContext(), "BloodBank", Toast.LENGTH_LONG).show();
                } else if (response.equals("2")) {
                    // Toast.makeText(getApplicationContext(), "User", Toast.LENGTH_LONG).show();
                    Intent obj = new Intent(getApplicationContext(), bloodreq.class);
                    Id = tvId.getText().toString();
                    obj.putExtra("MyId", Id);
                    startActivity(obj);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid Email and Password", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", Email);
                map.put("pass", Password);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void EmailtoId(String Email) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, EmailtoIdURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //   Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                tvId.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("email", Email);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}