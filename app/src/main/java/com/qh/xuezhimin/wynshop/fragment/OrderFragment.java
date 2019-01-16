package com.qh.xuezhimin.wynshop.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.fragment.order.AllListFragment;
import com.qh.xuezhimin.wynshop.fragment.order.CommentFragment;
import com.qh.xuezhimin.wynshop.fragment.order.FinishFragment;
import com.qh.xuezhimin.wynshop.fragment.order.PayFragment;
import com.qh.xuezhimin.wynshop.fragment.order.ReceiverFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 订单
 */
public class OrderFragment extends Fragment {


    @BindView(R.id.rb_order_all_list)
    RadioButton mRbOrderAllList;
    @BindView(R.id.rb_order_pay)
    RadioButton mRbOrderPay;
    @BindView(R.id.rb_order_receiver)
    RadioButton mRbOrderReceiver;
    @BindView(R.id.rb_order_comment)
    RadioButton mRbOrderComment;
    @BindView(R.id.rb_order_finish)
    RadioButton mRbOrderFinish;
    @BindView(R.id.order_view_page)
    ViewPager mOrderViewPage;
    private View view;
    private Unbinder unbinder;
    //fragment集合
    private List<Fragment> fragments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    private void initView(View view) {
        fragments = new ArrayList<>();
        fragments.add(new AllListFragment());
        fragments.add(new PayFragment());
        fragments.add(new ReceiverFragment());
        fragments.add(new CommentFragment());
        fragments.add(new FinishFragment());

        //viewpager和fragment切换
        mOrderViewPage.setAdapter(new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });


    }


    @OnClick({R.id.rb_order_all_list, R.id.rb_order_pay, R.id.rb_order_receiver,
            R.id.rb_order_comment, R.id.rb_order_finish})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_order_all_list:
                mOrderViewPage.setCurrentItem(0);
                break;
            case R.id.rb_order_pay:
                mOrderViewPage.setCurrentItem(1);
                break;
            case R.id.rb_order_receiver:
                mOrderViewPage.setCurrentItem(2);
                break;
            case R.id.rb_order_comment:
                mOrderViewPage.setCurrentItem(3);
                break;
            case R.id.rb_order_finish:
                mOrderViewPage.setCurrentItem(4);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
