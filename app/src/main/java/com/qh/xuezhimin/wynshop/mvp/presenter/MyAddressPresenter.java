package com.qh.xuezhimin.wynshop.mvp.presenter;


import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.my.AddressBean;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.http.NetWorkManager;
import com.qh.xuezhimin.wynshop.http.RequestInterFace;


import java.util.List;

import io.reactivex.Observable;

public class MyAddressPresenter extends BasePresenter {
    public MyAddressPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        RequestInterFace requestInterFace = NetWorkManager.getInstance().create(RequestInterFace.class);
        Observable<Result<List<AddressBean>>> resultObservable = requestInterFace.queryAddress((long) args[0], (String) args[1]);
        return resultObservable;
    }
}
