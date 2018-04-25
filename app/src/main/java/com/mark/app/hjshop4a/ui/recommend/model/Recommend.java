package com.mark.app.hjshop4a.ui.recommend.model;

/**
 * Created by pc on 2018/4/16.
 */

public class Recommend {
    private  String userName;
  private   String regTime;

    public String getName() {
        return userName;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getDate() {
        return regTime;
    }

    public void setDate(String date) {
        this.regTime = date;
    }
}
