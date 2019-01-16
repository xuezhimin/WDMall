package com.qh.xuezhimin.wynshop.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.QueryCartBean;
import com.qh.xuezhimin.wynshop.view.AddSubLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询购物车适配器
 */
public class QueryCartAdapter extends RecyclerView.Adapter<QueryCartAdapter.MyViewHolder> {

    private Context mContext;
    private List<QueryCartBean> queryCartBeanList = new ArrayList<>();

    public QueryCartAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //集合添加数据
    public void addAll(List<QueryCartBean> data) {
        if (data != null) {
            queryCartBeanList.addAll(data);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.goods_query_cart_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final QueryCartBean queryCartBean = queryCartBeanList.get(i);
        //单选框
        myViewHolder.check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckBox checkBox = (CheckBox) view;
                queryCartBean.setSelected(checkBox.isChecked() ? 1 : 0);
                //计算价格
                calculatePrice();
            }
        });
        //图片
        myViewHolder.image.setImageURI(Uri.parse(queryCartBean.getPic()));
        //标题
        myViewHolder.txtTitle.setText(queryCartBean.getCommodityName());
        //价格
        myViewHolder.txtprice.setText("￥ " + queryCartBean.getPrice());
        //加减器
        myViewHolder.addSub.setCount(queryCartBean.getCount());
        myViewHolder.addSub.setAddSubListener(new AddSubLayout.AddSubListener() {
            @Override
            public void addSub(int count) {
                queryCartBean.setCount(count);
                //计算价格
                calculatePrice();
            }
        });

        //设置复选框是否选中  此属性 手动加上的
        if (queryCartBean.getSelected() == 0) {
            myViewHolder.check.setChecked(false);
        } else {
            myViewHolder.check.setChecked(true);
        }


    }

    /**
     * 计算总价
     */
    private void calculatePrice() {
        //设置初始值
        double totalPrice = 0;
        //循环遍历商品
        for (int i = 0; i < queryCartBeanList.size(); i++) {
            QueryCartBean queryCartBean = queryCartBeanList.get(i);
            //商品在选中的状态下
            if (queryCartBean.getSelected() == 1) {
                totalPrice = totalPrice + queryCartBean.getCount() * queryCartBean.getPrice();
            }

            if (totalPriceListener != null) {
                totalPriceListener.totalPrice(totalPrice);
            }

        }
    }

    /**
     * 全部选中或者取消
     *
     * @param isCheck
     */
    public void checkAll(boolean isCheck) {
        //循环商品
        for (int i = 0; i < queryCartBeanList.size(); i++) {
            QueryCartBean queryCartBean = queryCartBeanList.get(i);
            queryCartBean.setSelected(isCheck ? 1 : 0);
        }
        //刷新适配器
        notifyDataSetChanged();
        //计算总价
        calculatePrice();
    }

    @Override
    public int getItemCount() {
        return queryCartBeanList.size();
    }


    /**
     * 接口回调  实现商品的总价
     */
    public interface TotalPriceListener {
        void totalPrice(double totalPrice);
    }

    private TotalPriceListener totalPriceListener;

    public void setTotalPriceListener(TotalPriceListener totalPriceListener) {
        this.totalPriceListener = totalPriceListener;
    }


    /**
     * 内部类
     */
    class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox check;
        private TextView txtTitle;
        private TextView txtprice;
        private SimpleDraweeView image;
        private AddSubLayout addSub;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            check = itemView.findViewById(R.id.cart_goods_check_single);
            image = itemView.findViewById(R.id.cart_goods_item_img);
            txtTitle = itemView.findViewById(R.id.cart_goods_item_title);
            txtprice = itemView.findViewById(R.id.cart_goods_item_price);
            addSub = itemView.findViewById(R.id.add_sub_layout);

        }
    }


}
