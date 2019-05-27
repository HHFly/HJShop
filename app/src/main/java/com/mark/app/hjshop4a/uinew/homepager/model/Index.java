package com.mark.app.hjshop4a.uinew.homepager.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Index extends BaseModel {
    List<Banner>  banners ;//banner列表
    List<ShowProduct> products;//展示图片列表

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<ShowProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ShowProduct> products) {
        this.products = products;
    }
}
