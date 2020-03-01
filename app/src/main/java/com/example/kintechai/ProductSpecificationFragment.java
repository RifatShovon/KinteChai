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
public class ProductSpecificationFragment extends Fragment {


    public ProductSpecificationFragment() {
        // Required empty public constructor
    }

    private RecyclerView productSpecificationRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_specification, container, false);

        productSpecificationRecyclerView = view.findViewById(R.id.product_specification_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        productSpecificationRecyclerView.setLayoutManager(linearLayoutManager);

        List<ProductSpecificationModel> productSpecificationModelList = new ArrayList<>();
        productSpecificationModelList.add(new ProductSpecificationModel("BRAND","LENOVO"));
        productSpecificationModelList.add(new ProductSpecificationModel("MODEL","THINKPAD"));
        productSpecificationModelList.add(new ProductSpecificationModel("SERIES","THINKPAD"));
        productSpecificationModelList.add(new ProductSpecificationModel("DIMENSIONS(mm)","338.00 x 226.00 x 38.10"));
        productSpecificationModelList.add(new ProductSpecificationModel("WEIGHT(kg)","1.36"));
        productSpecificationModelList.add(new ProductSpecificationModel("COLORS","BLACK"));
        productSpecificationModelList.add(new ProductSpecificationModel("OPERATING SYSTEM","WINDOWS 10"));
        productSpecificationModelList.add(new ProductSpecificationModel("BATTERY CAPACITY(WHR)","23.5"));
        productSpecificationModelList.add(new ProductSpecificationModel("BATTERY CELL","3"));
        productSpecificationModelList.add(new ProductSpecificationModel("SIZE","14.00 INCH"));
        productSpecificationModelList.add(new ProductSpecificationModel("RESOLUTION","1920x1080 PIXELS"));
        productSpecificationModelList.add(new ProductSpecificationModel("PROCESSOR","INTEL CORE i7 6600U"));
        productSpecificationModelList.add(new ProductSpecificationModel("BASE CLOCK SPEED","3.4 GHz"));
        productSpecificationModelList.add(new ProductSpecificationModel("CACHE","4MB"));
        productSpecificationModelList.add(new ProductSpecificationModel("RAM","12GB"));
        productSpecificationModelList.add(new ProductSpecificationModel("GRAPHIC PROCESSOR","INTEL HD GRAPHIC 520"));
        productSpecificationModelList.add(new ProductSpecificationModel("DEDICATED GRAPHIC MEMORY TYPE","DDR4"));
        productSpecificationModelList.add(new ProductSpecificationModel("HARD DISK","NO"));
        productSpecificationModelList.add(new ProductSpecificationModel("SSD","256GB"));


        ProductSpecificationAdapter productSpecificationAdapter = new ProductSpecificationAdapter(productSpecificationModelList);
        productSpecificationRecyclerView.setAdapter(productSpecificationAdapter);
        productSpecificationAdapter.notifyDataSetChanged();

        return view;
    }

}
