package com.example.cuoiki;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
public class StudentAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<Student> studentList;

    public StudentAdapter(MainActivity context, int layout, List<Student> studentList) {
        this.context = context;
        this.layout = layout;
        this.studentList = studentList;
    }
    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView ten,diachi,sdt,email;
        ImageView sua,xoa,them;
        Button add;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder.ten = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.diachi = (TextView) convertView.findViewById(R.id.tv_address);
            viewHolder.sdt = (TextView)convertView.findViewById(R.id.tv_phone_number);
            viewHolder.email = (TextView) convertView.findViewById(R.id.tv_email);
            viewHolder.sua = (ImageView) convertView.findViewById(R.id.imgedit);
            viewHolder.xoa = (ImageView) convertView.findViewById(R.id.imgdel);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Student student = studentList.get(position);
        viewHolder.ten.setText(student.getmName());
        viewHolder.diachi.setText(student.getmAddress());
        viewHolder.sdt.setText(student.getmPhoneNumber());
        viewHolder.email.setText(student.getmEmail());
        viewHolder.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.Dialog_update(student.getmID(),student.getmName(),student.getmAddress(),student.getmPhoneNumber(),student.getmEmail());
            }
        });
        viewHolder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogDelete(student.getmID(),student.getmName(),student.getmAddress(),student.getmPhoneNumber(),student.getmEmail());

            }
        });
        return convertView;
    }
}
