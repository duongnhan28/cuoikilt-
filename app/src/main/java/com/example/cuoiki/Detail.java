package com.example.cuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Detail extends AppCompatActivity {
    TextView name, address,phone,email;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detail);
        Anhxa();
        Intent intent = getIntent();
        String ten = getIntent().getStringExtra("name");
        String diachi = getIntent().getStringExtra("address");
        String emaill = getIntent().getStringExtra("email");
        String sdt = getIntent().getStringExtra("phone");

        name.setText("Tên: " +ten);
        address.setText("Địa chỉ: " +diachi);
        email.setText("Email: "+emaill);
        phone.setText("Số điện thoại: "+sdt);
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Detail.this,MainActivity.class);
               startActivity(intent);
           }
       });
    }

    private void Anhxa() {
        name = (TextView)findViewById(R.id.tv_name);
        address = (TextView)findViewById(R.id.tv_address);
        phone = (TextView)findViewById(R.id.tv_phone_number);
        email = (TextView) findViewById(R.id.tv_email);
        back = (Button) findViewById(R.id.back);
    }
}