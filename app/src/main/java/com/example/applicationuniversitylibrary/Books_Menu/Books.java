package com.example.applicationuniversitylibrary.Books_Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.applicationuniversitylibrary.Activities.ImagesActivity;
import com.example.applicationuniversitylibrary.Business.BusinessActivity;
import com.example.applicationuniversitylibrary.Computing.ComputingActivity;
import com.example.applicationuniversitylibrary.Economics.EconomicsActivity;
import com.example.applicationuniversitylibrary.Gaming.GamingActivity;
import com.example.applicationuniversitylibrary.Activities.HomeNav;
import com.example.applicationuniversitylibrary.Mathematics.MathematicsActivity;
import com.example.applicationuniversitylibrary.R;
import com.example.applicationuniversitylibrary.Timetable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Books extends AppCompatActivity {

    CardView cwSale, cwGaming, cwEconomics, cwBusiness, cwComputing, cwMathematics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);

        cwSale = findViewById(R.id.card_for_sale);
        cwSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Books.this, ImagesActivity.class);
                startActivity(i);
            }
        });

        cwGaming = findViewById(R.id.card_gaming);
        cwGaming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Books.this, GamingActivity.class);
                startActivity(i);
            }
        });

        cwComputing = findViewById(R.id.card_computing);
        cwComputing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Books.this, ComputingActivity.class);
                startActivity(i);
            }
        });

        cwMathematics = findViewById(R.id.card_mathematics);
        cwMathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Books.this, MathematicsActivity.class);
                startActivity(i);

            }
        });

        cwEconomics = findViewById(R.id.card_economics);
        cwEconomics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Books.this, EconomicsActivity.class);
                startActivity(i);


            }
        });

        cwBusiness = findViewById(R.id.card_business);
        cwBusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Books.this, BusinessActivity.class);
                startActivity(i);
            }
        });



            BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

            bottomNavigationView.setSelectedItemId(R.id.books);

            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.home:
                            startActivity(new Intent(getApplicationContext(), HomeNav.class));

                            return true;

                        case R.id.books:
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

