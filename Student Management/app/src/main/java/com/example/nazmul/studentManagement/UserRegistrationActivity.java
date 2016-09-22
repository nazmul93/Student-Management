package com.example.nazmul.studentManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserRegistrationActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private EditText phoneNo;
    private EditText emailNo;
    private UserInfo userInfo;
    private UserInfoManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        userName = (EditText) findViewById(R.id.userNameETR);
        password = (EditText) findViewById(R.id.passwordETR);
        phoneNo = (EditText) findViewById(R.id.phoneNumberETR);
        emailNo = (EditText) findViewById(R.id.emailNumberETR);
        userInfo = new UserInfo();
        manager = new UserInfoManager(this);
    }

    public void register(View view) {
        userInfo.setContext(UserRegistrationActivity.this);
        userInfo.setUserName(userName.getText().toString());
        userInfo.setPassWord(password.getText().toString());
        userInfo.setPhoneNo(phoneNo.getText().toString());
        userInfo.setEmailNo(emailNo.getText().toString());

        if ((userName.length() > 2 && userName != null) && (password.length() > 8 && password != null) &&
                (phoneNo.length() == 11 && phoneNo != null) && (emailNo.length() > 11 && emailNo != null)) {

            UserInfo user = new UserInfo(userInfo.getUserName(), userInfo.getPassWord(), userInfo.getPhoneNo(), userInfo.getEmailNo());

            boolean validity = manager.checkValidity(userInfo.getUserName());

            if (validity) {
                Toast.makeText(UserRegistrationActivity.this, "User Name is already in use", Toast.LENGTH_SHORT).show();
            } else {
                boolean value = manager.addUser(user);
                if (value) {
                    Toast.makeText(UserRegistrationActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    Intent registerIntent = new Intent(UserRegistrationActivity.this, LoginActivity.class);
                    startActivity(registerIntent);
                } else {
                    Toast.makeText(UserRegistrationActivity.this, "Registration Failed ", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
