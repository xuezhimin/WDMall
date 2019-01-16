package com.qh.xuezhimin.wynshop.mvp.presenter;

import com.qh.xuezhimin.wynshop.bean.QueryCartBean;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.http.NetWorkManager;
import com.qh.xuezhimin.wynshop.http.RequestInterFace;

import java.util.List;

import io.reactivex.Observable;

public class QueryCartPresenter extends BasePresenter {

    public QueryCartPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable<Result<List<QueryCartBean>>> observable(Object... args) {
        RequestInterFace requestInterFace = NetWorkManager.getInstance().create(RequestInterFace.class);
        return requestInterFace.queryCart((long) args[0], (String) args[1]);
    }
}
