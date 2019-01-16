package com.qh.xuezhimin.wynshop.activity.my;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.UserInfo;
import com.qh.xuezhimin.wynshop.bean.my.AddressBean;
import com.qh.xuezhimin.wynshop.core.db.DaoMaster;
import com.qh.xuezhimin.wynshop.core.db.UserInfoDao;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.MyAddressPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author lmx
 * @date 2019/1/14
 * 我的地址
 */
public class MyAddressActivity extends AppCompatActivity {

    @BindView(R.id.my_address_realname)
    TextView mMyAddressRealname;
    @BindView(R.id.my_address_realphone)
    TextView mMyAddressRealphone;
    @BindView(R.id.my_address_realAddress)
    TextView mMyAddressRealAddress;
    @BindView(R.id.my_view)
    View mMyView;
    private UserInfo LOGIN_USER;
    private MyAddressPresenter myAddressPresenter = new MyAddressPresenter(new AddressDataCall());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);
        //调用数据库（greenDao）获取用户的id
        UserInfoDao userInfoDao = DaoMaster.newDevSession(getBaseContext(), UserInfoDao.TABLENAME).getUserInfoDao();
        List<UserInfo> userInfos = userInfoDao.queryBuilder().where(UserInfoDao.Properties.Status.eq(1)).list();
        //读取第一项
        LOGIN_USER = userInfos.get(0);
        ButterKnife.bind(this);

        //请求数据
        myAddressPresenter.request(LOGIN_USER.getUserId(), LOGIN_USER.getSessionId());

    }


    class AddressDataCall implements DataCall<Result<List<AddressBean>>> {

        @Override
        public void success(Result<List<AddressBean>> data) {
            if (data.getStatus().equals("0000")) {
                List<AddressBean> result = data.getResult();
                AddressBean addressBean = result.get(0);
                mMyAddressRealname.setText(addressBean.getRealName());
                mMyAddressRealphone.setText(addressBean.getPhone());
                mMyAddressRealAddress.setText(addressBean.getAddress());
            } else {
                Toast.makeText(getBaseContext(), data.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void fail(ApiException e) {

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myAddressPresenter.unBind();
    }
}
