package com.example.applicationuniversitylibrary.Models;

import com.google.firebase.database.Exclude;

public class Upload {

    private String mName;
    private String mImageUrl;
    private String mKey;
    private String mAuthor;
    private String mDescription;
    private String mCategory;


    public Upload(){
        //needed
    }


    public Upload(String name, String imageUrl, String author, String description, String category){

        if (name.trim().equals("")){
            name = "No name";
        }
        mName = name;
        mImageUrl = imageUrl;
        mAuthor = author;
        mDescription = description;
        mCategory = category;


    }


    public String getName(){
        return mName;
    }

    public void setName(String name){

        mName = name;
    }


    public String getImageUrl(){
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl){
        mImageUrl = imageUrl;
    }

    public String getAuthor(){
        return mAuthor;
    }

    public void setAuthor(String author){
        mAuthor = author;
    }

    public String getDescription(){
        return mDescription;
    }
    public void setDescription(String description){

        mDescription = description;
    }

    public String getCategory(){
        return mCategory;
    }

    public void setCategory(String category){
        mCategory = category;
    }


    @Exclude
    public String getKey(){
        return mKey;
    }

    @Exclude
    public void setKey (String key){
        mKey = key;
    }
}

