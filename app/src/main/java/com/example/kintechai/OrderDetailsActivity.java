package com.example.kintechai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class OrderDetailsActivity extends AppCompatActivity {

    private int position;

    private TextView title,price,quantity;
    private ImageView productImage,orderedIndicator,packedIndicator,shippedIndicator,deliveredIndicator;
    private ProgressBar O_P_progress,P_S_progress,S_D_progress;
    private TextView orderedTitle, packedTitle, shippedTitle, deliveredTitle;
    private TextView orderedDate, packedDate, shippedDate, deliveredDate;
    private TextView orderedBody, packedBody, shippedBody, deliveredBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Order details");
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        position = getIntent().getIntExtra("Position",-1);
        MyOrderItemModel model = DBqueries.myOrderItemModelList.get(position);

        title = findViewById(R.id.product_title);
        price = findViewById(R.id.product_price);
        quantity = findViewById(R.id.product_quantity);

        productImage = findViewById(R.id.product_image);
        orderedIndicator = findViewById(R.id.ordered_indicator);
        packedIndicator = findViewById(R.id.packed_indicator);
        shippedIndicator = findViewById(R.id.shipping_indicator);
        deliveredIndicator = findViewById(R.id.delivered_indicator);

        O_P_progress = findViewById(R.id.ordered_packed_progress);
        P_S_progress = findViewById(R.id.packed_shipping_progress);
        S_D_progress = findViewById(R.id.shipping_delivered_progress);

        orderedTitle = findViewById(R.id.ordered_title);
        packedTitle = findViewById(R.id.packed_title);
        shippedTitle = findViewById(R.id.shipping_title);
        deliveredTitle = findViewById(R.id.delivered_title);

        orderedDate = findViewById(R.id.ordered_date);
        packedDate = findViewById(R.id.packed_date);
        shippedDate = findViewById(R.id.shipping_date);
        deliveredDate = findViewById(R.id.delivered_date);

        orderedBody = findViewById(R.id.ordered_body);
        packedBody = findViewById(R.id.packed_body);
        shippedBody = findViewById(R.id.shipping_body);
        deliveredBody = findViewById(R.id.delivered_body);


        title.setText(model.getProductTitle());
        if (model.getDiscountedPrice() != null){
            price.setText(model.getDiscountedPrice());
        }else {
            price.setText(model.getProductPrice());
        }
        quantity.setText(String.valueOf(model.getProductQuantity()));
        Glide.with(this).load(model.getProductImage()).into(productImage);

        switch (model.getOrderStatus()){

            case "Ordered":
                //orderedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                orderedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                orderedDate.setText(String.valueOf(model.getOrderDate()));

                P_S_progress.setVisibility(View.GONE);
                S_D_progress.setVisibility(View.GONE);
                O_P_progress.setVisibility(View.GONE);

                packedIndicator.setVisibility(View.GONE);
                packedBody.setVisibility(View.GONE);
                packedDate.setVisibility(View.GONE);
                packedTitle.setVisibility(View.GONE);

                shippedIndicator.setVisibility(View.GONE);
                shippedBody.setVisibility(View.GONE);
                shippedDate.setVisibility(View.GONE);
                shippedTitle.setVisibility(View.GONE);

                deliveredIndicator.setVisibility(View.GONE);
                deliveredBody.setVisibility(View.GONE);
                deliveredDate.setVisibility(View.GONE);
                deliveredTitle.setVisibility(View.GONE);
                break;

            case "Packed":
                //orderedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                orderedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                orderedDate.setText(String.valueOf(model.getOrderDate()));

                //packedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                packedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                packedDate.setText(String.valueOf(model.getPackedDate()));

                O_P_progress.setVisibility(View.VISIBLE);
                O_P_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));

                P_S_progress.setVisibility(View.GONE);
                S_D_progress.setVisibility(View.GONE);

                shippedIndicator.setVisibility(View.GONE);
                shippedBody.setVisibility(View.GONE);
                shippedDate.setVisibility(View.GONE);
                shippedTitle.setVisibility(View.GONE);

                deliveredIndicator.setVisibility(View.GONE);
                deliveredBody.setVisibility(View.GONE);
                deliveredDate.setVisibility(View.GONE);
                deliveredTitle.setVisibility(View.GONE);
                break;

            case "Shipped":
                //orderedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                orderedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                orderedDate.setText(String.valueOf(model.getOrderDate()));

                //packedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                packedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                packedDate.setText(String.valueOf(model.getPackedDate()));

                //shippedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                shippedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                shippedDate.setText(String.valueOf(model.getShippedDate()));

                O_P_progress.setVisibility(View.VISIBLE);
                O_P_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                P_S_progress.setVisibility(View.VISIBLE);
                P_S_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));

                S_D_progress.setVisibility(View.GONE);

                deliveredIndicator.setVisibility(View.GONE);
                deliveredBody.setVisibility(View.GONE);
                deliveredDate.setVisibility(View.GONE);
                deliveredTitle.setVisibility(View.GONE);
                break;

            case "Delivered":
                //orderedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                orderedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                orderedDate.setText(String.valueOf(model.getOrderDate()));

                //packedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                packedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                packedDate.setText(String.valueOf(model.getPackedDate()));

                //shippedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                shippedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                shippedDate.setText(String.valueOf(model.getShippedDate()));

                //deliveredIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                deliveredIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                deliveredDate.setText(String.valueOf(model.getDeliveredDate()));

                O_P_progress.setVisibility(View.VISIBLE);
                O_P_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                P_S_progress.setVisibility(View.VISIBLE);
                P_S_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                S_D_progress.setVisibility(View.VISIBLE);
                S_D_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));

                break;

            case "Cancelled":

                if (model.getPackedDate().after(model.getOrderDate())){

                    if (model.getShippedDate().after(model.getPackedDate())){

                        //orderedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                        orderedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                        orderedDate.setText(String.valueOf(model.getOrderDate()));

                        //packedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                        packedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                        packedDate.setText(String.valueOf(model.getPackedDate()));

                        //shippedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                        shippedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                        shippedDate.setText(String.valueOf(model.getShippedDate()));

                        //deliveredIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                        deliveredIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#95A5A6")));
                        deliveredDate.setText(String.valueOf(model.getCancelledDate()));
                        deliveredTitle.setText("Cancelled");
                        deliveredBody.setText("Your Order Has Been Cancelled.");

                        O_P_progress.setVisibility(View.VISIBLE);
                        O_P_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                        P_S_progress.setVisibility(View.VISIBLE);
                        P_S_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                        S_D_progress.setVisibility(View.VISIBLE);
                        S_D_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));

                    }else {
                        //orderedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                        orderedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                        orderedDate.setText(String.valueOf(model.getOrderDate()));

                        //packedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                        packedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                        packedDate.setText(String.valueOf(model.getPackedDate()));

                        //shippedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                        shippedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#95A5A6")));
                        shippedDate.setText(String.valueOf(model.getCancelledDate()));
                        shippedTitle.setText("Cancelled");
                        shippedBody.setText("Your Order Has Been Cancelled.");

                        O_P_progress.setVisibility(View.VISIBLE);
                        O_P_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                        P_S_progress.setVisibility(View.VISIBLE);
                        P_S_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));

                        S_D_progress.setVisibility(View.GONE);

                        deliveredIndicator.setVisibility(View.GONE);
                        deliveredBody.setVisibility(View.GONE);
                        deliveredDate.setVisibility(View.GONE);
                        deliveredTitle.setVisibility(View.GONE);
                    }

                }else {
                    //orderedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.successGreen)));
                    orderedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));
                    orderedDate.setText(String.valueOf(model.getOrderDate()));

                    //packedIndicator.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                    packedIndicator.setImageTintList(ColorStateList.valueOf(Color.parseColor("#95A5A6")));
                    packedDate.setText(String.valueOf(model.getCancelledDate()));
                    packedTitle.setText("Cancelled");
                    packedBody.setText("Your Order Has Been Cancelled.");

                    O_P_progress.setVisibility(View.VISIBLE);
                    O_P_progress.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#00ff00")));

                    P_S_progress.setVisibility(View.GONE);
                    S_D_progress.setVisibility(View.GONE);

                    shippedIndicator.setVisibility(View.GONE);
                    shippedBody.setVisibility(View.GONE);
                    shippedDate.setVisibility(View.GONE);
                    shippedTitle.setVisibility(View.GONE);

                    deliveredIndicator.setVisibility(View.GONE);
                    deliveredBody.setVisibility(View.GONE);
                    deliveredDate.setVisibility(View.GONE);
                    deliveredTitle.setVisibility(View.GONE);
                }

                break;

        }


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
