package com.qh.xuezhimin.wynshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.adapter.DetailsPagerAdapter;
import com.qh.xuezhimin.wynshop.fragment.details.DetailsCommentsFragment;
import com.qh.xuezhimin.wynshop.fragment.details.DetailsIntroduceFragment;
import com.qh.xuezhimin.wynshop.fragment.details.GoodsIntroduceFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author lmx
 * @date 2019/1/8
 * 商品详情页面
 */
public class DetailsActivity extends AppCompatActivity {


    @BindView(R.id.details_tablayout)
    TabLayout mDetailsTablayout;
    @BindView(R.id.details_viewpager)
    ViewPager mDetailsViewpager;
    //默认值
    private int cid = 1;
    private List<Fragment> mFragmentList;
    private List<String> mListTitle;
    private GoodsIntroduceFragment goodsIntroduceFragment = new GoodsIntroduceFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        initData(cid);
        initView();
    }

    private void initView() {
        mFragmentList = new ArrayList<>();
        mListTitle = new ArrayList<>();
        mFragmentList.add(goodsIntroduceFragment);
        mFragmentList.add(new DetailsIntroduceFragment());
        mFragmentList.add(new DetailsCommentsFragment());
        mListTitle.add("商品");
        mListTitle.add("详情");
        mListTitle.add("评论");
        //此方法就是让tablayout和ViewPager联动
        mDetailsViewpager.setAdapter(new DetailsPagerAdapter
                (getSupportFragmentManager(), DetailsActivity.this, mFragmentList, mListTitle));
        mDetailsTablayout.setupWithViewPager(mDetailsViewpager);
    }


    /**
     * 传商品的id
     *
     * @param cid
     */
    private void initData(int cid) {
        Intent intent = getIntent();
        int intExtra = intent.getIntExtra("goodsId", cid);
        Bundle bundle = new Bundle();
        bundle.putInt("cid", intExtra);
        goodsIntroduceFragment.setArguments(bundle);
    }

}
