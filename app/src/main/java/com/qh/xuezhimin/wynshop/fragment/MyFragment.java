package com.qh.xuezhimin.wynshop.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.activity.LoginActivity;
import com.qh.xuezhimin.wynshop.activity.my.MyAddressActivity;
import com.qh.xuezhimin.wynshop.activity.my.MyFootActivity;
import com.qh.xuezhimin.wynshop.activity.my.MyInfoActivity;
import com.qh.xuezhimin.wynshop.activity.my.MyWalletActivity;
import com.qh.xuezhimin.wynshop.bean.UserInfo;
import com.qh.xuezhimin.wynshop.core.db.DaoMaster;
import com.qh.xuezhimin.wynshop.core.db.UserInfoDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 我的
 */
public class MyFragment extends Fragment {


    @BindView(R.id.my_nick_name)
    TextView mMyNickName;
    @BindView(R.id.my_info)
    RelativeLayout mMyInfo;
    @BindView(R.id.my_circle)
    RelativeLayout mMyCircle;
    @BindView(R.id.my_foot)
    RelativeLayout mMyFoot;
    @BindView(R.id.my_wallet)
    RelativeLayout mMyWallet;
    @BindView(R.id.my_address)
    RelativeLayout mMyAddress;
    @BindView(R.id.my_avatar)
    SimpleDraweeView mMyAvatar;
    @BindView(R.id.my_login_out)
    Button mMyLoginOut;
    private View view;
    private Unbinder unbinder;
    private UserInfo LOGIN_USER;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //首先查询个人信息
        UserInfoDao userInfoDao = DaoMaster.newDevSession(getContext(), UserInfoDao.TABLENAME).getUserInfoDao();
        List<UserInfo> userInfos = userInfoDao.queryBuilder().where(UserInfoDao.Properties.Status.eq(1)).list();
        LOGIN_USER = userInfos.get(0);//读取第一项

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        unbinder = ButterKnife.bind(this, view);

//        mMyAvatar.setImageURI(Uri.parse("res:///" + R.drawable.bg_my));
        mMyAvatar.setImageURI(Uri.parse(LOGIN_USER.getHeadPic()));
        mMyNickName.setText(LOGIN_USER.getNickName());

        return view;
    }

    @OnClick({R.id.my_info, R.id.my_circle, R.id.my_foot, R.id.my_wallet, R.id.my_address, R.id.my_avatar, R.id.my_login_out})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_info:
                startActivity(new Intent(getContext(), MyInfoActivity.class));
                break;
            case R.id.my_circle:
                break;
            case R.id.my_foot:
                startActivity(new Intent(getContext(), MyFootActivity.class));
                break;
            case R.id.my_wallet:
                startActivity(new Intent(getContext(), MyWalletActivity.class));
                break;
            case R.id.my_address:
                startActivity(new Intent(getContext(), MyAddressActivity.class));
                break;
            case R.id.my_avatar:
                break;
            case R.id.my_login_out:
                UserInfoDao userInfoDao = DaoMaster.newDevSession(getActivity(), UserInfoDao.TABLENAME).getUserInfoDao();
                //删除用户
                userInfoDao.delete(LOGIN_USER);
                //跳转登录页
                startActivity(new Intent(getActivity(), LoginActivity.class));
                //本页面关闭
                getActivity().finish();
                break;
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
    }
}
