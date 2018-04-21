package com.mark.app.hjshop4a.ui.provinceagent;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by pc on 2018/4/21.
 */

public class ProvinceAreaBusniessAdapter extends BaseHasTopListRvAdapter<ProvinceAB,ProvinceAB> {

    public ProvinceAreaBusniessAdapter(ProvinceAB provinceAB, ArrayList<ProvinceAB> provinceABS) {
        super(provinceAB, provinceABS);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.item_area_busniess_top;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_province_area_busniess_body;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, ProvinceAB provinceAB) {

               holder.visibility(R.id.local_address,false);

    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, ProvinceAB provinceAB) {

    }


}
