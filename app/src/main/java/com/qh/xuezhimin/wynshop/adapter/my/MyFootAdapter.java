package com.qh.xuezhimin.wynshop.adapter.my;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.my.FootBean;

import java.util.ArrayList;
import java.util.List;

public class MyFootAdapter extends RecyclerView.Adapter<MyFootAdapter.MyViewHolder> {

    private Context mContext;
    private List<FootBean> mList = new ArrayList<>();

    public MyFootAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //集合添加数据
    public void addAll(List<FootBean> data) {
        if (data != null) {
            mList.addAll(data);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.goods_my_foot_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 内部类
     */

    class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView simpleDraweeView;
        private TextView txtTitle, txtPrice, txtSee;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.my_foot_img_item);
            txtTitle = itemView.findViewById(R.id.my_foot_title_item);
            txtPrice = itemView.findViewById(R.id.my_foot_price_item);
            txtSee = itemView.findViewById(R.id.my_foot_see_item);
        }
    }

}
