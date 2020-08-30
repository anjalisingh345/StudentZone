package com.example.astudentzone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class DashboardActivity extends AppCompatActivity {

    private Button NewEntry;
   private ImageView back;
   private Animation newentryanimup,newentrydown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_dashboard);

        newentryanimup = AnimationUtils.loadAnimation(this,R.anim.buttonanim);
        newentrydown = AnimationUtils.loadAnimation(this,R.anim.newentrydownanim);

        NewEntry = findViewById(R.id.submitNDE);

        NewEntry.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    NewEntry.startAnimation(newentryanimup);

                }
                else if (event.getAction() == MotionEvent.ACTION_UP){

                    NewEntry.startAnimation(newentrydown);
                }
                return false;
            }
        });
        NewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
  /*  @Override
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
                Intent intent = new Intent(DashboardActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

}