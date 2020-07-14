package com.example.majoringrecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText inputEmail;
    private Button btnReset, btnBack;
    String email;
    Spinner spinner1;
    String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        inputEmail=findViewById(R.id.Email);
        btnReset = (Button) findViewById(R.id.btn_reset);
        btnBack = (Button) findViewById(R.id.btn_back);
        spinner1=findViewById(R.id.spinner1);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this,
                R.array.type,
                R.layout.color_spinner_layout
        );

        spinner1.setAdapter(adapter);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = spinner1.getSelectedItem().toString();
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                type =spinner1.getChildAt(0).toString();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = inputEmail.getText().toString().trim();
                if (!email.isEmpty()){
                    if (type.equalsIgnoreCase("student")) {
//                    // login user
                        sendEmail();
                    } else {
                        adminEmail();
                    }


                } else {
                    Toast.makeText(getApplicationContext(),"Please enter your email",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendEmail(){
        final ProgressDialog loading = ProgressDialog.show(this,"Please Wait","Contacting Server",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://pickupandlaundry.com/mrs/php/forgotpassword.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        if (response.equalsIgnoreCase("Success")) {
                            Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                            Toast.makeText(ForgotPasswordActivity.this, "Password has been sent to your email.", Toast.LENGTH_LONG).show();
                            startActivity(i);
                            finish();
                        } else {
                            loading.dismiss();
                            Toast.makeText(ForgotPasswordActivity.this, "Invalid email", Toast.LENGTH_LONG).show();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(ForgotPasswordActivity.this, "No internet . Please check your connection",
                            Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(ForgotPasswordActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("email", email);

                //returning parameter
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void adminEmail(){
        final ProgressDialog loading = ProgressDialog.show(this,"Please Wait","Contacting Server",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://pickupandlaundry.com/mrs/php/adminForgotPwd.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        if (response.equalsIgnoreCase("Success")) {
                            Intent i = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                            Toast.makeText(ForgotPasswordActivity.this, "Password has been sent to your email.", Toast.LENGTH_LONG).show();
                            startActivity(i);
                            finish();
                        } else {
                            loading.dismiss();
                            Toast.makeText(ForgotPasswordActivity.this, "Invalid email", Toast.LENGTH_LONG).show();
                        }
                    }
                },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(ForgotPasswordActivity.this, "No internet . Please check your connection",
                            Toast.LENGTH_LONG).show();
                } else {

                    Toast.makeText(ForgotPasswordActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }){
            @Override
            protected Map<String, String> getParams () throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put("email", email);

                //returning parameter
                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
