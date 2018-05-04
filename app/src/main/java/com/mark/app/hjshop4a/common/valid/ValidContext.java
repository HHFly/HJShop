package com.mark.app.hjshop4a.common.valid;

/**
 * 校验正则表达式
 * Created by lenovo on 2017/10/10.
 */

public interface ValidContext {
    /**
     * 用于校验是否允许点击按钮
     */
    interface ShowBtn {
//        String PHONE = "\\d{7,}";//手机号
        String PHONE = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        String PWD = "^.{6,}$";//密码
        String CODE = "\\d{6}";//短信验证码
        String NAME = "\\S{1,}";//姓名
        String PHONE_LANDLINE = "^\\d{7,}$";
        String SHOPNAME = "\\S{1,}";//姓名
    }

    /**
     * 校验输入格式
     */
    interface INPUT {
//        String PHONE = "(^44\\d{10}$)|(^0\\d{10}$)|(^1\\d{10}$)|(^[2-9]\\d{9}$)";//手机号
//        String PHONE = "\\d{11,}";//手机号
        String PHONE = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$";
        String PWD = "^.{6,}$";//密码
        String CODE = "\\d{6}";//短信验证码
        String NAME = "\\S{1,}";//姓名
        String PHONE_LANDLINE = "^\\d{7,}$";
        String SHOPNAME = "\\S{1,}";//姓名
    }
}
