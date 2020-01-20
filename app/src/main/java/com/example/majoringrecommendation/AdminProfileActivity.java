package com.example.majoringrecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminProfileActivity extends AppCompatActivity {
    Button edit,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        edit=findViewById(R.id.edit);
        back=findViewById(R.id.back);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminProfileActivity.this, UpdateAdminActivity.class));
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminProfileActivity.this, adminMain.class));
            }
        });
    }
}
