package com.example.majoringrecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class deleteAccActivity extends AppCompatActivity {
    User user;
    EditText pwd;
    Button delete,back;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_acc);
        user=(User)getIntent().getSerializableExtra("UserDetails");
        pwd=findViewById(R.id.dpwd);
        delete=findViewById(R.id.btn_del);
        back=findViewById(R.id.back1);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(deleteAccActivity.this, ProfileActivity.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pass=pwd.getText().toString().trim();
                if(pass!=null){
                    dAcc();
                }else{
                    Toast.makeText(deleteAccActivity.this, "Please Insert Password.", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


    }

    public void dAcc(){
        final ProgressDialog loading = ProgressDialog.show(deleteAccActivity.this,"Please Wait","Contacting Server",false,false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://pickupandlaundry.com/mrs/php/deleteAcc.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                loading.dismiss();

                if (response.equalsIgnoreCase("success")) {

                    Toast.makeText(deleteAccActivity.this, "Successfully Deleted", Toast.LENGTH_LONG)
                            .show();
                    SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.clear().commit();
                    Intent i = new Intent(deleteAccActivity.this, LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }else{

                    Toast.makeText(deleteAccActivity.this, "Delete failed", Toast.LENGTH_LONG)
                            .show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(deleteAccActivity.this,"No internet . Please check your connection",
                            Toast.LENGTH_LONG).show();
                }
                else{

                    Toast.makeText(deleteAccActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", user.getEmail());
                params.put("password", pass);
                return params;
            }

        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
