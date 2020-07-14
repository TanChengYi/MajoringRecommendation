package com.example.majoringrecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
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
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ViewAllActivity extends AppCompatActivity {

    Button back;

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        user=(User)getIntent().getSerializableExtra("UserDetails");
        back=findViewById(R.id.back);
        retrieveJSON();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ViewAllActivity.this, adminMain.class);
                i.putExtra("UserDetails",user);
                startActivity(i);
            }
        });
    }

    private void retrieveJSON() {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://pickupandlaundry.com/mrs/php/getArecom.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrrr", ">>" + response);
                        try {
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray jArray=jsonObject.getJSONArray("recom");
                            TableLayout tv = (TableLayout) findViewById(R.id.table);
                            tv.removeAllViewsInLayout();
                            int flag = 1;
                            for (int i = -1; i < jArray.length(); i++) {
                                TableRow tr = new TableRow(ViewAllActivity.this);
                                tr.setLayoutParams(new TableRow.LayoutParams(
                                        TableRow.LayoutParams.MATCH_PARENT,
                                        TableRow.LayoutParams.WRAP_CONTENT));
                                if (flag == 1) {
                                    TextView b6 = new TextView(ViewAllActivity.this);
                                    b6.setText("Matrix No");
                                    b6.setTextColor(Color.BLUE);
                                    b6.setTextSize(15);
                                    tr.addView(b6);
                                    TextView b19 = new TextView(ViewAllActivity.this);
                                    b19.setPadding(10, 0, 0, 0);
                                    b19.setTextSize(15);
                                    b19.setText("SE");
                                    b19.setTextColor(Color.BLUE);
                                    tr.addView(b19);
                                    TextView b29 = new TextView(ViewAllActivity.this);
                                    b29.setPadding(10, 0, 0, 0);
                                    b29.setText("IS");
                                    b29.setTextColor(Color.BLUE);
                                    b29.setTextSize(15);
                                    tr.addView(b29);
                                    TextView b39 = new TextView(ViewAllActivity.this);
                                    b39.setPadding(10, 0, 0, 0);
                                    b39.setText("CN");
                                    b39.setTextColor(Color.BLUE);
                                    b39.setTextSize(15);
                                    tr.addView(b39);
                                    TextView b49 = new TextView(ViewAllActivity.this);
                                    b49.setPadding(10, 0, 0, 0);
                                    b49.setText("IM");
                                    b49.setTextColor(Color.BLUE);
                                    b49.setTextSize(15);
                                    tr.addView(b49);
                                    tv.addView(tr);
                                    final View vline = new View(ViewAllActivity.this);
                                    vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
                                    vline.setBackgroundColor(Color.BLUE);
                                    tv.addView(vline);
                                    flag = 0;
                                } else {
                                    JSONObject json_data = jArray.getJSONObject(i);
//                                    Log.i(“log_tag”, ”id: “+json_data.getInt(“Id”) + “,Username: “
//                                    +json_data.getString(“username”) + “,No: “
//                                    +json_data.getString(“comment”));
                                    TextView b = new TextView(ViewAllActivity.this);
                                    String stime = String.valueOf(json_data.getString("uid"));
                                    b.setText(stime);
                                    b.setTextColor(Color.RED);
                                    b.setTextSize(15);
                                    tr.addView(b);
                                    TextView b1 = new TextView(ViewAllActivity.this);
                                    b1.setPadding(10, 0, 0, 0);
                                    b1.setTextSize(15);
                                    String stime1 = json_data.getString("se");
                                    b1.setText(stime1);
                                    b1.setTextColor(Color.BLACK);
                                    tr.addView(b1);
                                    TextView b2 = new TextView(ViewAllActivity.this);
                                    b2.setPadding(10, 0, 0, 0);
                                    String stime2 = json_data.getString("is");
                                    b2.setText(stime2);
                                    b2.setTextColor(Color.BLACK);
                                    b2.setTextSize(15);
                                    tr.addView(b2);
                                    TextView b3 = new TextView(ViewAllActivity.this);
                                    b3.setPadding(10, 0, 0, 0);
                                    String stime3 = json_data.getString("cn");
                                    b3.setText(stime3);
                                    b3.setTextColor(Color.BLACK);
                                    b3.setTextSize(15);
                                    tr.addView(b3);
                                    TextView b4 = new TextView(ViewAllActivity.this);
                                    b4.setPadding(10, 0, 0, 0);
                                    String stime4 = json_data.getString("im");
                                    b4.setText(stime4);
                                    b4.setTextColor(Color.BLACK);
                                    b4.setTextSize(15);
                                    tr.addView(b4);
                                    tv.addView(tr);
                                    final View vline1 = new View(ViewAllActivity.this);
                                    vline1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
                                    vline1.setBackgroundColor(Color.WHITE);
                                    tv.addView(vline1);
                                }
                            }
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
