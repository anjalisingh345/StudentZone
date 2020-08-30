package com.example.astudentzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {

    private CountryCodePicker ccp;
    private EditText phonetext;
    private EditText codetext;
    private Button getotpbtn;
    private String checker = "", phoneNumber = "";
    private RelativeLayout relativeLayout;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       // check();

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        phonetext = findViewById(R.id.phoneText);
        codetext = findViewById(R.id.codeText);
        getotpbtn = findViewById(R.id.continueNextButton);
        relativeLayout = findViewById(R.id.phoneAuth);
        ccp = findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(phonetext);


       /*/ authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                // Get signedIn user
                FirebaseUser user = mAuth.getCurrentUser();

                //if user is signed in, we call a helper method to save the user details to Firebase
                if (user != null) {

                    // User is signed in
                    // you could place other firebase code
                    //logic to save the user details to Firebase
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    // User is signed out


                }
            }
        };
/*/
        getotpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getotpbtn.getText().equals("Login") || checker.equals("Code sent")) {
                    String verificationcode = codetext.getText().toString();
                    if (verificationcode.equals("")) {
                        Toast.makeText(RegisterActivity.this, "please verification code first", Toast.LENGTH_LONG).show();
                    } else {
                        progressDialog.setTitle("code Verfication");
                        progressDialog.setMessage("please wait, while we are verification your code number.");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationcode);
                        signInWithPhoneAuthCredential(credential);
                    }
                } else {
                    phoneNumber = ccp.getFullNumberWithPlus();
                    if (!phoneNumber.equals("")) {
                        progressDialog.setTitle("Phone Number Verfication");
                        progressDialog.setMessage("please wait, while we are verification phone number.");
                        progressDialog.setCanceledOnTouchOutside(false);
                        progressDialog.show();

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 60, TimeUnit.SECONDS, RegisterActivity.this, mCallbacks);        // OnVerificationStateChangedCallbacks

                    } else {
                        Toast.makeText(RegisterActivity.this, "please write valid phone Number", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(RegisterActivity.this, "Invailed phone Number.......", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                relativeLayout.setVisibility(View.VISIBLE);
                getotpbtn.setText("GET OTP ");
                codetext.setVisibility(View.GONE);
            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                mVerificationId = s;
                mResendToken = forceResendingToken;
                relativeLayout.setVisibility(View.GONE);
                checker = "Code sent";
                getotpbtn.setText("Login");
                codetext.setVisibility(View.VISIBLE);
                progressDialog.dismiss();

                Toast.makeText(RegisterActivity.this, "code has been sent, please check.", Toast.LENGTH_LONG).show();

            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "you are login Successfully", Toast.LENGTH_LONG).show();
                            sendUserToHomePage();
                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            progressDialog.dismiss();
                            String e = task.getException().toString();
                            Toast.makeText(RegisterActivity.this, "Error: " + e, Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }

    private void sendUserToHomePage() {
        setlogin();

    }



  /*/  public  void check()
    {
        final SharedPreferences sharedPreferences=getSharedPreferences("check",MODE_PRIVATE);
        final String condition =sharedPreferences.getString("con","");
        if (condition.equals("TRUE"))
        {
            Intent i=new Intent(RegisterActivity.this,HomeActivity.class);
            startActivity(i);
            finish();
        }


    }/*/

    // user one time login one number code

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //if user is signed in, we call a helper method to save the user details to Firebase
        if (firebaseUser != null)
        {
            // User is signed in
            // you could place other firebase code
            //logic to save the user details to Firebase
            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        else {

            // User is signed out
        } }


    private void setlogin() {

       /*/ SharedPreferences sharedPreferences=getSharedPreferences("check",MODE_PRIVATE);
        SharedPreferences.Editor e;
        e=sharedPreferences.edit();
        e.putString("con","TRUE");
        e.commit();/*/
    }

}