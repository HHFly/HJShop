package com.mark.app.hjshop4a.common.utils;

import android.net.Uri;
import android.text.TextUtils;

/**
 * 链接地址解析工具类
 * 跳转到专题列表
 * pdapp://special/list?specialId=90
 * Created by lenovo on 2017/12/1.
 */

public class PdUrlUtil {
     static final String SCHEME = "pdapp";

    public static final String PARAMETER_KEY_SPECIAL_ID = "specialId";//专题id
    public static final String PARAMETER_KEY_SPECIAL_NAME = "specialName";//专题specialName
     public  static  final  String PARAMETER_KEY_REDNEWUSER_CODE ="redPacketCode";//新人红包code
 //   public static final String PARAMETER_KEY_REDNEWUSER_NAME = "rednewuserName";//专题specialName
    /**
     * 是否为panda内部链接
     *
     * @param url
     */
    public static boolean isPdAppUrl(String url) {
        return !TextUtils.isEmpty(url) && Uri.parse(url).getScheme().equals(SCHEME);
    }

    /**
     * 是否为专题列表
     *
     * @param url
     * @return
     */
    public static boolean isSpecialList(String url) {
        if (!TextUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            return uri.getAuthority().equals("special") && uri.getPath().equals("/list");
        }
        return false;
    }
  /*是否为新人红包
  * */
  public  static  boolean isRedNewUserAct(String url){
      if (!TextUtils.isEmpty(url)){
          Uri uri =Uri.parse(url);
          return uri.getAuthority().equals("redPacket");

      }
      return true;
  }
    /**
     * 获取Uri参数
     *
     * @param url
     * @param key
     * @param def
     * @return
     */
    public static long getLongQueryParameter(String url, String key, long def) {
        long result = def;
        String strValue = getQueryParameter(url, key);
        try {
            result = Long.valueOf(strValue);
        } catch (Exception e) {
            LogUtils.logFormat("PdUrlUtil", "getQueryParameter", "Long.valueOf(" + strValue + ")报错");
        }
        return result;
    }

    /**
     * 获取Uri参数
     *
     * @param url
     * @param key
     * @param def
     * @return
     */
    public static String getStringQueryParameter(String url, String key, String def) {
        String result = def;
        String strValue = getPacketCode(url, key);
        if (!TextUtils.isEmpty(strValue)) {
            result = strValue;
        }
        return result;
    }

    /**
     * 获取Uri参数
     *
     * @param url
     * @param key
     * @return
     */
    public static String getQueryParameter(String url, String key) {
        String result = null;
        if (!TextUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            try {
                result = uri.getQueryParameter(key);
            } catch (Exception e) {
                LogUtils.logFormat("PdUrlUtil", "getQueryParameter", "uri.getQueryParameter(" + key + ")报错");
            }
        }
        return result;
    }
    /*获取红包code
    * */
    public  static  String getPacketCode(String url ,String def){
        String result = null;
        if (!TextUtils.isEmpty(url)) {
            Uri uri = Uri.parse(url);
            try {
                result = uri.getEncodedQuery().substring(uri.getEncodedQuery().indexOf("=")+1);
            } catch (Exception e) {
                LogUtils.logFormat("PdUrlUtil", "getQueryParameter", "uri.getQueryParameter("  + ")报错");
            }
        }
        return result;
    }
}
