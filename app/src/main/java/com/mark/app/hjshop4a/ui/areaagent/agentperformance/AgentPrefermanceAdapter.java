package com.mark.app.hjshop4a.ui.areaagent.agentperformance;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/21.
 */

public class AgentPrefermanceAdapter extends BaseHasTopListRvAdapter<AgentPrefermance,AgentPrefermance> {

    public AgentPrefermanceAdapter(AgentPrefermance agentPrefermance, ArrayList<AgentPrefermance> agentPrefermances) {
        super(agentPrefermance, agentPrefermances);
    }

    @Override

    public int getTopItemResId() {
        return R.layout.item_agent_performance_top;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_agent_performance_body;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, AgentPrefermance agentPrefermance) {

    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, AgentPrefermance agentPrefermance) {

    }
}
