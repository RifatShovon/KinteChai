package com.example.kintechai;


import android.app.Dialog;
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
public class MyRewardsFragment extends Fragment {


    public MyRewardsFragment() {
        // Required empty public constructor
    }

    private RecyclerView rewardsRecyclerView;
    private Dialog loadingDialog;
    public static MyRewardsAdapter myRewardsAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_rewards, container, false);

        ////////////////////////////// loading dialog
        loadingDialog = new Dialog(getContext());
        loadingDialog.setContentView(R.layout.loading_progress_dialog);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.slider_background));
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();
        ////////////////////////////// loading dialog //////////////////////////////////////

        rewardsRecyclerView = view.findViewById(R.id.my_rewards_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewardsRecyclerView.setLayoutManager(layoutManager);
        myRewardsAdapter = new MyRewardsAdapter(DBqueries.rewardModelList, false);
        rewardsRecyclerView.setAdapter(myRewardsAdapter);
        if (DBqueries.rewardModelList.size() == 0){
            DBqueries.loadRewards(getContext(),loadingDialog, true);
        }else {
            loadingDialog.dismiss();
        }
        /*List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cashback", "till 2nd July, 2020", "GET 30% Cashback on any Product above BDT.1000/= and below BDT.5000/="));
        rewardModelList.add(new RewardModel("Discount", "till 10th March, 2020", "GET 20% OFF on any Product above BDT.2000/= and below BDT.4000/="));
        rewardModelList.add(new RewardModel("Buy 1 Get 1 Free", "till 2nd April, 2020", "Buy one and get one free!!"));
        rewardModelList.add(new RewardModel("Cashback", "till 4th May, 2020", "GET 20% Cashback on any Product above BDT.1500/= and below BDT.5000/="));
        rewardModelList.add(new RewardModel("Discount", "till 18th June, 2020", "GET 50% OFF on any Product above BDT.2000/= and below BDT.5000/="));
        rewardModelList.add(new RewardModel("Buy 1 Get 1 Free", "till 2nd July, 2020", "Buy one get one free!!"));
        rewardModelList.add(new RewardModel("20% OFF", "till 10th August, 2020", "GET 20% OFF on any Product above BDT.5000/= and below BDT.7000/="));
        rewardModelList.add(new RewardModel("Dicount", "till 26th September, 2020", "GET 20% OFF on any Product above BDT.1000/= and below BDT.5000/="));*/
        myRewardsAdapter.notifyDataSetChanged();
        return view;
    }
}
