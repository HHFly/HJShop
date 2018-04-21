package com.mark.app.hjshop4a.ui.areaagent.areabusniess;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBusniessAdapter extends BaseHasTopListRvAdapter<AreaBusniess,AreaBusniess > {
    public AreaBusniessAdapter(AreaBusniess areaBusniess, ArrayList<AreaBusniess> areaBusniesses) {
        super(areaBusniess, areaBusniesses);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.item_area_busniess_top;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_area_busniess_body;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, AreaBusniess areaBusniess) {

    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, AreaBusniess areaBusniess) {

    }


}
