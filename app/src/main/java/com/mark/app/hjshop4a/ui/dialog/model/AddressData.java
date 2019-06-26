package com.mark.app.hjshop4a.ui.dialog.model;

import com.mark.app.hjshop4a.uinew.bindinfo.model.CAddress;
import com.mark.app.hjshop4a.uinew.bindinfo.model.PAddress;

/**
 * Created by pc on 2018/5/9.
 */

public class AddressData {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {

        return name;
    }

    public AddressData() {
    }


    public AddressData(CAddress data) {
        this.id = String.valueOf(data.getCityId());
        this.name = data.getCityName();
    }
}
