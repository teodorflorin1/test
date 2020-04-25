package com.example.applicationuniversitylibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.applicationuniversitylibrary.Books_Menu.Books;
import com.example.applicationuniversitylibrary.R;
import com.example.applicationuniversitylibrary.Timetable;
import com.example.applicationuniversitylibrary.WebViews.CareersWebView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class StudentSupportActivity extends AppCompatActivity {

    private CardView Sfe, WellBeing, AceTeam, Careers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_support);

        // set up the cardViews

        Sfe = findViewById(R.id.card_sfe);
        Sfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentSupportActivity.this, SfeActivity.class);
                startActivity(i);
            }
        });

        WellBeing = findViewById(R.id.card_WellBeing);
        WellBeing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentSupportActivity.this, WellbeingActivity.class);
                startActivity(i);
            }
        });

        AceTeam = findViewById(R.id.card_ace);
        AceTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentSupportActivity.this, AceActivity.class);
                startActivity(i);
            }
        });

        Careers = findViewById(R.id.card_careers);
        Careers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StudentSupportActivity.this, CareersWebView.class);
                startActivity(i);
            }
        });


 // bottom navigation

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
