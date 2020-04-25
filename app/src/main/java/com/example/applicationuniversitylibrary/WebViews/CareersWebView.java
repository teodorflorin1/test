package com.example.applicationuniversitylibrary.WebViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.applicationuniversitylibrary.R;

public class CareersWebView extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_careers_web_view);



        webView = findViewById(R.id.careers_webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.roehampton.ac.uk/careers/");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if(webView.canGoBack()){
            webView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}
