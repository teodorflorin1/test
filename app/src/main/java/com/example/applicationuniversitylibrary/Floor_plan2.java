package com.example.applicationuniversitylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class Floor_plan2 extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_floor_plan2);

            ImageView imageView = (ImageView)findViewById(R.id.img_floor_2);
            imageView.setImageResource(R.drawable.lg1);
        }
    }
