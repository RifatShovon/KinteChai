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
public class AboutUsFragment extends Fragment {


    public AboutUsFragment() {
        // Required empty public constructor
    }

    private RecyclerView aboutUsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        aboutUsRecyclerView = view.findViewById(R.id.about_us_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        aboutUsRecyclerView.setLayoutManager(layoutManager);

        List<AboutUsModel> aboutUsModelList = new ArrayList<>();
        aboutUsModelList.add(new AboutUsModel("Md. Oahiduzzaman Mondol Zihad", "Md. Zishan Ali", "Md. Rifat hasan","1711855642","1712587042","1712162642"));

        AboutUsAdapter aboutUsAdapter = new AboutUsAdapter(aboutUsModelList);
        aboutUsRecyclerView.setAdapter(aboutUsAdapter);
        aboutUsAdapter.notifyDataSetChanged();

        return view;
    }

}
