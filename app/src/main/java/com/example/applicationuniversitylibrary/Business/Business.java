package com.example.applicationuniversitylibrary.Business;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;

public class Business {
    private String Title;
    private String DESCRIPTION;
    private String mAuthor;
    private String mImageUrl;
    private String uName;
    private String userPhoto;
    private String mKey;
    private Object timeStamp ;



    public Business() {
        //needed

    }




    public Business(String title, String imageUrl, String DESCRIPTION, String author) {
        if (title.trim().equals("")){
            title = "No title";
        }
        this.Title = title;
        this.mImageUrl = imageUrl;
        this.mAuthor = author;
        this.DESCRIPTION = DESCRIPTION;

        this.timeStamp = ServerValue.TIMESTAMP;



    }




    public String getTitle() {
        return Title;
    }


    public void setTitle(String title) {
        Title = title;
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

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getUsername() {
        return uName;
    }

    public void setUsername(String username) {
        this.uName = username;
    }


    @Exclude
    public String getKey(){
        return mKey;
    }

    @Exclude
    public void setKey (String key){
        mKey = key;
    }


    public void setTimeStamp(Object timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Object getTimeStamp() {
        return timeStamp;
    }

}
