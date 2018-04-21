package com.mark.app.hjshop4a.model.launch;


import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * 引导页
 * Created by lenovo on 2017/9/7.
 */

public class GuidePage extends BaseModel {
    private int imageResId;
    /**
     * 图片地址
     */
    private String sgpImg;
    /**
     * 图片链接
     */
    private String sgpHref;
    /**
     * 排序
     */
    private int sort;

    public GuidePage() {
    }

    public GuidePage(int imageResId) {
        this.imageResId = imageResId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public GuidePage setImageResId(int imageResId) {
        this.imageResId = imageResId;
        return this;
    }

    public String getSgpImg() {
        return sgpImg;
    }

    public void setSgpImg(String sgpImg) {
        this.sgpImg = sgpImg;
    }

    public String getSgpHref() {
        return sgpHref;
    }

    public void setSgpHref(String sgpHref) {
        this.sgpHref = sgpHref;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
