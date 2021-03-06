package com.mark.app.hjshop4a.base.model;




import com.mark.app.hjshop4a.common.utils.MapUtils;

import java.util.Map;

/**
 * 功能：请求参数封装父类
 */

public class ParamBaseModel extends BaseModel {
    private Map<String, String> mMapData;

    public Map<String, String> getMap() {
        Map<String, String> map = MapUtils.getMap(this);
        if (mMapData == null) {
            mMapData = map;
        } else {
            mMapData.putAll(map);
        }
        return mMapData;
    }

    public void addMap(ParamBaseModel data) {
        if (data != null) {
            Map<String, String> map = data.getMap();
            if (mMapData == null) {
                mMapData = map;
            } else {
                mMapData.putAll(map);
            }
        }
    }
}
