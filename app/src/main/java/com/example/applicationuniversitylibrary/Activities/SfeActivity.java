package com.example.applicationuniversitylibrary.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.applicationuniversitylibrary.R;
import com.example.applicationuniversitylibrary.WebViews.SfeWebview;

public class SfeActivity extends AppCompatActivity {

    private TextView tvSfe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sfe);

        tvSfe = findViewById(R.id.tv_sfe_web);

        tvSfe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SfeActivity.this, SfeWebview.class);
                startActivity(i);
            }
        });

    }
}
