package com.mark.app.hjshop4a.ui.areaagent.areabusniess;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.ui.areaagent.areabusniess.model.AreaBusniess;
import com.mark.app.hjshop4a.ui.areaagent.areabusniess.model.MerchantData;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AreaBusniessAdapter extends BaseHasTopListRvAdapter<AreaBusniess,MerchantData> {
    public AreaBusniessAdapter(AreaBusniess areaBusniess, List<MerchantData> merchantData) {
        super(areaBusniess, merchantData);
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
        if (areaBusniess!=null){
                holder.text(R.id.turnOverTotal,areaBusniess.getTurnOverTotal());
                holder.text(R.id.turnOverMonth,areaBusniess.getTurnOverMonth());
                holder.text(R.id.cityName,areaBusniess.getCityName());
        }
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, MerchantData merchantData) {
        if(merchantData!=null) {
            holder.text(R.id.shopName, merchantData.getShopName());
            holder.text(R.id.userName, merchantData.getUserName());
            holder.text(R.id.cellPhone, merchantData.getCellPhone());
            holder.text(R.id.regTime, merchantData.getRegTime());
            holder.text(R.id.turnOver, merchantData.getTurnOver());
        }
    }



}
