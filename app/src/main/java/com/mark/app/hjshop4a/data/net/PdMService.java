package com.mark.app.hjshop4a.data.net;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.mark.app.hjshop4a.BuildConfig;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.app.AppContext;
import com.mark.app.hjshop4a.common.utils.LogUtils;
import com.mark.app.hjshop4a.data.cookie.CookieManger;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.model.login.model.LoginRepo;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.model.AgentPreformance;
import com.mark.app.hjshop4a.ui.areaagent.areabusniess.model.AreaBusniess;
import com.mark.app.hjshop4a.ui.areaagent.areaincome.model.AreaIncome;
import com.mark.app.hjshop4a.ui.areaagent.billreview.model.AreaBillReview;
import com.mark.app.hjshop4a.ui.areaagent.businessreview.model.BusinessReview;
import com.mark.app.hjshop4a.ui.assedetail.model.AssetDetail;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;
import com.mark.app.hjshop4a.ui.bankcard.model.BankCategory;
import com.mark.app.hjshop4a.ui.bankcard.model.InfoBank;
import com.mark.app.hjshop4a.ui.business.billrecord.model.BillsRecord;
import com.mark.app.hjshop4a.ui.business.consumecommit.model.Custom;
import com.mark.app.hjshop4a.ui.consumptionbill.model.MemberTradeInList;
import com.mark.app.hjshop4a.ui.goldbeanconsume.model.BusniessGoldBeanCS;
import com.mark.app.hjshop4a.ui.businessapply.model.BusinessApply;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BalanceList;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BalanceWithDrawList;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BeanList;
import com.mark.app.hjshop4a.ui.consumptionbill.model.BeanTradeInList;
import com.mark.app.hjshop4a.ui.consumptionbill.model.TopUpList;
import com.mark.app.hjshop4a.ui.goldbeanconsume.model.BeanConsume;
import com.mark.app.hjshop4a.ui.goldbeanconsume.model.Shop;
import com.mark.app.hjshop4a.ui.homepager.model.Lookup;
import com.mark.app.hjshop4a.ui.homepager.model.MeCenterInfo;
import com.mark.app.hjshop4a.ui.onlinerecharge.model.PayWayList;
import com.mark.app.hjshop4a.ui.recommend.model.ZXingCode;
import com.mark.app.hjshop4a.ui.userinfo.model.UserInfo;
import com.mark.app.hjshop4a.ui.withdraw.model.WithDraw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


import io.reactivex.Observable;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * 网络请求服务
 * Created by zhuwh on 2017/9/11.
 */

public interface PdMService {
    String baseUrl = BuildConfig.TEST_URL;

//    String baseUrl = "http://192.168.0.6:8080";

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static PdMService newService(String mBaseUrl, Context context) {
            Gson gson = new GsonBuilder().create();
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            OkHttpClient client = getOkHttpClient( context);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(PdMService.class);
        }

        /**
         * 获取拦截器
         *
         * @return
         */
        private static OkHttpClient getOkHttpClient(final Context context) {
            OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {

                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request oldRequest = chain.request();
                    Request.Builder builder = oldRequest.newBuilder();

                    builder.method(oldRequest.method(), oldRequest.body());

                    //获取token
                    String token = App.getToken();
                    if (TextUtils.isEmpty(token)) {
                        token = "";
                    }
                    //获取平台
                    String platform = AppContext.getPlatForm();

                    //获取版本号
                    String version =AppContext.versionName();
                    //刷新token
//                    token = refreshTOken(chain, oldRequest, token,context);

                    builder.addHeader("Authorization", token);
//                            .addHeader("platform", platform)
//                            .addHeader("version",version);

                    Request newRequest = builder.build();
                    LogUtils.logFormat(this, "intecept", newRequest);
                    return chain.proceed(newRequest);
                }


                public String refreshToken() {
//                    LoginRepo repo = App.get().getAppContext().getLoginRepo();
//                    if (repo == null) return "";
//
//
//                    String refreshToken = repo.getRefreshToken();
//                    long nowTime = repo.getNowTime();
//                    long expTime = repo.getExpiresIn();
//                    long currentTime = System.currentTimeMillis();
//                    long parseTime = (currentTime - nowTime) * 3 / 1000;
//                    if (parseTime > expTime && expTime > 0 && nowTime > 0) {
//                        return refreshToken;
//                    }
                    return "";
                }
                /**
                 * 刷新Token
                 * @param chain
                 * @param original
                 * @param token
                 * @return
                 * @throws IOException
                 */
                private String refreshTOken(Chain chain, Request original, String token, Context context) throws IOException {
                    if (!TextUtils.isEmpty(refreshToken())) {
                        LogUtils.logFormat("ServiceHelper", "intercept", "刷新token");
                        RequestBody body = new FormBody.Builder()
                                .add("refreshToken", refreshToken())
                                .add("grantType", "refreshToken")
                                .build();
                        Request requestRefresh = original.newBuilder()
                                .addHeader("Authorization", token)
//                                .addHeader("version", version)
//                                .addHeader("platform", platform)
                                .url(baseUrl + "/api/oauth2/refresh/token")
                                .post(body)
                                .build();
                        Response resposnRefresh = chain.proceed(requestRefresh);
                        LogUtils.logFormat("ServiceHelper", "intercept", "resposnRefresh:" + resposnRefresh);
                        if (resposnRefresh.isSuccessful()) {
                            String tempResponse = resposnRefresh.body().string();
                            LogUtils.logFormat("ServiceHelper", "intercept", "tempResponse:" + tempResponse);

                            JsonParser parser = new JsonParser();
                            JsonObject allJson = parser.parse(tempResponse).getAsJsonObject();

                            if (!allJson.get("result").isJsonNull()) {
                                JsonObject dataJson = allJson.getAsJsonObject("result");
                                token = dataJson.get("accessToken").getAsString();
                                int expiresIn = dataJson.get("expiresIn").getAsInt();
                                String refresh_token = dataJson.get("refresh_token").getAsString();
                                long nowTime = allJson.get("nowTime").getAsLong();
                                //获取新的token信息
                                LoginRepo repo = new LoginRepo().setAccessToken(token).setExpiresIn(expiresIn).setRefreshToken(refresh_token).setNowTime(nowTime);
//                                App.get().setLogin(repo);
                            }
                        }
                    }
                    return token;
                }
            }).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS)
                    .cookieJar(new CookieManger(context) )
                    .build();
            return client;
        }

    }

