package com.example.cuoiki;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    StudentHelper studentHelper;
    ArrayList<Student> arrayList;
    StudentAdapter studentAdapter;
    ImageView imgsreach,add;
    EditText timkiem;
    Button btnadd;
    String Name,Address,Phone,Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        listView =(ListView)findViewById(R.id.lv_student);
        timkiem = (EditText)findViewById(R.id.edtthem);
        imgsreach = (ImageView) findViewById(R.id.imgtimkiem);
        add = (ImageView) findViewById(R.id.btnthem);
        arrayList = new ArrayList<>();
        studentAdapter = new StudentAdapter(this, R.layout.dong_noi_dung, arrayList);
        listView.setAdapter(studentAdapter);
       add.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DialogAdd();
           }
       });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = arrayList.get(position);
                Intent intent = new Intent(MainActivity.this, Detail.class);
                intent.putExtra("name", student.getmName());
                intent.putExtra("address", student.getmAddress());
                intent.putExtra("email", student.getmEmail());
                intent.putExtra("phone", student.getmPhoneNumber());
                startActivity(intent);
            }
        });
        imgsreach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = timkiem.getText().toString().trim();
                if (TextUtils.isEmpty(search)){
                    Toast.makeText(MainActivity.this,"Bạn chưa nhập nội dung tìm kiếm",Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor data = studentHelper.GetData("SELECT * FROM Sinhvien WHERE Name LIKE '%" +search+"%' ");
                arrayList.clear();
                while (data.moveToNext()) {
                    int id = data.getInt(0);
                    String name = data.getString(1);
                    String address = data.getString(2);
                    String phone = data.getString(3);
                    String email = data.getString(4);
                    arrayList.add(new Student(id,name,address,phone,email));
                }
                studentAdapter.notifyDataSetChanged();
                studentAdapter = new StudentAdapter(MainActivity.this,R.layout.dong_noi_dung,arrayList);
                listView.setAdapter(studentAdapter);
            }
        });
        studentHelper = new StudentHelper(this,"Student.sqlite",null,1);
        //tao bang
        studentHelper.QueryData("CREATE TABLE IF NOT EXISTS Sinhvien (Id INTEGER PRIMARY KEY AUTOINCREMENT, Name VARCHAR,Address VARCHAR,Phone VARCHAR,Email VARCHAR)");
