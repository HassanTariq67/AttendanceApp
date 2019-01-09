package com.naqibhatti.mod.madfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class AdminPanel extends AppCompatActivity implements View.OnClickListener {

    CardView classsCreate;
    CardView courseCreate;
    CardView teacherCreate;
    CardView teacherAssign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        classsCreate = (CardView) findViewById(R.id.cardclass);
        courseCreate = (CardView) findViewById(R.id.cardcourse);
        teacherAssign = (CardView) findViewById(R.id.cardassign);
        teacherCreate = (CardView) findViewById(R.id.cardteacher);

        classsCreate.setOnClickListener(this);
        courseCreate.setOnClickListener(this);
        teacherAssign.setOnClickListener(this);
        teacherCreate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.cardclass){
            Intent a;
            a = new Intent(this, ClassCreate.class);
            this.startActivity(a);
        }else if(v.getId() == R.id.cardcourse){
            Intent b;
            b = new Intent(this, CourseCreate.class);
            this.startActivity(b);
        }else if(v.getId() == R.id.cardteacher){
            Intent c;
            c = new Intent(this, TeacherCreate.class);
            this.startActivity(c);
        }else if(v.getId() == R.id.cardassign){
            Intent d;
            d = new Intent(this, TeacherAssign.class);
            this.startActivity(d);
        }
    }
}
