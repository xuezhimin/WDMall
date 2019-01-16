package com.qh.xuezhimin.wynshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.activity.DetailsActivity;
import com.qh.xuezhimin.wynshop.bean.KeyWordBean;

import java.util.ArrayList;
import java.util.List;


/**
 * 关键字搜索的适配器
 */
public class SearchKeyWordAdapter extends XRecyclerView.Adapter<SearchKeyWordAdapter.MyViewHolder> {

    private Context mContext;
    //list集合
    private List<KeyWordBean> mListData = new ArrayList<>();

    public SearchKeyWordAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //添加集合
    public void addAll(List<KeyWordBean> data) {
        if (data != null) {
            mListData.addAll(data);
        }
    }

    //数据清空
    public void clearData() {
        mListData.clear();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.goods_search_key_word_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        String masterPic = mListData.get(i).getMasterPic();
        final KeyWordBean keyWordBean = mListData.get(i);
        myViewHolder.imageView.setImageURI(Uri.parse(masterPic));
        myViewHolder.txtTitle.setText(mListData.get(i).getCommodityName());
        myViewHolder.txtPrice.setText("￥" + mListData.get(i).getPrice() + "");

        //条目点击 获取商品id
        if (onItemClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(mListData.get(i).getCommodityId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    /**
     * 内部类
     */
    class MyViewHolder extends XRecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView txtTitle, txtPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.goods_search_key_word_img);
            txtTitle = itemView.findViewById(R.id.goods_search_key_word_title);
            txtPrice = itemView.findViewById(R.id.goods_search_key_word_price);
        }
    }

    /**
     * 条目点击进入详情页面 接口回调
     */

    //定义接口
    public interface OnItemClickListener {
        void onItemClick(int cid);
    }

    //方法名
    private OnItemClickListener onItemClickListener;

    //set方法      设置点击方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


}
