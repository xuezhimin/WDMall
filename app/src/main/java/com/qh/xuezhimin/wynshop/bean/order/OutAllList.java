package com.qh.xuezhimin.wynshop.bean.order;

import java.util.List;

public class OutAllList {


    private List<DetailList> detailList;

    private String expressCompName;

    private String expressSn;

    private String orderId;

    private int orderStatus;

    private int payAmount;

    private int payMethod;

    private int userId;

    public void setDetailList(List<DetailList> detailList) {
        this.detailList = detailList;
    }

    public List<DetailList> getDetailList() {
        return this.detailList;
    }

    public void setExpressCompName(String expressCompName) {
        this.expressCompName = expressCompName;
    }

    public String getExpressCompName() {
        return this.expressCompName;
    }

    public void setExpressSn(String expressSn) {
        this.expressSn = expressSn;
    }

    public String getExpressSn() {
        return this.expressSn;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderStatus() {
        return this.orderStatus;
    }

    public void setPayAmount(int payAmount) {
        this.payAmount = payAmount;
    }

    public int getPayAmount() {
        return this.payAmount;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public int getPayMethod() {
        return this.payMethod;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return this.userId;
    }
}
