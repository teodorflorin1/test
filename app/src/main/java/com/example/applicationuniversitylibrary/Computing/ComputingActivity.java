package com.example.applicationuniversitylibrary.Computing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.applicationuniversitylibrary.Books_Menu.Books;
import com.example.applicationuniversitylibrary.Activities.HomeNav;
import com.example.applicationuniversitylibrary.R;
import com.example.applicationuniversitylibrary.Timetable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ComputingActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ComputingAdapter mAdapter;


    private FloatingActionButton pBook;
    private FirebaseStorage mStorage;
    private DatabaseReference mDatabaseRef;
    private ValueEventListener mDBListener;
    private List<Computing> mComputing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computing);

        pBook = findViewById(R.id.fab);
        pBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ComputingActivity.this, PostActivityComputing.class);
                startActivity(i);
            }
        });

        EditText editText = findViewById(R.id.et_search);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());

            }
        });


        mRecyclerView = findViewById(R.id.recycler_view_computing);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mComputing = new ArrayList<>();

        mAdapter = new ComputingAdapter(ComputingActivity.this, mComputing);

        mRecyclerView.setAdapter(mAdapter);

        mStorage = FirebaseStorage.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Computing");

        mDBListener = mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mComputing.clear();

                for (DataSnapshot blogSnapshot : dataSnapshot.getChildren()) {
                    Computing post = blogSnapshot.getValue(Computing.class);
                    post.setKey(blogSnapshot.getKey());
                    mComputing.add(post);


                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ComputingActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void filter(String text){

        ArrayList<Computing> filteredList = new ArrayList<>();

        for (Computing item : mComputing){
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }

        mAdapter.filterList(filteredList);
    }
}
