package com.naqibhatti.mod.madfinal;

import android.Manifest;
import android.app.Activity;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.util.NumberUtils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StudentRegistration extends AppCompatActivity {

    ImageButton stdReg;
    EditText fname;
    EditText age2;
    EditText password;
    EditText registrationNo;
    Button passButton;
    Button fingerPrintButton;
    Spinner selectedClass;

    String deviceId;
    String IMEI;
    String Name;
    String age;
    String passw;
    String regNo;
    String className;
    String classId;
    Boolean IMEIexist;
    DatabaseReference database;
    ArrayList<String> classList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        Intent intent = getIntent();
        final String AuthenticationType = intent.getStringExtra("AuthenticationType");

        stdReg = (ImageButton) findViewById(R.id.streg);
        fname = (EditText) findViewById(R.id.Name);
        age2 = (EditText) findViewById(R.id.age1);
        password = ((EditText) findViewById(R.id.pass));
        registrationNo = (EditText) findViewById(R.id.RegNo);

        if(AuthenticationType.equals("Fingerprint")){
            password.setEnabled(false);
        }else{
            password.setEnabled(true);
        }

        stdReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AuthenticationType.equals("Fingerprint"))
                    FingerPrintRegister();
                else
                    PasswordRegister();
            }
        });
    }

    public String getIMEI(){
        TelephonyManager telephonyManager;
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ContextCompat.checkSelfPermission(StudentRegistration.this, Manifest.permission.READ_PHONE_STATE)
                == PackageManager.PERMISSION_GRANTED) {
            deviceId = telephonyManager.getDeviceId();
        }
        return deviceId;
    }

    //Code to register using fingerprint
    public void FingerPrintRegister(){
        IMEI = getIMEI();
        Name = fname.getText().toString();
        age = age2.getText().toString();
        regNo = registrationNo.getText().toString();

        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(passw) || TextUtils.isEmpty(regNo)){
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_SHORT).show();
        }else{

            if(checkIMEI(IMEI)){
            StringTokenizer classTokenizer = new StringTokenizer(regNo,"-");
            className = classTokenizer.nextToken().toString();
            database = FirebaseDatabase.getInstance().getReference("classes");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Class cls = snapshot.getValue(Class.class);
                        if(cls.getClassName().toString().equals(className)){
                            classId = cls.getClassId();
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            database  = FirebaseDatabase.getInstance().getReference("student");
            String id = database.push().getKey();
            Student std = new Student(id,Name,age,passw,regNo,classId);
            database.child(id).setValue(std);
            Toast.makeText(this,"Successfully registered",Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,StudentCourseActivity.class);
                intent.putExtra("studentId",id);
            startActivity(intent);
            finish();
            }
            else{
                Toast.makeText(this,"Phone already registered",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Code to register using password
    public void PasswordRegister(){
        Name = fname.getText().toString();
        age = age2.getText().toString();
        passw = password.getText().toString();
        regNo = registrationNo.getText().toString();

        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(age) || TextUtils.isEmpty(passw) || TextUtils.isEmpty(regNo)){
            Toast.makeText(this,"Fill all the fields",Toast.LENGTH_SHORT).show();
        }else{
            StringTokenizer classTokenizer = new StringTokenizer(regNo,"-");
            className = classTokenizer.nextToken().toString();
            database = FirebaseDatabase.getInstance().getReference("classes");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Class cls = snapshot.getValue(Class.class);
                        if(cls.getClassName().toString().equals(className)){
                            classId = cls.getClassId();
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            //Creating new Student
            database  = FirebaseDatabase.getInstance().getReference("student");
            String id = database.push().getKey();
            Student std = new Student(id,Name,age,passw,regNo,classId);
            database.child(id).setValue(std);
            Toast.makeText(this,"Successfully registered",Toast.LENGTH_SHORT).show();

            //Calling a new Intent
            Intent intent = new Intent(this,StudentCourseActivity.class);
            intent.putExtra("studentId",id);
            startActivity(intent);
            finish();
        }
    }

    public Boolean checkIMEI(final String IMEI){
        IMEIexist = false ;
        database = FirebaseDatabase.getInstance().getReference("student");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Student std = snapshot.getValue(Student.class);
                    if(std.getPassword().toString().equals(IMEI)){
                        IMEIexist = true;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return IMEIexist;
    }
}

