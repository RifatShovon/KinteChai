package com.example.kintechai;

import androidx.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    private boolean successResponse = false;
    private FirebaseFirestore firebaseFirestore;
    private boolean allProductsAvailable = true;
    public static boolean getQtyIDs = true;


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
        firebaseFirestore = FirebaseFirestore.getInstance();
        getQtyIDs = true;

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

                getQtyIDs = false;
                Intent myAddressesIntent = new Intent(DeliveryActivity.this, MyAddressesActivity.class);
                myAddressesIntent.putExtra("MODE", SELECT_ADDRESS);
                startActivity(myAddressesIntent);
            }
        });


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deliveryActivity = DeliveryActivity.this;
                if (allProductsAvailable) {
                    paymentMethodDialog.show(); // never delete..
                }else {
                    ///// nothing
                }
            }
        });

        bkash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getQtyIDs = false;
                paymentMethodDialog.dismiss();
                loadingDialog.show();
                BkashActivity();
            }
        });


        cashOnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getQtyIDs = false;
                paymentMethodDialog.dismiss();
                Intent intent = new Intent(DeliveryActivity.this, ConfirmOrderActivity.class);
                startActivity(intent);
            }
        });
    }

    public void BkashActivity() {
        Intent intent = new Intent(this, BkashActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {

        //////////////////////accessing quantity
        if (getQtyIDs) {
            for (int x = 0; x < cartItemModelList.size() - 1; x++) {

                for (int y = 0;y < cartItemModelList.get(x).getProductQuantity();y++){
                    final String quantityDocumentName = UUID.randomUUID().toString().substring(0,20);

                    Map<String,Object> timestamp = new HashMap<>();
                    timestamp.put("time", FieldValue.serverTimestamp());
                    final int finalX = x;
                    final int finalY = y;
                    firebaseFirestore.collection("PRODUCTS").document(cartItemModelList.get(x).getProductID()).collection("QUANTITY").document(quantityDocumentName).set(timestamp)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    cartItemModelList.get(finalX).getQtyIDs().add(quantityDocumentName);

                                    if (finalY + 1 == cartItemModelList.get(finalX).getProductQuantity()){

                                        firebaseFirestore.collection("PRODUCTS").document(cartItemModelList.get(finalX).getProductID()).collection("QUANTITY").orderBy("time", Query.Direction.ASCENDING).limit(cartItemModelList.get(finalX).getStockQuantity()).get()
                                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                        if (task.isSuccessful()){
                                                            List<String> serverQuantity = new ArrayList<>();

                                                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                                                                serverQuantity.add(queryDocumentSnapshot.getId());
                                                            }

                                                            for (String qtyId : cartItemModelList.get(finalX).getQtyIDs()){

                                                                if (!serverQuantity.contains(qtyId)){
                                                                    Toast.makeText(DeliveryActivity.this, "Sorry ! all products may not be available in required quantity...",Toast.LENGTH_SHORT).show();
                                                                    allProductsAvailable = false;
                                                                }

                                                                if (serverQuantity.size() >= cartItemModelList.get(finalX).getStockQuantity()){
                                                                    firebaseFirestore.collection("PRODUCTS").document(cartItemModelList.get(finalX).getProductID()).update("in_stock",false);
                                                                }
                                                            }

                                                        }else {
                                                            String error = task.getException().getMessage();
                                                            Toast.makeText(DeliveryActivity.this,error,Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                });


                                    }
                                }
                            });
                }

               /* final int finalX = x;
                firebaseFirestore.collection("PRODUCTS").document(cartItemModelList.get(x).getProductID()).collection("QUANTITY").orderBy("available", Query.Direction.DESCENDING).limit(cartItemModelList.get(x).getProductQuantity()).get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {

                                    for (final QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                                        if ((boolean) queryDocumentSnapshot.get("available")) {

                                            firebaseFirestore.collection("PRODUCTS").document(cartItemModelList.get(finalX).getProductID()).collection("QUANTITY").document(queryDocumentSnapshot.getId()).update("available", false)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                cartItemModelList.get(finalX).getQtyIDs().add(queryDocumentSnapshot.getId());

                                                            } else {
                                                                String error = task.getException().getMessage();
                                                                Toast.makeText(DeliveryActivity.this, error, Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });


                                        } else {
                                            //// not available
                                            allProductsAvailable = false;
                                            Toast.makeText(DeliveryActivity.this, "all products may not be available at requiered quantity!", Toast.LENGTH_SHORT).show();
                                            break;
                                        }
                                    }

                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(DeliveryActivity.this, error, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });*/

            }
        } else {
            getQtyIDs = true;
        }
        //////////////////////accessing quantity////////////////////


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

    @Override
    protected void onPause() {
        super.onPause();
        loadingDialog.dismiss();

        if (getQtyIDs) {
            for (int x = 0; x < cartItemModelList.size() - 1; x++) {

                if (!successResponse) {
                    for (final String qtyID : cartItemModelList.get(x).getQtyIDs()) {
                        final int finalX = x;
                        firebaseFirestore.collection("PRODUCTS").document(cartItemModelList.get(x).getProductID()).collection("QUANTITY").document(qtyID).delete()  //.update("available", true);
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        if (qtyID.equals(cartItemModelList.get(finalX).getQtyIDs().get(cartItemModelList.get(finalX).getQtyIDs().size() - 1))){
                                            cartItemModelList.get(finalX).getQtyIDs().clear();

                                            firebaseFirestore.collection("PRODUCTS").document(cartItemModelList.get(finalX).getProductID()).collection("QUANTITY").orderBy("time", Query.Direction.ASCENDING).get()
                                                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                            if (task.isSuccessful()){

                                                                if (task.getResult().getDocuments().size() < cartItemModelList.get(finalX).getStockQuantity()){
                                                                    firebaseFirestore.collection("PRODUCTS").document(cartItemModelList.get(finalX).getProductID()).update("in_stock",true);
                                                                }

                                                            }else {
                                                                String error = task.getException().getMessage();
                                                                Toast.makeText(DeliveryActivity.this,error,Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });


                                        }
                                    }
                                });
                    }
                }else {
                    cartItemModelList.get(x).getQtyIDs().clear();
                }
            }
        }

    }
}


























