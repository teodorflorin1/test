package com.example.applicationuniversitylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.applicationuniversitylibrary.Activities.HomeNav;
import com.example.applicationuniversitylibrary.Books_Menu.Books;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Timetable extends AppCompatActivity {


    Button btn_table_1, btn_table_2, btn_table_3, btn_table_4, btn_table_5, btn_table_6 ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);


        btn_table_1 = findViewById(R.id.btn_table_1);
        btn_table_2 = findViewById(R.id.btn_table_2);
        btn_table_3 = findViewById(R.id.btn_table_3);
        btn_table_4 = findViewById(R.id.btn_table_4);
        btn_table_5 = findViewById(R.id.btn_table_5);
        btn_table_6 = findViewById(R.id.btn_table_6);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.timetable);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeNav.class));

                        return true;

                    case R.id.books:
                        startActivity(new Intent(getApplicationContext(), Books.class));

                        return true;

                    case R.id.timetable:
                        return true;

                }
                return false;
            }
        });




        btn_table_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Timetable.this, Floor_plan.class);
                startActivity(i);
            }
        });

        btn_table_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Timetable.this, Floor_plan.class);
                startActivity(i);
            }
        });

        btn_table_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Timetable.this, Floor_plan2.class);
                startActivity(i);
            }
        });

        btn_table_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Timetable.this, Floor_plan3.class);
                startActivity(i);

            }
        });

        btn_table_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Timetable.this, Floor_plan.class);
                startActivity(i);

            }
        });

        btn_table_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Timetable.this, Floor_plan.class);
                startActivity(i);
            }
        });

    } }
