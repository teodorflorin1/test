package com.example.applicationuniversitylibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.applicationuniversitylibrary.ChatMessage;

import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.applicationuniversitylibrary.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class ChatActivity extends AppCompatActivity {
    EditText input;
    RecyclerView chatRecView;
    DatabaseReference dbChatRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        FloatingActionButton fab = findViewById(R.id.fab);

        chatRecView = findViewById(R.id.list_of_message);
        dbChatRef = FirebaseDatabase.getInstance().getReference("/chat");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(false);
        chatRecView.setHasFixedSize(true);
        chatRecView.setLayoutManager(layoutManager);

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(ChatActivity.this, MainActivity.class));
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabClick();
            }
        });
    }

    private void fabClick() {
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            Toast.makeText(getApplicationContext(), "Not logged in!", Toast.LENGTH_SHORT).show();
            onStart();
        } else {
            input = findViewById(R.id.input);
            // Read the input field and push a new instance
            // of ChatMessage to the Firebase database
            String message = input.getText().toString();
            if (message.isEmpty()) {
                input.setError("You can't post an empty Message. !!");
            } else {
                if (FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl() == null) {
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("chat")
                            .push()
                            .setValue(new ChatMessage(
                                    input.getText().toString(),
                                    FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getEmail(),
                                    "null")
                            );
                    Log.d("abcdabcd", String.valueOf(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()));
                    // Clear the input
                } else {
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("chat")
                            .push()
                            .setValue(new ChatMessage(input.getText().toString(),
                                    FirebaseAuth.getInstance()
                                            .getCurrentUser()
                                            .getDisplayName(),
                                    FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString())
                            );
                }
            }
            input.setText("");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        final FirebaseRecyclerAdapter<ChatMessage, ChatViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<ChatMessage, ChatViewHolder>(
                        ChatMessage.class,
                        R.layout.message_row,
                        ChatViewHolder.class,
                        dbChatRef) {
                    @Override
                    protected void populateViewHolder(ChatViewHolder viewHolder, ChatMessage model, int position) {
                        final String chatKey = getRef(position).getKey();

                        viewHolder.setMessageText(model.getMessageText());
                        viewHolder.setMessageTime(model.getMessageTime());
                        viewHolder.setUserName(model.getMessageUser());
                        viewHolder.setUserProfileImage(model.getProfileUrl(), getApplicationContext());
                    }
                };
        chatRecView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView messageTime;
        TextView messageText;
        ImageView userProfileImage;

        public ChatViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.message_user);
            messageTime = itemView.findViewById(R.id.message_time);
            messageText = itemView.findViewById(R.id.message_text);
            userProfileImage = itemView.findViewById(R.id.profile_image);
        }

        public void setUserName(String usr) {
            userName.setText(usr);
        }

        public void setMessageTime(long time) {
            messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)",
                    time));
        }

        public void setMessageText(String message) {
            messageText.setText(message);
        }

        public void setUserProfileImage(String profile_url, Context mctx) {
            if (profile_url == "null"){
                userProfileImage.setImageResource(R.drawable.ic_user_white);
            }else{
                Picasso.with(mctx)
                        .load(profile_url)
                        .into(userProfileImage);
            }

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp();
    }

}