package com.example.applicationuniversitylibrary.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applicationuniversitylibrary.Activities.PostDetailActivity;
import com.example.applicationuniversitylibrary.Activities.PostReviewActivity;
import com.example.applicationuniversitylibrary.Models.Blog;
import com.example.applicationuniversitylibrary.R;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ImageViewHolder> {
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private Context mContext;
    private List<Blog> mBlog;
    private BlogAdapter.OnItemClickListener bListener;




    public BlogAdapter(Context context, List<Blog> blog) {
        mContext = context;
        mBlog = blog;

    }

    @NonNull
    @Override
    public BlogAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.blog_row, parent, false);
        return new BlogAdapter.ImageViewHolder(v);
    }

    //displaying the data and its attributes

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, final int position) {

        Blog blogCurrent = mBlog.get(position);
        holder.mTextViewPostTitle.setText(blogCurrent.getTitle());
        holder.mTextViewPostTopic.setText(blogCurrent.getTopic());
        holder.mTextViewPostDescription.setText(blogCurrent.getDESCRIPTION());



        Picasso.with(mContext)
                .load(blogCurrent.getImageUrl())
                .fit()
                .centerInside()
                .into(holder.imageView);




    }


    @Override
    public int getItemCount() {
        return mBlog.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView mTextViewPostTitle, mTextViewPostTopic, mTextViewPostDescription, mTextViewUserName;
        public ImageView imageView;


        // set up the holder for images and their attributes
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewPostTitle = itemView.findViewById(R.id.tv_post_title);
            imageView = itemView.findViewById(R.id.post_image);
            mTextViewPostTopic = itemView.findViewById(R.id.tv_post_topic);
            mTextViewPostDescription = itemView.findViewById(R.id.tv_post_description);

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();




            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postDetailActivity = new Intent(mContext, PostDetailActivity.class);
                    int position =getAdapterPosition();

                    postDetailActivity.putExtra("title", mBlog.get(position).getTitle());
                    postDetailActivity.putExtra("postImage", mBlog.get(position).getImageUrl());
                    postDetailActivity.putExtra("description", mBlog.get(position).getDESCRIPTION());
                    postDetailActivity.putExtra("postKey", mBlog.get(position).getKey());
                    postDetailActivity.putExtra("userName", mBlog.get(position).getUsername());



                   mContext.startActivity(postDetailActivity);
                }
            });


        }


        @Override
        public void onClick(View v) {

            if (bListener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    bListener.onItemClick(position);
                }
            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatever = menu.add(android.view.Menu.NONE, 1, 1, "Do Whatever");
            MenuItem delete = menu.add(Menu.NONE, 2, 2, "Delete");

            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            if (bListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {

                    switch (item.getItemId()) {
                        case 1:
                            bListener.onWhatEverClick(position);
                            return true;

                        case 2:
                            bListener.onDeleteClick(position);
                            return true;
                    }
                }
            }
            return false;
        }
    }

    public interface OnItemClickListener{
        void onItemClick(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);

    }

    public void setOnItemClickListener(BlogAdapter.OnItemClickListener listener){
        bListener = listener;

    }


    public void filterList(ArrayList<Blog>filteredList){

        mBlog = filteredList;
        notifyDataSetChanged();

    }

}
