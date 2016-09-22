package com.example.nazmul.studentManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {
    private EditText searchET;
    private String text;
    private StudentProfileManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchET = (EditText)findViewById(R.id.searchText);

    }

    public void searchStudent(View view) {

        text = searchET.getText().toString();
        StudentInfo info;
        info= manager.getStudentInfo(text);

            //Toast.makeText(SearchActivity.this, "Record deleted", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(SearchActivity.this,ShowInformation.class);
        intent.putExtra("id",info.getId());
            startActivity(intent);

    }


}
