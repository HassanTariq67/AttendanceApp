package com.naqibhatti.mod.madfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class  AdminActivity extends AppCompatActivity  {

    ImageButton signin;
    EditText adminUser;
    EditText adminPass;
    DatabaseReference firebase;
    private String fetchedUsername, fetchedPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        signin = (ImageButton) findViewById(R.id.adminsign);
        adminUser = (EditText) findViewById(R.id.useradmin);
        adminPass = (EditText) findViewById(R.id.passadmin);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInAdmin();
            }
        });
    }

    public void signInAdmin() {
        final String username, password ;

        username = adminUser.getText().toString();
        password = adminPass.getText().toString();

        //getting admin's login
        firebase = FirebaseDatabase.getInstance().getReference("admins");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot adminSnapshot:dataSnapshot.getChildren()){
                    Admin admin = adminSnapshot.getValue(Admin.class);
                    fetchedUsername = admin.getUsername();
                    fetchedPassword = admin.getPassword();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (username.equals(fetchedUsername) && password.equals(fetchedPassword)) {
            Intent t;
            t = new Intent(this, AdminPanel.class);
            this.startActivity(t);
            Toast.makeText(this, "Access Granted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Username or password is incorrect!", Toast.LENGTH_SHORT).show();
        }

    }

}
