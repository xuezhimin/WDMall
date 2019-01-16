package com.qh.xuezhimin.wynshop.mvp.presenter;

import com.qh.xuezhimin.wynshop.bean.KeyWordBean;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.http.DataCall;
import com.qh.xuezhimin.wynshop.http.NetWorkManager;
import com.qh.xuezhimin.wynshop.http.RequestInterFace;

import java.util.List;

import io.reactivex.Observable;

public class SearchKeyWordPresenter extends BasePresenter {

    private int page = 1;
    private boolean refresh = false;

    public SearchKeyWordPresenter(DataCall dataCall) {
        super(dataCall);
    }

    @Override
    protected Observable observable(Object... args) {
        RequestInterFace requestInterFace = NetWorkManager.getInstance().create(RequestInterFace.class);
        refresh = (boolean) args[0];
        if (refresh) {
            page = 1;
        } else {
            page++;
        }
        Observable<Result<List<KeyWordBean>>> searchKeyWord = requestInterFace.searchKeyWord((String) args[1], page, 10);
        return searchKeyWord;
    }
}
