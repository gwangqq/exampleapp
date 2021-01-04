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

import org.json.JSONException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button testBtn;
    TextInputEditText testEditText;
    Button loginBtn;
    Button purchaseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testBtn = (Button) findViewById(R.id.btn_test);
        testEditText = (TextInputEditText) findViewById(R.id.et_test);
        loginBtn = (Button) findViewById(R.id.btn_login);
        purchaseBtn = (Button) findViewById(R.id.btn_purchase);

        loginBtn.setOnClickListener(this);
        testBtn.setOnClickListener(this);
        purchaseBtn.setOnClickListener(this);
        AdBrixRm.setEventUploadCountInterval(AdBrixRm.AdBrixEventUploadCountInterval.MIN);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_test: {
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
                break;
            }
            case R.id.btn_login: {
                sendLoginLog();
                Toast.makeText(this, "login test", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.btn_purchase:{
                purchaseEvent();
                Toast.makeText(this, "purchase test", Toast.LENGTH_LONG).show();
                break;
            }
        }


    }

    // purchase postback test
    void purchaseEvent() {

        // 상품 카테고리 설정
        AdBrixRm.CommerceCategoriesModel productCategory = new AdBrixRm.CommerceCategoriesModel()
                .setCategory("category 1")
                .setCategory("category 2")
                .setCategory("category 3")
                .setCategory("category 4")
                .setCategory("category 5");

        //상품 추가 정보 설정
        AdBrixRm.AttrModel productAttr1 = new AdBrixRm.AttrModel()
                .setAttrs("size", 25)
                .setAttrs("color", "blue")
                .setAttrs("vip", false);

        AdBrixRm.AttrModel productAttr2 = new AdBrixRm.AttrModel()
                .setAttrs("size", 33)
                .setAttrs("color", "black")
                .setAttrs("vip", true);

        // 이벤트 추가 정보
        AdBrixRm.AttrModel purchaseAttr = new AdBrixRm.AttrModel()
                .setAttrs("grade", "vip")
                .setAttrs("howmany_buy", 36)
                .setAttrs("discount", true);

        AdBrixRm.CommonProperties.Purchase purchaseat = new AdBrixRm.CommonProperties.Purchase()
                .setAttrModel(purchaseAttr);

        // 상품 정보 모델 생성
        AdBrixRm.CommerceProductModel productModel_1 =
                new AdBrixRm.CommerceProductModel().setProductID("5385487400")
                        .setProductName("product1")
                        .setPrice(10000.00)
                        .setQuantity(1)
                        .setDiscount(1000.00)
                        .setCurrency(AdBrixRm.Currency.KR_KRW)
                        .setCategory(productCategory)
                        .setAttrModel(productAttr1);

        // 상품 정보 모델 생성
        AdBrixRm.CommerceProductModel productModel_2 =
                new AdBrixRm.CommerceProductModel().setProductID("145099811")
                        .setProductName("product2")
                        .setPrice(15500.00)
                        .setQuantity(3)
                        .setDiscount(1200.00)
                        .setCurrency(AdBrixRm.Currency.KR_KRW)
                        .setCategory(productCategory)
                        .setAttrModel(productAttr2);

        // 상품 정보 모델 ArrayList 생성
        ArrayList productModelArrayList = new ArrayList<>();
        productModelArrayList.add(productModel_1);
        productModelArrayList.add(productModel_2);

        // 상품 결제 - 모바일 결제
        AdBrixRm.Common.purchase("290192012", productModelArrayList, 22500.0,0.00, 3500.00, AdBrixRm.CommercePaymentMethod.MobilePayment,purchaseat);

    }

    // login test
    public String getUserType(){
        return "멘티";
    }

    public void sendLoginLog() {

        AdBrixRm.login("test_user_id");
        AdBrixRm.UserProperties userProperties = new AdBrixRm.UserProperties();
        userProperties.setAttrs("user_type", getUserType());
        AdBrixRm.saveUserProperties(userProperties);
    }


    public boolean isEmpty(String cityName) {
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









