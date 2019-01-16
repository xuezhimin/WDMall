package com.qh.xuezhimin.wynshop.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.adapter.CircleAdapter;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.CircleBean;
import com.qh.xuezhimin.wynshop.bean.UserInfo;
import com.qh.xuezhimin.wynshop.core.db.DaoMaster;
import com.qh.xuezhimin.wynshop.core.db.UserInfoDao;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.CirclePresenter;
import com.qh.xuezhimin.wynshop.util.LogUtils;
import com.qh.xuezhimin.wynshop.view.SpacingItemDecoration;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 圈子
 */
public class CircleFragment extends Fragment implements XRecyclerView.LoadingListener {


    XRecyclerView mCircleXResyleView;
    //p层
    private CirclePresenter circlePresenter = new CirclePresenter(new CircleCall());
    private CircleAdapter mCircleAdapter;
    public UserInfo LOGIN_USER;
    private Unbinder unbinder;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //首先查询个人信息
        UserInfoDao userInfoDao = DaoMaster.newDevSession(getContext(), UserInfoDao.TABLENAME).getUserInfoDao();
        List<UserInfo> userInfos = userInfoDao.queryBuilder().where(UserInfoDao.Properties.Status.eq(1)).list();
        if (userInfos != null && userInfos.size() > 0) {
            LOGIN_USER = userInfos.get(0);//读取第一项
        }

        // 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
        long time = System.currentTimeMillis();
        view = inflater.inflate(R.layout.fragment_circle, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView(view);
        LogUtils.e(this.toString() + "页面加载使用：" + (System.currentTimeMillis() - time));
        // 每次ViewPager要展示该页面时，均会调用该方法获取显示的View
        return view;
    }

    private void initView(View view) {

        mCircleXResyleView = view.findViewById(R.id.circle_x_resyleView);

        //适配器
        mCircleAdapter = new CircleAdapter(getContext());
        mCircleXResyleView.setAdapter(mCircleAdapter);

        //布局管理器
        mCircleXResyleView.setLayoutManager(new GridLayoutManager(getContext(), 1));

        int space = getResources().getDimensionPixelSize(R.dimen.dip_20);

        mCircleXResyleView.addItemDecoration(new SpacingItemDecoration(space));

        //上拉和下拉的监听
        mCircleXResyleView.setLoadingListener(this);
        //刷新
        mCircleXResyleView.refresh();

    }


    @Override
    public void onRefresh() {
        if (circlePresenter.isRunning()) {
            mCircleXResyleView.refreshComplete();
            return;
        }
        circlePresenter.request(true, LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }


    @Override
    public void onLoadMore() {
        if (circlePresenter.isRunning()) {
            mCircleXResyleView.loadMoreComplete();
            return;
        }
        circlePresenter.request(false, LOGIN_USER.getUserId(),
                LOGIN_USER.getSessionId());
    }


    class CircleCall implements DataCall<Result<List<CircleBean>>> {

        @Override
        public void success(Result<List<CircleBean>> data) {
            mCircleXResyleView.refreshComplete();
            mCircleXResyleView.loadMoreComplete();
            if (data.getStatus().equals("0000")) {
                //添加列表并刷新
                if (circlePresenter.getPage() == 1) {
                    mCircleAdapter.clear();
                }
                mCircleAdapter.addAll(data.getResult());
                mCircleAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void fail(ApiException e) {
            mCircleXResyleView.refreshComplete();
            mCircleXResyleView.loadMoreComplete();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
