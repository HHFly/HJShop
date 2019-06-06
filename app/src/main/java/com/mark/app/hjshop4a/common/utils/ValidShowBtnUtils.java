package com.mark.app.hjshop4a.common.utils;

import java.util.regex.Pattern;

/**
 * 验证用户输入工具类
 * 用于显示按钮是否可点击
 * Created by lenovo on 2017/9/5.
 */

public class ValidShowBtnUtils {

    //短信验证码
    private static final String VERIFY_CODE = "\\d{6}";
    //手机号
//    private static final String PHONE = "^\\d{11,}$";
    private static final String PHONE ="^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}$";

    //密码
    private static final String PWD = "^.{6,20}$";
    //联系人
    private static final String CONTACTS = "^.{1,}$";
    //地址
    private static final String ADDRESS = "^\\S+$";
    //门牌号
    private static final String HOUSE_NUM = "^.{1,}$";
    //邮编
    private static final String ZIP_CODE = "^.{1,}$";

    /**
     * 邮编
     *
     * @param data
     * @return
     */
    public static boolean zipcode(String data) {
        return Pattern.matches(ZIP_CODE, data);
    }

    /**
     * 门牌号
     *
     * @param data
     * @return
     */
    public static boolean houseNum(String data) {
        return Pattern.matches(HOUSE_NUM, data);
    }

    /**
     * 验证地址
     *
     * @param data
     * @return
     */
    public static boolean address(String data) {
        return Pattern.matches(ADDRESS, data);
    }

    /**
     * 验证联系人
     *
     * @param data
     * @return
     */
    public static boolean contacts(String data) {
        return Pattern.matches(CONTACTS, data);
    }

    /**
     * 校验验证码
     *
     * @param data
     * @return
     */
    public static boolean verifyCode(String data) {
        return Pattern.matches(VERIFY_CODE, data);
    }

    /**
     * 校验手机号
     *
     * @param data
     * @return
     */
    public static boolean phone(String data) {
        return Pattern.matches(PHONE, data);
    }

    /**
     * 校验密码
     *
     * @param data
     * @return
     */
    public static boolean pwd(String data) {
        return Pattern.matches(PWD, data);
    }
    /**
     * 校验密码
     *
     * @param data
     * @return
     */
    public static boolean idCard(String data) {
         IdCardUtil idCardUtil =new IdCardUtil(data);
         return  0==idCardUtil.isCorrect();

    }
}
