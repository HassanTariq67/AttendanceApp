package com.naqibhatti.mod.madfinal;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StudentActivity extends AppCompatActivity {

    ImageButton signStudent;
    ImageButton registerStudent;
    EditText studentId;
    EditText passwordField;
    String password;
    String deviceId;
    String IMEI;
    String loggedInStudent;    //to save currently loggedIn student
    boolean exist;
    public DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        Intent intent = getIntent();
        final String AuthenticationType = intent.getStringExtra("AuthenticationType");

        signStudent = (ImageButton) findViewById(R.id.logSt);
        passwordField = (EditText) findViewById(R.id.studentPassword);

        if (AuthenticationType.equals("Fingerprint"))
            passwordField.setEnabled(false);
        else
            passwordField.setEnabled(true);

        signStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AuthenticationType.equals("Fingerprint"))
                    FingerprintLogin();
                else
                    Passwordlogin();
            }
        });
    }

    public void FingerprintLogin(){
        IMEI = getIMEI();

        database = FirebaseDatabase.getInstance().getReference("student");

        //Studetn Id and password
        final String studentid = ((TextView) findViewById(R.id.studentId)).getText().toString();

        //Checking if id is empty
        if(TextUtils.isEmpty(studentid)){
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show();
        }else{
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Student std = snapshot.getValue(Student.class);
                        String RegNo = std.getRegNo().toString();
                        if(RegNo.equals(studentid)){
                            if(std.getPassword().toString().equals(IMEI))
                            {
                                loggedInStudent = std.getStudentId().toString();
                                exist = true;
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            if(exist){
                Intent intent = new Intent(this,StudentCourseActivity.class);
                intent.putExtra("studentId",loggedInStudent);
                this.startActivity(intent);
            }
            else{
                Toast.makeText(this,"Incorrect credentials",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void Passwordlogin(){
        password = passwordField.getText().toString();
        database = FirebaseDatabase.getInstance().getReference("student");

        //Studetn Id and password
        final String studentid = ((TextView) findViewById(R.id.studentId)).getText().toString();

        //Checking if id is empty
        if(TextUtils.isEmpty(studentid) || TextUtils.isEmpty(password)){
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_LONG).show();
        }else{
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Student std = snapshot.getValue(Student.class);
                        String RegNo = std.getRegNo().toString();
                        if(RegNo.equals(studentid)){
                            if(std.getPassword().toString().equals(password))
                            {
                                loggedInStudent = std.getStudentId().toString();
                                exist = true;
                                break;
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            if(exist){
                Intent intent = new Intent(this,StudentCourseActivity.class);
                intent.putExtra("studentId",loggedInStudent);
                this.startActivity(intent);
            }
            else{
                Toast.makeText(this,"Incorrect credentials",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getIMEI(){
        TelephonyManager telephonyManager;
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(StudentActivity.this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            deviceId = telephonyManager.getDeviceId();
        }
        return deviceId;
    }
}
