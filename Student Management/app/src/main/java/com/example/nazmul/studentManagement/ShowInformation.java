package com.example.nazmul.studentManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ShowInformation extends AppCompatActivity {
    private TextView nameShowTV
            , fathersNameShowTV, mothersNameShowTV, addressShowTV,genderShowTV,
            contactNoShowTV, schoolsNameShowTV, classNameShowTV, dateOfBirthShowTV;

    private TextView idShow;
    private Button updateB;
    private Button deleteB;
    int id;

    StudentProfileManager manager;
    StudentInfo studentInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);

        nameShowTV = (TextView) findViewById(R.id.txtNameIn);
        fathersNameShowTV=(TextView) findViewById(R.id.txtFatherNameIn);
        mothersNameShowTV=(TextView) findViewById(R.id.txtMotherNameIn);
        addressShowTV=(TextView) findViewById(R.id.addressIn);
        genderShowTV = (TextView)findViewById(R.id.genderIn);

        schoolsNameShowTV=(TextView) findViewById(R.id.schoolNameIn);
        classNameShowTV=(TextView) findViewById(R.id.classNameIn);
        dateOfBirthShowTV=(TextView) findViewById(R.id.dobIn);
        //idShow = (TextView) findViewById(R.id.txtIdIn);
        contactNoShowTV = (TextView) findViewById(R.id.txtContactNoIn);
        updateB=(Button)findViewById(R.id.btnUpdate);
        deleteB=(Button)findViewById(R.id.btnDelete);

        manager = new StudentProfileManager(this);

        Intent intent=getIntent();
        id = intent.getIntExtra("id",-1);
        studentInfo=manager.getStudentInfo(id);

        nameShowTV.setText(studentInfo.getStudentName());
        fathersNameShowTV.setText(studentInfo.getFathersName());
        mothersNameShowTV.setText(studentInfo.getMothersName());
        addressShowTV.setText(studentInfo.getAddress());
        genderShowTV.setText(studentInfo.getGender());
        schoolsNameShowTV.setText(studentInfo.getSchoolsName());
        classNameShowTV.setText(studentInfo.getClassName());
        dateOfBirthShowTV.setText(studentInfo.getDateOfBirth());
        contactNoShowTV.setText(studentInfo.getContactNumber());
        //idShow.setText(studentInfo.getId()+"");
        //Toast.makeText(SecondActivity.this, id+ ": id", Toast.LENGTH_SHORT).show();

    }

    public void delete(View view) {
        // int in=Integer.parseInt(id);
        boolean delete =manager.deleteStudentInfo(id);
        if (delete) {
            Toast.makeText(ShowInformation.this, "Record deleted", Toast.LENGTH_SHORT).show();
            Intent intentS=new Intent(ShowInformation.this,ListActivity.class);
            startActivity(intentS);
        }
        else{
            Toast.makeText(ShowInformation.this, "Record has not deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void update(View view) {
        Intent intentS=new Intent(ShowInformation.this,StudentRegistrationActivity.class);
        intentS.putExtra("idS",id);
        startActivity(intentS);
    }
}
