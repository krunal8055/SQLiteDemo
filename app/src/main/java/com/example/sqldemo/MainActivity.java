package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText EmailID,Password;
    Button Login_btn,Registration_btn;
    String email,pass;

    DatabaseHelper databaseHelper;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EmailID = findViewById(R.id.userid_login);
        Password = findViewById(R.id.userpass_login);
        Login_btn = findViewById(R.id.Login_btn);
        Registration_btn = findViewById(R.id.reg_btn_login);

        databaseHelper = new DatabaseHelper(MainActivity.this);
        user = new User();

        Login_btn.setOnClickListener(this);
        Registration_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == Login_btn)
        {
            email = EmailID.getText().toString();
            pass = Password.getText().toString();

            if(email.isEmpty())
            {
                Toast.makeText(this,"Please Enter Email ID!",Toast.LENGTH_LONG).show();
            }
            else if(pass.isEmpty())
            {
                Toast.makeText(this,"Please Enter Password!",Toast.LENGTH_LONG).show();
            }
            else if(!email.isEmpty() && !pass.isEmpty())
            {
                loginMethod(email,pass);
            }
        }
        else if(v == Registration_btn)
        {
            Intent i = new Intent(this,RegistrationActivity.class);
            startActivity(i);
            finish();
        }
    }

    private void loginMethod(String EMAIL,String PASS) {
        if (databaseHelper.CheckUser(EMAIL, PASS)) {

            Toast.makeText(this, "Successfully Login!", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, HomeActivity.class);
            i.putExtra("Current_userEmail", EMAIL);
            startActivity(i);
            finish();
        }
        else
        {
            Toast.makeText(this, "Email id is not Registered!", Toast.LENGTH_LONG).show();
        }
    }
}
