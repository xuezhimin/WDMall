package com.qh.xuezhimin.wynshop.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qh.xuezhimin.wynshop.R;

import java.util.ArrayList;
import java.util.List;

public class CircleImageAdapter extends RecyclerView.Adapter<CircleImageAdapter.MyHodler> {

    private List<String> mList = new ArrayList<>();

    public void addAll(List<String> list) {
        mList.addAll(list);
    }


    @NonNull
    @Override
    public MyHodler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(), R.layout.circle_image_item, null);
        return new MyHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHodler myHodler, int position) {
        myHodler.image.setImageURI(Uri.parse(mList.get(position)));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void clear() {
        mList.clear();
    }

    class MyHodler extends RecyclerView.ViewHolder {
        SimpleDraweeView image;

        public MyHodler(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.circle_image);
        }
    }

}
