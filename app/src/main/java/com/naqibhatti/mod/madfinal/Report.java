package com.naqibhatti.mod.madfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Report extends AppCompatActivity {

    Button courseWise;
    Button individualReport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        courseWise = (Button) findViewById(R.id.courseWise);
        individualReport = (Button) findViewById(R.id.individualReport);

        courseWise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Report.this,SelectCourseWiseCourse.class);
                startActivity(intent);
            }
        });

        individualReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent= new Intent(Report.this,SelectIndividualCourse.class);
            }
        });
    }

}
