package com.example.applicationuniversitylibrary.Models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.ServerValue;


public class Blog {
    private String Title;
    private String DESCRIPTION;
    private String mTopic;
    private String mImageUrl;
    private String uName;
    private String userPhoto;
    private String mKey;
    private Object timeStamp ;



    public Blog() {
    // empty constructor needed

    }




    public Blog(String title, String imageUrl, String DESCRIPTION, String topic) {
        if (title.trim().equals("")){
            title = "No title";
        }
        this.Title = title;
        this.mImageUrl = imageUrl;
        this.mTopic = topic;
        this.DESCRIPTION = DESCRIPTION;

        this.timeStamp = ServerValue.TIMESTAMP;



    }



 // getters and setters
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


    public String getTopic(){
        return mTopic;
    }

    public void setTopic(String topic){
        mTopic = topic;
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
