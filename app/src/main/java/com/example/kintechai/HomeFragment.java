package com.example.kintechai;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }

    private RecyclerView categoryRecyclerView;
    private CategoryAdapter categoryAdapter;
    private RecyclerView testing;
    private List<CategoryModel> categoryModelList;
    private FirebaseFirestore firebaseFirestore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryModelList = new ArrayList<CategoryModel>();

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("CATEGORIES").orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(),documentSnapshot.get("categoryName").toString()));
                            }
                            categoryAdapter.notifyDataSetChanged();
                        }else {
                            String error = task.getException().getMessage();
                            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        /*categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Electronics"));
        categoryModelList.add(new CategoryModel("link","Appliances"));
        categoryModelList.add(new CategoryModel("link","Furniture"));
        categoryModelList.add(new CategoryModel("link","Fashion"));
        categoryModelList.add(new CategoryModel("link","Toys"));
        categoryModelList.add(new CategoryModel("link","Sports"));
        categoryModelList.add(new CategoryModel("link","Wall Art"));
        categoryModelList.add(new CategoryModel("link","Books"));
        categoryModelList.add(new CategoryModel("link","Shoes"));*/



        //////////Banner Slider

        List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.forget_email_icon2,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.forget_email_icon1,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.logo_icon1,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.cart_black,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.custom_error_icon,"#63A1E7"));

        //////////Banner Slider

        ////////// Horizontal Product Layout

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.iphone,"Iphone X","64GB","BDT 80,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.samsung,"Samsung A4","16GB","BDT 14,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.xiaomi,"Xiaomi P6","32GB","BDT 19,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.alcatle,"Alcatle 8","64GB","BDT 22,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.sony,"Sony Xperia","16GB","BDT 27,500"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.nokia,"Nokia Z1","64GB","BDT 30,500"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.ruggear,"RugGear V4","128GB","BDT 23,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.pixel,"Pixel 2 XL","64GB","BDT 64,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.huwaei,"Huwaei P20","256GB","BDT 55,000"));

        ////////// Horizontal Product Layout

        ////////// ////////////////// //////

        testing = view.findViewById(R.id.home_page_recyclerview);
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        testing.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#ff0000"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.iphone,"#DFC660"));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#000000"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.iphone,"#DFC660"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#000000"));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.iphone,"#DFC660"));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#000000"));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        testing.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        ///////// ////////////////// ///////

        return view;
    }

}
