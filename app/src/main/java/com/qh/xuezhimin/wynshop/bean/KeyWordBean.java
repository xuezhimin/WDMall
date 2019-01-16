package com.qh.xuezhimin.wynshop.bean;

public class KeyWordBean {

    private int commodityId;

    private String commodityName;

    private String masterPic;

    private int price;

    private int saleNum;

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getCommodityId() {
        return this.commodityId;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getCommodityName() {
        return this.commodityName;
    }

    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }

    public String getMasterPic() {
        return this.masterPic;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return this.price;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }

    public int getSaleNum() {
        return this.saleNum;
    }

}
