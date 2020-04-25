package com.example.applicationuniversitylibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.applicationuniversitylibrary.Books_Menu.Books;
import com.example.applicationuniversitylibrary.PostBook;
import com.example.applicationuniversitylibrary.R;
import com.example.applicationuniversitylibrary.Timetable;
import com.example.applicationuniversitylibrary.WebViews.Webview;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//setting up the main menu of the app
public class HomeNav extends AppCompatActivity {

   CardView floorplan, webView, postBook, forum;
   ImageButton signoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_nav);


        signoutBtn = findViewById(R.id.signout_btn);
        signoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeNav.this, MainActivity.class);
                startActivity(i);
            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.home);
//set up the views fro the bottom navigation
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.home:
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

        // set up the items form the main menu
        floorplan = findViewById(R.id.cw_student_support);
        webView = findViewById(R.id.cw_moodle);
        postBook = findViewById(R.id.cw_post_book);
        forum = findViewById(R.id.cw_forum);


        forum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeNav.this, ForumActivity.class);
                startActivity(i);
            }
        });

        postBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeNav.this, PostBook.class);
                startActivity(i);
            }
        });


        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeNav.this, Webview.class);
                startActivity(i);
            }
        });

        floorplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeNav.this, StudentSupportActivity.class);
                startActivity(i);

            }
        });

    }
}
