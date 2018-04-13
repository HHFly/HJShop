package com.mark.app.hjshop4a.common.valid;

import java.util.regex.Pattern;

/**
 * 校验工具类(用于校验按钮是否允许点击)
 * Created by lenovo on 2017/10/10.
 */

public class ValidShowBtnUtils implements ValidListener {

    private static ValidShowBtnUtils utils;

    public ValidShowBtnUtils() {
    }

    public static ValidShowBtnUtils get() {
        if (utils == null) {
            synchronized (ValidShowBtnUtils.class) {
                if (utils == null) {
                    utils = new ValidShowBtnUtils();
                }
            }
        }
        return utils;
    }

    @Override
    public boolean shopName(String data) {
        return Pattern.matches(ValidContext.ShowBtn.SHOPNAME, data);
    }

    @Override
    public boolean phoneLandLine(String data) {
        return Pattern.matches(ValidContext.ShowBtn.PHONE_LANDLINE, data);
    }

    @Override
    public boolean phone(String data) {
        return Pattern.matches(ValidContext.ShowBtn.PHONE, data);
    }

    @Override
    public boolean password(String data) {
        return Pattern.matches(ValidContext.ShowBtn.PWD, data);
    }

    @Override
    public boolean code(String data) {
        return Pattern.matches(ValidContext.ShowBtn.CODE, data);
    }

    @Override
    public boolean name(String data) {
        return Pattern.matches(ValidContext.ShowBtn.NAME, data);
    }
}
