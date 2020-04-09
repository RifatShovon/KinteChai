package com.example.kintechai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DeliveryActivity extends AppCompatActivity {

    public static List<CartItemModel> cartItemModelList;
    private RecyclerView deliveryRecyclerView;
    private Button changeORaddNewAddressBtn;
    public static final int SELECT_ADDRESS = 0;
    private TextView totalAmount;
    private TextView fullname;
    private TextView fullAddress;
    private TextView pincode;
    private Button continueBtn;
    private Dialog loadingDialog;
    private Dialog paymentMethodDialog;
    private ImageButton bkash;
    private ImageButton cashOnDelivery;
    public static Activity deliveryActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delivery");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));


        deliveryRecyclerView = findViewById(R.id.delivery_recyclerview);
        changeORaddNewAddressBtn = findViewById(R.id.change_or_add_address_btn);
        totalAmount = findViewById(R.id.total_cart_amount);
        fullname = findViewById(R.id.fullname);
        fullAddress = findViewById(R.id.address);
        pincode = findViewById(R.id.pincode);
        continueBtn = findViewById(R.id.cart_continue_btn);


        ////////////////////////////// loading dialog
        loadingDialog = new Dialog(DeliveryActivity.this);
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ////////////////////////////// loading dialog //////////////////////////////////////

        ////////////////////////////// payment method dialog
        paymentMethodDialog = new Dialog(DeliveryActivity.this);
        paymentMethodDialog.setContentView(R.layout.payment_method);
        paymentMethodDialog.setCancelable(true);
        paymentMethodDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
        paymentMethodDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bkash = paymentMethodDialog.findViewById(R.id.bkash);
        cashOnDelivery = paymentMethodDialog.findViewById(R.id.cod_btn);
        ////////////////////////////// payment method dialog //////////////////////////////////////


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deliveryRecyclerView.setLayoutManager(layoutManager);

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList, totalAmount, false);
        deliveryRecyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeORaddNewAddressBtn.setVisibility(View.VISIBLE);
        changeORaddNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressesIntent = new Intent(DeliveryActivity.this, MyAddressesActivity.class);
                myAddressesIntent.putExtra("MODE", SELECT_ADDRESS);
                startActivity(myAddressesIntent);
            }
        });


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliveryActivity = DeliveryActivity.this;
                paymentMethodDialog.show();
            }
        });

        bkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    paymentMethodDialog.dismiss();
                    loadingDialog.show();
                    BkashActivity();
            }
        });


        cashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paymentMethodDialog.dismiss();
                Intent intent = new Intent(DeliveryActivity.this,ConfirmOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    public void BkashActivity(){
        Intent intent = new Intent(this, BkashActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        fullname.setText(DBqueries.addressesModelList.get(DBqueries.selectedAddress).getFullname());
        fullAddress.setText(DBqueries.addressesModelList.get(DBqueries.selectedAddress).getAddress());
        pincode.setText(DBqueries.addressesModelList.get(DBqueries.selectedAddress).getPincode());
        super.onStart();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Inflate the menu; this adds items to the action bar if it is present.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            deliveryActivity = null;
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        loadingDialog.dismiss();
    }*/
}
