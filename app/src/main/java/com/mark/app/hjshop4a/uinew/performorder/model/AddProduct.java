package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class AddProduct extends BaseModel {
    long addProductId;
    String productPrice;
    String auditKeyWord;
    String browsePic;

    public long getAddProductId() {
        return addProductId;
    }

    public void setAddProductId(long addProductId) {
        this.addProductId = addProductId;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getAuditKeyWord() {
        return auditKeyWord;
    }

    public void setAuditKeyWord(String auditKeyWord) {
        this.auditKeyWord = auditKeyWord;
    }

    public String getBrowsePic() {
        return browsePic;
    }

    public void setBrowsePic(String browsePic) {
        this.browsePic = browsePic;
    }
}
