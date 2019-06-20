package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class AddProductPic extends BaseModel {
    String productBrowsePic;
    long addProductId;

    public void setAddProductId(long addProductId) {
        this.addProductId = addProductId;
    }

    public String getProductBrowsePic() {
        return productBrowsePic;
    }

    public void setProductBrowsePic(String productBrowsePic) {
        this.productBrowsePic = productBrowsePic;
    }

}
