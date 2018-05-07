package com.mark.app.hjshop4a.ui.recommend;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.ShareBoardConfig;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.io.ByteArrayOutputStream;
import java.lang.ref.WeakReference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/16.
 */

public class RecommendActivity extends BaseActivity implements OnRefreshLoadmoreListener {
    private  RecommendAdapter recommendAdapter;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    PagingBaseModel mPagingData;
    private UMShareListener mShareListener;
    private ShareAction mShareAction;
    private ShareDialog shareDialog;
    private IWXAPI api;
    private String inviteUrl="https://www.baidu.com";
    private  String inviteTitle= "惠家商城";
    private  String   inviteContent ="快来下载惠家商城App吧！";
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
//        api = WXAPIFactory.createWXAPI(this, BuildConfig.WX_APPID,true);
//        api.registerApp(BuildConfig.WX_APPID);
        PlatformConfig.setWeixin("你的微信APPID", "你的微信AppSecret");//微信APPID和AppSecret
        PlatformConfig.setQQZone("你的QQAPPID", "你的QQAppSecret");//QQAPPID和AppSecret
        PlatformConfig.setSinaWeibo("你的微博APPID", "你的微博APPSecret","微博的后台配置回调地址");//微博

        mShareListener = new CustomShareListener(this);
        /*增加自定义按钮的分享面板*/
        mShareAction = new ShareAction(RecommendActivity.this).setDisplayList(
                SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.SINA, SHARE_MEDIA.QQ
               )
//                .addButton("复制文本", "复制文本", "umeng_socialize_copy", "umeng_socialize_copy")
//                .addButton("复制链接", "复制链接", "umeng_socialize_copyurl", "umeng_socialize_copyurl")
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
//                        if (snsPlatform.mShowWord.equals("复制文本")) {
//                            Toast.makeText(RecommendActivity.this, "复制文本按钮", Toast.LENGTH_LONG).show();
//                        } else if (snsPlatform.mShowWord.equals("复制链接")) {
//                            Toast.makeText(RecommendActivity.this, "复制链接按钮", Toast.LENGTH_LONG).show();
//
//                        } else {
                            UMWeb web = new UMWeb(inviteUrl);
                            web.setTitle(inviteTitle);
                            web.setDescription(inviteContent);
                            web.setThumb(new UMImage(RecommendActivity.this,R.mipmap.ic_launcher));
                            new ShareAction(RecommendActivity.this).withMedia(web)
                                    .setPlatform(share_media)
                                    .setCallback(mShareListener)
                                    .share();
//                        }
                    }
                });

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
                recommendAdapter.notifyData(data, data.getRecommendList(), isRefresh);
            }else {
                recommendAdapter.notifyData( data.getRecommendList(), isRefresh);
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
                showShareDialog();
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
  * 显示*/
    private  void  showShareDialog() {
        ShareBoardConfig config = new ShareBoardConfig();
        config.setMenuItemBackgroundShape(ShareBoardConfig.BG_SHAPE_NONE);
        mShareAction.open(config);
    }

    private static class CustomShareListener implements UMShareListener {

        private WeakReference<RecommendActivity> mActivity;

        private CustomShareListener(RecommendActivity activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {

            if (platform.name().equals("WEIXIN_FAVORITE")) {
                Toast.makeText(mActivity.get(), platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
            } else {
                if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                        && platform != SHARE_MEDIA.EMAIL
                        && platform != SHARE_MEDIA.FLICKR
                        && platform != SHARE_MEDIA.FOURSQUARE
                        && platform != SHARE_MEDIA.TUMBLR
                        && platform != SHARE_MEDIA.POCKET
                        && platform != SHARE_MEDIA.PINTEREST

                        && platform != SHARE_MEDIA.INSTAGRAM
                        && platform != SHARE_MEDIA.GOOGLEPLUS
                        && platform != SHARE_MEDIA.YNOTE
                        && platform != SHARE_MEDIA.EVERNOTE) {
                    Toast.makeText(mActivity.get(), platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
                }

            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform != SHARE_MEDIA.MORE && platform != SHARE_MEDIA.SMS
                    && platform != SHARE_MEDIA.EMAIL
                    && platform != SHARE_MEDIA.FLICKR
                    && platform != SHARE_MEDIA.FOURSQUARE
                    && platform != SHARE_MEDIA.TUMBLR
                    && platform != SHARE_MEDIA.POCKET
                    && platform != SHARE_MEDIA.PINTEREST

                    && platform != SHARE_MEDIA.INSTAGRAM
                    && platform != SHARE_MEDIA.GOOGLEPLUS
                    && platform != SHARE_MEDIA.YNOTE
                    && platform != SHARE_MEDIA.EVERNOTE) {
                Toast.makeText(mActivity.get(), platform + " 分享失败啦", Toast.LENGTH_SHORT).show();

            }

        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

            Toast.makeText(mActivity.get(), platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    }
}
