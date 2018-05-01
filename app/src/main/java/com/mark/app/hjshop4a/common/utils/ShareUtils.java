package com.mark.app.hjshop4a.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.mark.app.hjshop4a.BuildConfig;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.wxapi.model.WeiXinPay;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import java.io.ByteArrayOutputStream;

/**
 * Created by pc on 2018/5/1.
 */

public class ShareUtils  {
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
