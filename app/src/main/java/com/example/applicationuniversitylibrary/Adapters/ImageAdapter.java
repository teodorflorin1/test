package com.example.applicationuniversitylibrary.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationuniversitylibrary.Activities.ChatActivity;
import com.example.applicationuniversitylibrary.Activities.PostReviewActivity;
import com.example.applicationuniversitylibrary.R;
import com.example.applicationuniversitylibrary.Models.Upload;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;
    private OnItemClickListener mListener;

    public ImageAdapter(Context context, List<Upload> uploads){
        mContext = context;
        mUploads = uploads;
    }
    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());
        holder.textViewAuthor.setText(uploadCurrent.getAuthor());
        holder.textViewDescription.setText(uploadCurrent.getDescription());
        holder.textViewCategory.setText(uploadCurrent.getCategory());


        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView textViewName, textViewAuthor, textViewDescription, textViewCategory;
        public ImageView imageView;
        public ImageButton imgBtn, imgBtnRew;


        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            imageView = itemView.findViewById(R.id.image_view_upload);
            textViewAuthor = itemView.findViewById(R.id.tv_auth_name);
            textViewDescription = itemView.findViewById(R.id.tv_description);
            textViewCategory = itemView.findViewById(R.id.tv_category);



            imgBtn = itemView.findViewById(R.id.img_btn);
            imgBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent postDetailActivity = new Intent(mContext, ChatActivity.class);
                    int position =getAdapterPosition();

                    postDetailActivity.putExtra("title", mUploads.get(position).getName());
                    postDetailActivity.putExtra("postImage", mUploads.get(position).getImageUrl());
                    postDetailActivity.putExtra("description", mUploads.get(position).getDescription());
                    postDetailActivity.putExtra("postKey", mUploads.get(position).getKey());
                    postDetailActivity.putExtra("userName", mUploads.get(position).getName());



                    mContext.startActivity(postDetailActivity);
                }
            });


            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
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
            if (mListener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){

                    switch (item.getItemId()){
                        case 1:
                            mListener.onWhatEverClick(position);
                            return true;

                        case 2:
                            mListener.onDeleteClick(position);
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

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;

    }

    public void filterList(ArrayList<Upload> filteredList){
        mUploads = filteredList;
        notifyDataSetChanged();
    }
}
