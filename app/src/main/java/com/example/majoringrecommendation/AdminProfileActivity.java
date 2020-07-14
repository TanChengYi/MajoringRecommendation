package com.example.majoringrecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AdminProfileActivity extends AppCompatActivity {
    Button cPwd,cName,back;
    User user;
    TextView em,nm,mn;
    String email,matrix,Uname,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        user=(User)getIntent().getSerializableExtra("UserDetails");
        loadData();
        cName=findViewById(R.id.achgUname);
        cPwd=findViewById(R.id.achgPwd);
        back=findViewById(R.id.aback);

        cName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminProfileActivity.this, adminCnameActivity.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });

        cPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminProfileActivity.this, adminCpwdActivity.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminProfileActivity.this, adminMain.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });
    }

    private void loadData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://pickupandlaundry.com/mrs/php/getAdetail.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        try {

                            JSONObject obj = new JSONObject(response);
                            email=obj.getString("em");
                            matrix=obj.getString("mn");
                            Uname=obj.getString("nm");
                            password=obj.getString("pwd");

                            em=findViewById(R.id.aemail);
                            mn=findViewById(R.id.aNo);
                            nm=findViewById(R.id.aName);
                            em.setText(email);
                            mn.setText(matrix);
                            nm.setText(Uname);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                //Adding parameters to request
                params.put("email", user.getEmail());
                //returning parameter
                return params;
            }
        };

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }
}
