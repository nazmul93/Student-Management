package com.example.nazmul.studentManagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Mobile App Develop on 21-7-16.
 */
public class UserInfoManager {
    private DataBaseHelper helper;
    private SQLiteDatabase database;
    private Context context;

    public UserInfoManager(Context context) {
        this.context = context;
        helper = new DataBaseHelper(context);

    }

    private void open() {
        database = helper.getWritableDatabase();

    }

    private void close() {
        helper.close();
    }

    public boolean addUser(UserInfo userInfo) {

        this.open();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseHelper.COL_USER_NAME, userInfo.getUserName());
        contentValues.put(DataBaseHelper.COL_USER_PASSWORD, userInfo.getPassWord());
        contentValues.put(DataBaseHelper.COL_USER_PHONENO, userInfo.getPhoneNo());
        contentValues.put(DataBaseHelper.COL_USER_EMAIL_NO, userInfo.getEmailNo());

        long inserted = database.insert(DataBaseHelper.TABLE_USER, null, contentValues);
        this.close();

        database.close();

        if (inserted > 0) {
            return true;
        } else return false;

    }

    public ArrayList<UserInfo> getAllUserInfo() {

        this.open();

        ArrayList<UserInfo> userList = new ArrayList<>();

        Cursor cursor = database.query(DataBaseHelper.TABLE_USER, null, null, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_USER_ID));
                String userName = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_NAME));
                String password = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_PASSWORD));
                String phoneNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_PHONENO));
                String emailNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_EMAIL_NO));

                UserInfo userInfo = new UserInfo(id, userName, password, phoneNo,emailNo );
                userList.add(userInfo);
                cursor.moveToNext();
            }
            this.close();

        }
        return userList;
    }

    public ArrayList<UserInfo>  getAllUserEmailAndPhoneNo() {
        this.open();
        ArrayList<UserInfo> userList = new ArrayList<>();
        Cursor cursor = database.query(DataBaseHelper.TABLE_USER, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_USER_ID));
                String password = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_PASSWORD));
                String phoneNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_PHONENO));
                String emailNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_EMAIL_NO));

                UserInfo userInfo = new UserInfo(id,password, phoneNo,emailNo );
                userList.add(userInfo);
                cursor.moveToNext();
            }
            this.close();
        }
        return userList;
    }

    public UserInfo getUserInfo(int id) {

        this.open();

        Cursor cursor = database.query(DataBaseHelper.TABLE_USER, new String[]{DataBaseHelper.COL_USER_ID,
                        DataBaseHelper.COL_USER_NAME, DataBaseHelper.COL_USER_PASSWORD, DataBaseHelper.COL_USER_PHONENO, DataBaseHelper.COL_USER_EMAIL_NO},
                DataBaseHelper.COL_USER_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();

        int colId = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_USER_ID));
        String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_NAME));
        String password = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_PASSWORD));
        String phoneNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_PHONENO));
        String emailNo = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_USER_EMAIL_NO));

        UserInfo userInfo = new UserInfo(colId, name, password, emailNo, phoneNo);
        this.close();
        return userInfo;
    }

    public boolean updateUserInfo(int id, UserInfo userInfo) {
        this.open();
        ContentValues cv = new ContentValues();
        cv.put(DataBaseHelper.COL_USER_NAME, userInfo.getUserName());
        cv.put(DataBaseHelper.COL_USER_PASSWORD, userInfo.getPassWord());
        cv.put(DataBaseHelper.COL_USER_PHONENO, userInfo.getPhoneNo());
        cv.put(DataBaseHelper.COL_USER_EMAIL_NO, userInfo.getEmailNo());

        int updated = database.update(DataBaseHelper.TABLE_USER, cv, DataBaseHelper.COL_USER_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else
            return false;
    }

    public boolean checkValidity(String userName, String password){
        this.open();
        boolean value=false;
        ArrayList<UserInfo> userDataArray=getAllUserInfo();
        for(UserInfo userInfo:userDataArray){
            String dbUserName=userInfo.getUserName();
            String dbPassword=userInfo.getPassWord();
//            Toast.makeText(contex, dpPassword, Toast.LENGTH_SHORT).show();
            if(userName.equals(dbUserName) && password.equals(dbPassword)){
                value=true;
                break;
            }else {
                value=false;
            }
        }
        this.close();
        return value;
    }
    public boolean checkValidity(String userName){
        this.open();
        boolean value=false;
        ArrayList<UserInfo> userDataArray=getAllUserInfo();
        for(UserInfo userInfo:userDataArray){
            String dbUserName=userInfo.getUserName();
            if(userName.equals(dbUserName)){
                value=true;
            }else {
                value=false;
            }
        }
        this.close();
        return value;
    }
    public String emailOrPhoneNoValidation(String number){
        this.open();
        String value=null;
        ArrayList<UserInfo> userDataArray=getAllUserEmailAndPhoneNo();
        for(UserInfo userInfo:userDataArray){
            String dbPhoneNo=userInfo.getPhoneNo();
            String dbEmailNo=userInfo.getEmailNo();
            value=userInfo.getPassWord();

            if(number.equals(dbPhoneNo) || number.equals(dbEmailNo)){
                value=userInfo.getPassWord();
                break;
            }else {
                value=null;
            }
        }
        this.close();
        return value;
    }
}
