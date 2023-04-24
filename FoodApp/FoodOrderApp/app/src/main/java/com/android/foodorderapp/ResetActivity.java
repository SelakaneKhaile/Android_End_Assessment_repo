package com.android.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ResetActivity extends AppCompatActivity {

    private TextView username;
    private EditText password;
    private EditText repassword;
    private Button btnconfirm;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resert);

        username = (TextView) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        btnconfirm = (Button) findViewById(R.id.btnconfirm);
        DB = new DBHelper(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));

        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if(repass.equals(pass)) {

                    Boolean checkpasswordupade = DB.updatepassword(user, pass);
                    if (checkpasswordupade == true) {
                        Intent intent = new Intent(ResetActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password updated successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ResetActivity.this, "Password not updated successfully", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(ResetActivity.this, "Passwords not match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}