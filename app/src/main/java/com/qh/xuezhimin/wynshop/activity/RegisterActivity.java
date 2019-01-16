package com.qh.xuezhimin.wynshop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qh.xuezhimin.wynshop.R;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.core.exception.ApiException;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.mvp.presenter.RegPresenter;
import com.qh.xuezhimin.wynshop.util.MD5Utils;
import com.qh.xuezhimin.wynshop.util.UIUtils;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 手机号
     */
    private EditText mRegEditPhone;
    /**
     * 验证码
     */
    private EditText mRegEditVerfitionCode;
    /**
     * 获取验证码
     */
    private TextView mRegTxtGetVerfitionCode;
    /**
     * 注册密码
     */
    private EditText mRegEditPwd;
    private ImageView mRegImgEye;
    /**
     * 已有账户？立即登录
     */
    private TextView mRegTxtGoLogin;
    /**
     * 注册
     */
    private Button mBtnReg;
    //p层
    private RegPresenter mRegPresenter = new RegPresenter(new RegCall());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
    }

    private void initView() {
        mRegEditPhone = findViewById(R.id.reg_edit_phone);
        mRegEditVerfitionCode = findViewById(R.id.reg_edit_verfition_code);
        mRegTxtGetVerfitionCode = findViewById(R.id.reg_txt_get_verfition_code);
        mRegTxtGetVerfitionCode.setOnClickListener(this);
        mRegEditPwd = findViewById(R.id.reg_edit_pwd);
        mRegImgEye = findViewById(R.id.reg_img_eye);
        mRegImgEye.setOnClickListener(this);
        mRegTxtGoLogin = findViewById(R.id.reg_txt_go_login);
        mRegTxtGoLogin.setOnClickListener(this);
        mBtnReg = findViewById(R.id.btn_reg);
        mBtnReg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //获取验证码
            case R.id.reg_txt_get_verfition_code:
                break;
            //注册密码小眼睛
            case R.id.reg_img_eye:
                break;
            //跳入登录页
            case R.id.reg_txt_go_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            //注册按钮
            case R.id.btn_reg:
                String regName = mRegEditPhone.getText().toString();
                String regPwd = mRegEditPwd.getText().toString();
//                mRegPresenter.request(regName, MD5Utils.md5(regPwd));
                mRegPresenter.request(regName, regPwd);
                break;
        }
    }

    class RegCall implements DataCall<Result> {
        @Override
        public void success(Result result) {
            if (result.getStatus().equals("0000")) {
                Toast.makeText(getBaseContext(), result.getMessage(), Toast.LENGTH_LONG).show();
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


//        @Override
//        public void accept(Result result) throws Exception {
//            if (result.getStatus().equals("0000")) {
//                Toast.makeText(getBaseContext(), result.getMessage(), Toast.LENGTH_LONG).show();
//                finish();
//            } else {
//                Toast.makeText(getBaseContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
    }

}
