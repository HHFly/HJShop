package com.mark.app.hjshop4a.ui.homepager.model;


import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * 功能：版本检测返回数据
 * 作者：ljju
 */

public class Lookup extends BaseModel {
    private int appUpgradeId;//id
    private int appUpgradeVersion;// 最新版本的版本号：100
    private String appUpgradeUrl;//最新版本的下载地址
    private int appUpgradeType;//1 android 2 ios
    /**
     * 更新类型：
     * 1、必须更新才能打开app
     * 2、提示更新但可以不更新打开app
     * 3、不更新
     */
    private int appUpgradeStatus;
    private int appUpgradeRole;//1:user ,2:merchant,3:delivery
    private String appUpgradeDesc;//更新描述信息

    public Lookup() {
    }

    /**
     * 获取文件名
     *
     * @return
     */
    public String getFileName() {
        return "panda" + getAppUpgradeVersion() + ".apk";
    }

    public int getAppUpgradeId() {
        return appUpgradeId;
    }

    public int getAppUpgradeVersion() {
        return appUpgradeVersion;
    }

    public String getAppUpgradeUrl() {
        return appUpgradeUrl;
    }

    public int getAppUpgradeType() {
        return appUpgradeType;
    }

    public int getAppUpgradeStatus() {
        return appUpgradeStatus;
    }

    public int getAppUpgradeRole() {
        return appUpgradeRole;
    }

    public String getAppUpgradeDesc() {
        return appUpgradeDesc;
    }

    public void setAppUpgradeId(int appUpgradeId) {
        this.appUpgradeId = appUpgradeId;
    }

    public void setAppUpgradeVersion(int appUpgradeVersion) {
        this.appUpgradeVersion = appUpgradeVersion;
    }

    public void setAppUpgradeUrl(String appUpgradeUrl) {
        this.appUpgradeUrl = appUpgradeUrl;
    }

    public void setAppUpgradeType(int appUpgradeType) {
        this.appUpgradeType = appUpgradeType;
    }

    public void setAppUpgradeStatus(int appUpgradeStatus) {
        this.appUpgradeStatus = appUpgradeStatus;
    }

    public void setAppUpgradeRole(int appUpgradeRole) {
        this.appUpgradeRole = appUpgradeRole;
    }

    public void setAppUpgradeDesc(String appUpgradeDesc) {
        this.appUpgradeDesc = appUpgradeDesc;
    }
}
