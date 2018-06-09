package com.mark.app.hjshop4a.ui.homepager.model;

import android.support.annotation.RawRes;
import android.support.annotation.StringRes;

import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.ui.business.consumecommit.model.Model;

/**
 * Created by pc on 2018/6/8.
 */

public class CenterDataModel extends BaseModel {
    private @RawRes int img;
    private @StringRes int text;
    private String onclick;

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getText() {
        return text;
    }

    public void setText(int text) {
        this.text = text;
    }

    public String getOnclick() {
        return onclick;
    }

    public void setOnclick(String onclick) {
        this.onclick = onclick;
    }
}
