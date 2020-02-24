package com.example.kintechai;


import android.graphics.Color;
import android.os.Bundle;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    ////////// Banner Slider

    private ViewPager bannerSliderViewPager;
    private List<SliderModel> sliderModelList;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 2000;
    final private long PERIOD_TIME = 2000;

    ////////// Banner Slider


    ////////// Strip Ad

    private ImageView stripAdImage;
    private ConstraintLayout stripAdContainer;

    ////////// Strip Ad

    ////////// Horizontal Product Layout

    private TextView horizontalLayoutTitle;
    private Button horizontalLayoutViewAllBtn;
    private RecyclerView horizontalRecyclerView;

    ////////// Horizontal Product Layout


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        List<CategoryModel> categoryModelList = new ArrayList<CategoryModel>();
        categoryModelList.add(new CategoryModel("link","Home"));
        categoryModelList.add(new CategoryModel("link","Electronics"));
        categoryModelList.add(new CategoryModel("link","Appliances"));
        categoryModelList.add(new CategoryModel("link","Furniture"));
        categoryModelList.add(new CategoryModel("link","Fashion"));
        categoryModelList.add(new CategoryModel("link","Toys"));
        categoryModelList.add(new CategoryModel("link","Sports"));
        categoryModelList.add(new CategoryModel("link","Wall Art"));
        categoryModelList.add(new CategoryModel("link","Books"));
        categoryModelList.add(new CategoryModel("link","Shoes"));

        categoryAdapter = new CategoryAdapter(categoryModelList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        //////////Banner Slider

        bannerSliderViewPager = view.findViewById(R.id.banner_slider_view_pager);

        sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.custom_error_icon,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.forget_email_icon2,"#63A1E7"));

        sliderModelList.add(new SliderModel(R.mipmap.forget_email_icon1,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.logo_icon1,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.ic_launcher,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.cart_black,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.profile_placeholder,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.home_icon,"#63A1E7"));

        sliderModelList.add(new SliderModel(R.mipmap.custom_error_icon,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.forget_email_icon2,"#63A1E7"));
        sliderModelList.add(new SliderModel(R.mipmap.forget_email_icon1,"#63A1E7"));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderModelList);
        bannerSliderViewPager.setAdapter(sliderAdapter);
        bannerSliderViewPager.setClipToPadding(false);
        bannerSliderViewPager.setPageMargin(20);

        bannerSliderViewPager.setCurrentItem(currentPage);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == ViewPager.SCROLL_STATE_IDLE){
                    pageLooper();
                }
            }
        };
        bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

        startBannerSlideShow();

        bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerSlideShow();
                if (event.getAction() == MotionEvent.ACTION_UP){
                    startBannerSlideShow();
                }
                return false;
            }
        });

        //////////Banner Slider


        ////////// Strip Ad

        stripAdImage = view.findViewById(R.id.strip_ad_image);
        stripAdContainer = view.findViewById(R.id.strip_ad_container);

        stripAdImage.setImageResource(R.drawable.stripadd);
        stripAdContainer.setBackgroundColor(Color.parseColor("#E07816"));

        ////////// Strip Ad


        ////////// Horizontal Product Layout

        horizontalLayoutTitle = view.findViewById(R.id.horizontal_scroll_layout_title);
        horizontalLayoutViewAllBtn = view.findViewById(R.id.horizontal_scroll_view_all_button);
        horizontalRecyclerView = view.findViewById(R.id.horizontal_scroll_layout_recyclerview);

        List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.image2,"Iphone X","64GB","BDT 80,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.samsung,"Samsung A4","16GB","BDT 14,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.xiaomi,"Xiaomi P6","32GB","BDT 19,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.alcatle,"Alcatle 8","64GB","BDT 22,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.sony,"Sony Xperia","16GB","BDT 27,500"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.nokia,"Nokia Z1","64GB","BDT 30,500"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.ruggear,"RugGear V4","128GB","BDT 23,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.pixel,"Pixel 2 XL","64GB","BDT 64,000"));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.huwaei,"Huwaei P20","256GB","BDT 55,000"));

        HorizontalProductScrollAdapter horizontalProductScrollAdapter = new HorizontalProductScrollAdapter(horizontalProductScrollModelList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalRecyclerView.setLayoutManager(linearLayoutManager);

        horizontalRecyclerView.setAdapter(horizontalProductScrollAdapter);
        horizontalProductScrollAdapter.notifyDataSetChanged();

        ////////// Horizontal Product Layout

        return view;
    }
    //////////Banner Slider

    private  void pageLooper(){
        if (currentPage == sliderModelList.size() - 2){
            currentPage = 2;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
        if (currentPage == 1){
            currentPage = sliderModelList.size() - 3;
            bannerSliderViewPager.setCurrentItem(currentPage,false);
        }
    }

    private void startBannerSlideShow(){
        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= sliderModelList.size()){
                    currentPage = 1;
                }
                bannerSliderViewPager.setCurrentItem(currentPage++,true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        },DELAY_TIME,PERIOD_TIME);
    }

    private void stopBannerSlideShow(){
        timer.cancel();
    }

    //////////Banner Slider


}
