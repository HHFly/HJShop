package com.mark.app.hjshop4a.ui.recommend.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/16.
 */

public class ZXingCode extends BaseModel{
    private  String qRCode ;
    private ArrayList<Recommend>  recommendList;

    public String getCode() {
        return qRCode;
    }

    public void setCode(String code) {
        this.qRCode = code;
    }

    public ArrayList<Recommend> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(ArrayList<Recommend> recommendList) {
        this.recommendList = recommendList;
    }
}
