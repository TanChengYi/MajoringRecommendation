package com.example.majoringrecommendation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class adminMain extends AppCompatActivity {
    Button view,manage,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        view=findViewById(R.id.view);
        manage=findViewById(R.id.manage);
        logout=findViewById(R.id.logout);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminMain.this, ViewAllActivity.class));
            }
        });

        manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminMain.this, AdminProfileActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(adminMain.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }
}
