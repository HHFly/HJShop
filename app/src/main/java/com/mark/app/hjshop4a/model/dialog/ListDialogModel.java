package com.mark.app.hjshop4a.model.dialog;


import com.mark.app.hjshop4a.base.model.BaseModel;

/**
 * 列表对话框模型
 * Created by lenovo on 2017/9/5.
 */

public class ListDialogModel extends BaseModel {
    private int id;
    private String name;

    public ListDialogModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
