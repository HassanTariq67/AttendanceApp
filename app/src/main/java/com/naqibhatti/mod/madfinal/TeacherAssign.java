package com.naqibhatti.mod.madfinal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TeacherAssign extends AppCompatActivity {

    Spinner teacherSpinner;
    DatabaseReference database;
    List<String> teachers = new ArrayList<String>();
    List<Courses> courses = new ArrayList<Courses>();
    CourseList courseAdapter;
    ListView listViewCourses;
    List<String> selectedCourses = new ArrayList<String>();
    List<String> matchedCourses = new ArrayList<String>();
    String selectedTeacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_assign);

        teacherSpinner = (Spinner) findViewById(R.id.spinner);
        listViewCourses = (ListView) findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance().getReference("teachers");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Teacher teacher = snapshot.getValue(Teacher.class);
                    teachers.add(teacher.getTeacherId().toString());
                }

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                        TeacherAssign.this,R.layout.spinner_item,teachers);
                spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
                teacherSpinner.setAdapter(spinnerArrayAdapter);
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
                    courses.add(crs);
                }
                courseAdapter = new CourseList(TeacherAssign.this, courses);
                //attaching adapter to the listview
                listViewCourses.setAdapter(courseAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final Button button = (Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSelectedCourses();
            }
        });
    }

    public void getSelectedCourses(){
        selectedCourses = courseAdapter.getSelectedcourses();

        database = FirebaseDatabase.getInstance().getReference("courses");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Courses course = snapshot.getValue(Courses.class);
                    for(int i=0; i<selectedCourses.size();i++){
                        if(course.getcName()==selectedCourses.get(i)){
                            matchedCourses.add(course.getCourseId());
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        assignCourses();
    }

    public void assignCourses(){
        database = FirebaseDatabase.getInstance().getReference("teachers");
        final String teacherId = teacherSpinner.getSelectedItem().toString();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Teacher teach = snapshot.getValue(Teacher.class);
                    String teachId = teach.getTeacherId().toString();
                    if(teachId.equals(teacherId)){
                        selectedTeacher = teach.getId();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database = FirebaseDatabase.getInstance().getReference("assignedCourses");
        String id = database.push().getKey();
        for(int i=0;i<matchedCourses.size();i++){
            AssignedCourses assignedCourse = new AssignedCourses(id,selectedTeacher,matchedCourses.get(i));
            database.child(id).setValue(assignedCourse);
        }
        Toast.makeText(this,"Courses assigned to "+ teacherId , Toast.LENGTH_SHORT).show();
    }
}
