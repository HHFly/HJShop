package com.mark.app.hjshop4a.ui.provinceagent.areabusniess;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.ui.areaagent.areabusniess.model.AreaBusniess;
import com.mark.app.hjshop4a.ui.areaagent.areabusniess.model.ProxyData;
import com.mark.app.hjshop4a.ui.areaagent.businessreview.BusinessReviewAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class ProvinceAreaBusniessAdapter extends BaseHasTopListRvAdapter<AreaBusniess,ProxyData> {
    public ProvinceAreaBusniessAdapter(AreaBusniess areaBusniess, List<ProxyData> proxyData) {
        super(areaBusniess, proxyData);
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
    public void bindTopData(AutoViewHolder holder, int topPos, AreaBusniess areaBusniess) {
        holder.visibility(R.id.local_address,false);
        if(areaBusniess!=null){
            holder.text(R.id.turnOverTotal,areaBusniess.getTurnOverTotal());
            holder.text(R.id.turnOverMonth,areaBusniess.getTurnOverMonth());
        }
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, final ProxyData proxyData) {
            if(proxyData!=null){
                holder.text(R.id.cityName,proxyData.getCityName());
                holder.text(R.id.areaProxy,proxyData.getAreaProxy());
                holder.text(R.id.cellPhone	,proxyData.getCellPhone());
                holder.text(R.id.shopNum,proxyData.getShopNum());
                holder.text(R.id.userNum,proxyData.getUserNum());
                holder.text(R.id.turnTotal,proxyData.getTurnTotal());
               holder.get(R.id.rl_location).setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       if(onItemClickListener!=null){
                           onItemClickListener.onClickDetails(proxyData.getCityId());
                       }
                   }
               });
            }
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {

        void onClickDetails(long cityId);
    }

}
