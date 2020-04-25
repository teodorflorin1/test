package com.example.applicationuniversitylibrary.Gaming;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationuniversitylibrary.Activities.PostReviewActivity;
import com.example.applicationuniversitylibrary.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class GamingAdapter extends RecyclerView.Adapter<GamingAdapter.ImageViewHolder> {

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private Context mContext;
    private List<Gaming> mGaming;



    public GamingAdapter(Context context, List<Gaming> gaming) {
        mContext = context;
        mGaming = gaming;

    }

    @NonNull
    @Override
    public GamingAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.book_row_gaming, parent, false);
        return new GamingAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GamingAdapter.ImageViewHolder holder, int position) {
        Gaming compCurrent = mGaming.get(position);
        holder.mTextViewPostTitle.setText(compCurrent.getTitle());
        holder.mTextViewPostTopic.setText(compCurrent.getAuthor());
        holder.mTextViewPostDescription.setText(compCurrent.getDESCRIPTION());
        //holder.mTextViewUserName.setText(blogCurrent.getUsername());


        Picasso.with(mContext)
                .load(compCurrent.getImageUrl())
                .fit()
                .centerInside()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mGaming.size();
    }


    public class ImageViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextViewPostTitle, mTextViewPostTopic, mTextViewPostDescription;
        public ImageView imageView, imageReview;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextViewPostTitle = itemView.findViewById(R.id.tv_post_title);
            imageView = itemView.findViewById(R.id.post_image);
            mTextViewPostTopic = itemView.findViewById(R.id.tv_post_topic);
            mTextViewPostDescription = itemView.findViewById(R.id.tv_post_description);
            imageReview = itemView.findViewById(R.id.img_btn_review_G);

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();






            imageReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postDetailActivity = new Intent(mContext, PostReviewActivity.class);
                    int position = getAdapterPosition();

                    postDetailActivity.putExtra("title", mGaming.get(position).getTitle());
                    postDetailActivity.putExtra("author", mGaming.get(position).getAuthor());
                    postDetailActivity.putExtra("postImage", mGaming.get(position).getImageUrl());
                    postDetailActivity.putExtra("description", mGaming.get(position).getDESCRIPTION());
                    postDetailActivity.putExtra("postKey", mGaming.get(position).getKey());


                    mContext.startActivity(postDetailActivity);
                }
            });


        }


    }


    public void filterList(ArrayList<Gaming> filteredList){
        mGaming = filteredList;
        notifyDataSetChanged();
    }
}


