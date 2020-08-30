package com.example.astudentzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeActivity extends AppCompatActivity  {

    // implement GoogleApiClient.OnConnectionFailedListener
    private Button dataentry,datavali;
    private GoogleApiClient googleApiClient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dataentry= findViewById(R.id.dataentry);
        datavali = findViewById(R.id.datavalidation);

        dataentry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();

            }

        });

//     user logout code
        datavali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        FirebaseAuth.getInstance().signOut();
        Intent intent1 = new Intent(HomeActivity.this, RegisterActivity.class);
        startActivity(intent1);
        finish();


        //      gotoMainActivity();*/

    }

    });
  /*  @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    private void setlogout()
    {

       /* SharedPreferences sharedPreferences=getSharedPreferences("check",MODE_PRIVATE);
        SharedPreferences.Editor e;
        e=sharedPreferences.edit();
        e.clear();
        e.commit();
    }
    private void gotoMainActivity() {
        setlogout();
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }*/
}}