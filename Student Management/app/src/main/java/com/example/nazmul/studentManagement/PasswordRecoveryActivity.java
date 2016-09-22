package com.example.nazmul.studentManagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordRecoveryActivity extends AppCompatActivity {

    private EditText emailOrPhone;
    private TextView textViewPassword;
    private UserInfoManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovary);

        emailOrPhone=(EditText)findViewById(R.id.phoneNumberPRET);
        textViewPassword=(TextView) findViewById(R.id.passwordTVPR);
        manager=new UserInfoManager(this);
    }

    public void submit(View view) {
        String number=emailOrPhone.getText().toString();
        String validity = manager.emailOrPhoneNoValidation(number);
        if(validity==null){
            Toast.makeText(PasswordRecoveryActivity.this, "Phone number not mached", Toast.LENGTH_SHORT).show();
        }else{textViewPassword.setText("Your Password is: "+validity);}

    }
}
