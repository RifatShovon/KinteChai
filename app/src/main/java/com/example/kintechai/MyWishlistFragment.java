package com.example.kintechai;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishlistFragment extends Fragment {


    public MyWishlistFragment() {
        // Required empty public constructor
    }

    private RecyclerView wishlistRecyclerview;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_wishlist, container, false);

        wishlistRecyclerview = view.findViewById(R.id.my_wishlist_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishlistRecyclerview.setLayoutManager(linearLayoutManager);

        List<WishlistModel> wishlistModelList = new ArrayList<>();
        wishlistModelList.add(new WishlistModel(R.drawable.product_image,"Honda CB Trigger",1,"3.5",145,"BDT.5,55,0000/=","BDT.5,80,000/=","Cash On Delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.iphone,"Iphone X",0,"4.5",280,"BDT.70,0000/=","BDT.88,000/=","Cash On Delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.laptop,"Lenovo Ideapad",2,"2.6",132,"BDT.55,0000/=","BDT.80,000/=","Cash On Delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.product_image,"Honda CB Trigger",4,"5",165,"BDT.5,55,0000/=","BDT.5,80,000/=","Cash On Delivery"));
        wishlistModelList.add(new WishlistModel(R.drawable.laptop,"Lenovo Ideapad",1,"1.9",1451,"BDT.55,0000/=","BDT.80,000/=","Cash On Delivery"));

        WishlistAdapter wishlistAdapter = new WishlistAdapter(wishlistModelList);
        wishlistRecyclerview.setAdapter(wishlistAdapter);
        wishlistAdapter.notifyDataSetChanged();

        return view;
    }

}
