package com.example.kintechai;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;

import java.util.HashMap;
import java.util.Map;

public class OrderDetailsActivity extends AppCompatActivity {

    private int position;

    private TextView title, price, quantity;
    private ImageView productImage, orderedIndicator, packedIndicator, shippedIndicator, deliveredIndicator;
    private ProgressBar O_P_progress, P_S_progress, S_D_progress;
    private TextView orderedTitle, packedTitle, shippedTitle, deliveredTitle;
    private TextView orderedDate, packedDate, shippedDate, deliveredDate;
    private TextView orderedBody, packedBody, shippedBody, deliveredBody;
    private int rating;
    private LinearLayout rateNowContainer;
    private TextView fullName, address, pincode;
    private TextView totalItems, totalItemsPrice, deliveryPrice, totalAmount, savedAmount;

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

        position = getIntent().getIntExtra("Position", -1);
        final MyOrderItemModel model = DBqueries.myOrderItemModelList.get(position);

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

        rateNowContainer = findViewById(R.id.rate_now_container);

        fullName = findViewById(R.id.fullname);
        address = findViewById(R.id.address);
        pincode = findViewById(R.id.pincode);

        totalItems = findViewById(R.id.total_items);
        totalItemsPrice = findViewById(R.id.total_items_price);
        deliveryPrice = findViewById(R.id.delivery_price);
        totalAmount = findViewById(R.id.total_price);
        savedAmount = findViewById(R.id.saved_amount);


        title.setText(model.getProductTitle());
        if (!model.getDiscountedPrice().equals("")) {
            price.setText("BDT." + model.getDiscountedPrice() + "/=");
        } else {
            price.setText("BDT." + model.getProductPrice() + "/=");
        }
        quantity.setText("Qty: " + String.valueOf(model.getProductQuantity()));
        Glide.with(this).load(model.getProductImage()).into(productImage);

        switch (model.getOrderStatus()) {

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

                if (model.getPackedDate().after(model.getOrderDate())) {

                    if (model.getShippedDate().after(model.getPackedDate())) {

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

                    } else {
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

                } else {
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
///// rating layout

        rating = model.getRating();
        setRating(rating);
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            final int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                    final DocumentReference documentReference = FirebaseFirestore.getInstance().collection("PRODUCTS").document(model.getProductId());
                    FirebaseFirestore.getInstance().runTransaction(new Transaction.Function<Object>() {
                        @Nullable
                        @Override
                        public Object apply(@NonNull Transaction transaction) throws FirebaseFirestoreException {

                            DocumentSnapshot documentSnapshot = transaction.get(documentReference);

                            if (rating != 0) {
                                Long increase = documentSnapshot.getLong(starPosition + 1 + "_star") + 1;
                                Long decrease = documentSnapshot.getLong(rating + 1 + "_star") - 1;
                                transaction.update(documentReference, starPosition + 1 + "_star", increase);
                                transaction.update(documentReference, rating + 1 + "_star", decrease);

                            } else {
                                Long increase = documentSnapshot.getLong(starPosition + 1 + "_star") + 1;
                                transaction.update(documentReference, starPosition + 1 + "_star", increase);
                            }
                            return null;
                        }
                    }).addOnSuccessListener(new OnSuccessListener<Object>() {
                        @Override
                        public void onSuccess(Object object) {
                            Map<String, Object> myRating = new HashMap<>();
                            if (DBqueries.myRatedIds.contains(model.getProductId())) {
                                myRating.put("rating_" + DBqueries.myRatedIds.indexOf(model.getProductId()), (long) starPosition + 1);
                            } else {
                                myRating.put("list_size", (long) DBqueries.myRatedIds.size() + 1);
                                myRating.put("product_ID_" + DBqueries.myRatedIds.size(), model.getProductId());
                                myRating.put("rating_" + DBqueries.myRatedIds.size(), (long) starPosition + 1);
                            }

                            FirebaseFirestore.getInstance().collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA").document("MY_RATINGS")
                                    .update(myRating).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        DBqueries.myOrderItemModelList.get(position).setRating(starPosition);
                                        if (DBqueries.myRatedIds.contains(model.getProductId())) {
                                            DBqueries.myRating.set(DBqueries.myRatedIds.indexOf(model.getProductId()), Long.parseLong(String.valueOf(starPosition + 1)));
                                        } else {
                                            DBqueries.myRatedIds.add(model.getProductId());
                                            DBqueries.myRating.add(Long.parseLong(String.valueOf(starPosition + 1)));
                                        }

                                    } else {
                                        String error = task.getException().getMessage();
                                        Toast.makeText(OrderDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    });
                }
            });
        }

        ///// rating layout////////////////

        fullName.setText(model.getFullName());
        address.setText(model.getAddress());
        pincode.setText(model.getPincode());

        totalItems.setText("Price(" + model.getProductQuantity() + " items)");

        Long totalItemsPriceValue;

        if (model.getDiscountedPrice().equals("")) {
            totalItemsPriceValue = model.getProductQuantity() * Long.valueOf(model.getProductPrice());
            totalItemsPrice.setText("BDT." + totalItemsPriceValue + "/=");

        } else {
            totalItemsPriceValue = model.getProductQuantity() * Long.valueOf(model.getDiscountedPrice());
            totalItemsPrice.setText("BDT." + totalItemsPriceValue + "/=");
        }
        if (model.getDeliveryPrice().equals("FREE")) {
            deliveryPrice.setText(model.getDeliveryPrice());
            totalAmount.setText(totalItemsPrice.getText());
        } else {
            deliveryPrice.setText("BDT." + model.getDeliveryPrice() + "/=");
            totalAmount.setText("BDT." + (totalItemsPriceValue + Long.valueOf(model.getDeliveryPrice())) + "/=");
        }
        if (!model.getCuttedPrice().equals("")) {
            if (!model.getDiscountedPrice().equals("")) {
                savedAmount.setText("You saved BDT." + model.getProductQuantity() * (Long.valueOf(model.getCuttedPrice()) - Long.valueOf(model.getDiscountedPrice())) + "/= on this order.");
            } else {
                savedAmount.setText("You saved BDT." + model.getProductQuantity() * (Long.valueOf(model.getCuttedPrice()) - Long.valueOf(model.getProductPrice())) + "/= on this order.");
            }
        } else {
            if (!model.getDiscountedPrice().equals("")) {
                savedAmount.setText("You saved BDT." + model.getProductQuantity() * (Long.valueOf(model.getProductPrice()) - Long.valueOf(model.getDiscountedPrice())) + "/= on this order.");
            } else {
                savedAmount.setText("You saved BDT.0/= on this order.");
            }
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

    private void setRating(int starPosition) {
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            ImageView starBtn = (ImageView) rateNowContainer.getChildAt(x);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#A79D9D")));
            if (x <= starPosition) {
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }
}
