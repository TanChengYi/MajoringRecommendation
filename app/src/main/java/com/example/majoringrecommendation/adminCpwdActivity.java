package com.example.majoringrecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class adminCpwdActivity extends AppCompatActivity {
    User user;
    EditText pwd;
    Button update,back;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cpwd);

        user = (User) getIntent().getSerializableExtra("UserDetails");
        pwd = findViewById(R.id.newPwd);
        update = findViewById(R.id.btn_upd);
        back = findViewById(R.id.back1);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(adminCpwdActivity.this, AdminProfileActivity.class);
                i.putExtra("UserDetails", user);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pass = pwd.getText().toString().trim();
                    if (pass!=null) {
                        changePass( );
                    } else {
                        Toast.makeText(adminCpwdActivity.this, "Please Insert New Password.", Toast.LENGTH_LONG)
                                .show();
                    }


            }
        });
    }

    public void changePass( ) {
        final ProgressDialog loading = ProgressDialog.show(adminCpwdActivity.this, "Please Wait", "Contacting Server", false, false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://pickupandlaundry.com/mrs/php/adminCpwd.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                loading.dismiss();

                if (response.equalsIgnoreCase("Success")) {

                    Toast.makeText(adminCpwdActivity.this, "Successfully Updated", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(adminCpwdActivity.this, AdminProfileActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("UserDetails", user);
                    startActivity(i);
                    finish();
                } else {

                    Toast.makeText(adminCpwdActivity.this, "Update failed", Toast.LENGTH_LONG)
                            .show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(adminCpwdActivity.this, "No internet . Please check your connection",
                            Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(adminCpwdActivity.this, error.toString(), Toast.LENGTH_LONG).show();
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