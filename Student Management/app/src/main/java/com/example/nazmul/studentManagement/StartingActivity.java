package com.example.nazmul.studentManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class StartingActivity extends AppCompatActivity {

    private UserInfo login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        login=new UserInfo();
        login.sharedPreferences = getSharedPreferences("LoginData", MODE_PRIVATE);
        login.editor = login.sharedPreferences.edit();
    }

    public void addStudent(View view) {
        Intent intent = new Intent(StartingActivity.this, StudentRegistrationActivity.class);
        startActivity(intent);
    }

    public void showList(View view) {
        Intent intent = new Intent(StartingActivity.this, ListActivity.class);
        startActivity(intent);
    }

    public void searchStudent(View view) {
        Intent intent = new Intent(StartingActivity.this, SearchActivity.class);
        startActivity(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.exit:
                finish();
                System.exit(0);
                Toast.makeText(StartingActivity.this, "exit", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void logOut(View view) {
        login.editor.putBoolean(login.SESSION, false);
        login.editor.commit();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
        Toast.makeText(StartingActivity.this, "Log Out", Toast.LENGTH_SHORT).show();
    }
}
