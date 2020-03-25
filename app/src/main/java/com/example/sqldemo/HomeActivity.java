package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    TextView cUserEmail;
    Button cLogOutBtn;
    String cuserEmailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cUserEmail = findViewById(R.id.user_EMAIL_Home);
        cLogOutBtn = findViewById(R.id.logout_btn);

        cuserEmailID = getIntent().getStringExtra("Current_userEmail");
        cUserEmail.setText(" Hello , "+cuserEmailID);

        cLogOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this,"Successfully Logout!",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(HomeActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
