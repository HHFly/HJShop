package com.mark.app.hjshop4a.data.entity;

/**
 * 返回模型
 * Created by zhuwh on 2017/9/11.
 */

public class BaseResultEntity<T> {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误说明(文案)
     */
    private String reason;
    /**
     * 结果数据
     */
    private T result;
    /**
     * 时间
     */
    private long nowTime;

    public int getResultCode() {
        return code;
    }

    public void setResultCode(int resultCode) {
        this.code = resultCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getNowTime() {
        return nowTime;
    }

    public void setNowTime(long nowTime) {
        this.nowTime = nowTime;
    }
}
