package com.example.bloodbankproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class UpdateWork extends AppCompatActivity {
    EditText Name, Contact, Location, City, Type, Required;
    String Requpdid, Requpdname, Requpdcontact, Requpdaddress, Requpdcity, Requpdblood, Requpdbag,Requser;
    String name, contact, location, city, bloodtype, bags;
    Button updatebtn;
    String UpdateURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_work);
        Name = findViewById(R.id.requpdateName);
        Contact = findViewById(R.id.requpdateContact);
        Location = findViewById(R.id.requpdateLocation);
        City = findViewById(R.id.requpdateCity);
        Type = findViewById(R.id.requpdateType);
        Required = findViewById(R.id.requpdateRequiredBlood);
        updatebtn = findViewById(R.id.btnup);


        Intent Requestupd = getIntent();
        Requpdid = Requestupd.getStringExtra("updId");
        Requpdname = Requestupd.getStringExtra("updName");
        Requpdcontact = Requestupd.getStringExtra("updContact");
        Requpdaddress = Requestupd.getStringExtra("updAddress");
        Requpdcity = Requestupd.getStringExtra("updCity");
        Requpdblood = Requestupd.getStringExtra("updBlood");
        Requpdbag = Requestupd.getStringExtra("updBag");
        Requser=Requestupd.getStringExtra("upduser");

        Name.setText(Requpdname);
        Contact.setText(Requpdcontact);
        Location.setText(Requpdaddress);
        City.setText(Requpdcity);
        Type.setText(Requpdblood);
        Required.setText(Requpdbag);

    }

    public void updateReq(View view) {
        name = Name.getText().toString();
        contact = Contact.getText().toString();
        location = Location.getText().toString();
        city = City.getText().toString();
        bloodtype = Type.getText().toString();
        bags = Required.getText().toString();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Updating...");
        progressDialog.show();

        UpdateURL = "https://personable-bubble.000webhostapp.com/UpdateUserBloodReq.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UpdateURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                Intent d = new Intent(getApplicationContext(), UserRequest.class);
                d.putExtra("userId",Requser);
                startActivity(d);
                finish();
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
                map.put("id", Requpdid);
                map.put("name", name);
                map.put("contact", contact);
                map.put("Address", location);
                map.put("City", city);
                map.put("blood", bloodtype);
                map.put("Bags", bags);
                return map;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
