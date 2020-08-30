package com.example.astudentzone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.spark.submitbutton.SubmitButton;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ImageView back;
    SubmitButton submit;

EditText clientMono,valiMono,pgname,city,area,ownername,prfdlanguage;

    DatabaseReference firebaseDatabase;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clientMono = findViewById(R.id.clientnum);
        valiMono = findViewById(R.id.valinum);
        pgname = findViewById(R.id.pgname);
        city = findViewById(R.id.city);
        area = findViewById(R.id.area);
        ownername = findViewById(R.id.ownerName);
        prfdlanguage = findViewById(R.id.language);



        submit = findViewById(R.id.sub);
        firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("user");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveinfoOnly();

            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    private  void  saveinfoOnly(){
        final  String sclientMonoo = clientMono.getText().toString();
        final  String svalino = valiMono.getText().toString();
        final  String sPGName = pgname.getText().toString();
        final  String sCity = city.getText().toString();
        final  String sowner = ownername.getText().toString();
        final  String slanguage = prfdlanguage.getText().toString();
        final  String sarea = area.getText().toString();

        if (sclientMonoo.equals(""))
        {
            Toast.makeText(this,"please enter client Mobile Number",Toast.LENGTH_LONG).show();
    }
         else if(slanguage.equals(""))

        {
            Toast.makeText(this,"please enter c",Toast.LENGTH_LONG).show();
        }

        else if (svalino.equals(""))
        {
            Toast.makeText(this,"please enter client Mobile Number",Toast.LENGTH_LONG).show();
        }
        else if(sCity.equals(""))

        {
            Toast.makeText(this,"please enter c",Toast.LENGTH_LONG).show();
        }

      else   if (sPGName.equals(""))
        {
            Toast.makeText(this,"please enter client Mobile Number",Toast.LENGTH_LONG).show();
        }
        else if(sowner.equals(""))

        {
            Toast.makeText(this,"please enter c",Toast.LENGTH_LONG).show();
        }
        else if(sarea.equals(""))

        {
            Toast.makeText(this,"please enter c",Toast.LENGTH_LONG).show();
        }
        else {


            HashMap<String, Object> profileMap = new HashMap<>();
            profileMap.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
            profileMap.put("ClientNo",sclientMonoo);
            profileMap.put("ValidetNo",svalino);
            profileMap.put("PGName",sPGName);
            profileMap.put("City",sCity);
            profileMap.put("OWnerName",sowner);
            profileMap.put("Language",slanguage);
            profileMap.put("Area",sarea);

            firebaseDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(profileMap)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                              if (task.isSuccessful()){
                                  Intent intent =  new Intent(MainActivity.this,DashboardActivity.class);
                                  startActivity(intent);
                                  finish();
                                  Toast.makeText(MainActivity.this,"Update",Toast.LENGTH_LONG).show();

                              }
                              else {
                                  Toast.makeText(MainActivity.this,task.getException().toString(),Toast.LENGTH_LONG).show();
                              }
                              }

                    }

                    ).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }
            });

        }
 /*   @Override,
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.back:
                Intent intent = new Intent(MainActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }}*/
}}