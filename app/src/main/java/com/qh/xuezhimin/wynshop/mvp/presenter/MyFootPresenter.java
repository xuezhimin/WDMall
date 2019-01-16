package com.qh.xuezhimin.wynshop.mvp.presenter;

import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.my.FootBean;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.http.NetWorkManager;
import com.qh.xuezhimin.wynshop.http.RequestInterFace;

import java.util.List;

import io.reactivex.Observable;

public class MyFootPresenter extends BasePresenter {

    public MyFootPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        RequestInterFace requestInterFace = NetWorkManager.getInstance().create(RequestInterFace.class);
        Observable<Result<List<FootBean>>> myFoot = requestInterFace.myFoot((long) args[0], (String) args[1], (int) args[2], (int) args[3]);
        return myFoot;
    }
}
