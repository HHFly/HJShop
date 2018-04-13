package com.mark.app.hjshop4a.common.valid;

import java.util.regex.Pattern;

/**
 * 校验工具类(用于校验输入验证)
 * Created by lenovo on 2017/10/10.
 */

public class ValidUtils implements ValidListener {

    private static ValidUtils utils;

    public ValidUtils() {
    }

    public static ValidUtils get() {
        if (utils == null) {
            synchronized (ValidUtils.class) {
                if (utils == null) {
                    utils = new ValidUtils();
                }
            }
        }
        return utils;
    }

    @Override
    public boolean shopName(String data) {
        return Pattern.matches(ValidContext.INPUT.SHOPNAME, data);
    }

    @Override
    public boolean phoneLandLine(String data) {
        return Pattern.matches(ValidContext.INPUT.PHONE_LANDLINE, data);
    }

    @Override
    public boolean phone(String data) {
        return Pattern.matches(ValidContext.INPUT.PHONE, data);
    }

    @Override
    public boolean password(String data) {
        return Pattern.matches(ValidContext.INPUT.PWD, data);
    }

    @Override
    public boolean code(String data) {
        return Pattern.matches(ValidContext.INPUT.CODE, data);
    }

    @Override
    public boolean name(String data) {
        return Pattern.matches(ValidContext.INPUT.NAME, data);
    }
}
