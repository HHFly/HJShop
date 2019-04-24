package com.mark.app.hjshop4a.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


import com.mark.app.hjshop4a.BuildConfig;




/**
 * 微信支付回调Activity
 * @author
 * @create time 2017-09-15
 */
public class WXPayEntryActivity {
//	private IWXAPI wxAPI;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//		wxAPI = WXAPIFactory.createWXAPI(this, BuildConfig.WX_APPID);
//		wxAPI.handleIntent(getIntent(), this);
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent){
//        super.onNewIntent(intent);
//		setIntent(intent);
//        wxAPI.handleIntent(intent, this);
//    }
//
//	@Override
//	public void onReq(BaseReq baseReq) {}
//
//	@Override
//	public void onResp(BaseResp resp) {
//		Log.i("ansen", "微信支付回调 返回错误码:"+resp.errCode+" 错误名称:"+resp.errStr);
//		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX){//微信支付
//
//		}
//		finish();
//	}
}
