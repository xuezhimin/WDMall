package com.qh.xuezhimin.wynshop.mvp.presenter;

import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.http.NetWorkManager;
import com.qh.xuezhimin.wynshop.http.RequestInterFace;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class LoginPresenter extends BasePresenter {


    public LoginPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        RequestInterFace requestInterFace = NetWorkManager.getInstance().create(RequestInterFace.class);
        return requestInterFace.login((String) args[0], (String) args[1]);
    }
}
