package com.example.sql_lite_4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCourseOffline extends AppCompatActivity {

    private Button btnStore;

    EditText image;
    EditText title;
    EditText desc;
    EditText icon;
    EditText name;

    private DataBaseSQL dataBaseSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course_teachers);

        dataBaseSQL = new DataBaseSQL(this);

        btnStore = (Button) findViewById(R.id.btnstore);

        image = findViewById(R.id.image);
        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        icon = findViewById(R.id.icon);
        name = findViewById(R.id.name);


        btnStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataBaseSQL.addBook(
                             image.getText().toString().trim(),
                             title.getText().toString().trim(),
                             desc.getText().toString().trim(),
                             icon.getText().toString().trim(),
                             name.getText().toString().trim()
                     );


                    Toast.makeText(AddCourseOffline.this, "Stored Successfully!", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(AddCourseTeachers.this,MainActivity.class);
                    ///intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    //// startActivity(intent);
                }

        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddCourseOffline.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, 1);
    }
}