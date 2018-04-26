package com.mark.app.hjshop4a.ui.businessapply.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/25.
 */

public class BusinessApply extends BaseModel {
    private BusniessApplyUserInfo busniessApplyUserInfo;
    private  CompanyInfo companyInfo;
    private List<ShopCategory> shopCategoryList;
    private  int  auditStatus;

    public BusniessApplyUserInfo getBusniessApplyUserInfo() {
        return busniessApplyUserInfo;
    }

    public void setBusniessApplyUserInfo(BusniessApplyUserInfo busniessApplyUserInfo) {
        this.busniessApplyUserInfo = busniessApplyUserInfo;
    }

    public CompanyInfo getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInfo companyInfo) {
        this.companyInfo = companyInfo;
    }

    public List<ShopCategory> getShopCategoryList() {
        return shopCategoryList;
    }

    public void setShopCategoryList(List<ShopCategory> shopCategoryList) {
        this.shopCategoryList = shopCategoryList;
    }

    public int getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(int auditStatus) {
        this.auditStatus = auditStatus;
    }
}
