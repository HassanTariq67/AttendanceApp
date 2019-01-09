package com.naqibhatti.mod.madfinal;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by whit3hawks on 11/16/16.
 */
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {


    private Context context;


    // Constructor
    public FingerprintHandler(Context mContext) {
        context = mContext;
    }


    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {
        CancellationSignal cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }


    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        this.update("Fingerprint Authentication error\n" + errString, false);
    }


    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        this.update("Fingerprint Authentication help\n" + helpString, false);
    }


    @Override
    public void onAuthenticationFailed() {
        this.update("Fingerprint Authentication failed.", false);
    }


    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("Fingerprint Authentication succeeded.", true);
        if(((IMEIClass) ((Activity) context).getApplication()).getAuthenticationType()== "Register"){
            Intent intent ;
            intent = new Intent(context,StudentRegistration.class);
            //telling the intent it is a finger based log in so that activity can disbale password button
            intent.putExtra("AuthenticationType","Fingerprint");
            context.startActivity(intent);
        }
        if(((IMEIClass) ((Activity) context).getApplication()).getAuthenticationType()== "Login"){{
                Intent intent = new Intent(context,StudentActivity.class);
            //telling the intent it is a finger based log in so that activity can disbale password button
            intent.putExtra("AuthenticationType","Fingerprint");
                context.startActivity(intent);
        }}
    }


    public void update(String e, Boolean success){
        TextView textView = (TextView) ((Activity)context).findViewById(R.id.errorText);
        textView.setText(e);
        if(success){
            textView.setTextColor(ContextCompat.getColor(context,R.color.colorPrimaryDark));
            ((IMEIClass) ((Activity) context).getApplication()).setIMEI("Authenticated");

        }
    }

    public void StudentDashboard(){
        Intent intent = new Intent(context,StudentRegistration.class);
        context.startActivity(intent);

    }
}