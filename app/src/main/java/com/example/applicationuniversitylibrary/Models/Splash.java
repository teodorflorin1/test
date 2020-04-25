package com.example.applicationuniversitylibrary.Models;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.applicationuniversitylibrary.Activities.MainActivity;
import com.example.applicationuniversitylibrary.R;

// splash animation
public class Splash extends AppCompatActivity {


    private ImageView iv;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv = findViewById(R.id.img_splash);
        tv = findViewById(R.id.tv_splash);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.mytransition);
        iv.startAnimation(myanim);
        tv.startAnimation(myanim);
        final Intent i = new Intent(this, MainActivity.class);
        Thread timer = new Thread(){

            @Override
            public void run() {
                try {
                    sleep(3000); // duration of animation
                } catch(InterruptedException e) {

                    e.printStackTrace();
                }
                finally {
                    startActivity(i); // directing the user to Main Menu
                    finish();
                }
            }
        };
        timer.start();
    }

}

