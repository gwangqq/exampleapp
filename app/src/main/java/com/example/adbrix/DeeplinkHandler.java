package com.example.adbrix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.igaworks.v2.core.AdBrixRm;

public class DeeplinkHandler extends AppCompatActivity implements AdBrixRm.DeferredDeeplinkListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deeplink_handler);
        onNewIntent(DeeplinkHandler.this.getIntent());
        AdBrixRm.setDeferredDeeplinkListener(this);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //==========do something using intent============
        Uri uri = Uri.parse(intent.getDataString());
        String data = uri.getQueryParameter("string");
        Log.d("deeplink url ", "onNewIntent(Intent intent) ::: " + data);

        Intent startResultActivityIntent = new Intent(getApplicationContext(), ResultActivity.class);
        startResultActivityIntent.putExtra("search_string", data);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startResultActivityIntent);
        DeeplinkHandler.this.finish();
        //===============================================
        setIntent(intent);
        AdBrixRm.deeplinkEvent(DeeplinkHandler.this);
    }

    @Override
    public void onReceiveDeferredDeeplink(String urlStr) {
        // Adbrix SDK returns deferred deeplink url.
        // You can open specific activity, change view or do whatever you want with this 'urlStr' value.

        Log.d("Deferred Deeplink url: ", urlStr);



//        Log.d("deeplink url ", "onNewIntent(Intent intent) ::: " + data);
//        Intent startResultActivityIntent = new Intent(getApplicationContext(), ResultActivity.class);
//        startResultActivityIntent.putExtra("search_string", data);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(startResultActivityIntent);
//        DeeplinkHandler.this.finish();
    }
}