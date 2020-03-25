package com.example.kintechai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = getIntent().getStringExtra("CategoryName");
        getSupportActionBar().setTitle(title);
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerview);

        //////////Banner Slider

        /*List<SliderModel> sliderModelList = new ArrayList<SliderModel>();

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

        //////////Banner Slider*/

        /*////////// Horizontal Product Layout

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

        ////////// Horizontal Product Layout*/

        ////////// ////////////////// //////

        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        /*homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#ff0000"));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.stripadd,"#000000"));
        homePageModelList.add(new HomePageModel(3, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(2, "Deals of the day!" , horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(1,R.drawable.iphone,"#DFC660"));*/

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Inflate the menu; this adds items to the action bar if it is present.
        int id = item.getItemId();

        if (id == R.id.main_search_icon) {
            //todo: search
            return true;
        }else if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
