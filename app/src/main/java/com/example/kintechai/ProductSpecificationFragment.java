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
        productSpecificationModelList.add(new ProductSpecificationModel(0,"GENERAL"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"BRAND","LENOVO"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"MODEL","THINKPAD"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"SERIES","THINKPAD"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"DIMENSIONS(mm)","338.00 x 226.00 x 38.10"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"WEIGHT(kg)","1.36"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"COLORS","BLACK"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"OPERATING SYSTEM","WINDOWS 10"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"BATTERY CAPACITY(WHR)","23.5"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"BATTERY CELL","3"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"DISPLAY"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"SIZE","14.00 INCH"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RESOLUTION","1920x1080 PIXELS"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"PROCESSOR"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"PROCESSOR","INTEL CORE i7 6600U"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"BASE CLOCK SPEED","3.4 GHz"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"CACHE","4MB"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"MEMORY"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"RAM","12GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"GRAPHICS"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"GRAPHIC PROCESSOR","INTEL HD GRAPHIC 520"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"DEDICATED GRAPHIC MEMORY TYPE","DDR4"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"STORAGE"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"HARD DISK","NO"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"SSD","256GB"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"CONNECTIVITY"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"WI-FI","802.11 AC"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"BLUETOOTH","4"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"INPUTS"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"WEB CAMERA","YES"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"FINGER PRINT SENSOR","NO"));
        productSpecificationModelList.add(new ProductSpecificationModel(0,"POTS AND SLOTS"));
        productSpecificationModelList.add(new ProductSpecificationModel(1,"NUMBER OF USB PORTS","3"));


        ProductSpecificationAdapter productSpecificationAdapter = new ProductSpecificationAdapter(productSpecificationModelList);
        productSpecificationRecyclerView.setAdapter(productSpecificationAdapter);
        productSpecificationAdapter.notifyDataSetChanged();

        return view;
    }

}
