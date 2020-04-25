package com.example.applicationuniversitylibrary.Economics;

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

class EconomicsAdapter extends RecyclerView.Adapter<EconomicsAdapter.ImageViewHolder> {

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private Context mContext;
    private List<Economics> mEconomics;



    public EconomicsAdapter(Context context, List<Economics> economics) {
        mContext = context;
        mEconomics = economics;

    }

    @NonNull
    @Override
    public EconomicsAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.book_row_economics ,parent, false);
        return new EconomicsAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EconomicsAdapter.ImageViewHolder holder, int position) {

        Economics compCurrent = mEconomics.get(position);
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
        return mEconomics.size();
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

                    postDetailActivity.putExtra("title", mEconomics.get(position).getTitle());
                    postDetailActivity.putExtra("author", mEconomics.get(position).getAuthor());
                    postDetailActivity.putExtra("postImage", mEconomics.get(position).getImageUrl());
                    postDetailActivity.putExtra("description", mEconomics.get(position).getDESCRIPTION());
                    postDetailActivity.putExtra("postKey", mEconomics.get(position).getKey());


                    mContext.startActivity(postDetailActivity);
                }
            });


        }


    }

    public void filterList(ArrayList<Economics> filteredList){
        mEconomics = filteredList;
        notifyDataSetChanged();
    }
}
