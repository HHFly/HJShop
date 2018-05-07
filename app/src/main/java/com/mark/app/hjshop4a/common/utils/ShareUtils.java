package com.mark.app.hjshop4a.common.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.mark.app.hjshop4a.BuildConfig;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.wxapi.model.WeiXinPay;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

import java.io.ByteArrayOutputStream;

/**
 * Created by pc on 2018/5/1.
 */

public class ShareUtils   {
    private static ShareUtils utils;
    /**
     * 获取实例
     *
     * @return
     */
    private static ShareUtils getInstance() {
        if (utils == null) {
            synchronized (ShareUtils.class) {
                if (utils == null) {
                    utils = new ShareUtils();
                }
            }
        }
        return utils;
    }
  private static UMShareListener  umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //分享开始的回调
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
//            Toast.makeText(ShareActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
//            Toast.makeText(ShareActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
//            Toast.makeText(ShareActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * 分享链接
     */
    public static void shareWeb(final Activity activity, String WebUrl, String title, String description, String imageUrl, int imageID, SHARE_MEDIA platform) {
        final UMWeb web = new UMWeb(WebUrl);//连接地址
        web.setTitle(title);//标题
        web.setDescription(description);//描述
        if (TextUtils.isEmpty(imageUrl)) {
            web.setThumb(new UMImage(activity, imageID));  //本地缩略图
        } else {
            web.setThumb(new UMImage(activity, imageUrl));  //网络缩略图
        }

        new ShareAction(activity)
         .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.SINA, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setShareboardclickCallback(new ShareBoardlistener() {
                    @Override
                    public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
                        if (share_media == SHARE_MEDIA.QQ) {
//                            Log.e("点击QQ");
                            new ShareAction(activity).setPlatform(SHARE_MEDIA.QQ)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN) {
//                            Log.e("点击微信");
                            new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.SINA) {
                            new ShareAction(activity).setPlatform(SHARE_MEDIA.SINA)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        } else if (share_media == SHARE_MEDIA.WEIXIN_CIRCLE) {
                            new ShareAction(activity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                                    .withMedia(web)
                                    .setCallback(umShareListener)
                                    .share();
                        }
                    }
                }).open();



    }
    /*
   * 原生微信分享
   * */
    /**
     * 微信分享
     * @param friendsCircle  是否分享到朋友圈
     */
    public void shareWX(String title, String text,  String url, IWXAPI api,boolean friendsCircle,byte[] image){
        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = "www.baidu.com";//分享url
        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = "惠家商城";
        msg.description = "分享描述";
        msg.thumbData =image;//封面图片byte数组

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = friendsCircle ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;
        api.sendReq(req);
    }
    /**
     * 发起支付
     * @param weiXinPay
     */
    public void pay(WeiXinPay weiXinPay,IWXAPI api){
        PayReq req = new PayReq();
        req.appId = BuildConfig.WX_APPID;//appid
        req.nonceStr=weiXinPay.getNoncestr();//随机字符串，不长于32位。推荐随机数生成算法
        req.packageValue=weiXinPay.getPackage_value();//暂填写固定值Sign=WXPay
        req.sign=weiXinPay.getSign();//签名
        req.partnerId=weiXinPay.getPartnerid();//微信支付分配的商户号
        req.prepayId=weiXinPay.getPrepayid();//微信返回的支付交易会话ID
        req.timeStamp=weiXinPay.getTimestamp();//时间戳


        api.sendReq(req);
    }
}
