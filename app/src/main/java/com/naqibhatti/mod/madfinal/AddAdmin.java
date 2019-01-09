package com.naqibhatti.mod.madfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class AddAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_admin);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView username = (TextView) findViewById(R.id.editText4);
                TextView password = (TextView) findViewById(R.id.editText3);

                final String user = username.getText().toString().trim();
                final String pass = password.getText().toString().trim();
                DatabaseReference database = FirebaseDatabase.getInstance().getReference("admins");
                String id = database.push().getKey();
                Admin admin = new Admin(id, user,pass);
                database.child(id).setValue(admin);
            }
        });

    }
}
