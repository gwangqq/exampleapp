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
    String result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String string = intent.getExtras().getString("search_string");
        testTextView = (TextView) findViewById(R.id.tv_test);

        if (string != null) {
            testTextView.setText(string);
        } else {
            onNewIntent(ResultActivity.this.getIntent());
            testTextView.setText(result);
        }


    }
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //==========do something using intent============
        Uri uri = Uri.parse(intent.getDataString());
        result = uri.getQueryParameter("string");
        Log.d("deeplink url ", "onNewIntent(Intent intent) :::" + result);
        //===============================================
        setIntent(intent);
        AdBrixRm.deeplinkEvent(ResultActivity.this);
    }
}



/*





    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // ============= do something using intent ===============
        Uri uri = Uri.parse(intent.getDataString());
        result = uri.getQueryParameter("string");
        Log.d("example", "onNewIntent(Intent intent) ::: " + result);
        // =======================================================
        setIntent(intent);
        AdBrixRm.deeplinkEvent(ResultActivity.this);
    }

*/

