package com.qh.xuezhimin.wynshop.fragment.order;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.adapter.order.PayAdapter;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.UserInfo;
import com.qh.xuezhimin.wynshop.bean.order.DetailList;
import com.qh.xuezhimin.wynshop.bean.order.OutAllList;
import com.qh.xuezhimin.wynshop.core.db.DaoMaster;
import com.qh.xuezhimin.wynshop.core.db.UserInfoDao;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.order.PayPresneter;
import com.qh.xuezhimin.wynshop.util.UIUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 待付款
 */
public class PayFragment extends Fragment {


    @BindView(R.id.pay_recycle_view)
    RecyclerView mPayRecycleView;
    private View view;
    private Unbinder unbinder;
    //实例化p
    private PayPresneter payPresneter = new PayPresneter(new OrderPayCall());
    //适配器
    private PayAdapter payAdapter;
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
        View view = inflater.inflate(R.layout.fragment_pay, container, false);
        unbinder = ButterKnife.bind(this, view);
        //适配器
        payAdapter = new PayAdapter(getContext());
        mPayRecycleView.setAdapter(payAdapter);
        //布局管理器
        mPayRecycleView.setLayoutManager
                (new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //请求数据
        payPresneter.request(LOGIN_USER.getUserId(), LOGIN_USER.getSessionId(), 1, 1, 1);
        return view;
    }


    /**
     * 内部类
     */
    class OrderPayCall implements DataCall<Result<List<OutAllList>>> {

        @Override
        public void success(Result<List<OutAllList>> data) {
            if (data.getStatus().equals("0000")) {
                List<OutAllList> orderList = data.getOrderList();
                payAdapter.addAll(orderList.get(0).getDetailList());
                payAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), data.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {
            UIUtils.showToastSafe(e.getCode() + " " + e.getDisplayMessage());
            Toast.makeText(getContext(), "数据解析异常" + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /**
     * 释放内存
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        payPresneter.unBind();
    }
}
