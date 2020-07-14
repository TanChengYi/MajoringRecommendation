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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RecommendationActivity extends AppCompatActivity {
    Button back;
    TextView se,is,im,cn;
    User user;
    String SE,IS,IM,CN;
    TextView em;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        back=findViewById(R.id.back);
        user=(User)getIntent().getSerializableExtra("UserDetails");
        em=findViewById(R.id.remail);
        em.setText(user.getEmail());


        retrieveJSON();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecommendationActivity.this, MainActivity.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });
    }

    private void retrieveJSON() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://pickupandlaundry.com/mrs/php/getRecom.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        try {

                            JSONObject obj = new JSONObject(response);
                            SE=obj.getString("se");
                            IM=obj.getString("im");
                            IS=obj.getString("is");
                            CN=obj.getString("cn");

                            se=findViewById(R.id.se);
                            is=findViewById(R.id.is);
                            im=findViewById(R.id.im);
                            cn=findViewById(R.id.cn);
                            se.setText(SE);
                            is.setText(IS);
                            im.setText(IM);
                            cn.setText(CN);
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
