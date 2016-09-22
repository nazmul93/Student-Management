package com.example.nazmul.studentManagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listStudent;
    private ArrayList<StudentInfo> studentListAll;
    private CustomAdapter adapter;
    private StudentProfileManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listStudent=(ListView)findViewById(R.id.listData);
        manager=new StudentProfileManager(this);
        listData();
    }

    public void listData(){
        ////arrListAll=new ArrayList<Contact>();
        studentListAll=manager.getAllProfile();
        adapter=new CustomAdapter(ListActivity.this, studentListAll);
        listStudent.setAdapter(adapter);

        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(ListActivity.this,ShowInformation.class);
                intent.putExtra("id",studentListAll.get(i).getId());
                startActivity(intent);
            }
        });
    }
}
