package com.example.nazmul.studentManagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class StudentRegistrationActivity extends AppCompatActivity {

    private RadioGroup genderRadioGroup;
    private RadioButton genderRadioButton;
    private EditText nameET, fathersNameET, mothersNameET, addressET, contactNoET, schoolsNameET, classNameET;
    private TextView dateOfBirthTV;
    private StudentProfileManager manager;

    private ImageButton ib;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    StudentInfo studentInfo;
    StudentInfo infoFetch;
    int idS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameET = (EditText) findViewById(R.id.std_name);
        fathersNameET = (EditText) findViewById(R.id.fathers_name);
        mothersNameET = (EditText) findViewById(R.id.mothers_name);
        addressET = (EditText) findViewById(R.id.address);
        contactNoET = (EditText) findViewById(R.id.contact_no);
        schoolsNameET = (EditText) findViewById(R.id.schools_name);
        classNameET = (EditText) findViewById(R.id.class_name);
        dateOfBirthTV = (TextView) findViewById(R.id.date_of_birth);

        genderRadioGroup = (RadioGroup) findViewById(R.id.genderRadioGroup);

        manager = new StudentProfileManager(this);


        //ib = (ImageButton) findViewById(R.id.imageButton1);
        cal = Calendar.getInstance();

        Intent intentS=getIntent();
        idS = intentS.getIntExtra("idS",-1);
        if(idS>0){
            infoFetch=manager.getStudentInfo(idS);
            nameET.setText(infoFetch.getStudentName());
            fathersNameET.setText(infoFetch.getFathersName());
            mothersNameET.setText(infoFetch.getMothersName());
            addressET.setText(infoFetch.getAddress());
            contactNoET.setText(infoFetch.getContactNumber());
            schoolsNameET.setText(infoFetch.getSchoolsName());
            classNameET.setText(infoFetch.getClassName());


        }

    }


    public void saveToDataBase(View view) {

        if (idS > 0) {
                    updateRecord();
        } else {
            String name = nameET.getText().toString();
            String fathersName = fathersNameET.getText().toString();
            String mothersName = mothersNameET.getText().toString();
            String address = addressET.getText().toString();
            String contactNumber = contactNoET.getText().toString();
            String schoolsName = schoolsNameET.getText().toString();
            String className = classNameET.getText().toString();
            String dob = dateOfBirthTV.getText().toString();


            int selectedId = genderRadioGroup.getCheckedRadioButtonId();
            genderRadioButton = (RadioButton) findViewById(selectedId);
            String gender = genderRadioButton.getText().toString();

            if (TextUtils.isEmpty(name)) {
                nameET.setError("enter name");
                return;
            }
            else if(TextUtils.isEmpty(fathersName))
                {
                    fathersNameET.setError("enter father's name");
                    return;
                }
            else if (TextUtils.isEmpty(contactNumber) || contactNumber.length() != 11){
                contactNoET.setError("enter valid contact number");
                return;
            }
            else if (TextUtils.isEmpty(className)){
                classNameET.setError("enter student's class");
                return;
            }

             else {
                StudentInfo studentInfo = new StudentInfo(name, fathersName, mothersName, address, contactNumber, schoolsName, className, dob, gender);

                boolean inserted = manager.addStdProfile(studentInfo);
                if (inserted) {
                    Toast.makeText(StudentRegistrationActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(StudentRegistrationActivity.this, StartingActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(StudentRegistrationActivity.this, "Failed ", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void saveDate(View view) {
        // Process to get Current Date
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        dateOfBirthTV.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, year, month, day);
        dpd.show();
    }

    public void updateRecord() {


        String name = nameET.getText().toString();
        String fathersName = fathersNameET.getText().toString();
        String mothersName = mothersNameET.getText().toString();
        String address = addressET.getText().toString();
        String contactNumber = contactNoET.getText().toString();
        String schoolsName = schoolsNameET.getText().toString();
        String className = classNameET.getText().toString();
        String dob = dateOfBirthTV.getText().toString();

        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
        genderRadioButton = (RadioButton) findViewById(selectedId);
        String gender = genderRadioButton.getText().toString();

        if (TextUtils.isEmpty(name)) {
            nameET.setError("enter name");
            return;
        } else if (TextUtils.isEmpty(fathersName)) {
            fathersNameET.setError("enter father's name");
            return;
        } else if (TextUtils.isEmpty(contactNumber) || contactNumber.length() != 11) {
            contactNoET.setError("enter valid contact number");
            return;
        } else if (TextUtils.isEmpty(className)) {
            classNameET.setError("enter student's class");
            return;
        } else {
            StudentInfo studentInfo = new StudentInfo(name, fathersName, mothersName, address, contactNumber, schoolsName, className, dob, gender);
            boolean updated = manager.updateStudentInfo(idS, studentInfo);
            if (updated) {
                Toast.makeText(StudentRegistrationActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(StudentRegistrationActivity.this, ListActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(StudentRegistrationActivity.this, "Update Failed ", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
