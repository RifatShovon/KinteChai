package com.example.kintechai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;
    public static List<WishlistModel> wishlistModelList;
    public static List<HorizontalProductScrollModel> horizontalProductScrollModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("title"));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorAccent));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        gridView = findViewById(R.id.grid_layout);

        int layout_code = getIntent().getIntExtra("layout_code",-1);

        if (layout_code == 0) {
            gridView.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            /*List<WishlistModel> wishlistModelList = new ArrayList<>();
            wishlistModelList.add(new WishlistModel(R.drawable.product_image,"Honda CB Trigger",1,"3.5",145,"BDT.5,55,0000/=","BDT.5,80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.iphone,"Iphone X",0,"4.5",280,"BDT.70,0000/=","BDT.88,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.laptop,"Lenovo Ideapad",2,"2.6",132,"BDT.55,0000/=","BDT.80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image,"Honda CB Trigger",4,"5",165,"BDT.5,55,0000/=","BDT.5,80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.laptop,"Lenovo Ideapad",1,"1.9",1451,"BDT.55,0000/=","BDT.80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.iphone,"Iphone X",0,"4.5",280,"BDT.70,0000/=","BDT.88,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.laptop,"Lenovo Ideapad",2,"2.6",132,"BDT.55,0000/=","BDT.80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image,"Honda CB Trigger",4,"5",165,"BDT.5,55,0000/=","BDT.5,80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.laptop,"Lenovo Ideapad",1,"1.9",1451,"BDT.55,0000/=","BDT.80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.iphone,"Iphone X",0,"4.5",280,"BDT.70,0000/=","BDT.88,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.laptop,"Lenovo Ideapad",2,"2.6",132,"BDT.55,0000/=","BDT.80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image,"Honda CB Trigger",4,"5",165,"BDT.5,55,0000/=","BDT.5,80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.laptop,"Lenovo Ideapad",1,"1.9",1451,"BDT.55,0000/=","BDT.80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.iphone,"Iphone X",0,"4.5",280,"BDT.70,0000/=","BDT.88,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.laptop,"Lenovo Ideapad",2,"2.6",132,"BDT.55,0000/=","BDT.80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.product_image,"Honda CB Trigger",4,"5",165,"BDT.5,55,0000/=","BDT.5,80,000/=","Cash On Delivery"));
            wishlistModelList.add(new WishlistModel(R.drawable.laptop,"Lenovo Ideapad",1,"1.9",1451,"BDT.55,0000/=","BDT.80,000/=","Cash On Delivery"));
*/
            WishlistAdapter adapter = new WishlistAdapter(wishlistModelList, false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        } else if (layout_code == 1) {

            gridView.setVisibility(View.VISIBLE);
            /*horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.iphone, "Iphone X", "64GB", "BDT 80,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.samsung, "Samsung A4", "16GB", "BDT 14,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.xiaomi, "Xiaomi P6", "32GB", "BDT 19,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.alcatle, "Alcatle 8", "64GB", "BDT 22,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.sony, "Sony Xperia", "16GB", "BDT 27,500"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.nokia, "Nokia Z1", "64GB", "BDT 30,500"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.ruggear, "RugGear V4", "128GB", "BDT 23,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.pixel, "Pixel 2 XL", "64GB", "BDT 64,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.huwaei, "Huwaei P20", "256GB", "BDT 55,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.iphone, "Iphone X", "64GB", "BDT 80,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.samsung, "Samsung A4", "16GB", "BDT 14,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.xiaomi, "Xiaomi P6", "32GB", "BDT 19,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.alcatle, "Alcatle 8", "64GB", "BDT 22,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.sony, "Sony Xperia", "16GB", "BDT 27,500"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.nokia, "Nokia Z1", "64GB", "BDT 30,500"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.ruggear, "RugGear V4", "128GB", "BDT 23,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.pixel, "Pixel 2 XL", "64GB", "BDT 64,000"));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.mipmap.huwaei, "Huwaei P20", "256GB", "BDT 55,000"));
*/
            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductLayoutAdapter);
            //gridProductLayoutAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
