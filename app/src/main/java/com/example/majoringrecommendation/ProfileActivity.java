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

public class ProfileActivity extends AppCompatActivity {
    Button chgPwd,chgName,chgMno,back;
    User user;
    TextView em,nm,mn;
    String email,matrix,Uname,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user=(User)getIntent().getSerializableExtra("UserDetails");
        loadData();

        chgPwd=findViewById(R.id.chgPwd);
        chgName=findViewById(R.id.chgUname);
        chgMno=findViewById(R.id.chgMno);
        back=findViewById(R.id.back);
        chgPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, updateUpwdActivity.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });
        chgName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, updateNameActivity.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });
        chgMno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Intent i = new Intent(ProfileActivity.this, deleteAccActivity.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, MainActivity.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });
    }

    private void loadData(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://pickupandlaundry.com/mrs/php/getUdetail.php",
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
                            user.setPassword(password);

                            em=findViewById(R.id.email);
                            mn=findViewById(R.id.mNo);
                            nm=findViewById(R.id.uName);
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
