package com.example.nazmul.studentManagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class StudentProfileManager {
    private StudentInfo studentInfo;
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Context context;

    public StudentProfileManager(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
    }

    private void open(){
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
    }

    private void close(){
        dataBaseHelper.close();
    }

    public boolean addStdProfile(StudentInfo studentInfo){
        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(dataBaseHelper.COL_NAME, studentInfo.getStudentName());
        contentValues.put(dataBaseHelper.COL_FATHERS_NAME, studentInfo.getFathersName());
        contentValues.put(dataBaseHelper.COL_MOTHERS_NAME, studentInfo.getMothersName());
        contentValues.put(dataBaseHelper.COL_ADDRESS, studentInfo.getAddress());
        contentValues.put(dataBaseHelper.COL_CONTACT, studentInfo.getContactNumber());
        contentValues.put(dataBaseHelper.COL_SCHOOLS_NAME, studentInfo.getSchoolsName());
        contentValues.put(dataBaseHelper.COL_CLASS_NAME, studentInfo.getClassName());
        contentValues.put(dataBaseHelper.COL_DOB, studentInfo.getDateOfBirth());
        contentValues.put(dataBaseHelper.COL_GENDER, studentInfo.getGender());

        long inserted = sqLiteDatabase.insert(dataBaseHelper.TABLE_REGISTRATION, null, contentValues);
        this.close();

        if (inserted > 0){
            return true;
        }
        else return false;
    }

    public ArrayList<StudentInfo> getAllProfile(){
        this.open();
        ArrayList<StudentInfo> profileList = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.query(dataBaseHelper.TABLE_REGISTRATION, null,null,null,null,null,null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++){
                int id = cursor.getInt(cursor.getColumnIndex(dataBaseHelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_NAME));
                String fathersName = cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_FATHERS_NAME));
                String mothersName = cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_MOTHERS_NAME));
                String address = cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_ADDRESS));
                String contactNo = cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_CONTACT));
                String schoolsName = cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_SCHOOLS_NAME));
                String className = cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_CLASS_NAME));
                String dateOfBirth = cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_DOB));
                String gender = cursor.getString(cursor.getColumnIndex(dataBaseHelper.COL_GENDER));

                StudentInfo studentInfo = new StudentInfo(id, name, fathersName, mothersName, address, contactNo, schoolsName, className, dateOfBirth, gender);
                profileList.add(studentInfo);
                cursor.moveToNext();
            }
            this.close();
        }
        return profileList;
    }
    public StudentInfo getStudentInfo(int id) {

        this.open();

        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.TABLE_REGISTRATION, new String[]{DataBaseHelper.COL_ID, DataBaseHelper.COL_NAME, DataBaseHelper.COL_FATHERS_NAME,
        DataBaseHelper.COL_MOTHERS_NAME,DataBaseHelper.COL_ADDRESS,DataBaseHelper.COL_CONTACT,DataBaseHelper.COL_SCHOOLS_NAME,DataBaseHelper.COL_CLASS_NAME,
        DataBaseHelper.COL_DOB,DataBaseHelper.COL_GENDER },
                DataBaseHelper.COL_ID + " = " + id, null, null, null,null);
        cursor.moveToFirst();

        int mId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
        String fatherName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_FATHERS_NAME));
        String motherName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_MOTHERS_NAME));
        String address = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_ADDRESS));
        String contactNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CONTACT));
        String schoolName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_SCHOOLS_NAME));
        String className = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CLASS_NAME));
        String dob = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_DOB));
        String gender = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_GENDER));


        StudentInfo studentInfo = new StudentInfo(mId, name, fatherName,motherName,address,contactNo,schoolName,className,dob,gender);
        this.close();
        return studentInfo;
    }

    public StudentInfo getStudentInfo(String name) {

        this.open();

        Cursor cursor = sqLiteDatabase.query(DataBaseHelper.TABLE_REGISTRATION, new String[]{DataBaseHelper.COL_ID, DataBaseHelper.COL_NAME, DataBaseHelper.COL_FATHERS_NAME,
                        DataBaseHelper.COL_MOTHERS_NAME,DataBaseHelper.COL_ADDRESS,DataBaseHelper.COL_CONTACT,DataBaseHelper.COL_SCHOOLS_NAME,DataBaseHelper.COL_CLASS_NAME,
                        DataBaseHelper.COL_DOB,DataBaseHelper.COL_GENDER },
                DataBaseHelper.COL_NAME + " = " + name, null, null, null,null);
        cursor.moveToFirst();

        int mId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_ID));
        String sName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
        String fatherName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_FATHERS_NAME));
        String motherName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_MOTHERS_NAME));
        String address = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_ADDRESS));
        String contactNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CONTACT));
        String schoolName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_SCHOOLS_NAME));
        String className = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_CLASS_NAME));
        String dob = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_DOB));
        String gender = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_GENDER));


        StudentInfo studentInfo = new StudentInfo(mId, sName, fatherName,motherName,address,contactNo,schoolName,className,dob,gender);
        this.close();
        return studentInfo;
    }

    public boolean updateStudentInfo(int id, StudentInfo studentInfo) {
        this.open();

        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COL_NAME, studentInfo.getStudentName());
        cv.put(DataBaseHelper.COL_FATHERS_NAME,studentInfo.getFathersName());
        cv.put(DataBaseHelper.COL_MOTHERS_NAME,studentInfo.getMothersName());
        cv.put(DataBaseHelper.COL_ADDRESS,studentInfo.getAddress());
        cv.put(DataBaseHelper.COL_GENDER,studentInfo.getGender());
        cv.put(DataBaseHelper.COL_SCHOOLS_NAME,studentInfo.getSchoolsName());
        cv.put(DataBaseHelper.COL_CLASS_NAME,studentInfo.getClassName());
        cv.put(DataBaseHelper.COL_DOB,studentInfo.getDateOfBirth());
        cv.put(DataBaseHelper.COL_CONTACT,studentInfo.getContactNumber());


        int updated = sqLiteDatabase.update(DataBaseHelper.TABLE_REGISTRATION, cv, DataBaseHelper.COL_ID + " = '" + id + "'", null);
        this.close();
        if (updated > 0) {
            return true;
        } else
            return false;
    }

    public boolean deleteStudentInfo(int id) {
        this.open();
        int deleted = sqLiteDatabase.delete(DataBaseHelper.TABLE_REGISTRATION, DataBaseHelper.COL_ID + " = '" + id + "'", null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;
    }
}
