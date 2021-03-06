package com.qh.xuezhimin.wynshop.mvp.presenter.order;


import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.http.NetWorkManager;
import com.qh.xuezhimin.wynshop.http.RequestInterFace;
import com.qh.xuezhimin.wynshop.mvp.presenter.BasePresenter;

import io.reactivex.Observable;

public class FinishPresneter extends BasePresenter {

    public FinishPresneter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        RequestInterFace requestInterFace = NetWorkManager.getInstance().create(RequestInterFace.class);
        return requestInterFace.queryAllOrder((long) args[0], (String) args[1], (int) args[2], (int) args[3], (int) args[4]);
    }
}
