package com.qh.xuezhimin.wynshop.mvp.presenter;

import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.http.NetWorkManager;
import com.qh.xuezhimin.wynshop.http.RequestInterFace;

import io.reactivex.Observable;

public class CirclePresenter extends BasePresenter {

    private int page = 1;

    public CirclePresenter(DataCall dataCall) {
        super(dataCall);
    }

    public int getPage() {
        return page;
    }

    @Override
    protected Observable observable(Object... args) {
        RequestInterFace requestInterFace = NetWorkManager.getInstance().create(RequestInterFace.class);
        boolean refresh = (boolean) args[0];
        if (refresh) {
            page = 1;
        } else {
            page++;
        }
        return requestInterFace.findCircleList((long) args[1], (String) args[2], page, 20);
    }
}
