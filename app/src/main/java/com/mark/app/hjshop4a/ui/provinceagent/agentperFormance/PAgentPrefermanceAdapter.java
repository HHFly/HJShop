package com.mark.app.hjshop4a.ui.provinceagent.agentperFormance;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.model.AgentPreformance;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.model.PerformanceCitys;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.model.PerformanceProvinces;

import java.util.List;

/**
 * Created by pc on 2018/5/1.
 */

public class PAgentPrefermanceAdapter extends BaseHasTopListRvAdapter<AgentPreformance,PerformanceProvinces> {
    public PAgentPrefermanceAdapter(AgentPreformance agentPreformance, List<PerformanceProvinces> performanceProvinces) {
        super(agentPreformance, performanceProvinces);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.item_agent_performance_top_2;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_agent_performance_body_p;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, AgentPreformance agentPreformance) {
        if(agentPreformance.getPerformanceProvince()!=null&&agentPreformance.getPerformanceProvince().getPerformanceProvinceDay()!=null&&agentPreformance.getPerformanceProvince().getPerformanceProvinceMonth()!=null&&agentPreformance.getPerformanceProvince().getPerformanceProvinceTotal()!=null) {
            holder.text(R.id.tv_1_1, agentPreformance.getPerformanceProvince().getPerformanceProvinceDay().getTurnOver());
            holder.text(R.id.tv_1_2, agentPreformance.getPerformanceProvince().getPerformanceProvinceDay().getUserNum());
            holder.text(R.id.tv_1_3, agentPreformance.getPerformanceProvince().getPerformanceProvinceDay().getShopNum());
            holder.text(R.id.tv_2_1, agentPreformance.getPerformanceProvince().getPerformanceProvinceMonth().getTurnOver());
            holder.text(R.id.tv_2_2, agentPreformance.getPerformanceProvince().getPerformanceProvinceMonth().getUserNum());
            holder.text(R.id.tv_2_3, agentPreformance.getPerformanceProvince().getPerformanceProvinceMonth().getShopNum());
            holder.text(R.id.tv_1_1, agentPreformance.getPerformanceProvince().getPerformanceProvinceTotal().getTurnOver());
            holder.text(R.id.tv_1_2, agentPreformance.getPerformanceProvince().getPerformanceProvinceTotal().getUserNum());
            holder.text(R.id.tv_1_3, agentPreformance.getPerformanceProvince().getPerformanceProvinceTotal().getShopNum());
        }
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, final PerformanceProvinces performanceProvinces) {
        if(performanceProvinces!=null){
            holder.text(R.id.store_name,performanceProvinces.getCityName());
            holder.text(R.id.newUserDay,performanceProvinces.getNewUserDay());
            holder.text(R.id.userTotal,performanceProvinces.getUserTotal());
            holder.text(R.id.customsDay,performanceProvinces.getCustomsDay());
            holder.text(R.id.beanIncomeDay,performanceProvinces.getBeanIncomeDay());
            holder.text(R.id.customsMonth,performanceProvinces.getCustomsMonth());
            holder.text(R.id.beanIncomeMonth,performanceProvinces.getBeanIncomeMonth());
            holder.text(R.id.turnOverMonth,performanceProvinces.getTurnOverMonth());
            holder.text(R.id.turnOverTotal,performanceProvinces.getTurnOverTotal());
            holder.get(R.id.rl_location).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListener!=null){
                        onItemClickListener.onClickDetails(performanceProvinces.getCityId());
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
