package com.naqibhatti.mod.madfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherLogin extends AppCompatActivity {

    EditText id;
    EditText password;
    DatabaseReference database;
    ImageButton login;
    String textId;
    String globalTeacherId;
    String textPassword;
    boolean exist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);

        login = (ImageButton) findViewById(R.id.logSt);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginTeacher();
            }
        });
    }

    public void LoginTeacher(){
        id = (EditText) findViewById(R.id.teacherId);
        password = (EditText) findViewById(R.id.teacherPassword);

        textId = id.getText().toString();
        textPassword = password.getText().toString();
        if(TextUtils.isEmpty(textId) || TextUtils.isEmpty(textPassword)){
                Toast.makeText(this,"Fill all fields",Toast.LENGTH_SHORT).show();
            }else{
            database = FirebaseDatabase.getInstance().getReference("teachers");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Teacher teacher = snapshot.getValue(Teacher.class);
                        String teacherpassword = teacher.getPassword().toString();
                        String teacherId = teacher.getTeacherId().toString();
                        if(teacherpassword.equals(textPassword) && teacherId.equals(textId)){
                            globalTeacherId = teacher.getId();
                            exist = true;
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });

            if(exist){
                ((IMEIClass) this.getApplication()).setTeacherId(globalTeacherId);
                Intent intent = new Intent(TeacherLogin.this,TeacherDashboard.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this,"Invalid Credentials",Toast.LENGTH_SHORT).show();
            }
        }

    }
}
