package com.mark.app.hjshop4a.ui.areaagent.agentperformance;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.model.AgentPreformance;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.model.PerformanceCitys;

import java.util.List;

/**
 * Created by pc on 2018/4/21.
 */

public class AgentPrefermanceAdapter extends BaseHasTopListRvAdapter<AgentPreformance,PerformanceCitys> {

    public AgentPrefermanceAdapter(AgentPreformance agentPreformance, List<PerformanceCitys> performanceCitys) {
        super(agentPreformance, performanceCitys);
    }

    @Override

    public int getTopItemResId() {
        return R.layout.item_agent_performance_top_2;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_agent_performance_body;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, AgentPreformance agentPreformance) {
        if(agentPreformance.getPerformanceCity()!=null&&agentPreformance.getPerformanceCity().getPerformanceCityDay()!=null&&agentPreformance.getPerformanceCity().getPerformanceCityMonth()!=null&&agentPreformance.getPerformanceCity().getPerformanceCityTotal()!=null) {
            holder.setVisibility(true);
            holder.text(R.id.tv_1_1, agentPreformance.getPerformanceCity().getPerformanceCityDay().getTurnOver());
            holder.text(R.id.tv_1_2, agentPreformance.getPerformanceCity().getPerformanceCityDay().getUserNum());
            holder.text(R.id.tv_1_3, agentPreformance.getPerformanceCity().getPerformanceCityDay().getShopNum());
            holder.text(R.id.tv_2_1, agentPreformance.getPerformanceCity().getPerformanceCityMonth().getTurnOver());
            holder.text(R.id.tv_2_2, agentPreformance.getPerformanceCity().getPerformanceCityMonth().getUserNum());
            holder.text(R.id.tv_2_3, agentPreformance.getPerformanceCity().getPerformanceCityMonth().getShopNum());
            holder.text(R.id.tv_3_1, agentPreformance.getPerformanceCity().getPerformanceCityTotal().getTurnOver());
            holder.text(R.id.tv_3_2, agentPreformance.getPerformanceCity().getPerformanceCityTotal().getUserNum());
            holder.text(R.id.tv_3_3, agentPreformance.getPerformanceCity().getPerformanceCityTotal().getShopNum());
        }else {
            holder.setVisibility(false);
        }

    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, PerformanceCitys performanceCitys) {
        if(performanceCitys!=null){
            if(performanceCitys!=null) {  //筛选后的数据
                holder.text(R.id.store_name, performanceCitys.getShopName());
                holder.text(R.id.userName, performanceCitys.getUserName());
                holder.text(R.id.newUserDay, performanceCitys.getNewUserDay());
                holder.text(R.id.userTotal, performanceCitys.getUserTotal());
                holder.text(R.id.customsDay, performanceCitys.getCustomsDay());
                holder.text(R.id.beanIncomeDay, performanceCitys.getBeanIncomeDay());
                holder.text(R.id.customsMonth, performanceCitys.getCustomsMonth());
                holder.text(R.id.beanIncomeMonth, performanceCitys.getBeanIncomeMonth());
                holder.text(R.id.turnOverMonth, performanceCitys.getTurnOverMonth());
                holder.text(R.id.turnOverTotal, performanceCitys.getTurnOverTotal());
            }else {
                holder.visibility(R.id.rl_1,false);
                holder.visibility(R.id.rl_2,false);
                holder.visibility(R.id.rl_3,false);
                holder.visibility(R.id.rl_4,false);
            }
        }
    }


}