//    //后台日志
//    @FormUrlEncoded
//    @POST("/api/log/push")
//    Observable<BaseResultEntity> logPush(@Field("logDesc") String logDesc);
//
//    //后台绑定推送别名
//    @FormUrlEncoded
//    @POST("/api/push/bindAlias")
//    Observable<BaseResultEntity> bindAlias(
//            @Field("type") int type,
//            @Field("registrationId") String registrationId,
//            @Field("alias") String alias);
//
//    @GET("/api/startPage")
//    Observable<BaseResultEntity> getRibots();
//
    /**
     * 强制更新
     **/
    @GET("/api/page/forceUpdate")
    Observable<BaseResultEntity<Lookup>> forceUpdate(@Header("version") int version);

//图片上传
    @POST("/api/user/common/uploadFile")
    Observable<BaseResultEntity<String>> uploadImage(@Body RequestBody body);
    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("/api/oauth2/login")
    Observable<BaseResultEntity<LoginRepo>> login(@Field("account") String account,
                                                  @Field("password") String password,
                                                  @Field("type") int type);


    /**
     * 注册
     *
     * @return
     */
    @FormUrlEncoded
    @POST("/api/user/reg")
    Observable<BaseResultEntity<LoginRepo>> register(@FieldMap Map<String, String> map);

    /*
    * 登出*/

    @GET("/api/oauth2/logout")
    Observable<BaseResultEntity>logout();

    /*忘记密码*/
    @FormUrlEncoded
    @POST("/api/pwd/reset")
    Observable<BaseResultEntity>forgetPSW(@Field("userName") String userName,
                                          @Field("verification") String verification,
                                          @Field("passwd") String passwd);

    /*
    * 验证码0 登录 1 注册 2 找回密码
    * */
    @GET("/api/user/getCaptcha")
    Observable<BaseResultEntity>getCode(@Query("telephone") String telephone,
                                        @Query("macId") String macId,
                                        @Query("type") String type);
/*
* 银行卡
* */
    @GET("/api/app/bank/list")
    Observable<BaseResultEntity<ArrayList<BankCard>>>getBankList(@QueryMap Map<String, String> headers);

/*
* 新增银行卡1 添加 2 删除
* */
    @FormUrlEncoded
    @POST("/api/app/bank/edit")
    Observable<BaseResultEntity>editBnakCard(@Field("bankId") long bankId,
                                             @Field("type") int type,
                                            @Field("accountHolder") String accountHolder,
                                            @Field("bankCategoryId") long bankCategoryId,
                                            @Field("bankBranchName") String bankBranchName,
                                            @Field("bankAccount") String bankAccount);
    /*返回银行*/
    @GET("/api/app/bank/category/list")
    Observable<BaseResultEntity<List<BankCategory>>>bangkCategory();
    /*
    //开户行id
    * */
    @GET("/api/merchant/data/config/bank")
    Observable<BaseResultEntity<InfoBank>> configBank();

    /*
    * 我的*/
    @GET("/api/app/center")
    Observable<BaseResultEntity<MeCenterInfo>>center(@Query("userType") int userType);
    /*
     * 我的*/
    @GET("/api/app/center")
    Observable<BaseResultEntity<com.mark.app.hjshop4a.uinew.homepager.model.MeCenterInfo>>center();
