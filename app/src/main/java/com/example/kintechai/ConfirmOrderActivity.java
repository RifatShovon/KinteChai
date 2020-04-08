package com.example.kintechai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class ConfirmOrderActivity extends AppCompatActivity {

    public static Activity confirmOrder;

    private ImageButton continueShoppingBtn;
    private TextView orderId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        continueShoppingBtn = findViewById(R.id.continue_shopping_btn);
        orderId = findViewById(R.id.order_id);
        if (Main2Activity.main2Activity != null){
            Main2Activity.main2Activity.finish();
            Main2Activity.main2Activity = null;
            Main2Activity.showCart = false;
        }
        if (ProductDetailsActivity.productDetailsActivity != null){
            ProductDetailsActivity.productDetailsActivity.finish();
            ProductDetailsActivity.productDetailsActivity = null;
        }
        if (BkashActivity.bkashActivity != null){
            BkashActivity.bkashActivity.finish();
            BkashActivity.bkashActivity = null;
        }
        if (DeliveryActivity.deliveryActivity != null){
            DeliveryActivity.deliveryActivity.finish();
            DeliveryActivity.deliveryActivity = null;
        }
        continueShoppingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                /*Intent intent = new Intent(ConfirmOrderActivity.this, DeliveryActivity.class);
                startActivity(intent);*/
            }
        });
    }
}
