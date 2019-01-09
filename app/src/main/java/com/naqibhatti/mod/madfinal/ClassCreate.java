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

public class ClassCreate extends AppCompatActivity {

    Button createClass;
    EditText nameOfCourse;
    String nameGiven;
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_create);

        createClass = (Button) findViewById(R.id.createbutton);
        nameOfCourse = (EditText) findViewById(R.id.editName);

        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddClass();
            }
        });
    }


    public void AddClass() {
        nameGiven = (String) nameOfCourse.getText().toString();
        database = FirebaseDatabase.getInstance().getReference("classes");

        if (TextUtils.isEmpty(nameGiven)) {
            Toast.makeText(this, "No Class name give", Toast.LENGTH_SHORT).show();
        } else {
            String id = database.push().getKey();
            Class clas = new Class(id,nameGiven);
            database.child(id).setValue(clas);
            //USE THIS NAME TO CREATE A NEW CLASS AND SAVE THE DATA TO THE DATABASE.
            Toast.makeText(this, "Class Created", Toast.LENGTH_SHORT).show();
            Intent z;
            z = new Intent(this, AdminPanel.class);
            this.startActivity(z);
        }
    }
}