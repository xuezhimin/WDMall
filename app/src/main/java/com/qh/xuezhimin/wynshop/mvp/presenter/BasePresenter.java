package com.qh.xuezhimin.wynshop.mvp.presenter;

import com.qh.xuezhimin.wynshop.bean.QueryCartBean;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.core.exception.CustomException;
import com.qh.xuezhimin.wynshop.core.exception.ResponseTransformer;
import com.qh.xuezhimin.wynshop.http.DataCall;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BasePresenter {


    private DataCall dataCall;

    private boolean running;

    public BasePresenter(DataCall dataCall) {
        this.dataCall = dataCall;
    }


    protected abstract Observable<Result<List<QueryCartBean>>> observable(Object... args);


    public void request(Object... args) {
        if (running) {
            return;
        }
        running = true;
        observable(args)
                .compose(ResponseTransformer.handleResult())
                .compose(new ObservableTransformer() {
                    @Override
                    public ObservableSource apply(Observable upstream) {
                        return upstream.subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new Consumer<Result>() {
                    @Override
                    public void accept(Result result) throws Exception {
                        running = false;
                        dataCall.success(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        running = false;
                        //处理异常
                        dataCall.fail(CustomException.handleException(throwable));
                    }
                });


//        observable(args).subscribeOn(Schedulers.newThread())//将请求调度到子线
//                .observeOn(AndroidSchedulers.mainThread())//观察响应结果，把响应结果调度到主线程中处理程上

//                .subscribe(consumer, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        throwable.printStackTrace();
//                    }
//                });
    }


    //暴露一个运行的方法
    public boolean isRunning() {
        return running;
    }


    public void unBind() {
        dataCall = null;
    }


}
