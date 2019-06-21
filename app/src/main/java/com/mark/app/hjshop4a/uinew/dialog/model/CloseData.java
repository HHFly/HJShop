package com.mark.app.hjshop4a.uinew.dialog.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class CloseData extends BaseModel {
    String season;
    int type;

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    public static List<CloseData> getData(){
        List<CloseData> data =new ArrayList<>();
        CloseData closeData1 =new CloseData();
        closeData1.setSeason("未找到商品");
        closeData1.setType(1);
        data.add(closeData1);
        CloseData closeData2 =new CloseData();
        closeData2.setSeason("没时间做");
        closeData2.setType(2);
        data.add(closeData2);
        CloseData closeData3 =new CloseData();
        closeData3.setSeason("货款价格与平台不符");
        closeData3.setType(3);
        data.add(closeData3);
        CloseData closeData4 =new CloseData();
        closeData4.setSeason("其它原因");
        closeData4.setType(4);
        data.add(closeData4);
        return data;
     }
}
