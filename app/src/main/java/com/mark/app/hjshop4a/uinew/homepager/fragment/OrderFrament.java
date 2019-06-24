package com.mark.app.hjshop4a.uinew.homepager.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfoParam;
import com.mark.app.hjshop4a.uinew.homepager.adapter.HomeAdapter;
import com.mark.app.hjshop4a.uinew.homepager.adapter.OrderAdapter;
import com.mark.app.hjshop4a.uinew.homepager.model.Index;
import com.mark.app.hjshop4a.uinew.homepager.model.ReciveOrderParam;
import com.mark.app.hjshop4a.uinew.homepager.model.ShowProduct;
import com.mark.app.hjshop4a.uinew.order.OrderInfo;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class OrderFrament extends BaseFragment {
    private OrderAdapter mAdapter;
    RefreshLayout refreshLayout;
    long id;
    @Override
    public int getContentResId() {
        return R.layout.fragment_blank;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void findView() {
        refreshLayout =getView(R.id.refreshLayout);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
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
        setTvText(R.id.titlebar_tv_title,"任务大厅");
//        setIvImage(R.id.bg_img,R.mipmap.home);
        setViewVisibilityGone(R.id.titlebar_iv_return,false);
        requestData(id);
    }

    @Override
    public void onClick(View v) {

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
                        refreshLayout.finishRefresh();
                    }
                });
    }

    /**
     * 初始化adapter
     *
     *
     */
    private void initRvAdapter(List<OrderInfo> data, boolean isfresh) {

        RecyclerView rv = getView(R.id.recyclerView);
        if(mAdapter==null) {
            if (rv != null) {
                mAdapter =new OrderAdapter(data);
                GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
                layoutManage.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return position==0? 1:2;
                    }
                });
                rv.setLayoutManager(layoutManage);
                rv.setAdapter(mAdapter);
            }
            mAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
                @Override
                public void onClick(OrderInfo data) {
                    getOrder(data);
                }

            });
        }else {
            mAdapter.notifyData(data,isfresh);
        }
    }
    /*确认接单*/
    private  void getOrder(OrderInfo data){
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
                    }

                    @Override
                    public void onAllFinish() {
//                        RefreshLayoutUtils.finish(mRefreshLayout);
                    }
                });
    }
}
