package com.example.kintechai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationActions;
import android.view.textclassifier.TextLanguage;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.internal.StringResourceValueReader;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import static com.example.kintechai.DBqueries.cartItemModelList;
import static com.example.kintechai.DBqueries.cartList;

public class ConfirmOrderActivity extends AppCompatActivity {

    public static Activity confirmOrder;

    //public static List<CartItemModel> cartItemModelList;

    private ImageButton continueShoppingBtn;
    private TextView orderId;
    private boolean successResponse = false;
    public static boolean fromCart;
    private Dialog loadingDialog;

    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_order);

        continueShoppingBtn = findViewById(R.id.continue_shopping_btn);
        orderId = findViewById(R.id.order_id);

        String order_id = UUID.randomUUID().toString().substring(0,28);

        orderId.setText("Order ID: "+DeliveryActivity.order_id);

        ////////////////////////////// loading dialog
        loadingDialog = new Dialog(ConfirmOrderActivity.this);
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ////////////////////////////// loading dialog //////////////////////////////////////
        firebaseFirestore = FirebaseFirestore.getInstance();

        successResponse = true;

        DeliveryActivity.getQtyIDs = false;
        for (int x = 0; x < cartItemModelList.size() - 1; x++) {
            for (String qtyID : cartItemModelList.get(x).getQtyIDs()){
                firebaseFirestore.collection("PRODUCTS").document(cartItemModelList.get(x).getProductID()).collection("QUANTITY").document(qtyID).update("user_ID", FirebaseAuth.getInstance().getUid());
            }
        }

        if (Main2Activity.main2Activity != null){
            Main2Activity.main2Activity.finish();
            Main2Activity.main2Activity = null;
            Main2Activity.showCart = false;
        }else {
            Main2Activity.resetMainActivity = true;
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

        if (fromCart){
            loadingDialog.show();
            Map<String, Object> updateCartList = new HashMap<>();
            long cartListSize = 0;
            final List<Integer> indexList = new ArrayList<>();
            for (int x = 0; x < DBqueries.cartList.size(); x++) {
                if (!DeliveryActivity.cartItemModelList.get(x).isInStock()){
                    updateCartList.put("product_ID_" + cartListSize, cartItemModelList.get(x).getProductID());
                    cartListSize++;
                }else {
                    indexList.add(x);
                }
            }
            updateCartList.put("list_size", cartListSize);

            FirebaseFirestore.getInstance().collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA").document("MY_CART")
                    .set(updateCartList).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){

                        for (int x = 0;x < indexList.size();x++){
                            DBqueries.cartList.remove(indexList.get(x).intValue());
                            DBqueries.cartItemModelList.remove(indexList.get(x).intValue());
                            DBqueries.cartItemModelList.remove(DBqueries.cartItemModelList.size()-1);
                        }
                    }else {
                        String error = task.getException().getMessage();
                        Toast.makeText(ConfirmOrderActivity.this, error, Toast.LENGTH_SHORT).show();
                    }
                    loadingDialog.dismiss();
                }
            });
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

    @Override
    public void onBackPressed() {
        if (successResponse){
            finish();
            return;
        }
        super.onBackPressed();
    }
}