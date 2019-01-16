package com.qh.xuezhimin.wynshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.UserInfo;
import com.qh.xuezhimin.wynshop.core.WDActivity;
import com.qh.xuezhimin.wynshop.core.WDApplication;
import com.qh.xuezhimin.wynshop.core.db.DaoMaster;
import com.qh.xuezhimin.wynshop.core.db.UserInfoDao;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.LoginPresenter;
import com.qh.xuezhimin.wynshop.util.MD5Utils;
import com.qh.xuezhimin.wynshop.util.UIUtils;


public class LoginActivity extends WDActivity implements View.OnClickListener {

    /**
     * 手机号
     */
    private EditText mLoginEditPhone;
    /**
     * 登录密码
     */
    private EditText mLoginEditPwd;
    private ImageView mLoginImgEye;
    /**
     * 记住密码
     */
    private CheckBox mLoginCbRembPwd;
    /**
     * 快速注册
     */
    private TextView mLoginTxtQuickReg;
    /**
     * 登录
     */
    private Button mBtnLogin;
    private LoginPresenter loginPresenter = new LoginPresenter(new LoginCall());

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    protected void initView() {
        mLoginEditPhone = findViewById(R.id.login_edit_phone);
        mLoginEditPwd = findViewById(R.id.login_edit_pwd);
        mLoginImgEye = findViewById(R.id.login_img_eye);
        mLoginImgEye.setOnClickListener(this);
        mLoginCbRembPwd = findViewById(R.id.login_cb_remb_pwd);
        mLoginCbRembPwd.setOnClickListener(this);
        mLoginTxtQuickReg = findViewById(R.id.login_txt_quick_reg);
        mLoginTxtQuickReg.setOnClickListener(this);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnLogin.setOnClickListener(this);

        //记住密码操作
        boolean remPas = WDApplication.getShare().getBoolean("remPas", true);
        if (remPas) {
            mLoginCbRembPwd.setChecked(true);
            mLoginEditPhone.setText(WDApplication.getShare().getString("mobile", ""));
            mLoginEditPwd.setText(WDApplication.getShare().getString("pas", ""));
        }



    }

    @Override
    protected void destoryData() {
        loginPresenter.unBind();
    }

    private boolean pwdVisibile = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //密码隐藏
            case R.id.login_img_eye:
                if (pwdVisibile) {//密码显示，则隐藏
                    mLoginEditPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pwdVisibile = false;
                } else {//密码隐藏则显示
                    mLoginEditPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pwdVisibile = true;
                }
                break;
            case R.id.login_cb_remb_pwd:
                break;
            case R.id.login_txt_quick_reg:
                //直接注册
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                //获取输入的手机号和密码
                String mPhoneNum = mLoginEditPhone.getText().toString().trim();
                String mPwd = mLoginEditPwd.getText().toString().trim();
                if (TextUtils.isEmpty(mPhoneNum)) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mPwd)) {
                    Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                //记住密码
                if (mLoginCbRembPwd.isChecked()) {
                    WDApplication.getShare().edit()
                            .putString("mobile", mPhoneNum)
                            .putString("pas", mPwd)
                            .commit();
                }
                //p层请求
//                loginPresenter.request(mPhoneNum, MD5Utils.md5(mPwd));
                loginPresenter.request(mPhoneNum, mPwd);
                break;

        }
    }


    class LoginCall implements DataCall<Result<UserInfo>> {
        @Override
        public void success(Result<UserInfo> result) {
            if (result.getStatus().equals("0000")) {
                //设置登录状态，保存到数据库
                result.getResult().setStatus(1);
                UserInfoDao userInfoDao = DaoMaster.newDevSession(getBaseContext(), UserInfoDao.TABLENAME).getUserInfoDao();
                userInfoDao.insertOrReplace(result.getResult());
                Toast.makeText(getBaseContext(), result.getMessage(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                finish();
            } else {
                Toast.makeText(getBaseContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                UIUtils.showToastSafe(result.getStatus() + "  " + result.getMessage());
            }
        }

        @Override
        public void fail(ApiException e) {
            UIUtils.showToastSafe(e.getCode() + " " + e.getDisplayMessage());
        }

    }

}
