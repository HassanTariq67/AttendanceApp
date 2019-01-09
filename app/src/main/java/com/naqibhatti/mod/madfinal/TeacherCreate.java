package com.naqibhatti.mod.madfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.util.NumberUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherCreate extends AppCompatActivity  {

    Button teachReg;
    EditText name;
    EditText id;
    EditText qualification;
    EditText exp;
    EditText passwordText;

    String Name;
    String teachID;
    int experience;
    String quali;
    String password;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_create);

        teachReg = (Button) findViewById(R.id.idbutton2);
        name = (EditText) findViewById(R.id.idname);
        id = (EditText) findViewById(R.id.teachId);
        qualification = (EditText) findViewById(R.id.idqualify);
        exp = (EditText) findViewById(R.id.idexp);
        passwordText = (EditText) findViewById(R.id.password);

        teachReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTeacher();
            }
        });
    }

    public void AddTeacher() {

        Name = name.getText().toString();
        teachID = id.getText().toString();
        String expe = exp.getText().toString();
        quali = qualification.getText().toString();
        password = passwordText.getText().toString();

        database = FirebaseDatabase.getInstance().getReference("teachers");
        //Pass the student data to the database

        //Validation of form:
        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(password) ||
                TextUtils.isEmpty(teachID) || TextUtils.isEmpty(expe) || TextUtils.isEmpty(quali) || !NumberUtils.isNumeric(expe)){
            Toast.makeText(this, "Fill All fields!!", Toast.LENGTH_SHORT).show();
        }else{
            String id = database.push().getKey();
            Teacher teach = new Teacher(id,teachID,Name,quali,expe,password,false);
            database.child(id).setValue(teach);
            Toast.makeText(this, "Teacher Registered", Toast.LENGTH_LONG).show();

            experience = Integer.parseInt(expe);

            Intent as;
            as = new Intent(this, AdminPanel.class);
            this.startActivity(as);
        }
    }
}
