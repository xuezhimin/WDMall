package com.qh.xuezhimin.wynshop.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.CircleBean;
import com.qh.xuezhimin.wynshop.util.DateUtils;
import com.qh.xuezhimin.wynshop.util.StringUtils;
import com.qh.xuezhimin.wynshop.view.SpacingItemDecoration;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.MyHolder> {

    private Context mContext;
    private List<CircleBean> mList = new ArrayList<>();

    public CircleAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addAll(List<CircleBean> list) {
        if (list != null) {
            this.mList.addAll(list);
        }
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.circle_item, viewGroup, false);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myHolder, final int i) {
        CircleBean circleBean = mList.get(i);
        myHolder.avatar.setImageURI(Uri.parse(circleBean.getHeadPic()));
        myHolder.nickname.setText(circleBean.getNickName());
        try {
            myHolder.time.setText(DateUtils.dateFormat(new Date(circleBean.getCreateTime()), DateUtils.MINUTE_PATTERN));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        myHolder.text.setText(circleBean.getContent());
        if (StringUtils.isEmpty(circleBean.getImage())) {
            myHolder.recyclerViewGrid.setVisibility(View.GONE);
        } else {
            myHolder.recyclerViewGrid.setVisibility(View.VISIBLE);
            String[] images = circleBean.getImage().split(",");
            //列数
            int colNum;
            if (images.length == 1) {
                colNum = 1;
            } else if (images.length == 2 || images.length == 4) {
                colNum = 2;
            } else {
                colNum = 3;
            }
            myHolder.gridLayoutManager.setSpanCount(colNum);
            myHolder.circleImageAdapter.clear();
            myHolder.circleImageAdapter.addAll(Arrays.asList(images));
            myHolder.circleImageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void clear() {
        mList.clear();
    }

    //内部类
    class MyHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView avatar;
        TextView nickname;
        TextView time;
        TextView text;
        RecyclerView recyclerViewGrid;
        GridLayoutManager gridLayoutManager;
        CircleImageAdapter circleImageAdapter;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.image_head);
            text = itemView.findViewById(R.id.txt_title);
            nickname = itemView.findViewById(R.id.nickname);
            time = itemView.findViewById(R.id.txt_time);
            recyclerViewGrid = itemView.findViewById(R.id.grid_view_img);
            circleImageAdapter = new CircleImageAdapter();
            int space = mContext.getResources().getDimensionPixelSize(R.dimen.dip_10);//图片间距
            gridLayoutManager = new GridLayoutManager(mContext, 3);
            recyclerViewGrid.addItemDecoration(new SpacingItemDecoration(space));
            recyclerViewGrid.setLayoutManager(gridLayoutManager);
            recyclerViewGrid.setAdapter(circleImageAdapter);
        }
    }

}
