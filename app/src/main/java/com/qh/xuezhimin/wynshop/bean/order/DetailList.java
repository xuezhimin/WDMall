package com.qh.xuezhimin.wynshop.bean.order;

public class DetailList {
    private int commentStatus;

    private int commodityCount;

    private int commodityId;

    private String commodityName;

    private String commodityPic;

    private int commodityPrice;

    private int orderDetailId;

    public void setCommentStatus(int commentStatus){
        this.commentStatus = commentStatus;
    }
    public int getCommentStatus(){
        return this.commentStatus;
    }
    public void setCommodityCount(int commodityCount){
        this.commodityCount = commodityCount;
    }
    public int getCommodityCount(){
        return this.commodityCount;
    }
    public void setCommodityId(int commodityId){
        this.commodityId = commodityId;
    }
    public int getCommodityId(){
        return this.commodityId;
    }
    public void setCommodityName(String commodityName){
        this.commodityName = commodityName;
    }
    public String getCommodityName(){
        return this.commodityName;
    }
    public void setCommodityPic(String commodityPic){
        this.commodityPic = commodityPic;
    }
    public String getCommodityPic(){
        return this.commodityPic;
    }
    public void setCommodityPrice(int commodityPrice){
        this.commodityPrice = commodityPrice;
    }
    public int getCommodityPrice(){
        return this.commodityPrice;
    }
    public void setOrderDetailId(int orderDetailId){
        this.orderDetailId = orderDetailId;
    }
    public int getOrderDetailId(){
        return this.orderDetailId;
    }
}
