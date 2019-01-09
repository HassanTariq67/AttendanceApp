package com.naqibhatti.mod.madfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class UsersActivity extends AppCompatActivity implements View.OnClickListener {

    Button cd;
    Button cd2;
    Button cd3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        cd = (Button) findViewById(R.id.admin);
        cd2 = (Button) findViewById(R.id.Student);
        cd3 = (Button) findViewById(R.id.Teacher);

        cd.setOnClickListener(this);
        cd2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });
        cd3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsersActivity.this,TeacherLogin.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent j;
        j = new Intent(this, AdminActivity.class);
        startActivity(j);
    }

    public void startNewActivity(){
        Intent intent = new Intent(this,AuthentationType.class);
        ((IMEIClass) this.getApplication()).setAuthenticationType("Login");
        startActivity(intent);
    }
}
