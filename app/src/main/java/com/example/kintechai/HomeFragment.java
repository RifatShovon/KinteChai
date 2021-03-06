package com.example.kintechai;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
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

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.kintechai.DBqueries.categoryModelList;
import static com.example.kintechai.DBqueries.firebaseFirestore;
import static com.example.kintechai.DBqueries.lists;
import static com.example.kintechai.DBqueries.loadCategories;
import static com.example.kintechai.DBqueries.loadFragmentData;
import static com.example.kintechai.DBqueries.loadedCategoriesNames;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    private ConnectivityManager connectivityManager;
    private NetworkInfo networkInfo;

    public static SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView categoryRecyclerView;
    private List<CategoryModel> categoryModelFakeList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private RecyclerView homePageRecyclerView;
    private List<HomePageModel> homePageModelFakeList = new ArrayList<>();
    private HomePageAdapter adapter;
    private ImageView noInternetConnection;
    private Button retryBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        noInternetConnection = view.findViewById(R.id.no_internet_connection);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        homePageRecyclerView = view.findViewById(R.id.home_page_recyclerview);
        retryBtn = view.findViewById(R.id.retry_btn);

        swipeRefreshLayout.setColorSchemeColors(getContext().getResources().getColor(R.color.colorAccent), getContext().getResources().getColor(R.color.colorAccent), getContext().getResources().getColor(R.color.colorAccent));

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(getContext());
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        homePageRecyclerView.setLayoutManager(testingLayoutManager);

        ////////////////// categories fake list
        categoryModelFakeList.add(new CategoryModel("null", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));
        categoryModelFakeList.add(new CategoryModel("", ""));

        ////////////////// categories fake list

        //////////// home page fake list
        List<SliderModel> sliderModelFakeList = new ArrayList<>();
        sliderModelFakeList.add(new SliderModel("null", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null", "#dfdfdf"));
        sliderModelFakeList.add(new SliderModel("null", "#dfdfdf"));

        List<HorizontalProductScrollModel> horizontalProductScrollModelFakeList = new ArrayList<>();
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));
        horizontalProductScrollModelFakeList.add(new HorizontalProductScrollModel("", "", "", "", ""));

        homePageModelFakeList.add(new HomePageModel(0, sliderModelFakeList));
        homePageModelFakeList.add(new HomePageModel(1, "", "#dfdfdf"));
        homePageModelFakeList.add(new HomePageModel(2, "", "#dfdfdf", horizontalProductScrollModelFakeList, new ArrayList<WishlistModel>()));
        homePageModelFakeList.add(new HomePageModel(3, "", "#dfdfdf", horizontalProductScrollModelFakeList));
        //////////// home page fake list

        categoryAdapter = new CategoryAdapter(categoryModelFakeList);

        adapter = new HomePageAdapter(homePageModelFakeList);

        connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            Main2Activity.drawer.setDrawerLockMode(0);
            noInternetConnection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homePageRecyclerView.setVisibility(View.VISIBLE);

            if (categoryModelList.size() == 0) {
                loadCategories(categoryRecyclerView, getContext());
            } else {
                categoryAdapter = new CategoryAdapter(categoryModelList);
                categoryAdapter.notifyDataSetChanged();
            }
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
       /* List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.forget_email_icon2,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.forget_email_icon1,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.logo_icon1,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.cart_black,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.custom_error_icon,"#63A1E7"));*/
            //////////Banner Slider

            ////////// Horizontal Product Layout
        /*List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.iphone,"Iphone X","64GB","BDT 80,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.samsung,"Samsung A4","16GB","BDT 14,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.xiaomi,"Xiaomi P6","32GB","BDT 19,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.alcatle,"Alcatle 8","64GB","BDT 22,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.sony,"Sony Xperia","16GB","BDT 27,500"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.nokia,"Nokia Z1","64GB","BDT 30,500"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.ruggear,"RugGear V4","128GB","BDT 23,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.pixel,"Pixel 2 XL","64GB","BDT 64,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.huwaei,"Huwaei P20","256GB","BDT 55,000"));*/
            ////////// Horizontal Product Layout

            ////////// ////////////////// //////
            if (lists.size() == 0) {
                loadedCategoriesNames.add("HOME");
                lists.add(new ArrayList<HomePageModel>());
                loadFragmentData(homePageRecyclerView, getContext(), 0, "Home");
            } else {
                adapter = new HomePageAdapter(lists.get(0));
                adapter.notifyDataSetChanged();
            }
            homePageRecyclerView.setAdapter(adapter);
        /*homePageModelList.add(new HomePageModel(0,sliderModelList));
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
        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#000000"));*/
            ///////// ////////////////// ///////
        } else {
            Main2Activity.drawer.setDrawerLockMode(1);
            categoryRecyclerView.setVisibility(View.GONE);
            homePageRecyclerView.setVisibility(View.GONE);
            Glide.with(this).load(R.drawable.no_internet_connection).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);
        }

        /////////////// refresh layout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                reloadPage();
            }
        });
        /////////////// refresh layout /////////////////

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reloadPage();
            }
        });
        return view;
    }

    private void reloadPage() {
        networkInfo = connectivityManager.getActiveNetworkInfo();
        /*categoryModelList.clear();
        lists.clear();
        loadedCategoriesNames.clear();*/
        DBqueries.clearData();
        if (networkInfo != null && networkInfo.isConnected() == true) {
            Main2Activity.drawer.setDrawerLockMode(0);
            noInternetConnection.setVisibility(View.GONE);
            retryBtn.setVisibility(View.GONE);
            categoryRecyclerView.setVisibility(View.VISIBLE);
            homePageRecyclerView.setVisibility(View.VISIBLE);

            categoryAdapter = new CategoryAdapter(categoryModelFakeList);
            adapter = new HomePageAdapter(homePageModelFakeList);
            categoryRecyclerView.setAdapter(categoryAdapter);
            homePageRecyclerView.setAdapter(adapter);

            loadCategories(categoryRecyclerView, getContext());

            loadedCategoriesNames.add("HOME");
            lists.add(new ArrayList<HomePageModel>());
            loadFragmentData(homePageRecyclerView, getContext(), 0, "Home");

        } else {
            Main2Activity.drawer.setDrawerLockMode(1);
            Toast.makeText(getContext(), "No Internet Connection!", Toast.LENGTH_SHORT).show();
            categoryRecyclerView.setVisibility(View.GONE);
            homePageRecyclerView.setVisibility(View.GONE);
            Glide.with(getContext()).load(R.drawable.no_internet_connection).into(noInternetConnection);
            noInternetConnection.setVisibility(View.VISIBLE);
            retryBtn.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);

        }
    }

}
