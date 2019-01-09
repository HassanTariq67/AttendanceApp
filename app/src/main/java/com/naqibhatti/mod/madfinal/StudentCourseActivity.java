package com.naqibhatti.mod.madfinal;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseActivity extends AppCompatActivity {

    DatabaseReference database;
    List<Courses> courses = new ArrayList<Courses>();
    CourseList courseAdapter;
    ListView listViewCourses;
    List<String> teacherIds = new ArrayList<String>();
    List<Teacher> teachers = new ArrayList<Teacher>();
    ListView listViewTeachers;
    TeacherList teacherAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_course);

        Intent intent = getIntent();
        String studentId = intent.getStringExtra("studentId");

        database = FirebaseDatabase.getInstance().getReference("courses");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Courses crs = snapshot.getValue(Courses.class);
                    courses.add(crs);
                }
                courseAdapter = new CourseList(StudentCourseActivity.this, courses);
                //attaching adapter to the listview
                listViewCourses.setAdapter(courseAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database = FirebaseDatabase.getInstance().getReference("assignedCourses");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    AssignedCourses assignCrs = snapshot.getValue(AssignedCourses.class);
                    for(int i=0;i<courses.size();i++){
                        if(assignCrs.getCourseId().toString().equals(courses.get(i).getCourseId().toString())){
                            teacherIds.add(assignCrs.getId().toString());
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database = FirebaseDatabase.getInstance().getReference("teachers");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Teacher teacher = snapshot.getValue(Teacher.class);
                    for(int i=0;i<teacherIds.size();i++){
                        if(teacher.getId().toString().equals(teacherIds.get(i).toString())){
                            teachers.add(teacher);
                            break;
                        }
                    }
                }
                teacherAdapter = new TeacherList(StudentCourseActivity.this, teachers);
                //attaching adapter to the listview
                listViewTeachers.setAdapter(teacherAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
