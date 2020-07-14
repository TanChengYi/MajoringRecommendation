package com.example.majoringrecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
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

public class QuestionActivity extends AppCompatActivity {
    Button submit;
    User user;
    RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7;
    int se,im,is,cn;
    int totse,totim,totis,totcn;
    int avgse,avgim,avgis,avgcn;
    String SE,IM,IS,CN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        rg1=findViewById(R.id.q1);
        rg2=findViewById(R.id.q2);
        rg3=findViewById(R.id.q3);
        rg4=findViewById(R.id.q4);
        rg5=findViewById(R.id.q5);
        rg6=findViewById(R.id.q6);
        rg7=findViewById(R.id.q7);
        user=(User)getIntent().getSerializableExtra("UserDetails");

        submit=findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rg1.getCheckedRadioButtonId()!=-1 && rg2.getCheckedRadioButtonId()!=-1 && rg3.getCheckedRadioButtonId()!=-1 && rg4.getCheckedRadioButtonId()!=-1
                        && rg5.getCheckedRadioButtonId()!=-1&& rg6.getCheckedRadioButtonId()!=-1&& rg7.getCheckedRadioButtonId()!=-1){
                    analysis();
                    uploadRecommendation();
                }else{
                    Toast.makeText(QuestionActivity.this, "Please answer all question", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void analysis(){
        im=0;cn=0;se=0;is=0;totis=0;totim=0;totcn=0;totse=0;
        avgcn=0;avgse=0;avgim=0;avgis=0;
        rg1=findViewById(R.id.q1);
        int group=rg1.getCheckedRadioButtonId();
        if(group==R.id.o1){
            im+=10;
        }else if(group==R.id.o2){
            cn+=10;
        }else if(group==R.id.o3){
            is+=10;
        }else if(group==R.id.o4){
            se+=10;
        }else{

        }
        totse+=10;
        totcn+=10;
        totim+=10;
        totis+=10;

        rg2=findViewById(R.id.q2);
        group=rg2.getCheckedRadioButtonId();
        if(group==R.id.op1){
            se+=10;
            is+=10;
        }else if(group==R.id.op2){
            se+=7;
            is+=7;
        }else if(group==R.id.op3){
            se+=3;
            is+=3;
        }else if(group==R.id.op4){
            se+=0;
            is+=0;
        }
        totse+=10;
        totis+=10;

        rg3=findViewById(R.id.q3);
        group=rg3.getCheckedRadioButtonId();
        if(group==R.id.q3o1){
            im+=8;cn+=7;is+=10;se+=10;
        }else if(group==R.id.q3o2){
            im+=5;cn+=4;is+=7;se+=7;
        }else if(group==R.id.q3o3){
            im+=3;cn+=2;is+=5;se+=5;
        }else if(group==R.id.q3o4){
            im+=0;cn+=0;is+=0;se+=0;
        }
        totse+=10;
        totcn+=7;
        totim+=8;
        totis+=10;

        rg4=findViewById(R.id.q4);
        group=rg4.getCheckedRadioButtonId();
        if(group==R.id.q4o1){
            im+=10;cn+=5;is+=8;se+=5;
        }else if(group==R.id.q4o2){
            im+=7;cn+=2;is+=5;se+=2;
        }else if(group==R.id.q4o3){
            im+=5;cn+=1;is+=3;se+=1;
        }else if(group==R.id.q4o4){
            im+=0;cn+=0;is+=0;se+=0;
        }else{

        }
        totse+=5;
        totcn+=5;
        totim+=10;
        totis+=8;

        rg5=findViewById(R.id.q5);
        group=rg5.getCheckedRadioButtonId();
        if(group==R.id.q5o1){
            is+=10;
        }else if(group==R.id.q5o2){
            is+=8;
        }else if(group==R.id.q5o3){
            is+=5;
        }else if(group==R.id.q5o4){
            is+=0;
        }else{

        }
        totis+=10;

        rg6=findViewById(R.id.q6);
        group=rg6.getCheckedRadioButtonId();
        if(group==R.id.q6o1){
            cn+=10;se+=8;
        }else if(group==R.id.q6o2){
            cn+=7;se+=5;
        }else if(group==R.id.q6o3){
            cn+=5;se+=3;
        }else if(group==R.id.q6o4){
            cn+=0;se+=0;
        }else{

        }
        totse+=8;
        totcn+=10;

        rg7=findViewById(R.id.q7);
        group=rg7.getCheckedRadioButtonId();
        if(group==R.id.q7o1){
            im+=8;cn+=8;is+=9;se+=10;
        }else if(group==R.id.q7o2){
            im+=5;cn+=5;is+=6;se+=7;
        }else if(group==R.id.q7o3){
            im+=3;cn+=3;is+=4;se+=5;
        }else if(group==R.id.q7o4){
            im+=0;cn+=0;is+=0;se+=0;
        }else{

        }
        totse+=10;
        totcn+=8;
        totim+=8;
        totis+=9;

        avgse=(se/totse)*100;
        avgis=(is/totis)*100;
        avgim=(im/totim)*100;
        avgcn=(se/totcn)*100;

        if(avgse>=80){
            SE="Highly Recommended";
        }else if(avgse>=50){
            SE="Recommended";
        }else{
            SE="Not Recommended";
        }

        if(avgis>=80){
            IS="Highly Recommended";
        }else if(avgis>=50){
            IS="Recommended";
        }else{
            IS="Not Recommended";
        }

        if(avgim>=80){
            IM="Highly Recommended";
        }else if(avgim>=50){
            IM="Recommended";
        }else{
            IM="Not Recommended";
        }

        if(avgcn>=80){
            CN="Highly Recommended";
        }else if(avgcn>=50){
            CN="Recommended";
        }else{
            CN="Not Recommended";
        }
    }

    public void uploadRecommendation(){
        //Getting values from edit texts
        final ProgressDialog loading = ProgressDialog.show(this,"Please Wait","Analysis answer",false,false);
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://pickupandlaundry.com/mrs/php/setRecom.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        //If we are getting success from server
                        if(response.equalsIgnoreCase("success")){

                            Intent i = new Intent(QuestionActivity.this, RecommendationActivity.class);
                            i.putExtra("UserDetails",user);
                            startActivity(i);
                            finish();


                        }else{
                            //If the server response is not success
                            //Displaying an error message on toast
                            loading.dismiss();
                            Toast.makeText(QuestionActivity.this, "Invalid Input", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        loading.dismiss();
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(QuestionActivity.this,"No internet . Please check your connection",
                                    Toast.LENGTH_LONG).show();
                        }
                        else{

                            Toast.makeText(QuestionActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }
                }){


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                //Adding parameters to request
                params.put("email", user.getEmail());
                params.put("se", SE);
                params.put("is",IS);
                params.put("im", IM);
                params.put("cn", CN);
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
