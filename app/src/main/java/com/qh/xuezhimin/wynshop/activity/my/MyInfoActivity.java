package com.qh.xuezhimin.wynshop.activity.my;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.UserInfo;
import com.qh.xuezhimin.wynshop.core.db.DaoMaster;
import com.qh.xuezhimin.wynshop.core.db.UserInfoDao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyInfoActivity extends AppCompatActivity {

    @BindView(R.id.my_info_headPic)
    SimpleDraweeView mMyInfoHeadPic;
    @BindView(R.id.my_info_nickName)
    TextView mMyInfoNickName;
    @BindView(R.id.my_info_pwd)
    TextView mMyInfoPwd;
    private UserInfo LOGIN_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //首先查询个人信息
        UserInfoDao userInfoDao = DaoMaster.newDevSession(getBaseContext(), UserInfoDao.TABLENAME).getUserInfoDao();
        List<UserInfo> userInfos = userInfoDao.queryBuilder().where(UserInfoDao.Properties.Status.eq(1)).list();
        LOGIN_USER = userInfos.get(0);//读取第一项
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);

        mMyInfoHeadPic.setImageURI(Uri.parse(LOGIN_USER.getHeadPic()));
        mMyInfoNickName.setText(LOGIN_USER.getNickName());
        mMyInfoPwd.setText(LOGIN_USER.getPhone());
    }


}
