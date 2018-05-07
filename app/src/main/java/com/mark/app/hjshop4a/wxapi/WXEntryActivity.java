package com.mark.app.hjshop4a.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.mark.app.hjshop4a.BuildConfig;
import com.mark.app.hjshop4a.app.App;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.socialize.weixin.view.WXCallbackActivity;


/**
 * 微信登陆分享回调Activity
 * @author pc
 * @create time 2015-05-25
 */
public class WXEntryActivity extends WXCallbackActivity  {
//    private IWXAPI wxAPI;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        wxAPI = WXAPIFactory.createWXAPI(this, BuildConfig.WX_APPID, true);
//        wxAPI.registerApp(BuildConfig.WX_APPID);
//        wxAPI.handleIntent(getIntent(), this);
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        wxAPI.handleIntent(getIntent(), this);
//        Log.i("ansen", "WXEntryActivity onNewIntent");
//    }
//
//    @Override
//    public void onReq(BaseReq arg0) {
//        Log.i("ansen", "WXEntryActivity onReq:" + arg0);
//    }
//
//    @Override
//    public void onResp(BaseResp resp) {
//        if (resp.getType() == ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX) {//分享
//            Log.i("ansen", "微信分享操作.....");
//
//        } else if (resp.getType() == ConstantsAPI.COMMAND_SENDAUTH) {//登陆
//            Log.i("ansen", "微信登录操作.....");
//            SendAuth.Resp authResp = (SendAuth.Resp) resp;
//
//            finish();
//        }
//    }
}