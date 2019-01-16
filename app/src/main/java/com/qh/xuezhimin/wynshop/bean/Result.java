package com.qh.xuezhimin.wynshop.bean;

public class Result<T> {


    /**
     * result : {"headPic":"http://172.17.8.100/images/small/head_pic/2018-11-21/20181121100733.jpg","nickName":"OP_8mY65","phone":"16619958760","sessionId":"154276714558512","sex":1,"userId":12}
     * message : 登录成功
     * status : 0000
     */

    private T result;
    private String message;
    private String status;
    private T orderList;

    String headPath;

    public T getOrderList() {
        return orderList;
    }

    public void setOrderList(T orderList) {
        this.orderList = orderList;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }


}
