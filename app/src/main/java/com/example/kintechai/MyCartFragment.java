package com.example.kintechai;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {


    public MyCartFragment() {
        // Required empty public constructor
    }

    private RecyclerView cartItemsRecyclerview;
    private Button continueBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_cart, container, false);

        cartItemsRecyclerview = view.findViewById(R.id.cart_items_recyclerview);
        continueBtn = view.findViewById(R.id.cart_continue_btn);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        cartItemsRecyclerview.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0,R.drawable.product_image,"Motorcycle Bike KTM RC 390", 2, "BDT.5,00,000/=","BDT.5,20,000/=",1,0,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.product_image,"Motorcycle Bike KTM RC 390", 0, "BDT.5,00,000/=","BDT.5,20,000/=",1,1,0));
        cartItemModelList.add(new CartItemModel(0,R.drawable.product_image,"Motorcycle Bike KTM RC 390", 2, "BDT.5,00,000/=","BDT.5,20,000/=",1,2,0));
        cartItemModelList.add(new CartItemModel(1,"price (3 items)", "BDT.30000/=", "Free", "BDT.30000/=", "BDT.2000/="));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        cartItemsRecyclerview.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();


        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deliveryIntent = new Intent(getContext(),AddAddressActivity.class);
                getContext().startActivity(deliveryIntent);
            }
        });
        return view;
    }

}
