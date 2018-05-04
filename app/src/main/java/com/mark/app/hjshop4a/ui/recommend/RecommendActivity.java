package com.mark.app.hjshop4a.ui.recommend;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.BuildConfig;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.mark.app.hjshop4a.base.model.PagingParam;
import com.mark.app.hjshop4a.common.utils.RefreshLayoutUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.dialog.SexDialog;
import com.mark.app.hjshop4a.ui.dialog.ShareDialog;
import com.mark.app.hjshop4a.ui.recommend.model.ZXingCode;
import com.mark.app.hjshop4a.ui.userinfo.model.CommitUserInfo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/16.
 */

public class RecommendActivity extends BaseActivity implements OnRefreshLoadmoreListener {
    private  RecommendAdapter recommendAdapter;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    PagingBaseModel mPagingData;

    private ShareDialog shareDialog;
    private IWXAPI api;
    private String inviteUrl;
    private  String inviteTitle;
    private  String   inviteContent;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_right_base_rvlist;
    }
    @Override
    public void findView() {
        mRefreshLayout = getView(R.id.refreshLayout);
        mRefreshLayout.setOnRefreshLoadmoreListener(this);
        mRefreshLayout.autoRefresh();
    }
    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"我的推荐");
        setTvText(R.id.titlebar_tv_right,"分享");
        api = WXAPIFactory.createWXAPI(this, BuildConfig.WX_APPID,true);
        api.registerApp(BuildConfig.WX_APPID);

    }

    private void initRvAdapter(ZXingCode data,boolean isRefresh) {
        if(recommendAdapter ==null){
            RecyclerView rv = getView(R.id.recyclerView);
            recommendAdapter = new RecommendAdapter(data, data.getRecommendList());
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(recommendAdapter);
        }
        else {
            if(isRefresh) {
                recommendAdapter.notifyData(data, data.getRecommendList(), true);
            }else {
                recommendAdapter.notifyData( data.getRecommendList(), false);
            }
        }

        boolean isShowEmpty = isRefresh && (data == null );
        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.titlebar_tv_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.titlebar_tv_right:
//                showShareDialog();
                break;
        }
    }

    /**
     * 请求数据
     */
    private void requestData(final  int curPage,final long timestamp) {
        PagingParam pagingParam = new PagingParam();
        pagingParam.setCurrentPage(curPage);
        pagingParam.setTimestamp(timestamp);

        App.getServiceManager().getPdmService()
                .recommend(pagingParam.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<ZXingCode>() {


                    @Override
                    public void onSuccess(BaseResultEntity<ZXingCode> obj) {
                        ZXingCode data = obj.getResult();
                        if(data!=null) {
                            initRvAdapter(data, curPage == 1);
                            if (mPagingData == null) {
                                mPagingData = new PagingBaseModel();
                            }
                            mPagingData.setPagingInfo(curPage, data.getRecommendList(), obj.getNowTime());

                        }
                        RefreshLayoutUtils.finish(mRefreshLayout, mPagingData);
                    }
                    @Override
                    public void onUnSuccessFinish() {
//                        initRvAdapter(new ZXingCode(), curPage == 1);
                        RefreshLayoutUtils.finish(mRefreshLayout);

                    }
                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();


                    }
                });
    }

    @Override
    public void onLoadmore(RefreshLayout refreshLayout) {
        RefreshLayoutUtils.loadMore(refreshLayout, mPagingData, new RefreshLayoutUtils.OnLoadMoreListener() {
            @Override
            public void onLoadMore(int nextPage, long timestamp) {
                requestData(nextPage,timestamp);
            }


        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshLayout) {
        requestData(1,0);
    }

    /**
     * 获取分享封面byte数组
     * @return
     */
    private  byte[] getThumbData() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize=2;
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.ic_logo,options);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        int quality = 100;
        while (output.toByteArray().length > 32768 && quality != 10) {//微信分享图片大小限制
            output.reset(); // 清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, output);// 这里压缩options%，把压缩后的数据存放到baos中
            quality -= 10;
        }
        bitmap.recycle();
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    /*
  * 显示性别选择*/
    private  void  showShareDialog(){
        if(shareDialog ==null){
            shareDialog =new ShareDialog();
        }
        shareDialog.setOnDialogClickListener(new ShareDialog.OnDialogClickListener() {


            @Override
            public void onClickWX(ShareDialog dialog) {
                ToastUtils.show("1");
            }

            @Override
            public void onClickWXF(ShareDialog dialog) {
                ToastUtils.show("2");
            }

            @Override
            public void onClickWeiBo(ShareDialog dialog) {
                ToastUtils.show("3");
            }

            @Override
            public void onClickQQ(ShareDialog dialog) {
                ToastUtils.show("4");
            }
        });
        shareDialog.setContent(this.getActivity());
        shareDialog.show(getFragmentManager());
    }
}