/*
*  资产明细 */
    @GET("/api/app/property/detail")
    Observable<BaseResultEntity<AssetDetail>>getAssetDetail(@Query("userType") int userType);
    /*
    * 金豆消费数据*/
    @GET("/api/app/bean/consume/get")
    Observable<BaseResultEntity<BeanConsume>>getConsume();

    /*店铺id 获取店铺名称*/
    @GET("/api/app/shop/shopInfo")
    Observable<BaseResultEntity<Shop>>getShopName(@Query("shopId") long shopId);
    /*金豆消费*/
    @FormUrlEncoded
    @POST("/api/app/bean/consume")
    Observable<BaseResultEntity>beanconsume(@Field("shopId") long shopId,
                                            @Field("beanTradeInNum") String beanTradeInNum);
    /*
    * 金豆换购数据
    * */
    @GET("/api/app/bean/tradeIn/get")
    Observable<BaseResultEntity<BusniessGoldBeanCS>>tradeInget();
    /*金豆换购
    * */
    @FormUrlEncoded
    @POST("/api/app/bean/tradeIn")
    Observable<BaseResultEntity>tradeIn(@Field("beanTradeInNum") int beanTradeInNum,
                                        @Field("cpatCha") String cpatCha);
    /*在线充值数据*/
    @GET("/api/app/topUp/online/get")
    Observable<BaseResultEntity<PayWayList>>onLineGet();
    /*在线充值*/
    @FormUrlEncoded
    @POST("/api/app/topUp/online")
    Observable<BaseResultEntity>onLine(@Field("topUpMoney") String topUpMoney,
                                       @Field("payWayCode") int payWayCode);

    /*我的推荐*/

    @GET("/api/app/recommend")
    Observable<BaseResultEntity<ZXingCode>>recommend(@QueryMap Map<String, String> map);
    /*
    * 商家收豆二维码
    * */
    @GET("/api/app/merchant/bean/QRcode")
    Observable<BaseResultEntity>merchantQRcode();

    /*消费账单金豆消费 userType=1 consummerType =1*/
    @GET("/api/app/comsumer/bill/list")
    Observable<BaseResultEntity<BeanList>>memberBeanList(@Query("userType") int userType,
                                                         @Query("consumerType") int consumerType,
                                                         @QueryMap Map<String, String> map);
    /*消费账单余额消费 userType=1 consummerType =2*/
    @GET("/api/app/comsumer/bill/list")
    Observable<BaseResultEntity<BalanceList>>memberBalanceList(@Query("userType") int userType,
                                                               @Query("consumerType") int consumerType,
                                                               @QueryMap Map<String, String> map);
    /*消费账单充值 userType=1 consummerType =4*/
    @GET("/api/app/comsumer/bill/list")
    Observable<BaseResultEntity<TopUpList>>TopUpList(@Query("userType") int userType,
                                                     @Query("consumerType") int consumerType,
                                                     @QueryMap Map<String, String> map);
    /*消费账单余额提现userType=2 consummerType =3*/
    @GET("/api/app/comsumer/bill/list")
    Observable<BaseResultEntity<BalanceWithDrawList>>busniessbalanceWithDrawList(@Query("userType") int userType,
                                                                                 @Query("consumerType") int consumerType,
                                                                                 @QueryMap Map<String, String> map);
    /*消费账单金豆兑换userType=2 consummerType =1*/
    @GET("/api/app/comsumer/bill/list")
    Observable<BaseResultEntity<BeanTradeInList>>busniessbeanTradeIn(@Query("userType") int userType,
                                                                     @Query("consumerType") int consumerType,
                                                                     @QueryMap Map<String, String> map);

    /*消费账单金豆消费 userType=2 consummerType =6*/
    @GET("/api/app/comsumer/bill/list")
    Observable<BaseResultEntity<MemberTradeInList>>memberBeanTrade(@Query("userType") int userType,
                                                                   @Query("consumerType") int consumerType,
                                                                   @QueryMap Map<String, String> map);

    /*申请商户数据*/
    @GET("/api/app/merchant/apply/get")
    Observable<BaseResultEntity<BusinessApply>>getMerchantapply();
    /*申请商户数据*/
    @GET("/api/app/merchant/apply/get/info")
    Observable<BaseResultEntity<BusinessApply>>getMerchantapplyInfo(@Query("shopId") long shopId);
    /*申请商户*/
    @FormUrlEncoded
    @POST("/api/app/merchant/apply")
    Observable<BaseResultEntity>merchantApply(@FieldMap  Map<String, String> map );
    /*商户申请审核*/
    @FormUrlEncoded
    @POST("/api/app/proxy/toAccept")
    Observable<BaseResultEntity>toAccept(@Field("shopId") long shopId,
                                         @Field(("auditStatus"))int auditStatus ,
                                         @Field(("auditRemark")) String auditRemark);
    /*个人信息获取*/
    @GET("/api/app/getUserInfo")
    Observable<BaseResultEntity<UserInfo>>getUserInfo(@Query("type") int type);

    /*修改个人信息*/
    @FormUrlEncoded
    @POST("/api/app/userInfo/update")
    Observable<BaseResultEntity>setUserInfo(@Field( "type") int type,
                                            @FieldMap Map<String, String> map);

    /*商家报单-获取数据*/
    @GET("/api/app/merchant/customs/data")
    Observable<BaseResultEntity<Custom>>getCustomsData();

    /*商家报单账单*/
    @GET("/api/app/merchant/customs/list")
    Observable<BaseResultEntity<BillsRecord>>getCustomsList(@QueryMap Map<String, String> map,
                                                            @Query("startTime") long startTime,
                                                            @Query("endTime") long endTime);
    /*商家报单提交*/
    @FormUrlEncoded
    @POST("/api/app/customs")
    Observable<BaseResultEntity>customs(@FieldMap Map<String, String> map);

    /*可以提现数据*/
    @GET("/api/app/balance/withDraw/get")
    Observable<BaseResultEntity<WithDraw>>withDrawget();
    /*提现*/
    @FormUrlEncoded
    @POST("/api/app/balance/withDraw")
    Observable<BaseResultEntity>withDraw(@Field("userType")int userType,
                                         @Field("withDraw")String withDraw,
                                         @Field("bank")String bank,
                                         @Field("bankNumber")String bankNumber,
                                         @Field("remark")String remark);
    /*区域收益  类型 1 市 2 省*/
    @GET("/api/app/area/income")
    Observable<BaseResultEntity<AreaIncome>>areaincome(@Query("type") int type);

    /*区域商户 类型 1 市 2 省*/
    @GET("/api/app/area/merchant")
    Observable<BaseResultEntity<AreaBusniess>>areaMerchant(@Query("type") int type,
                                                           @QueryMap Map<String, String> map,
                                                           @Query("startTime") long startTime,
                                                           @Query("endTime") long endTime,
                                                           @Query("cityId") long cityId);
    /*区域商户 类型 1 市 2 省*/
    @GET("/api/app/area/merchant/info")
    Observable<BaseResultEntity<AreaBusniess>>areaMerchant(@Query("type") int type,
                                                           @QueryMap Map<String, String> map,
                                                           @Query("startTime") long startTime,
                                                           @Query("endTime") long endTime,
                                                           @Query("cityId") long cityId,
                                                           @Query("userId") long userId);
    /*.代理业绩* 类型 1 市 2 省*/
    @GET("/api/app/proxy/performance")
    Observable<BaseResultEntity<AgentPreformance>>agentPerformance(@Query("type") int type,
                                                                   @QueryMap Map<String, String> map,
                                                                   @Query("startTime") long startTime,
                                                                   @Query("endTime") long endTime,
                                                                   @Query("cityId") long cityId
                                                                   );
    /*.代理业绩* 类型 1 市 2 省*/
    @GET("/api/app/proxy/performance/info")
    Observable<BaseResultEntity<AgentPreformance>>agentPerformance(@Query("type") int type,
                                                                   @QueryMap Map<String, String> map,
                                                                   @Query("startTime") long startTime,
                                                                   @Query("endTime") long endTime,
                                                                   @Query("cityId") long cityId,
                                                                   @Query("userId") long userId);


    /*报单申请*/
    @GET("/api/app/merchant/customs/list")
    Observable<BaseResultEntity<AreaBillReview>>areaBillReview( @QueryMap Map<String, String> map,
                                                                @Query("startTime") long startTime,
                                                                @Query("endTime") long endTime);

    /**/
    @GET("/api/app/merchant/apply/log")
    Observable<BaseResultEntity<BusinessReview>>busunessReview( @QueryMap Map<String, String> map);
/*获取图片验证码*/
    @GET("/api/randomVerification")
    Observable<BaseResultEntity>randomVerification();

    /*商户申请审核*/
    @FormUrlEncoded
    @POST("/api/app/proxy/toAccept")
    Observable<BaseResultEntity>merchantToAccept(@Field("shopId") long shopId,
                                                 @Field("auditStatus")int auditStatus,
                                                 @Field("auditRemark") String auditRemark);

    /*商户报单(代理商)-审核  审核状态 1 通过 2 不通过*/
    @FormUrlEncoded
    @POST("/api/app/customs/proxy/audit")
    Observable<BaseResultEntity>proxyToAccept(@Field("offlineOrderSn") String offlineOrderSn,
                                              @Field("auditStatus")int auditStatus,
                                              @Field("remark") String remark);
}
