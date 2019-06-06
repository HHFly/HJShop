package com.mark.app.hjshop4a.data.entity;

import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.PasswordUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class RainbowResultEntity<T> {
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
    private String result;
    /**
     * 时间
     */
    private long dateTime;

    private T obj ;



    public void setObj(T obj) {
        this.obj = obj;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        if(result==null){
            return null;
        }
        return PasswordUtil.decode(result);
    }

    public void setResult(String result) {
        this.result = result;
    }

    public long getDateTime() {
        return dateTime;
    }

    public void setDateTime(long dateTime) {
        this.dateTime = dateTime;
    }
}
