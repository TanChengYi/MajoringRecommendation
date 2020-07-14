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

public class adminCnameActivity extends AppCompatActivity {

    User user;
    EditText uName;
    Button update,back;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cname);
        user=(User)getIntent().getSerializableExtra("UserDetails");
        uName=findViewById(R.id.newUname);
        update=findViewById(R.id.btn_upd);
        back=findViewById(R.id.back1);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(adminCnameActivity.this, AdminProfileActivity.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=uName.getText().toString().trim();
                if(name!=null){
                    changeName();
                }else{
                    Toast.makeText(adminCnameActivity.this, "Please Input New Username.", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


    }

    public void changeName(){
        final ProgressDialog loading = ProgressDialog.show(adminCnameActivity.this,"Please Wait","Contacting Server",false,false);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://pickupandlaundry.com/mrs/php/adminCname.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                loading.dismiss();

                if (response.equalsIgnoreCase("Success")) {

                    Toast.makeText(adminCnameActivity.this, "Successfully Updated", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(adminCnameActivity.this, AdminProfileActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("UserDetails",user);
                    startActivity(i);
                    finish();
                }else{

                    Toast.makeText(adminCnameActivity.this, "Update failed", Toast.LENGTH_LONG)
                            .show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(adminCnameActivity.this,"No internet . Please check your connection",
                            Toast.LENGTH_LONG).show();
                }
                else{

                    Toast.makeText(adminCnameActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", user.getEmail());
                params.put("username", name);
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
