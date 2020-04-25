package com.example.applicationuniversitylibrary.Business;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationuniversitylibrary.Activities.PostReviewActivity;
import com.example.applicationuniversitylibrary.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.ImageViewHolder> {

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private Context mContext;
    private List<Business> mBusiness;





    public BusinessAdapter(Context context, List<Business> business) {
        mContext = context;
        mBusiness = business;

    }

    @NonNull
    @Override
    public BusinessAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.book_row_business ,parent, false);
        return new BusinessAdapter.ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessAdapter.ImageViewHolder holder, int position) {


        Business compCurrent = mBusiness.get(position);
        holder.mTextViewPostTitle.setText(compCurrent.getTitle());
        holder.mTextViewPostTopic.setText(compCurrent.getAuthor());
        holder.mTextViewPostDescription.setText(compCurrent.getDESCRIPTION());




        Picasso.with(mContext)
                .load(compCurrent.getImageUrl())
                .fit()
                .centerInside()
                .into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return mBusiness.size();
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

                    postDetailActivity.putExtra("title", mBusiness.get(position).getTitle());
                    postDetailActivity.putExtra("author", mBusiness.get(position).getAuthor());
                    postDetailActivity.putExtra("postImage", mBusiness.get(position).getImageUrl());
                    postDetailActivity.putExtra("description", mBusiness.get(position).getDESCRIPTION());
                    postDetailActivity.putExtra("postKey", mBusiness.get(position).getKey());


                    mContext.startActivity(postDetailActivity);
                }
            });


        }



    }

    public void filterList(ArrayList<Business> filteredList){
        mBusiness = filteredList;
        notifyDataSetChanged();
    }
}
