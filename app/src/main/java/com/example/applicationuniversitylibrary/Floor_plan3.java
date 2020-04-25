package com.example.applicationuniversitylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class Floor_plan3 extends AppCompatActivity {

        Image img_floor_3;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_floor_plan3);

            ImageView imageView = (ImageView)findViewById(R.id.img_floor_3);
            imageView.setImageResource(R.drawable.l101);

        }
    }