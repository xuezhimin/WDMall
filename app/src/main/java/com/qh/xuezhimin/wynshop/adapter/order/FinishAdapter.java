package com.qh.xuezhimin.wynshop.adapter.order;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.order.DetailList;

import java.util.ArrayList;
import java.util.List;

public class FinishAdapter extends RecyclerView.Adapter<FinishAdapter.MyViewHolder> {


    private Context mContext;
    private List<DetailList> mList = new ArrayList<>();

    public FinishAdapter(Context context) {
        this.mContext = context;
    }

    //集合添加数据
    public void addAll(List<DetailList> data) {
        if (data != null) {
            mList.addAll(data);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.goods_order_finish_five_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        DetailList detailList = mList.get(i);
        String[] images = detailList.getCommodityPic().split(",");
        myViewHolder.image.setImageURI(Uri.parse(images[1]));
        myViewHolder.txtTitle.setText(detailList.getCommodityName());
        myViewHolder.txtprice.setText("￥ " + detailList.getCommodityPrice());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 内部类
     */

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private TextView txtprice;
        private SimpleDraweeView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.order_finish_goods_item_img);
            txtTitle = itemView.findViewById(R.id.order_finish_goods_item_title);
            txtprice = itemView.findViewById(R.id.order_finish_goods_item_price);
        }
    }


}
