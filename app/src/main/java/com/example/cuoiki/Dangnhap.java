package com.example.cuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Dangnhap extends AppCompatActivity {
    private EditText loginUsername, loginPassword;
    private Button loginButton, siginButton;
    private DataBaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        loginUsername = findViewById(R.id.taikhoan);
        loginPassword = findViewById(R.id.matkhau);
        loginButton = findViewById(R.id.dangnhap);
        siginButton = findViewById(R.id.dangky);

        myDb = new DataBaseHelper(this);

        loginUser();
        siginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Dangnhap.this, "ok", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Dangnhap.this, Dangky.class);
                startActivity(intent);
            }
        });

    }

    private void loginUser() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean var = myDb.checkUser(loginUsername.getText().toString(), loginPassword.getText().toString());
                if (var) {
                    Toast.makeText(Dangnhap.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Dangnhap.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(Dangnhap.this, "Login Failed !!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}