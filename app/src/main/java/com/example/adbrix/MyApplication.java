package com.example.adbrix;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.animation.AccelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;

import com.igaworks.v2.core.AdBrixRm;
import com.igaworks.v2.core.application.AbxActivityHelper;
import com.igaworks.v2.core.application.AbxActivityLifecycleCallbacks;

public class MyApplication extends Application  {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        AbxActivityHelper.initializeSdk(MyApplication.this, "nvg08XJm8E6CeuSCZtc4OA", "dJdmYpId1kivO1RkHvvnOw");
        if (Build.VERSION.SDK_INT >= 14) {
            registerActivityLifecycleCallbacks(new AbxActivityLifecycleCallbacks());
        }
//        AdBrixRm.setDeferredDeeplinkListener(this);
        MyApplication.context = getApplicationContext();
    }
    public static Context ApplicationContext(){
        return MyApplication.context;
    }

//    @Override
//    public void onReceiveDeferredDeeplink(String urlStr) {
//        Log.d("Deferred Deeplink url==", urlStr);
//        Uri uri = Uri.parse(urlStr);
//        String string = uri.getQueryParameter("string");
//        Log.d("Deferred Deeplink", " string == " + string);
//        Intent intent = new Intent(ApplicationContext(), ResultActivity.class);
//        intent.putExtra("string", string);
//        startActivity(intent);
//    }
}