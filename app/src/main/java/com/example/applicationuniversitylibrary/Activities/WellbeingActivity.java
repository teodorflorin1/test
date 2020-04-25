package com.example.applicationuniversitylibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;


import com.example.applicationuniversitylibrary.Books_Menu.Books;
import com.example.applicationuniversitylibrary.Models.Pop;
import com.example.applicationuniversitylibrary.Models.PopActive;
import com.example.applicationuniversitylibrary.Models.PopLibrary;
import com.example.applicationuniversitylibrary.Models.PopStudent;
import com.example.applicationuniversitylibrary.R;
import com.example.applicationuniversitylibrary.Timetable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class WellbeingActivity extends AppCompatActivity {


    LinearLayout socialLife, studentUnion, studentLibrary, studentActive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellbeing);

        socialLife = findViewById(R.id.cafe);

        socialLife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(WellbeingActivity.this, Pop.class));
            }
        });

        studentUnion = findViewById(R.id.student);
        studentUnion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WellbeingActivity.this, PopStudent.class));
            }
        });

        studentLibrary = findViewById(R.id.library);
        studentLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WellbeingActivity.this, PopLibrary.class));
            }
        });

        studentActive = findViewById(R.id.active);
        studentActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WellbeingActivity.this, PopActive.class));
            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeNav.class));
                        return true;

                    case R.id.books:
                        startActivity(new Intent(getApplicationContext(), Books.class));
                        return true;

                    case R.id.timetable:
                        startActivity(new Intent(getApplicationContext(), Timetable.class));
                        return true;

                }
                return false;

            }
        });

    }

}
