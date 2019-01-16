package com.qh.xuezhimin.wynshop.fragment.details;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qh.xuezhimin.wynshop.R;

/**
 * 商品详情介绍（参数系列）
 */
public class DetailsIntroduceFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details_introduce, container, false);
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
