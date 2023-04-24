package com.android.foodorderapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    // Variable Declaration
    private EditText username;
    private EditText email;
    private EditText password;
    private EditText repassword;
    private Button btnsignup;
    private Button btnreset;
    private TextView txttologin;
    com.android.foodorderapp.DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Variable Initialization
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        btnsignup = (Button) findViewById(R.id.btnsignup);
        btnreset = (Button) findViewById(R.id.btnreset);
        txttologin = (TextView) findViewById(R.id.txttologin);
        DB = new com.android.foodorderapp.DBHelper(this);

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // clear data fields
                username.setText("");
                email.setText("");
                password.setText("");
                repassword.setText("");
            }
        });

        txttologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String em = email.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(user.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }
                else if(em.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if(pass.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(repass.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please Confirm Password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(pass.equals(repass))
                    {
                        Boolean checkuser = DB.checkUsername(user);
                        if(checkuser == false)
                        {
                            Boolean insert = DB.insertData(user,em,pass);
                            if(insert == true)
                            {
                                Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this, com.android.foodorderapp.LoginActivity.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(RegisterActivity.this, "User Already Exist!", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(RegisterActivity.this, "Passwords not match!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}