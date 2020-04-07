package com.example.kintechai;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.Viewholder> {

    private List<AboutUsModel> aboutUsModelList;

    public AboutUsAdapter(List<AboutUsModel> aboutUsModelList) {
        this.aboutUsModelList = aboutUsModelList;
    }

    @NonNull
    @Override
    public AboutUsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.about_us_layout, viewGroup, false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AboutUsAdapter.Viewholder viewholder, int position) {
        String title1 = aboutUsModelList.get(position).getTitle1();
        String title2 = aboutUsModelList.get(position).getTitle2();
        String title3 = aboutUsModelList.get(position).getTitle3();
        String id1 = aboutUsModelList.get(position).getId1();
        String id2 = aboutUsModelList.get(position).getId2();
        String id3 = aboutUsModelList.get(position).getId3();
        viewholder.setData(title1, title2, title3, id1, id2, id3);
    }

    @Override
    public int getItemCount() {
        return aboutUsModelList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        private TextView title11;
        private TextView title22;
        private TextView title33;
        private TextView id11;
        private TextView id22;
        private TextView id33;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            title11 = itemView.findViewById(R.id.title1);
            title22 = itemView.findViewById(R.id.title2);
            title33 = itemView.findViewById(R.id.title3);
            id11 = itemView.findViewById(R.id.id1);
            id22 = itemView.findViewById(R.id.id2);
            id33 = itemView.findViewById(R.id.id3);
        }

        private void setData(String titleName1, String titleName2, String titleName3, String titleId1, String titleId2, String titleId3){
            title11.setText(titleName1);
            title22.setText(titleName2);
            title33.setText(titleName3);
            id11.setText(titleId1);
            id22.setText(titleId2);
            id33.setText(titleId3);
        }
    }
}
