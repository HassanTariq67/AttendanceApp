package com.naqibhatti.mod.madfinal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SelectCourseWiseCourse extends AppCompatActivity {

    Spinner spinner;
    Button btn;
    List<String> courses = new ArrayList<String>();
    List<String> courseNames = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_course_wise_course);

        spinner = (Spinner) findViewById(R.id.courseSpinner);
        btn = (Button)findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("assignCourses");
        final String teacherId = ((IMEIClass) this.getApplication()).getTeacherId();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    AssignedCourses course = snapshot.getValue(AssignedCourses.class);
                    if(course.getTeacherId().toString().equals(teacherId)){
                        courses.add(course.getCourseId());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database = FirebaseDatabase.getInstance().getReference("courses");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Courses crs = snapshot.getValue(Courses.class);
                    for(int i=0;i<courses.size();i++){
                        if(crs.getCourseId() == courses.get(i)){
                            courseNames.add(crs.getcName());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                SelectCourseWiseCourse.this,R.layout.spinner_item,courseNames);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }
}
