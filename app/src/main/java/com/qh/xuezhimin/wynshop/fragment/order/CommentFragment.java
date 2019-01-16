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
import com.qh.xuezhimin.wynshop.adapter.order.CommentAdapter;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.UserInfo;
import com.qh.xuezhimin.wynshop.bean.order.OutAllList;
import com.qh.xuezhimin.wynshop.core.db.DaoMaster;
import com.qh.xuezhimin.wynshop.core.db.UserInfoDao;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.order.CommentPresneter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 待评价
 */
public class CommentFragment extends Fragment {


    @BindView(R.id.comment_recycle_view)
    RecyclerView mCommentRecycleView;
    private View view;
    private Unbinder unbinder;
    private CommentPresneter commentPresneter = new CommentPresneter(new CommentDataCall());
    private CommentAdapter commentAdapter;
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
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        unbinder = ButterKnife.bind(this, view);

        //适配器
        commentAdapter = new CommentAdapter(getContext());
        mCommentRecycleView.setAdapter(commentAdapter);
        //布局管理器
        mCommentRecycleView.setLayoutManager
                (new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        //请求数据
        commentPresneter.request(LOGIN_USER.getUserId(), LOGIN_USER.getSessionId(), 1, 1, 1);

        return view;
    }


    class CommentDataCall implements DataCall<Result<List<OutAllList>>> {
        @Override
        public void success(Result<List<OutAllList>> data) {
            if (data.getStatus().equals("0000")) {
                List<OutAllList> orderList = data.getOrderList();
                commentAdapter.addAll(orderList.get(0).getDetailList());
                commentAdapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getContext(), data.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        commentPresneter.unBind();
    }
}
