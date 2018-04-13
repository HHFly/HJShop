package com.mark.app.hjshop4a.common.androidenum.other;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Bundle的key值
 * Created by lenovo on 2017/8/27.
 */
@StringDef({
        BundleKey.TYPE,
        BundleKey.TITLE,
        BundleKey.URL,
        BundleKey.KEYWORD,
        BundleKey.PHONE,
        BundleKey.OBJECT,
        BundleKey.ADDRESS,
        BundleKey.ORDER_SN,
        BundleKey.ID,
        BundleKey.IMGURL,
        BundleKey.JSON,
        BundleKey.CODE,
        BundleKey.LAT,
        BundleKey.LNG,
        BundleKey.PARAM_OBJ,
        BundleKey.PARAM_STRING,
        BundleKey.SOURCE
})
@Retention(RetentionPolicy.SOURCE)
public @interface BundleKey {
    /**
     * 来源
     */
    String SOURCE = "source";
    /**
     * 对象参数
     */
    String PARAM_OBJ = "param";
    /**
     * 字符串参数
     */
    String PARAM_STRING = "param";
    /**
     * 纬度
     */
    String LAT = "lat";
    /**
     * 经度
     */
    String LNG = "lng";
    /**
     * code
     */
    String CODE = "code";
    /**
     * 各种id
     */
    String ID = "id";
    /**
     * 订单号
     */
    String ORDER_SN = "orderSn";
    /**
     * 关键词
     */
    String KEYWORD = "keyword";
    /**
     * 类型
     */
    String TYPE = "type";
    /**
     * 地址
     */
    String URL = "url";
    /**
     * 类型
     */
    String TITLE = "title";
    /**
     * 手机号
     */
    String PHONE = "phone";
    /**
     * 对象数据
     */
    String OBJECT = "object";
    /**
     * 地址
     */
    String ADDRESS = "address";
    /**
     * 图片地址
     */
    String IMGURL = "imgUrl";
    /**
     * json字符串
     */
    String JSON = "json";
}
