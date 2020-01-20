package com.example.majoringrecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpdateAdminActivity extends AppCompatActivity {
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_admin);

        update=findViewById(R.id.edit);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateAdminActivity.this, AdminProfileActivity.class));
            }
        });
    }
}
