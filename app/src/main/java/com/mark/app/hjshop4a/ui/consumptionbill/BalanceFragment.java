package com.mark.app.hjshop4a.ui.consumptionbill;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.model.consumptionbill.BalanceRepo;
import com.mark.app.hjshop4a.ui.consumptionbill.adapter.BalanceAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/17.
 */

public class BalanceFragment extends BaseFragment implements OnRefreshLoadmoreListener {
    //下拉刷新View
    SmartRefreshLayout mRefreshLayout;
    private BalanceAdapter balanceAdapter;
    //是否正在刷新数据
    boolean isRequestData;
    boolean isInit;


    @Override
    public int getContentResId() {
        return R.layout.fragment_bill;
    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initView() {
        isInit = false;
        initRefresh();
        initEmpty();
        List<BalanceRepo> a =new ArrayList<>();
        BalanceRepo b =new BalanceRepo();
        a.add(b);
        initRvAdapter( a,  false);
//        requestData(1);
//        mRefreshLayout.autoRefresh();
        isInit = true;
    }
    /**
     * 初始化下拉刷新View
     */
    private void initRefresh() {
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadmoreListener(this);
    }
    /**
     * 初始化空布局
     */
    private void initEmpty() {
        setVisibilityGone(R.id.empty_layout_empty, false);
        setTvText(R.id.empty_tv_title, "暂无记录");
    }
    /**
     * 刷新适配器
     *
     *
     * @param isRefresh
     */
    private void initRvAdapter(List<BalanceRepo> data, boolean isRefresh) {


        if (balanceAdapter == null) {
            RecyclerView rv = getView(R.id.recyclerView);
            balanceAdapter = new BalanceAdapter(data);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            if (rv != null) {
                rv.setLayoutManager(layoutManager);
                rv.setAdapter(balanceAdapter);
            }

        } else {
            balanceAdapter.notifyData(data, isRefresh);
        }

        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
        setVisibilityGone(R.id.empty_layout_empty, isShowEmpty);
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLoadmore(RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {

    }
}
