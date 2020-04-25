package com.example.applicationuniversitylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.applicationuniversitylibrary.Activities.HomeNav;
import com.example.applicationuniversitylibrary.Books_Menu.Books;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);

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
                        startActivity(new Intent(getApplicationContext(),Timetable.class));

                        return true;

                }
                return false;
            }
        });
    }
}
