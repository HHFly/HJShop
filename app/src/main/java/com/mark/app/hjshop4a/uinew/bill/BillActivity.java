package com.mark.app.hjshop4a.uinew.bill;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.FragmentUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.bill.Adapter.BillAdapter;
import com.mark.app.hjshop4a.uinew.bill.fragment.FiltrateSnFragment;
import com.mark.app.hjshop4a.uinew.bill.fragment.FiltrateTimeFragment;
import com.mark.app.hjshop4a.uinew.bill.model.BillRecord;
import com.mark.app.hjshop4a.uinew.bill.param.BillPageParam;
import com.mark.app.hjshop4a.uinew.orderList.OrderPage;
import com.mark.app.hjshop4a.uinew.orderList.PageParam;
import com.mark.app.hjshop4a.uinew.orderList.ShowOrder;
import com.mark.app.hjshop4a.uinew.orderList.ShowOrderAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.umeng.socialize.utils.ContextUtil.getContext;

public class BillActivity extends BaseActivity implements OnRefreshLoadMoreListener {
    RefreshLayout refreshLayout;
    PagingBaseModel mPagingData;
    BillAdapter mAdapter;
    private FrameLayout frameLayout;//主界面
    long time ;//查询时间
    String orderSn ;//查询订单号
    int mode=0;//0 时间 1 订单号
    //是否正在刷新数据
    boolean isRequestData;
    private BaseFragment mCurFragment;
    private FiltrateTimeFragment filtrateTimeFragment;
    private FiltrateSnFragment filtrateSnFragment;
    public PagingBaseModel getmPagingData() {
        if (mPagingData == null) {
            mPagingData = new PagingBaseModel();
        }
        return mPagingData;
    }
    @Override
    public int getContentViewResId() {
        return R.layout.activity_bill;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"账单");
        SimpleDateFormat formatter   =   new SimpleDateFormat("yyyy/MM");
        Date curDate =  new Date(System.currentTimeMillis());
        setTvText(R.id.tv_time, formatter.format(curDate));
        refreshLayout =getView(R.id.refreshLayout);
        refreshLayout.setOnRefreshLoadMoreListener(this);
        frameLayout =getView(R.id.fragment);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        time=c.getTimeInMillis();
        filtrateTimeFragment=new FiltrateTimeFragment();
        filtrateTimeFragment.setOnItemClickListener(new FiltrateTimeFragment.OnItemClickListener() {
            @Override
            public void onClickOK(long date,String strDate ) {
                time =date;
                requestByDate(1 ,0);
                frameLayout.setVisibility(View.GONE);
                setTvText(R.id.tv_time, strDate);
            }

            @Override
            public void onClickCancel() {
                frameLayout.setVisibility(View.GONE);
                Animation animBottomIN = AnimationUtils.loadAnimation(getActivity(),
                        R.anim.dialog_exit);
                frameLayout.startAnimation(animBottomIN);
            }
        });
        filtrateSnFragment=new FiltrateSnFragment();
        filtrateSnFragment.setOnItemClickListener(new FiltrateSnFragment.OnItemClickListener() {
            @Override
            public void onClickOK(String strDate) {
                orderSn=strDate;
                frameLayout.setVisibility(View.GONE);
                requestBySn(1 ,0);
            }

            @Override
            public void onClickCancel() {
                frameLayout.setVisibility(View.GONE);
            }
        });
        requestByDate(1,0);
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.filtrate_time);
        setClickListener(R.id.filtrate_orderSn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.filtrate_time:
                switchFragment(filtrateTimeFragment);
                break;
            case R.id.filtrate_orderSn:
                switchFragment(filtrateSnFragment);
                break;
        }
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        RefreshLayoutUtils.loadMore(refreshLayout, getmPagingData(), new RefreshLayoutUtils.OnLoadMoreListener() {

            @Override
            public void onLoadMore(int nextPage, long timestamp) {
                {
                   switch (mode){
                       case 0:
                           requestByDate(nextPage,timestamp);
                           break;
                       case 1:
                           requestBySn(nextPage,timestamp);
                           break;
                   }
                }
            }
        });
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        switch (mode){
            case 0:
                requestByDate(1,getmPagingData().getTimestamp());
                break;
            case 1:
                requestBySn(1,getmPagingData().getTimestamp());
                break;
        }
    }
    //根据时间查询
    public void requestByDate(final  int curPage,final  long tamp ){
        mode=0;
        BillPageParam billPageParam =new BillPageParam();
        billPageParam.setPageNo(curPage);
        billPageParam.setStartTime(time);
        requestData(curPage,billPageParam);
    }
    //根据订单号查询
    public void requestBySn(final  int curPage,final  long tamp ){
        mode=1;
        BillPageParam billPageParam =new BillPageParam();
        billPageParam.setPageNo(curPage);
        billPageParam.setOrderSn(orderSn);
        requestData(curPage,billPageParam);
    }
    /**
     * 请求数据
     */
    public void requestData( final  int curPage, BillPageParam pageParam) {
        if(!isRequestData) {
            isRequestData = true;

            App.getServiceManager().getmService()
                    .getOrderList(pageParam.toPswJson())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new RainbowObserver() {
                        @Override
                        public void onSuccess(RainbowResultEntity obj) {

                           List<BillRecord> mData = JsonUtils.getList(obj.getResult(), BillRecord.class);
                            if(mData!=null) {

                                getmPagingData().setPagingInfo(curPage, mData);

                                RefreshLayoutUtils.finish(refreshLayout, getmPagingData());



                                initRvAdapter(mData, curPage == 1);
                            }else {
                                initRvAdapter(null, curPage == 1);
                            }
                        }
                        @Override
                        public void onUnSuccessFinish() {
                            initRvAdapter(null, curPage == 1);

                        }
                        @Override
                        public void onAllFinish() {
                            super.onAllFinish();
//                        hideLoadingDialog();
                            RefreshLayoutUtils.finish(refreshLayout);
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
    private void initRvAdapter(List<BillRecord> data, boolean isFresh) {

        RecyclerView rv = getView(R.id.recyclerView);

        if(mAdapter==null) {
            if (rv != null) {
                mAdapter =new BillAdapter(data);
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(mAdapter);
            }
            mAdapter.setOnItemClickListener(new BillAdapter.OnItemClickListener() {
                @Override
                public void onClickTime() {

                }

                @Override
                public void onClickSn() {

                }
            });
        }else {
            if (rv != null) {
                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(mAdapter);
            }
            mAdapter.notifyData(data,isFresh);
        }
        boolean isShowEmpty = isFresh && (data == null || data.size() == 0);
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }

    /**
     * 选择Fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {

        int anim = R.anim.activity_none;
        switch (mode){
            case 0:
                anim=R.anim.dialog_enter;
                break;
            case 1:
                break;
        }
        Animation animBottomIN = AnimationUtils.loadAnimation(getActivity(), anim);
        frameLayout.setVisibility(View.VISIBLE);
        frameLayout.startAnimation(animBottomIN);
        mCurFragment = FragmentUtils.selectFragment(this, mCurFragment, fragment, R.id.fragment);
        mCurFragment.onUnFirstResume();
    }

}
