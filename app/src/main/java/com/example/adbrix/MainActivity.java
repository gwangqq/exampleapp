package com.example.adbrix;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.igaworks.v2.core.AdBrixRm;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button testBtn;
    TextInputEditText testEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testBtn = (Button) findViewById(R.id.btn_test);
        testEditText = (TextInputEditText) findViewById(R.id.et_test);
        testBtn.setOnClickListener(this);
        AdBrixRm.setEventUploadCountInterval(AdBrixRm.AdBrixEventUploadCountInterval.MIN);
    }

    @Override
    public void onClick(View v) {
        String inputString = testEditText.getText().toString().trim();
        if (isEmpty(inputString)) {
            return;
        } else {
            AdBrixRm.AttrModel eventAttr = new AdBrixRm.AttrModel().setAttrs("search_string", inputString);
            AdBrixRm.event("search_event", eventAttr);
            Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
            intent.putExtra("search_string", inputString);
            startActivity(intent);
        }
    }

    public boolean isEmpty(String cityName){
        if (cityName.equals("") || cityName.equals(null)) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Warning").setMessage("please type something");
            builder.setPositiveButton("okay", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    // alert dialog ok button click -> no event need
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return true;
        } else {
            return false;
        }
    }
}









