package com.qh.xuezhimin.wynshop.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.fragment.CartFragment;
import com.qh.xuezhimin.wynshop.fragment.CircleFragment;
import com.qh.xuezhimin.wynshop.fragment.HomePageFragment;
import com.qh.xuezhimin.wynshop.fragment.MyFragment;
import com.qh.xuezhimin.wynshop.fragment.OrderFragment;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton mRbHomePage;
    private RadioButton mRbHomeCircle;
    private RadioButton mRbHomeCart;
    private RadioButton mRbHomeOrder;
    private RadioButton mRbHomeMy;
    private FragmentManager mManager;
    private HomePageFragment mHomePageFragment;
    private CircleFragment mCircleFragment;
    private CartFragment mCartFragment;
    private OrderFragment mOrderFragment;
    private MyFragment mMyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        mManager = getSupportFragmentManager();
        mHomePageFragment = new HomePageFragment();
        mCircleFragment = new CircleFragment();
        mCartFragment = new CartFragment();
        mOrderFragment = new OrderFragment();
        mMyFragment = new MyFragment();
        // 开启事务
        FragmentTransaction transaction = mManager.beginTransaction();
        // 添加和默认展示Fragment
        transaction.add(R.id.home_frame_layout, mHomePageFragment);// 添加首页的Fragment
        transaction.add(R.id.home_frame_layout, mCircleFragment);
        transaction.add(R.id.home_frame_layout, mCartFragment);
        transaction.add(R.id.home_frame_layout, mOrderFragment);
        transaction.add(R.id.home_frame_layout, mMyFragment);
        transaction.hide(mCircleFragment);
        transaction.hide(mCartFragment);
        transaction.hide(mOrderFragment);
        transaction.hide(mMyFragment);

        transaction.commit(); // 最后提交

    }

    private void initView() {
        FrameLayout mHomeFrameLayout = findViewById(R.id.home_frame_layout);
        mRbHomePage = findViewById(R.id.rb_home_page);
        mRbHomePage.setOnClickListener(this);
        mRbHomeCircle = findViewById(R.id.rb_home_circle);
        mRbHomeCircle.setOnClickListener(this);
        mRbHomeCart = findViewById(R.id.rb_home_cart);
        mRbHomeCart.setOnClickListener(this);
        mRbHomeOrder = findViewById(R.id.rb_home_order);
        mRbHomeOrder.setOnClickListener(this);
        mRbHomeMy = findViewById(R.id.rb_home_my);
        mRbHomeMy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home_page:
                mManager.beginTransaction().show(mHomePageFragment)
                        .hide(mCircleFragment)
                        .hide(mCartFragment)
                        .hide(mOrderFragment)
                        .hide(mMyFragment)
                        .commit();
                break;
            case R.id.rb_home_circle:
                mManager.beginTransaction().show(mCircleFragment)
                        .hide(mHomePageFragment)
                        .hide(mCartFragment)
                        .hide(mOrderFragment)
                        .hide(mMyFragment)
                        .commit();
                break;
            case R.id.rb_home_cart:
                mManager.beginTransaction().show(mCartFragment)
                        .hide(mHomePageFragment)
                        .hide(mCircleFragment)
                        .hide(mOrderFragment)
                        .hide(mMyFragment)
                        .commit();
                break;
            case R.id.rb_home_order:
                mManager.beginTransaction().show(mOrderFragment)
                        .hide(mHomePageFragment)
                        .hide(mCircleFragment)
                        .hide(mCartFragment)
                        .hide(mMyFragment)
                        .commit();
                break;
            case R.id.rb_home_my:
                mManager.beginTransaction().show(mMyFragment)
                        .hide(mHomePageFragment)
                        .hide(mCircleFragment)
                        .hide(mCartFragment)
                        .hide(mOrderFragment)
                        .commit();
                break;
        }
    }
}
