package com.mark.app.hjshop4a.uinew.homepager.model;

import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.common.utils.StringUtils;

public class UpgradeSetting extends BaseModel {
    String version;//版本号
    String url;//string	下载地址

    int status;//状态0 不更新（已是最新版本）1 用户自选 2强制更新
    String context;//更新内容
    /**
     * 获取文件名
     *
     * @return
     */
    public String getFileName() {
        return "qie" + version + ".apk";
    }
    public int  getAppUpgradeVersion() {
        return Integer.parseInt(version) ;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppUpgradeUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getAppUpgradeStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAppUpgradeDesc() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