//        studentHelper.QueryData("INSERT INTO Sinhvien VALUES(null,'Nhân','32 Lê Độ','0123654987','duongnhan@gmail.com')");
//        studentHelper.QueryData("INSERT INTO Sinhvien VALUES(null,'Quyền','33 Lê Độ','052233366','duongq@gmail.com')");
//        studentHelper.QueryData("INSERT INTO Sinhvien VALUES(null,'Phương','34 Lê Độ','02154685','Phuong@gmail.com')");
//        studentHelper.QueryData("INSERT INTO Sinhvien VALUES(null,'Chung','35 Lê Độ','147258369','chung@gmail.com')");
//        studentHelper.QueryData("INSERT INTO Sinhvien VALUES(null,'Nghĩa','36 Lê Độ','0369258147','nghia@gmail.com')");
//        studentHelper.QueryData("INSERT INTO Sinhvien VALUES(null,'Linh','37 Lê Độ','000088999','linh@gmail.com')");
//        studentHelper.QueryData("INSERT INTO Sinhvien VALUES(null,'Sam','55 Hàm Nghi','69696969','Sam@gmail.com')");
//        studentHelper.QueryData("INSERT INTO Sinhvien VALUES(null,'Herry','69 Lê Độ','96969696','Herry@gmail.com')");


        Cursor data = studentHelper.GetData("SELECT * FROM Sinhvien");
        arrayList.clear();
        while (data.moveToNext()) {
            int id = data.getInt(0);
            String name = data.getString(1);
            String address = data.getString(2);
            String phone = data.getString(3);
            String email = data.getString(4);
            arrayList.add(new Student(id,name,address,phone,email));
        }
        studentAdapter.notifyDataSetChanged();
    }
    public void Dialog_update(int Id,String Name,String Address,String Phone, String Email) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_sua);
        EditText edtname = (EditText) dialog.findViewById(R.id.name);
        EditText edtdiachi = (EditText) dialog.findViewById(R.id.address);
        EditText edtphone = (EditText) dialog.findViewById(R.id.phone);
        EditText edtemail = (EditText) dialog.findViewById(R.id.email);
        Button btnsua = (Button) dialog.findViewById(R.id.btnsua);
        Button btnhuy = (Button) dialog.findViewById(R.id.btnhuy);
        edtname.setText(Name);
        edtdiachi.setText(Address);
        edtphone.setText(Phone);
        edtemail.setText(Email);
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                return;
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = edtname.getText().toString().trim();
                String Address = edtdiachi.getText().toString().trim();
                String PhoneNumber = edtphone.getText().toString().trim();
                String Email = edtemail.getText().toString().trim();
                if (TextUtils.isEmpty(Name)){
                    Toast.makeText(MainActivity.this,"Nội dung chưa được nhập",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                studentHelper.QueryData("UPDATE Sinhvien SET Name= '" +Name+"',Address= '" +Address+"',Phone= '" +PhoneNumber+"',Email= '" +Email+"' WHERE id = '"+Id+"' ");
                dialog.dismiss();
                Cursor data = studentHelper.GetData("SELECT * FROM Sinhvien");
                arrayList.clear();
                while (data.moveToNext()) {
                    int id = data.getInt(0);
                    String name = data.getString(1);
                    String address = data.getString(2);
                    String phone = data.getString(3);
                    String email = data.getString(4);
//            Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
                    arrayList.add(new Student(id,name,address,phone,email));
                }
                studentAdapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }
    public void DialogDelete(int Id,String Name,String Address,String Phone, String Email){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn có muốn xóa "+Name+" ?");
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                studentHelper.QueryData("DELETE FROM Sinhvien WHERE Id = '"+Id+"' ");
                Cursor data = studentHelper.GetData("SELECT * FROM Sinhvien");
                arrayList.clear();
                while (data.moveToNext()) {
                    int id = data.getInt(0);
                    String name = data.getString(1);
                    String address = data.getString(2);
                    String phone = data.getString(3);
                    String email = data.getString(4);
//            Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
                    arrayList.add(new Student(id,name,address,phone,email));
                }
                studentAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
    public void DialogAdd() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them);
        EditText edtname = (EditText) dialog.findViewById(R.id.name);
        EditText edtdiachi = (EditText) dialog.findViewById(R.id.address);
        EditText edtphone = (EditText) dialog.findViewById(R.id.phone);
        EditText edtemail = (EditText) dialog.findViewById(R.id.email);
        Button btnthem = (Button) dialog.findViewById(R.id.btnadd);
        Button btnhuy = (Button) dialog.findViewById(R.id.btnhuy);
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                return;
            }
        });
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Name = edtname.getText().toString().trim();
                String Address = edtdiachi.getText().toString().trim();
                String PhoneNumber = edtphone.getText().toString().trim();
                String Email = edtemail.getText().toString().trim();
                if (TextUtils.isEmpty(Name)){
                    Toast.makeText(MainActivity.this,"Nội dung chưa được nhập",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    return;
                }
                studentHelper.QueryData("INSERT INTO Sinhvien VALUES(null,'"+Name+"','"+Address+"','"+PhoneNumber+"','"+Email+"')");
                dialog.dismiss();
                Cursor data = studentHelper.GetData("SELECT * FROM Sinhvien");
                arrayList.clear();
                while (data.moveToNext()) {
                    int id = data.getInt(0);
                    String name = data.getString(1);
                    String address = data.getString(2);
                    String phone = data.getString(3);
                    String email = data.getString(4);
//            Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
                    arrayList.add(new Student(id,name,address,phone,email));
                }
                studentAdapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }

}