package com.mark.app.hjshop4a.ui.businessapply.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * Created by pc on 2018/4/25.
 */

public class ShopCategory extends BaseModel {
    private  long shopCategoryId;
    private  String shopCategoryName;

    public long getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }
}
