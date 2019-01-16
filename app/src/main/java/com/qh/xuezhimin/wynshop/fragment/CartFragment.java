package com.qh.xuezhimin.wynshop.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.adapter.QueryCartAdapter;
import com.qh.xuezhimin.wynshop.bean.QueryCartBean;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.UserInfo;
import com.qh.xuezhimin.wynshop.core.db.DaoMaster;
import com.qh.xuezhimin.wynshop.core.db.UserInfoDao;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.QueryCartPresenter;
import com.qh.xuezhimin.wynshop.util.UIUtils;

import java.util.List;

import javax.xml.transform.Transformer;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 购物车
 */
public class CartFragment extends Fragment implements QueryCartAdapter.TotalPriceListener {


    @BindView(R.id.cart_recycler_view)
    RecyclerView mCartRecyclerView;
    @BindView(R.id.cart_check_all)
    CheckBox mCartCheckAll;
    @BindView(R.id.cart_sum_price)
    TextView mCartSumPrice;
    @BindView(R.id.cart_settle_price)
    Button mCartSettlePrice;
    private View view;
    private Unbinder unbinder;
    //实例化p层
    private QueryCartPresenter queryCartPresenter = new QueryCartPresenter(new QueryCartCall());
    private QueryCartAdapter queryCartAdapter;
    private UserInfo LOGIN_USER;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //调用数据库（greenDao）获取用户的id
        UserInfoDao userInfoDao = DaoMaster.newDevSession(getContext(), UserInfoDao.TABLENAME).getUserInfoDao();
        List<UserInfo> userInfos = userInfoDao.queryBuilder().where(UserInfoDao.Properties.Status.eq(1)).list();
        //读取第一项
        LOGIN_USER = userInfos.get(0);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        unbinder = ButterKnife.bind(this, view);

        //适配器
        queryCartAdapter = new QueryCartAdapter(getContext());
        mCartRecyclerView.setAdapter(queryCartAdapter);

        //布局管理器
        mCartRecyclerView.setLayoutManager
                (new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //p层请求数据
        queryCartPresenter.request(LOGIN_USER.getUserId(), LOGIN_USER.getSessionId());

        //设置总价回调器
        queryCartAdapter.setTotalPriceListener(this);

        //全选操作
        mCartCheckAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                queryCartAdapter.checkAll(b);
            }
        });


        return view;
    }


    @OnClick(R.id.cart_settle_price)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_settle_price:
                break;
        }
    }

    //接口回调实现 价格的 方法
    @Override
    public void totalPrice(double totalPrice) {
        mCartSumPrice.setText(String.valueOf("合计：￥" + totalPrice));
    }


    /**
     * 内部类请求数据
     */
    class QueryCartCall implements DataCall<Result<List<QueryCartBean>>> {

        @Override
        public void success(Result<List<QueryCartBean>> data) {
            if (data.getStatus().equals("0000")) {
                List<QueryCartBean> queryCartBeanList = data.getResult();
                queryCartAdapter.addAll(queryCartBeanList);
                queryCartAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), data.getMessage() + "!!!", Toast.LENGTH_SHORT).show();
            }

        }

        @Override
        public void fail(ApiException e) {
            UIUtils.showToastSafe(e.getCode() + " " + e.getDisplayMessage());
            Toast.makeText(getContext(), "请求数据异常" + e, Toast.LENGTH_SHORT).show();
        }
    }

    //销毁视图
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    //释放内存
    @Override
    public void onDestroy() {
        super.onDestroy();
        queryCartPresenter.unBind();
    }
}
