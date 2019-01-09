package com.naqibhatti.mod.madfinal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TeacherDashboard extends AppCompatActivity {

    Button manualAttendance;
    Button allowAttendance;
    Button reports;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        manualAttendance = (Button)findViewById(R.id.TakeAttendance);
        allowAttendance = (Button) findViewById(R.id.AllowAutmoaticAttendance);
        reports = (Button) findViewById(R.id.SeeReporting);

        manualAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherDashboard.this,ManualAttendance.class);
                startActivity(intent);
                finish();
            }
        });

        allowAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllowAutomaticAttendance();
            }
        });

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(this,Reports.class);
            }
        });

    }

    public void AllowAutomaticAttendance() {
        String Id = ((IMEIClass) this.getApplication()).getTeacherId();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("teachers").child(Id);
        databaseReference.child("automaticAttendance").setValue(true);
    }
}
