package com.example.bloodbankproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateProfile extends AppCompatActivity {
    EditText u_name, u_email, u_contact, u_password, uc_password, u_blood, u_ADD, u_City;
    TextView u_id;
    CheckBox updch;
    String id, name, email, donor, contact, password, blood, Address, City;
    String UpdateURL, u_donor = "0", inemail, inname, inid, incontact, inblood, indon, inAdd, inCity;

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()) {
            case R.id.updcheck:
                if (checked) {
                    u_donor = "1";
                } else {
                    u_donor = "0";
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        u_id = findViewById(R.id.uid);
        u_name = findViewById(R.id.uusername);
        u_email = findViewById(R.id.uusermail);
        u_contact = findViewById(R.id.ucontact);
        u_password = findViewById(R.id.upassword);
        uc_password = findViewById(R.id.uc_password);
        u_blood = findViewById(R.id.ubloodgroup);
        u_ADD = findViewById(R.id.uAdd);
        u_City = findViewById(R.id.uCity);
        updch = findViewById(R.id.updcheck);

        id = u_id.getText().toString();
        name = u_name.getText().toString();
        email = u_email.getText().toString();
        contact = u_contact.getText().toString();
        password = u_password.getText().toString();
        blood = u_blood.getText().toString();
        Address = u_ADD.getText().toString();
        City = u_City.getText().toString();


        Intent Profileupd = getIntent();
        inname = Profileupd.getStringExtra("updEmail");
        inid = Profileupd.getStringExtra("updName");
        inemail = Profileupd.getStringExtra("updContact");
        incontact = Profileupd.getStringExtra("updBlood");
        inblood = Profileupd.getStringExtra("updId");
        indon = Profileupd.getStringExtra("updDon");
        inAdd = Profileupd.getStringExtra("updAdd");
        inCity = Profileupd.getStringExtra("updCity");

        u_name.setText(inname);
        u_contact.setText(incontact);
        u_blood.setText(inblood);
        u_id.setText(inid);
        u_email.setText(inemail);
        u_ADD.setText(inAdd);
        u_City.setText(inCity);

//        recyclerView = findViewById(R.id.rvupdate);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        newList = new ArrayList<>();


        //  getData();


    }

    public void update(View view) {
        id = u_id.getText().toString();
        name = u_name.getText().toString();
        email = u_email.getText().toString();
        contact = u_contact.getText().toString();
        password = u_password.getText().toString();
        blood = u_blood.getText().toString();
        Address = u_ADD.getText().toString();
        City = u_City.getText().toString();
        donor = u_donor;

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        UpdateURL = "https://personable-bubble.000webhostapp.com/UpdateUser.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UpdateURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                Intent upd = new Intent(getApplicationContext(), Dashboard.class);
                upd.putExtra("updId", id);
                startActivity(upd);
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("id", id);
                map.put("name", name);
                map.put("email", email);
                map.put("contact", contact);
                map.put("password", password);
                map.put("blood", blood);
                map.put("Donor", u_donor);
                map.put("Address", Address);
                map.put("City", City);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}