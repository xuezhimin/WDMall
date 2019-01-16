package com.qh.xuezhimin.wynshop.bean.my;

public class AddressBean {

    private String address;

    private int createTime;

    private int id;

    private String phone;

    private String realName;

    private int userId;

    private int whetherDefault;

    private String zipCode;

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }
    public void setCreateTime(int createTime){
        this.createTime = createTime;
    }
    public int getCreateTime(){
        return this.createTime;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setRealName(String realName){
        this.realName = realName;
    }
    public String getRealName(){
        return this.realName;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public int getUserId(){
        return this.userId;
    }
    public void setWhetherDefault(int whetherDefault){
        this.whetherDefault = whetherDefault;
    }
    public int getWhetherDefault(){
        return this.whetherDefault;
    }
    public void setZipCode(String zipCode){
        this.zipCode = zipCode;
    }
    public String getZipCode(){
        return this.zipCode;
    }

}
