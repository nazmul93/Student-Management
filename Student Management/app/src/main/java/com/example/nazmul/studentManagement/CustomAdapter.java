package com.example.nazmul.studentManagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    private TextView nameTV;
    //private TextView phoneNOTV;
    private StudentInfo studentInfo;
    private ArrayList<StudentInfo> studentList;
    private Context context;

    public CustomAdapter(Context context,ArrayList<StudentInfo> studentList) {

        super(context,R.layout.custom_layout,studentList);
        this.context=context;
        this.studentList=studentList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.custom_layout,null);
        nameTV=(TextView)convertView.findViewById(R.id.name);

        nameTV.setText(studentList.get(position).getStudentName());

        return convertView;
    }
}
