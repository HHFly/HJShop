package com.mark.app.hjshop4a.uinew.homepager.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.ui.homepager.model.UserCenter;
import com.mark.app.hjshop4a.uinew.homepager.adapter.HomeAdapter;
import com.mark.app.hjshop4a.uinew.homepager.adapter.MeAdapter;
import com.mark.app.hjshop4a.uinew.homepager.model.Banner;
import com.mark.app.hjshop4a.uinew.homepager.model.Index;
import com.mark.app.hjshop4a.uinew.homepager.model.ShowProduct;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/13.
 */

public class HomeFragment extends BaseFragment {
    private HomeAdapter mAdapter;
    RefreshLayout refreshLayout;
    @Override
    public int getContentResId() {
        return R.layout.fragment_blank;
    }

    @Override
    public void findView() {
        refreshLayout =getView(R.id.refreshLayout);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestData();
            }
        });
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"首页");
//        setIvImage(R.id.bg_img,R.mipmap.home);
        setViewVisibilityGone(R.id.titlebar_iv_return,false);
        requestData();
    }

    @Override
    public void onClick(View v) {

    }
    /**
     * 请求数据
     */
    private void requestData( ) {
//        showLoadingDialog();
        App.getServiceManager().getmService()
                .getIndex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<Index>() {


                    @Override
                    public void onSuccess(RainbowResultEntity<Index> obj) {
                        Index mData = JsonUtils.fromJson(obj.getResult(),Index.class);
                        initRvAdapter(mData,true);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
//                        hideLoadingDialog();
                        RefreshLayoutUtils.finish(refreshLayout);
                    }
                });
    }
    /**
     * 初始化adapter
     *
     *
     */
    private void initRvAdapter(Index data,boolean isfresh) {

        RecyclerView rv = getView(R.id.recyclerView);
        if(mAdapter==null) {
            if (rv != null) {
                mAdapter =new HomeAdapter(data.getBanners(),data.getListData());
//                GridLayoutManager layoutManage = new GridLayoutManager(getContext(), 2);
//                layoutManage.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                    @Override
//                    public int getSpanSize(int position) {
//                        return position==0? 1:2;
//                    }
//                });
                LinearLayoutManager layoutManage = new LinearLayoutManager(getContext());
                rv.setLayoutManager(layoutManage);
                rv.setAdapter(mAdapter);


            }
            mAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
                @Override
                public void onClickProduct(ShowProduct data) {

                }

                @Override
                public void onClickBanner(Banner data) {

                }


            });
        }else {
          mAdapter.notifyData(data.getBanners(),data.getListData(),isfresh);
        }
    }


}
