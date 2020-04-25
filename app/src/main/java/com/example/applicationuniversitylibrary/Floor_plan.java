package com.example.applicationuniversitylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class Floor_plan extends AppCompatActivity {

    ImageView img_floor_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floor_plan);


        img_floor_1 = (ImageView) findViewById(R.id.img_floor_1);
        img_floor_1.setImageResource(R.drawable.g01);

    }
}
