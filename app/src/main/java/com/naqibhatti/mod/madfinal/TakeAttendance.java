package com.naqibhatti.mod.madfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TakeAttendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_attendance);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String Id = (String) b.get("courseId");
        
    }
}
