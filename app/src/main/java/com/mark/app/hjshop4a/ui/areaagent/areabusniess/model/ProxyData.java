package com.mark.app.hjshop4a.ui.areaagent.areabusniess.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/28.
 */

public class ProxyData extends BaseModel {
    private String cityName; //城市名称
    private long cityId;    //城市ID
    private String areaProxy; //区域代理
    private String cellPhone; //	联系方式
    private int shopNum;    //店铺数量
    private int userNum;    //会员数量
    private String turnTotal; //营业额(汇总)
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getAreaProxy() {
        return areaProxy;
    }

    public void setAreaProxy(String areaProxy) {
        this.areaProxy = areaProxy;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public int getShopNum() {
        return shopNum;
    }

    public void setShopNum(int shopNum) {
        this.shopNum = shopNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getTurnTotal() {
        return turnTotal;
    }

    public void setTurnTotal(String turnTotal) {
        this.turnTotal = turnTotal;
    }
}
