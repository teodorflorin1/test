package com.example.applicationuniversitylibrary.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.applicationuniversitylibrary.Adapters.ReviewAdapter;
import com.example.applicationuniversitylibrary.Models.Review;
import com.example.applicationuniversitylibrary.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PostReviewActivity extends AppCompatActivity {

    ImageView imgPost,imgUserPost,imgCurrentUser;
    TextView txtPostDesc,txtPostName,txtPostTitle;
    EditText editTextReview;
    Button btnAddReview;
    String ReviewKey;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    RecyclerView RvComment;
    ReviewAdapter reviewAdapter;
    List<Review> listReview;
    static String REVIEW_KEY = "Review" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_review);

        RvComment = findViewById(R.id.rv_review);
        imgPost =findViewById(R.id.post_review_img);
        imgCurrentUser = findViewById(R.id.post_review_currentuser_img);

        txtPostTitle = findViewById(R.id.post_review_title);
        txtPostDesc = findViewById(R.id.post_review_desc);
        txtPostName = findViewById(R.id.post_review_name2);

        editTextReview = findViewById(R.id.post_review_comment);
        btnAddReview = findViewById(R.id.post_review_add_comment_btn);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        btnAddReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference commentReference = firebaseDatabase.getReference(REVIEW_KEY).child(ReviewKey).push();
                String comment_content = editTextReview.getText().toString();
                String userId = firebaseUser.getUid();
                String uname = firebaseUser.getDisplayName();
                String uimg = firebaseUser.getPhotoUrl().toString();
                Review review = new Review (comment_content, userId, uimg, uname);

                commentReference.setValue(review).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showMessage("Review added");
                        editTextReview.setText("");
                        btnAddReview.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("Fail to add review : " +e.getMessage());
                    }
                });

            }
        });

        String postImage = getIntent().getExtras().getString("postImage");
        Glide.with(this).load(postImage).into(imgPost);

        String postTitle = getIntent().getExtras().getString("title");
        txtPostTitle.setText(postTitle);

        String postAuthor = getIntent().getExtras().getString("author");
        txtPostName.setText(postAuthor);


        String postDescription = getIntent().getExtras().getString("description");
        txtPostDesc.setText(postDescription);


        Glide.with(this).load(firebaseUser.getPhotoUrl()).into(imgCurrentUser);
        ReviewKey = getIntent().getExtras().getString("postKey");




        iniRvReview();

    }

    private void iniRvReview() {


        RvComment.setLayoutManager(new LinearLayoutManager(this));


        DatabaseReference commentRef = firebaseDatabase.getReference(REVIEW_KEY).child(ReviewKey);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listReview = new ArrayList<>();

                for (DataSnapshot snap : dataSnapshot.getChildren()){

                    Review review = snap.getValue(Review.class);
                    listReview.add(review);

                }

                reviewAdapter = new ReviewAdapter(getApplicationContext(),listReview);
                RvComment.setAdapter(reviewAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showMessage(String message) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    private String timestampToString(long time){

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy",calendar).toString();
        return date;
    }
}
