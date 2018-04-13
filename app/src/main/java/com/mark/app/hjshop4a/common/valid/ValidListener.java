package com.mark.app.hjshop4a.common.valid;

/**
 * 校验监听
 * Created by lenovo on 2017/10/10.
 */

public interface ValidListener {
    /**
     * 店铺名称校验
     *
     * @param data
     * @return
     */
    boolean shopName(String data);

    /**
     * 校验座机
     *
     * @param data
     * @return
     */
    boolean phoneLandLine(String data);

    /**
     * 校验手机号
     *
     * @param data
     * @return
     */
    boolean phone(String data);

    /**
     * 校验密码
     *
     * @param data
     * @return
     */
    boolean password(String data);

    /**
     * 校验验证码
     *
     * @param data
     * @return
     */
    boolean code(String data);

    /**
     * 校验姓名
     *
     * @param data
     * @return
     */
    boolean name(String data);
}
