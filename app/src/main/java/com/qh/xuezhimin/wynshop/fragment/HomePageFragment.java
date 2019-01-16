package com.qh.xuezhimin.wynshop.fragment;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.activity.SearchActivity;
import com.qh.xuezhimin.wynshop.adapter.MlssAdapter;
import com.qh.xuezhimin.wynshop.adapter.PzshAdapter;
import com.qh.xuezhimin.wynshop.adapter.RxxpAdapter;
import com.qh.xuezhimin.wynshop.bean.BannerBean;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.homegoods.CommodityList;
import com.qh.xuezhimin.wynshop.bean.homegoods.HomeList;
import com.qh.xuezhimin.wynshop.bean.homegoods.ResultBean;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.BannerPresenter;
import com.qh.xuezhimin.wynshop.mvp.presenter.HomeGoodsPresenter;
import com.qh.xuezhimin.wynshop.util.UIUtils;
import com.qh.xuezhimin.wynshop.view.SpacingItemDecoration;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

import static android.support.constraint.Constraints.TAG;

/**
 * 首页
 */
public class HomePageFragment extends Fragment implements View.OnClickListener {


    private View view;
    private ImageButton mHomePageImgMenu;
    /**
     * 请输入您想要的宝贝
     */
    private TextView mHomePageEditSearch;
    private ImageButton mHomePageImgSearch;
    private MZBannerView mHomePageBanner;
    private ImageView mHomePageImgMoreYellow;
    private RecyclerView mHomePageHotSaleGoods;
    private ImageView mHomePageImgMorePurple;
    private RecyclerView mHomePageMagicGoods;
    private ImageView mHomePageImgMorePink;
    private RecyclerView mHomePageQLifeGoods;
    //实例化商品的p层
    private HomeGoodsPresenter homeGoodsPresenter = new HomeGoodsPresenter(new HomeGoodsCall());
    private RxxpAdapter mRxxpAdapter;
    private MlssAdapter mMlssAdapter;
    private PzshAdapter mPzshAdapter;
    private BannerPresenter bannerPresenter = new BannerPresenter(new BannerCall());


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        initView(view);

        //p层请求数据
        homeGoodsPresenter.request();
        //banner
        bannerPresenter.request();




        // 热销新品适配器
        mRxxpAdapter = new RxxpAdapter(getContext());
        mHomePageHotSaleGoods.setAdapter(mRxxpAdapter);

        //热销新品设置布局管理器
        mHomePageHotSaleGoods.setLayoutManager
                (new GridLayoutManager(getContext(), 3));

        // 魔力时尚适配器
        mMlssAdapter = new MlssAdapter(getContext());
        mHomePageMagicGoods.setAdapter(mMlssAdapter);

        //魔力时尚设置布局管理器
        mHomePageMagicGoods.setLayoutManager
                (new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //品质生活适配器
        mPzshAdapter = new PzshAdapter(getContext());
        mHomePageQLifeGoods.setAdapter(mPzshAdapter);

        //品质生活设置布局管理器
        mHomePageQLifeGoods.setLayoutManager
                (new GridLayoutManager(getContext(), 2));


        return view;
    }

    private void initView(View view) {
        mHomePageImgMenu = view.findViewById(R.id.home_page_img_menu);
        mHomePageImgMenu.setOnClickListener(this);
        mHomePageEditSearch = view.findViewById(R.id.home_page_edit_search);
        mHomePageImgSearch = view.findViewById(R.id.home_page_img_search);
        mHomePageImgSearch.setOnClickListener(this);
        mHomePageImgMoreYellow = view.findViewById(R.id.home_page_img_more_yellow);
        mHomePageImgMoreYellow.setOnClickListener(this);
        mHomePageHotSaleGoods = view.findViewById(R.id.home_page_hot_sale_goods);
        mHomePageImgMorePurple = view.findViewById(R.id.home_page_img_more_purple);
        mHomePageImgMorePurple.setOnClickListener(this);
        mHomePageMagicGoods = view.findViewById(R.id.home_page_magic_goods);
        mHomePageImgMorePink = view.findViewById(R.id.home_page_img_more_pink);
        mHomePageImgMorePink.setOnClickListener(this);
        mHomePageQLifeGoods = view.findViewById(R.id.home_page_q_life_goods);
        mHomePageEditSearch = view.findViewById(R.id.home_page_edit_search);
        mHomePageEditSearch.setOnClickListener(this);
        mHomePageBanner = view.findViewById(R.id.home_page_banner);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击菜单按钮
            case R.id.home_page_img_menu:
                break;
            //搜索操作
            case R.id.home_page_img_search:
                break;
            case R.id.home_page_edit_search:
                startActivity(new Intent(getActivity(), SearchActivity.class));
                break;
            //更多操作  热销商品
            case R.id.home_page_img_more_yellow:
                break;
            //更多操作  魔力时尚
            case R.id.home_page_img_more_purple:
                break;
            //更多操作  品质生活
            case R.id.home_page_img_more_pink:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mHomePageBanner.pause();
    }

    /**
     * 魅族banner的实现
     */

    class BannerViewHolder implements MZViewHolder<BannerBean> {
        private SimpleDraweeView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int position, BannerBean data) {
            // 数据绑定
            mImageView.setImageURI(Uri.parse(data.getImageUrl()));
        }

    }

    class BannerCall implements DataCall<Result<List<BannerBean>>> {

        @Override
        public void success(Result<List<BannerBean>> data) {
            if (data.getStatus().equals("0000")) {
                mHomePageBanner.setIndicatorVisible(false);
                mHomePageBanner.setPages(data.getResult(), new MZHolderCreator<BannerViewHolder>() {
                    @Override
                    public BannerViewHolder createViewHolder() {
                        return new BannerViewHolder();
                    }
                });
                mHomePageBanner.start();
            } else {
                Toast.makeText(getContext(), data.getMessage() + "请求Banner数据有误", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void fail(ApiException e) {
            UIUtils.showToastSafe(e.getCode() + " " + e.getDisplayMessage());
            Toast.makeText(getContext(), "数据解析异常" + e, Toast.LENGTH_SHORT).show();
        }
    }


    class HomeGoodsCall implements DataCall<Result<ResultBean>> {

        @Override
        public void success(Result<ResultBean> data) {
            if (data.getStatus().equals("0000")) {
                ResultBean result = data.getResult();
                //热销新品
                List<HomeList> rxxp = result.getRxxp();
                List<CommodityList> commodityList1 = rxxp.get(0).getCommodityList();
                mRxxpAdapter.addAll(commodityList1);
                mRxxpAdapter.notifyDataSetChanged();
                //魔力时尚
                List<HomeList> mlss = result.getMlss();
                List<CommodityList> commodityList2 = mlss.get(0).getCommodityList();
                mMlssAdapter.addAll(commodityList2);
                mMlssAdapter.notifyDataSetChanged();
                //品质生活
                List<HomeList> pzsh = result.getPzsh();
                List<CommodityList> commodityList3 = pzsh.get(0).getCommodityList();
                mPzshAdapter.addAll(commodityList3);
                mPzshAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            UIUtils.showToastSafe(e.getCode() + " " + e.getDisplayMessage());
            Toast.makeText(getContext(), "数据解析异常" + e, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 释放内存
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        homeGoodsPresenter.unBind();
    }

}
