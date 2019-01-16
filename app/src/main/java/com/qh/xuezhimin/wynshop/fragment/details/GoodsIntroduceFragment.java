package com.qh.xuezhimin.wynshop.fragment.details;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.adapter.DetailsGoodsAdapter;
import com.qh.xuezhimin.wynshop.bean.GoodsDetailsBean;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.UserInfo;
import com.qh.xuezhimin.wynshop.core.db.DaoMaster;
import com.qh.xuezhimin.wynshop.core.db.UserInfoDao;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.DetailsGoodsPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 商品介绍
 */
public class GoodsIntroduceFragment extends Fragment {


    @BindView(R.id.details_goods_itroduce_img)
    SimpleDraweeView mDetailsGoodsItroduceImg;
    @BindView(R.id.details_goods_itroduce_price)
    TextView mDetailsGoodsItroducePrice;
    @BindView(R.id.details_goods_itroduce_title)
    TextView mDetailsGoodsItroduceTitle;
    @BindView(R.id.details_goods_itroduce_kg)
    TextView mDetailsGoodsItroduceKg;
    private UserInfo LOGIN_USER;
    private View view;
    private Unbinder unbinder;
    private DetailsGoodsPresenter detailsGoodsPresenter = new DetailsGoodsPresenter(new DetailsGoodsCall());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //首先查询个人信息
        UserInfoDao userInfoDao = DaoMaster.newDevSession(getContext(), UserInfoDao.TABLENAME).getUserInfoDao();
        List<UserInfo> userInfos = userInfoDao.queryBuilder().where(UserInfoDao.Properties.Status.eq(1)).list();
        //读取第一项
        LOGIN_USER = userInfos.get(0);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_goods_introduce, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        Bundle bundle = this.getArguments();
        int cid = bundle.getInt("cid");
        detailsGoodsPresenter.request(LOGIN_USER.getUserId(), LOGIN_USER.getSessionId(), cid);
    }

    class DetailsGoodsCall implements DataCall<Result<GoodsDetailsBean>> {

        @Override
        public void success(Result<GoodsDetailsBean> data) {
            if (data.getStatus().equals("0000")) {
                GoodsDetailsBean goodsDetailsBean = data.getResult();
                String[] picture = goodsDetailsBean.getPicture().split(",");
                mDetailsGoodsItroduceImg.setImageURI(Uri.parse(picture[0]));
                mDetailsGoodsItroduceTitle.setText(goodsDetailsBean.getCommodityName());
                mDetailsGoodsItroducePrice.setText("￥ " + goodsDetailsBean.getPrice());
                mDetailsGoodsItroduceKg.setText(goodsDetailsBean.getWeight() + " kg");
            } else {
                Toast.makeText(getContext(), "获取数据有误", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
//            Toast.makeText(getContext(), "解析数据异常" + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
