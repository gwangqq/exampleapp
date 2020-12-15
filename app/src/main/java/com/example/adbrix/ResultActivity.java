package com.example.adbrix;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.igaworks.v2.core.AdBrixRm;
public class ResultActivity extends AppCompatActivity {
    TextView testTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String result = intent.getExtras().getString("search_string");
        testTextView = (TextView) findViewById(R.id.tv_test);
        testTextView.setText(result);
    }
}

