package com.mark.app.hjshop4a.uinew.order;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfoParam;
import com.mark.app.hjshop4a.uinew.homepager.model.ReciveOrderParam;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderFragment extends BaseFragment  {
    SmartRefreshLayout mRefreshLayout;//刷新框架
    OrderAdapter orderAdapter;

    long id;

    public void setId(long id) {
        this.id = id;
    }
    @Override
    public int getContentResId() {
        return R.layout.fragment_order;
    }

    @Override
    public void findView() {
        mRefreshLayout =getView(R.id.refreshLayout);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestData(id);
            }
        });
    }

    @Override
    public void setListener() {
      
      
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.任务大厅);
        setViewVisibilityGone(R.id.titlebar_iv_return,false);
        requestData(id);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onUnFirstResume() {
        requestData(id);
    }

    //请求数据
    private void requestData(long id) {
        AccountInfoParam accountInfoParam =new AccountInfoParam();
        accountInfoParam.setBuyerAccountId(id);
        App.getServiceManager().getmService()
                .getWaitReciveOrder(accountInfoParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<List<OrderInfo>>() {
                    @Override
                    public void onSuccess(RainbowResultEntity<List<OrderInfo>> obj) {
                        if (obj.getResult()==null){
                            return;
                        }
                        List<OrderInfo> data = JsonUtils.getList(obj.getResult(),OrderInfo.class);
                        if(data!=null) {
                            initRvAdapter(data, true);
                        }
                    }
                    @Override
                    public void onAllFinish() {
                        mRefreshLayout.finishRefresh();
                    }
                });
    }
    private void initRvAdapter(List<OrderInfo> data, boolean isRefresh){

        if(orderAdapter==null){
            RecyclerView rv = getView(R.id.recyclerView);
            orderAdapter = new OrderAdapter(data);
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(orderAdapter);
            orderAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
                @Override
                public void onClickOrder(OrderInfo data) {
                    sureReciveOrder(data);
                }
            });
        }
        else {
            orderAdapter.notifyData(data,isRefresh);
        }
        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
        setVisibilityGone(R.id.empty_layout_empty, isShowEmpty);
    }
// 确认订单
    private void sureReciveOrder(final OrderInfo data) {
        ReciveOrderParam reciveOrderParam =new ReciveOrderParam();
        reciveOrderParam.setSubOrderSn(data.getSubOrderSn());
        reciveOrderParam.setBuyerAccountId(id);
        App.getServiceManager().getmService()
                .sureReciveOrder(reciveOrderParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {
                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        requestData(id);
                        ActivityJumpUtils.actStepOne(getActivity(),data.getSubOrderSn());
                    }

                });
    }
}
