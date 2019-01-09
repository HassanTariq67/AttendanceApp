package com.naqibhatti.mod.madfinal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class AuthentationType extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Button LoginButton;
    Button RegisterButton;
    Spinner spinner;
    String item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentation_type);

        Spinner spinner = (Spinner) findViewById(R.id.authenticationType);
        spinner.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.authentationtype, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        RegisterButton = (Button) findViewById(R.id.Register);
        LoginButton = (Button) findViewById(R.id.Login);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisterActivity();
            }
        });

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLoginActivity();
            }
        });
    }

    public void startFingerPrintActivity(){
        Intent intent = new Intent(AuthentationType.this,FingerprintActivity.class);
        ((IMEIClass) this.getApplication()).setAuthenticationType("Login");
        startActivity(intent);
        finish();
    }

    public void startRegisterActivity(){
        if(item.equals("FingerPrint")){
            Intent intent = new Intent(this,FingerprintActivity.class);
            //setting authentication type as register
            ((IMEIClass) this.getApplication()).setAuthenticationType("Register");
//        intent.putExtra("AuthenticationType",spinner.getSelectedItem().toString());
            startActivity(intent);
        }else{
            Intent intent = new Intent(this,StudentRegistration.class);
            //setting authentication type as Login
            intent.putExtra("AuthenticationType",item);
            startActivity(intent);
        }
    }

    public void startLoginActivity(){
        if(item.equals("Password")){
            Intent intent = new Intent(this,StudentActivity.class);
            //setting authentication type as Login
            intent.putExtra("AuthenticationType",item);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this,FingerprintActivity.class);
            //setting authentication type as login
            ((IMEIClass) this.getApplication()).setAuthenticationType("Login");
//        intent.putExtra("AuthenticationType",spinner.getSelectedItem().toString());
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}
