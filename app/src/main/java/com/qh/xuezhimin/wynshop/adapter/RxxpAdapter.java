package com.qh.xuezhimin.wynshop.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.homegoods.CommodityList;

import java.util.ArrayList;
import java.util.List;

public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.MyViewHolder> {


    private Context mContext;
    private List<CommodityList> mListData = new ArrayList<>();

    public RxxpAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAll(List<CommodityList> data) {
        if (data != null) {
            mListData.addAll(data);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View inflate = View.inflate(mContext, R.layout.goods_rxxp_item, null);
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.goods_rxxp_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        String masterPic = mListData.get(i).getMasterPic();
        myViewHolder.imageView.setImageURI(Uri.parse(masterPic));
        myViewHolder.txtTitle.setText(mListData.get(i).getCommodityName());
        myViewHolder.txtPrice.setText("￥" + mListData.get(i).getPrice() + "");

    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }


    //内部类
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtTitle, txtPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.goods_rxxp_img);
            txtTitle = itemView.findViewById(R.id.goods_rxxp_title);
            txtPrice = itemView.findViewById(R.id.goods_rxxp_price);
        }
    }

}
