package com.naqibhatti.mod.madfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CourseCreate extends AppCompatActivity implements View.OnClickListener {

    EditText courseName;
    EditText discipline;
    Button createCourse;

    String CourseName;
    String Discipline;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_create);

        courseName = (EditText) findViewById(R.id.idnamecourse);
        discipline = (EditText) findViewById(R.id.iddisp);

        createCourse = (Button) findViewById(R.id.idbutton);

        createCourse.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String cName =  courseName.getText().toString();
        String dscp = discipline.getText().toString();

        database = FirebaseDatabase.getInstance().getReference("courses");

        if (TextUtils.isEmpty(cName) || TextUtils.isEmpty(dscp)) {
            Toast.makeText(this, "No course name give", Toast.LENGTH_SHORT).show();
        } else {
            String id = database.push().getKey();
            Courses course = new Courses(id,cName,0,dscp);
            database.child(id).setValue(course);
            //USE THIS NAME TO CREATE A NEW CLASS AND SAVE THE DATA TO THE DATABASE.
            Toast.makeText(this, "Course registered", Toast.LENGTH_SHORT).show();
            Intent z;
            z = new Intent(this, AdminPanel.class);
            this.startActivity(z);
        }
    }
}
