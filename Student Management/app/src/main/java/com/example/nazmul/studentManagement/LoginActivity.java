package com.example.nazmul.studentManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private CheckBox checkBox;
    private UserInfo userInfo;
    private UserInfoManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName=(EditText)findViewById(R.id.userNameETL);
        password=(EditText)findViewById(R.id.passwordETL);
        checkBox=(CheckBox)findViewById(R.id.checkbox);
        manager=new UserInfoManager(this);
        userInfo = new UserInfo();

        userInfo.sharedPreferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        userInfo.editor = userInfo.sharedPreferences.edit();
        if(userInfo.sharedPreferences.getBoolean(userInfo.SESSION,false)){
            Intent intent=new Intent(LoginActivity.this,StartingActivity.class);
            startActivity(intent);
        }
    }

    public void signUp(View view) {
        Intent intent=new Intent(LoginActivity.this,UserRegistrationActivity.class);
        startActivity(intent);
    }

    public void login(View view) {

        String userName=this.userName.getText().toString();
        String password=this.password.getText().toString();

        if(userName.length()<1){
            Toast.makeText(LoginActivity.this, "Please give your User Name", Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<1){
            Toast.makeText(LoginActivity.this, "Please give your Password", Toast.LENGTH_SHORT).show();
        }
        else if(password.length()<1 && userName.length()<1){
            Toast.makeText(LoginActivity.this, "Please give your User Name & Password", Toast.LENGTH_SHORT).show();
        }
        boolean validity = manager.checkValidity(userName, password);
        if (validity) {
            if (checkBox.isChecked()) {
                userInfo.editor.putBoolean(userInfo.SESSION, true);
                userInfo.editor.commit();
            }
            Intent loginIntent=new Intent(LoginActivity.this,StartingActivity.class);
            startActivity(loginIntent);
        } else {
            Toast.makeText(LoginActivity.this, "User Name or Password Not matched", Toast.LENGTH_SHORT).show();
        }
        clearText();
    }

    public void forgetPassword(View view) {
        Intent intent =new Intent(LoginActivity.this,PasswordRecoveryActivity.class);
        startActivity(intent);
    }
    public void clearText(){
        userName.getText().clear();
        password.getText().clear();
    }
}
