package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.List;

public class StepTwoParam extends BaseModel {
    String mainProductBrowsePic;
    String huobisanjiaPic1;
    String huobisanjiaPic2;
    String huobisanjiaPic3;
    String searchPic;
    List<AddProductPic> addProductPics;

    public String getMainProductBrowsePic() {
        return mainProductBrowsePic;
    }

    public void setMainProductBrowsePic(String mainProductBrowsePic) {
        this.mainProductBrowsePic = mainProductBrowsePic;
    }

    public String getHuobisanjiaPic1() {
        return huobisanjiaPic1;
    }

    public void setHuobisanjiaPic1(String huobisanjiaPic1) {
        this.huobisanjiaPic1 = huobisanjiaPic1;
    }

    public String getHuobisanjiaPic2() {
        return huobisanjiaPic2;
    }

    public void setHuobisanjiaPic2(String huobisanjiaPic2) {
        this.huobisanjiaPic2 = huobisanjiaPic2;
    }

    public String getHuobisanjiaPic3() {
        return huobisanjiaPic3;
    }

    public void setHuobisanjiaPic3(String huobisanjiaPic3) {
        this.huobisanjiaPic3 = huobisanjiaPic3;
    }

    public String getSearchPic() {
        return searchPic;
    }

    public void setSearchPic(String searchPic) {
        this.searchPic = searchPic;
    }

    public List<AddProductPic> getAddProductPics() {
        return addProductPics;
    }

    public void setAddProductPics(List<AddProductPic> addProductPics) {
        this.addProductPics = addProductPics;
    }
}
