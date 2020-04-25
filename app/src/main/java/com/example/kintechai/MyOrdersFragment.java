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
public class MyOrdersFragment extends Fragment {


    public MyOrdersFragment() {
        // Required empty public constructor
    }

    private RecyclerView myOrdersRecyclerView;
    public static MyOrderAdapter myOrderAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        myOrdersRecyclerView = view.findViewById(R.id.my_orders_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrdersRecyclerView.setLayoutManager(layoutManager);

        /*List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.product_image, 2, "Honda CB Trigger", "Delivered on Mon, 15th JAN 2019"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.laptop, 1, "Lenovo IdeaPad", "Delivered on Mon, 15th JAN 2019"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.iphone, 0, "Iphone X", "Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.product_image, 4, "Hondda CB Trigger", "Delivered on Mon, 15th JAN 2019"));*/

        myOrderAdapter = new MyOrderAdapter(DBqueries.myOrderItemModelList);
        myOrdersRecyclerView.setAdapter(myOrderAdapter);


        DBqueries.loadOrders(getContext(), myOrderAdapter);

        myOrderAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        myOrderAdapter.notifyDataSetChanged();
    }
}
