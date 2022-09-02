package com.example.sql_lite_4;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton floating_btn, floating_btn_delete_all;

    private ArrayList<BookModel> teachersModelArrayList;
    private CustomAdapter customAdapterTeacher;
    private DataBaseSQL databaseHelperTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floating_btn = findViewById(R.id.floating_btn);
        floating_btn_delete_all = findViewById(R.id.floating_btn_delete_all);

        floating_btn_delete_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseSQL myDB = new DataBaseSQL(MainActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        floating_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddCourseOffline.class);
                startActivity(intent);
            }
        });


        displayData();

    }

    private void displayData() {


        recyclerView = findViewById(R.id.recycler_view_sql);
        databaseHelperTeacher = new DataBaseSQL(this);

        teachersModelArrayList = databaseHelperTeacher.getAllBooks();


        customAdapterTeacher = new CustomAdapter(this,teachersModelArrayList);
        recyclerView.setAdapter(customAdapterTeacher);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
}