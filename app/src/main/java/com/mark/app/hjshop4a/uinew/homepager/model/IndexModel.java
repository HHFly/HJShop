package com.mark.app.hjshop4a.uinew.homepager.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

public class IndexModel extends BaseModel {
    ShowProduct product1;
    ShowProduct product2;
    public void  setData(ShowProduct showProduct){
        if(product1==null){
            product1=showProduct;
        }else {
            if(product2==null)
            product2=showProduct;
        }
    }
    public boolean isNull(){
        return product1==null||product2==null;
    }
    public ShowProduct getProduct1() {
        return product1;
    }

    public void setProduct1(ShowProduct product1) {
        this.product1 = product1;
    }

    public ShowProduct getProduct2() {
        return product2;
    }

    public void setProduct2(ShowProduct product2) {
        this.product2 = product2;
    }
}
