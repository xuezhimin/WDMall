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

import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.GoodsDetailsBean;

import java.util.ArrayList;
import java.util.List;

public class DetailsGoodsAdapter extends RecyclerView.Adapter<DetailsGoodsAdapter.MyViewHolder> {

    private Context mContext;
    private List<GoodsDetailsBean> mList = new ArrayList<>();

    public DetailsGoodsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAll(List<GoodsDetailsBean> list) {
        if (list != null) {
            this.mList.addAll(list);
        }
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.goods_details_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        GoodsDetailsBean goodsDetailsBean = mList.get(i);
        String[] pistures = goodsDetailsBean.getPicture().split(",");
        myViewHolder.imageView.setImageURI(Uri.parse(String.valueOf(pistures)));
        myViewHolder.txtTitle.setText(goodsDetailsBean.getCommodityName());
        myViewHolder.txtPrice.setText(goodsDetailsBean.getPrice());
        myViewHolder.txtKg.setText(goodsDetailsBean.getWeight());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //内部类
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtTitle, txtPrice, txtKg;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.details_goods_itroduce_img);
            txtTitle = itemView.findViewById(R.id.details_goods_itroduce_title);
            txtPrice = itemView.findViewById(R.id.details_goods_itroduce_price);
            txtKg = itemView.findViewById(R.id.details_goods_itroduce_kg);
        }
    }

}
