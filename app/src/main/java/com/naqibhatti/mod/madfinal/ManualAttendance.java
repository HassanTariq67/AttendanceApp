package com.naqibhatti.mod.madfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManualAttendance extends AppCompatActivity {

    CourseList courseAdapter;
    ListView listViewCourses;
    List<String> courses = new ArrayList<String>();
    Spinner spinner;
    String CourseId;
    Button btn;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_attendance);
        spinner = (Spinner) findViewById(R.id.courses_spinner);
        btn = (Button) findViewById( R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TakeAttendance();
            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("assignedCourses");
        final String teacherId = ((IMEIClass) this.getApplication()).getTeacherId();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    AssignedCourses crs = snapshot.getValue(AssignedCourses.class);
                    if(crs.getTeacherId().toString().equals(teacherId)){
                        courses.add(value);
                    }
                }
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                        ManualAttendance.this,R.layout.spinner_item,courses);
                spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerArrayAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void TakeAttendance(){
        final String selectedClass = spinner.getSelectedItem().toString();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("courses");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Courses crs = snapshot.getValue(Courses.class);
                    if(crs.getcName().toString().equals(selectedClass)){
                        CourseId = crs.getCourseId().toString();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Intent intent = new Intent(this,TakeAttendance.class);
        intent.putExtra("courseId",CourseId);
        startActivity(intent);

    }
}
