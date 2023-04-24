package com.android.foodorderapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button btnsignin;
    private TextView txtresetpass;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btnsignin = (Button) findViewById(R.id.btnresetpass);
        txtresetpass = (TextView) findViewById(R.id.txtresetpass);
        DB = new DBHelper(this);



        txtresetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, PasswordActivity.class);
                startActivity(intent);
            }
        });

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInAs(view);
            }
        });
    }

    public void signInAs(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("User SignIn")
                .setMessage("SignIn  As ...")
                .setPositiveButton("User", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // do something when the "Employee" button is clicked
                        String user = username.getText().toString();
                        String pass = password.getText().toString();

                        if(user.equals(""))
                        {
                            Toast.makeText(LoginActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                        }
                        else if(pass.equals(""))
                        {
                            Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Boolean checkuserpass = DB.checkusernamepassword(user,pass);
                            if(checkuserpass == true)
                            {
                                String mail = DB.getEmail(user);
                                Toast.makeText(LoginActivity.this, "Signin Successfull!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("name", user);
                                intent.putExtra("email", mail);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("ResAdmin", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String user = username.getText().toString();
                        String pass = password.getText().toString();
                        if(user.equals(""))
                        {
                            Toast.makeText(LoginActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                        }
                        else if(pass.equals(""))
                        {
                            Toast.makeText(LoginActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(user.equals("Admin") && pass.equals("1234"))
                            {

                                Toast.makeText(LoginActivity.this, "Signin Successfull!", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("name", user);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}