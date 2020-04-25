package com.example.applicationuniversitylibrary.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.applicationuniversitylibrary.Models.Blog;
import com.example.applicationuniversitylibrary.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class PostActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;


    private Button mButtonChooseImage;
    private Button mButtonUpload;
    private EditText mEditTextPostTitle, mEditTextPostTopic, mEditTextPostDescription, mEditTextName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUSer;
    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private StorageTask mBlogTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        mButtonChooseImage = findViewById(R.id.button_choose_image);
        mButtonUpload = findViewById(R.id.button_upload);
        mEditTextPostTitle = findViewById(R.id.edit_text_post_title);
        mImageView = findViewById(R.id.image_view);
        mEditTextPostTopic = findViewById(R.id.et_post_topic);
        mEditTextPostDescription = findViewById(R.id.et_post_description);
        mEditTextName = findViewById(R.id.et_post_name);
        mProgressBar = findViewById(R.id.progress_bar);

        //set up FireBase Database and FireBase Storage
        mStorageRef = FirebaseStorage.getInstance().getReference("Blog");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Blog");

        // image selection form files
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openFileChooser();

            }
        });


        mButtonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBlogTask != null && mBlogTask.isInProgress()){
                    Toast.makeText(PostActivity.this,"Upload in progress", Toast.LENGTH_SHORT).show();
                }else {
                    uploadPost();
                }
            }
        });

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        mDatabaseUSer = FirebaseDatabase.getInstance().getReference().child("Users").child(mCurrentUser.getUid());



    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            mImageUri = data.getData();

            Picasso.with(this).load(mImageUri).into(mImageView);

        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return  mime.getExtensionFromMimeType(cR.getType(uri));

    }

 // uploading when clicking upload button
    private void uploadPost() {



        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mImageUri));

            mBlogTask = fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                    startActivity(new Intent(PostActivity.this, ForumActivity.class));
                                }

                            }, 100);

                            Toast.makeText(PostActivity.this, "Upload successful", Toast.LENGTH_LONG).show();

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();


                            // post the details
                            Blog post = new Blog(mEditTextPostTitle.getText().toString().trim(),downloadUrl.toString(),
                                    mEditTextPostDescription.getText().toString(),
                                    mEditTextPostTopic.getText().toString());


                            String postId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(postId).setValue(post);





                        }
                    })

                    //if post fails
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(PostActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();


                        }
                    })
                    //progress bar for uploading the post
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int)progress);

                        }
                    });
        }

        else {

            Toast.makeText(this,"No file selected",Toast.LENGTH_SHORT).show();
        }
    }

}
