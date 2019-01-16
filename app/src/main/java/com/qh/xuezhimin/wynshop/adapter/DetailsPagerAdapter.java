package com.qh.xuezhimin.wynshop.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * 商品详情页面适配器（tablayout）
 */
public class DetailsPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private List<Fragment> mFragmentList;
    private List<String> mListTitle;

    public DetailsPagerAdapter(FragmentManager fm, Context mContext, List<Fragment> mFragmentList, List<String> mListTitle) {
        super(fm);
        this.mContext = mContext;
        this.mFragmentList = mFragmentList;
        this.mListTitle = mListTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mListTitle.size();
    }

    /**
     * 此方法用来显示tab上的名字
     */
    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitle.get(position);
    }
}
