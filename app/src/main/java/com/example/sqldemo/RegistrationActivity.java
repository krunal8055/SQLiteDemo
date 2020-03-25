package com.example.sqldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    EditText FirstName,LastName,EmailID,PhoneNo,Password,ConfirmPassword;
    String fn,ln,email,phone,password,confirm;
    Button mRegBtn,mLoginbtn;
    DatabaseHelper databaseHelper;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        FirstName = findViewById(R.id.userFN_reg);
        LastName = findViewById(R.id.userLN_reg);
        EmailID = findViewById(R.id.userEmail_reg);
        PhoneNo = findViewById(R.id.userPhone_reg);
        Password = findViewById(R.id.userpass_reg);
        ConfirmPassword = findViewById(R.id.userConfirm_reg);
        mRegBtn = findViewById(R.id.reg_btn);
        mLoginbtn = findViewById(R.id.loginbtn_reg);
        databaseHelper = new DatabaseHelper(RegistrationActivity.this);
        user = new User();

        mRegBtn.setOnClickListener(this);
        mLoginbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mRegBtn) {


            fn = FirstName.getText().toString().trim();
            ln = LastName.getText().toString().trim();
            email = EmailID.getText().toString().trim();
            phone = PhoneNo.getText().toString().trim();
            password = Password.getText().toString().trim();
            confirm = ConfirmPassword.getText().toString().trim();


            if (fn.isEmpty())
            {
                Toast.makeText(this,"Please Enter First Name!",Toast.LENGTH_LONG).show();
            }
            else if(ln.isEmpty())
            {
                Toast.makeText(this,"Please Enter Last Name!",Toast.LENGTH_LONG).show();
            }
            else if(email.isEmpty())
            {
                Toast.makeText(this,"Please Enter Email ID!",Toast.LENGTH_LONG).show();
            }
            else if(phone.isEmpty())
            {
                Toast.makeText(this,"Please Enter Phone no!",Toast.LENGTH_LONG).show();
            }
            else if(password.isEmpty())
            {
                Toast.makeText(this,"Please Enter Password!",Toast.LENGTH_LONG).show();
            }
            else if(confirm.isEmpty())
            {
                Toast.makeText(this,"Please Enter Confirm Password!",Toast.LENGTH_LONG).show();
            }
            else if(!password.contentEquals(confirm))
            {
                Toast.makeText(this,"Password And Confirm Password Should be equal !",Toast.LENGTH_LONG).show();
            }
            else
            {
                if(!databaseHelper.checkuser(email))
                {
                    user.setFirstName(fn);
                    user.setLastName(ln);
                    user.setEmailID(email);
                    user.setPhoneNO(phone);
                    user.setPasssword(password);

                    databaseHelper.addUser(user);

                    Toast.makeText(this,"Register Successfully!",Toast.LENGTH_LONG).show();

                    Intent i = new Intent(this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(this,"User Already Register! Please Try to Login",Toast.LENGTH_LONG).show();
                    Intent i = new Intent(this,MainActivity.class);
                    startActivity(i);
                    finish();
                }

            }

        }
        else if(v == mLoginbtn)
        {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}
