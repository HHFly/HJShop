package com.mark.app.hjshop4a.uinew.orderList;

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
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.dialog.CloseOrderDialog;
import com.mark.app.hjshop4a.uinew.performorder.model.CloseOrderParam;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StatusOrderFragment extends BaseFragment implements OnRefreshLoadMoreListener {
    RefreshLayout refreshLayout;
    ShowOrderAdapter mAdapter;
    //是否正在刷新数据
    boolean isRequestData;
    PagingBaseModel mPagingData;
    String subOrderSn;
    int status=1;
    private CloseOrderDialog closeOrderDialog;



    public void setStatus(int status) {

        this.status = status;
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_order;
    }

    @Override
    public void findView() {
        refreshLayout =getView(R.id.refreshLayout);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        refreshLayout.autoRefresh();
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {

    }
    /**
     * 请求数据
     */
    public void requestData( final  int curPage,final  long timetamp) {
        if(!isRequestData) {
            isRequestData = true;
            PageParam pageParam =new PageParam();
            pageParam.setOrderStatus(status);
            pageParam.setPageNo(curPage);
            App.getServiceManager().getmService()
                    .getOrderList(pageParam.toPswJson())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RainbowObserver<OrderPage>() {


                        @Override
                        public void onSuccess(RainbowResultEntity<OrderPage> obj) {
                            OrderPage mData = JsonUtils.fromJson(obj.getResult(), OrderPage.class);
                            initRvAdapter(mData.getOrders(), curPage == 1);
                            if (mPagingData == null) {
                                mPagingData = new PagingBaseModel();
                            }
                            mPagingData.setPagingInfo(curPage, mData.getOrders());
                            RefreshLayoutUtils.finish(refreshLayout, mPagingData);
                            App.get().getHomePagerActivity().getStatusFragment().initData(mData);
                        }
                        @Override
                        public void onUnSuccessFinish() {
//                            initRvAdapter(null, curPage == 1);
                            RefreshLayoutUtils.finish(refreshLayout);
                        }
                        @Override
                        public void onAllFinish() {
                            super.onAllFinish();
//                        hideLoadingDialog();
                            isRequestData = false;

                        }
                    });
        }
    }
    /**
     * 初始化adapter
     *
     *
     */
    private void initRvAdapter(List<ShowOrder> data,boolean isFresh) {


        if(mAdapter==null) {
            RecyclerView rv = getView(R.id.recyclerView);

            if (rv != null) {
                mAdapter =new ShowOrderAdapter(data);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(mAdapter);
            }
            mAdapter.setOnItemClickListener(new ShowOrderAdapter.OnItemClickListener() {
                @Override
                public void onClickClose(ShowOrder data) {
                    subOrderSn=data.getSubOrderSn();
                    showCloseOrderDialog();
                }

                @Override
                public void onClickStart(ShowOrder data) {
                    ActivityJumpUtils.actStep(getActivity(),data.getSubOrderSn(),data.getStep());
                }


            });
        }else {
          mAdapter.notifyData(data,isFresh);
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        RefreshLayoutUtils.loadMore(refreshLayout, mPagingData, new RefreshLayoutUtils.OnLoadMoreListener() {

            @Override
            public void onLoadMore(int nextPage, long timestamp) {
                {
                    requestData(nextPage,timestamp);
                }
            }
        });
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        requestData(1,mPagingData.getTimestamp());
    }

    /*
     * 显示选择*/
    private  void  showCloseOrderDialog(){
        if(closeOrderDialog ==null){
            closeOrderDialog =new CloseOrderDialog();
        }
        closeOrderDialog.setOnDialogClickListener(new CloseOrderDialog.OnDialogClickListener() {
            @Override
            public void onClickNo(CloseOrderDialog dialog) {

            }

            @Override
            public void onClickYes(int data) {
                closeOrder(data);
            }



        });
        closeOrderDialog.setContent(getActivity());
        closeOrderDialog.show(getActivity().getFragmentManager());
    }
    private void closeOrder(int data){
        CloseOrderParam closeOrderParam =new CloseOrderParam();
        closeOrderParam.setSubOrderSn(subOrderSn);
        closeOrderParam.setFailReasonId(data);
        App.getServiceManager().getmService()
                .closeOrder(closeOrderParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        Boolean Sccess = JsonUtils.fromJson(obj.getResult(),Boolean.class);
                        if(Sccess){
                            refreshLayout.autoRefresh();

                        }else {
                            ToastUtils.show("失败！："+obj.getReason());
                        }
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}
