package com.example.nazmul.studentManagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInfo {
    public static final String SESSION = "session";
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;

    int id;
    private String userName;
    private String passWord;
    private String phoneNo;
    private String emailNo;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName.length() > 2 && userName.length() < 15) {
            this.userName = userName;
        } else {
            Toast.makeText(getContext(), "User name must grater then Two carecter", Toast.LENGTH_SHORT).show();
        }
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        if (passWord.length() > 8 && passWord.length() < 20) {
            this.passWord = passWord;
        } else {
            Toast.makeText(getContext(), "Password must grater then Seven carecter", Toast.LENGTH_SHORT).show();
        }

    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        if (phoneNo.length() == 11) {
            this.phoneNo = phoneNo;
        } else {
            Toast.makeText(getContext(), "Phone Number must be Eleven degit", Toast.LENGTH_SHORT).show();
        }
    }

    public String getEmailNo() {
        return emailNo;
    }

    public void setEmailNo(String emailNo) {
        Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
        String email = emailNo;
        Matcher matcher = pattern.matcher(email);
        boolean matchFound = matcher.matches();
        if (matchFound) {
            this.emailNo = emailNo;
        } else {
            Toast.makeText(getContext(), "email Number is not Valid", Toast.LENGTH_SHORT).show();
        }

    }

    public UserInfo(int id, String userName, String passWord, String phoneNo, String emailNo) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNo = phoneNo;
        this.emailNo = emailNo;
    }

    public UserInfo(String userName, String passWord, String phoneNo, String emailNo) {
        this.userName = userName;
        this.passWord = passWord;
        this.phoneNo = phoneNo;
        this.emailNo = emailNo;
    }

    public UserInfo(int id, String passWord, String phoneNo, String emailNo) {
        this.id = id;
        this.passWord = passWord;
        this.phoneNo = phoneNo;
        this.emailNo = emailNo;
    }

    public UserInfo() {
    }
}
