package com.qh.xuezhimin.wynshop.http;


import com.qh.xuezhimin.wynshop.core.exception.ApiException;

/**
 * DataCall接口
 */
public interface DataCall<T> {

    void success(T data);

    void fail(ApiException e);

}
