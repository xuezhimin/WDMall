package com.qh.xuezhimin.wynshop.http;

import com.qh.xuezhimin.wynshop.bean.BannerBean;
import com.qh.xuezhimin.wynshop.bean.GoodsDetailsBean;
import com.qh.xuezhimin.wynshop.bean.KeyWordBean;
import com.qh.xuezhimin.wynshop.bean.QueryCartBean;
import com.qh.xuezhimin.wynshop.bean.Result;
import com.qh.xuezhimin.wynshop.bean.CircleBean;
import com.qh.xuezhimin.wynshop.bean.UserInfo;
import com.qh.xuezhimin.wynshop.bean.homegoods.ResultBean;
import com.qh.xuezhimin.wynshop.bean.my.AddressBean;
import com.qh.xuezhimin.wynshop.bean.my.FootBean;
import com.qh.xuezhimin.wynshop.bean.order.OutAllList;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestInterFace {

    //登录
    @POST("user/v1/login")
    @FormUrlEncoded
    Observable<Result<UserInfo>> login(@Field("phone") String phone,
                                       @Field("pwd") String pwd);

    //注册
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<Result> register(@Field("phone") String phone,
                                @Field("pwd") String pwd);

    //banner
    @GET("commodity/v1/bannerShow")
    Observable<Result<List<BannerBean>>> homeBanner();

    //首页三种类型数据
    @GET("commodity/v1/commodityList")
    Observable<Result<ResultBean>> homeList();

    //关键字搜索
    @GET("commodity/v1/findCommodityByKeyword")
    Observable<Result<List<KeyWordBean>>> searchKeyWord(
            @Query("keyword") String keyword,
            @Query("page") int page,
            @Query("count") int count);

    //圈子
    @GET("circle/v1/findCircleList")
    Observable<Result<List<CircleBean>>> findCircleList(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId,
            @Query("page") int page,
            @Query("count") int count);

    //详情页面展示
    @GET("commodity/v1/findCommodityDetailsById")
    Observable<Result<GoodsDetailsBean>> detailsByIdGetData(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId,
            @Query("commodityId") int commodityId);

    //购物车查询
    @GET("order/verify/v1/findShoppingCart")
    Observable<Result<List<QueryCartBean>>> queryCart(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId);

    //查看全部订单
    @GET("order/verify/v1/findOrderListByStatus")
    Observable<Result<List<OutAllList>>> queryAllOrder(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId,
            @Query("status") int status,
            @Query("page") int page,
            @Query("count") int count);

    //查看我的地址
    @GET("user/verify/v1/receiveAddressList")
    Observable<Result<List<AddressBean>>> queryAddress(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId);

    //我的足迹
    @GET("commodity/verify/v1/browseList")
    Observable<Result<List<FootBean>>> myFoot(
            @Header("userId") long userId,
            @Header("sessionId") String sessionId,
            @Query("page") int page,
            @Query("count") int count);


}
