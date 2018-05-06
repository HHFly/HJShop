package com.mark.app.hjshop4a.ui.businessapply.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

/**
 * Created by pc on 2018/4/25.
 */

public class BusinessApply extends BaseModel {
    private BusniessApplyUserInfo userInfo;//	个人信息
    private  CompanyInfo companyInfo;//公司信息
    private List<ShopCategory> shopCategoryList;//类目列表
    private  int  auditStatus;//审核状态 0 审核中 1 审核通过 2 审核不通过
   private  int userAuditStatus;//用户审核状态 0待审核 1审核通过 2审核不通过

    public BusniessApplyUserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(BusniessApplyUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getUserAuditStatus() {
        return userAuditStatus;
    }

    public void setUserAuditStatus(int userAuditStatus) {
        this.userAuditStatus = userAuditStatus;
    }

    public BusniessApplyUserInfo getBusniessApplyUserInfo() {
        return userInfo;
    }

    public void setBusniessApplyUserInfo(BusniessApplyUserInfo busniessApplyUserInfo) {
        this.userInfo = busniessApplyUserInfo;
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
