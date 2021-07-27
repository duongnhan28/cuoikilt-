package com.example.cuoiki;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Dangky extends AppCompatActivity {

    private EditText emailSignUp , usernameSignUp , passwordSignUp;
    private Button signUpButton,signInButton;
    private DataBaseHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_dangky);
        Intent intent = getIntent();
        emailSignUp = findViewById(R.id.dkEmail);
        usernameSignUp = findViewById(R.id.dkTaiKhoan);
        passwordSignUp = findViewById(R.id.dkMatKhau);

        signUpButton = findViewById(R.id.dkDangKy);
        signInButton = findViewById(R.id.dkDangNhap);

        myDB = new DataBaseHelper(this);
        insertUser();
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dangky.this,Dangnhap.class);
                startActivity(intent);
            }
        });
    }

    private void insertUser(){
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean var = myDB.registerUser(usernameSignUp.getText().toString() , emailSignUp.getText().toString() , passwordSignUp.getText().toString());
                if(var){
                    Toast.makeText(Dangky.this, "User Registered Successfully !!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(Dangky.this, "Registration Error !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}