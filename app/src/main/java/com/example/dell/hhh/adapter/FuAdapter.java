package com.example.dell.hhh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.hhh.R;
import com.example.dell.hhh.bean.Bean;
import com.example.dell.hhh.bean.FuBean;
import com.example.dell.hhh.bean.Sean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


import butterknife.BindView;
import butterknife.ButterKnife;

import static java.util.regex.Pattern.compile;

public class FuAdapter extends RecyclerView.Adapter<FuAdapter.SubViewHolder> {

    Context context;
    List<Sean.NewslistBean> beanList;


    public FuAdapter(Context context, List<Sean.NewslistBean> list) {
        this.context = context;
        beanList = new ArrayList<>();
    }

    public void setData(List<Sean.NewslistBean> setList) {
        this.beanList = setList;
        notifyDataSetChanged();
    }

 /*   //接收数据
    public void setData(Context context, List<FuBean.ResultsBean> beanList) {
        this.beanList = beanList;
        this.context = context;
    }*/



    @NonNull
    @Override
    public SubViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        SubViewHolder subViewHolder = new SubViewHolder(inflate);
        return subViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubViewHolder subViewHolder, int i) {

        subViewHolder.text1.setText(beanList.get(i).getTitle());
        Glide.with(context).load(beanList.get(i).getPicUrl()).into(subViewHolder.image);

      /*  String images = beanList.get(i).getImages();
        Pattern pen = compile("\\|");
        String [] img = pen.split(images);

        Glide.with(context).load(img[0]).into(subViewHolder.image);*/
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }



    class SubViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.text1)
        TextView text1;

        public SubViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }

}
