package com.qh.xuezhimin.wynshop.mvp.presenter;

import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.http.NetWorkManager;
import com.qh.xuezhimin.wynshop.http.RequestInterFace;

import io.reactivex.Observable;

public class DetailsGoodsPresenter extends BasePresenter {


    public DetailsGoodsPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        RequestInterFace requestInterFace = NetWorkManager.getInstance().create(RequestInterFace.class);
        return requestInterFace.detailsByIdGetData((long) args[0], (String) args[1], (int) args[2]);
    }
}
