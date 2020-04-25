package com.example.applicationuniversitylibrary.Models;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import androidx.annotation.Nullable;
import com.example.applicationuniversitylibrary.R;


public class Pop extends Activity {


 // popup view
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popup_social);

       DisplayMetrics dm = new DisplayMetrics();
       getWindowManager().getDefaultDisplay().getMetrics(dm);

       //initialize width and height of the popup
         int width = dm.widthPixels;
         int height = dm.heightPixels;

       getWindow().setLayout((int)(width*.8), (int)(height*.6));



    }
}
