package com.example.applicationuniversitylibrary.WebViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.applicationuniversitylibrary.R;

public class SfeWebview extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfe_webview);


        webView = findViewById(R.id.sfe_webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://logon.slc.co.uk/cas/login");

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
