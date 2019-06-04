package com.mark.app.hjshop4a.data.net;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mark.app.hjshop4a.BuildConfig;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.app.AppContext;
import com.mark.app.hjshop4a.common.utils.LogUtils;
import com.mark.app.hjshop4a.data.cookie.CookieManger;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.ui.homepager.model.UserCenter;
import com.mark.app.hjshop4a.uinew.homepager.model.Index;
import com.mark.app.hjshop4a.uinew.login.model.Token;
import com.mark.app.hjshop4a.uinew.userinfo.AccountInfo;
import com.mark.app.hjshop4a.uinew.userinfo.BindStatus;
import com.mark.app.hjshop4a.uinew.userinfo.UserCardInfo;
import com.mark.app.hjshop4a.uinew.userinfo.UserInfo;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
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
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RainbowService {
    String baseUrl = BuildConfig.TEST_URL;

//    String baseUrl = "http://192.168.0.6:8080";

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static RainbowService newService(String mBaseUrl, Context context) {
            Gson gson = new GsonBuilder().create();
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
            OkHttpClient client = getOkHttpClient( context);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

            return retrofit.create(RainbowService.class);
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

                    builder.addHeader("accessToken", token);
//                            .addHeader("platform", platform)
//                            .addHeader("version",version);

                    Request newRequest = builder.build();
                    LogUtils.logFormat(this, "intecept", newRequest);
                    return chain.proceed(newRequest);
                }
            }).readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS)
                    .cookieJar(new CookieManger(context) )
                    .build();
            return client;
        }

    }

    /**
     * 登录
     *
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/app/user/login")
    Observable<RainbowResultEntity<Token>> login(@Field("data") String  data);
    /*1.获取短信验证码
     * 1注册 2登陆 3重置密码 4绑定银行卡
     * */
    @POST("/api/user/getCaptcha")
    Observable<RainbowResultEntity>sendSMS(@Field("data") String  data);


    //修改登陆密码
    //修改类型1旧密码 2短信验证	是
    //新密码	是
    //旧密码	否
    //验证码	是

    @POST("/api/app/user/updateLoginPassword")
    Observable<RainbowResultEntity> updateLoginPassword(@Field("data") String  data);
    /*获取首页信息*/
    @GET("/api/app/user/index")
    Observable<RainbowResultEntity<Index>> getIndex();
    //获取用户中心数据
    @GET("/api/app/user/center")
    Observable<RainbowResultEntity<UserCenter>> getCenter();
    //6.获取绑定状态
    @GET("/api/app/user/bindStatus")
    Observable<RainbowResultEntity<BindStatus>> getBindStatus();
    //7.获取身份信息
    @GET("/api/app/user/getUserCardInfo")
    Observable<RainbowResultEntity<UserCardInfo>> getUserCardInfo();
    //8.修改身份信息
    /*真实姓名	是
        身份证号	是
        身份证正面	是
        身份证反面	是
        */
    @POST("/api/app/user/uploadIdcard")
    Observable<RainbowResultEntity> uploadIdcard(@Field("data") String  data);
    //    9.上传图片

    @POST("/api/app/user/common/uploadFile")
    Observable<RainbowResultEntity<String>> uploadImage(@Body RequestBody body);
    //10.修改联系方式
    @POST("/api/app/user/updateUserInfo")
    Observable<RainbowResultEntity>updateUserInfo(@Field("data") String  data);
    //11.获取qqwechat信息
    @GET("/api/app/user/getUserInfo")
    Observable<RainbowResultEntity<UserInfo>> getUserInfo();
    //12.绑定银行卡
    @POST("/api/app/user/bindBank")
    Observable<RainbowResultEntity> bindBank(@Field("data") String  data);
    //13.修改绑定银行卡
    @POST("/api/app/user/updateBank")
    Observable<RainbowResultEntity> updateBank(@Field("data") String  data);
    //14.获取绑定银行卡信息
    @GET("/api/shop/user/getUserBank")
    Observable<RainbowResultEntity<String>> getUserBank();
    //15.绑定买手账号
    @POST("/api/shop/user/addBuyerAccount")
    Observable<RainbowResultEntity> addBuyerAccount(@Field("data") String  data);
    //16.修改买手账号
    @POST("/api/shop/user/updateBuyerAccount")
    Observable<RainbowResultEntity> updateBuyerAccount(@Field("data") String  data);
//    17.获取买手账号列表
    @GET("/api/shop/user/getAcccountInfo")
    Observable<RainbowResultEntity<List<AccountInfo>>> getAcccountInfo();
    //18.获取买手审核账号
    @GET("/api/shop/user/getAcccountInfo")
    Observable<RainbowResultEntity<List<AccountInfo>>> getAcccountInfo1();
//19.获取买手审核通过账号
    @GET("/api/shop/user/getSuccessAcccounts")
    Observable<RainbowResultEntity<List<AccountInfo>>> getSuccessAcccounts();
    //20.获取买手账号信息
    @POST("/api/shop/user/updateBuyerAccount")
    Observable<RainbowResultEntity> updateBuyerAccount();
}