package com.mark.app.hjshop4a.app;

import android.content.Context;

import com.mark.app.hjshop4a.data.net.PdMService;
import com.mark.app.hjshop4a.data.net.RainbowService;

/**
 * Created by zhuwh on 2018/4/11.
 */

public class AppService {
    //网络服务
//    @Inject
  private   PdMService mPdMService;
  private RainbowService mService;
    private Context mContext;
    private String mBaseUrl;
    public AppService(Context context) {
        mContext = context;
        mBaseUrl = AppContext.getBaseUrl();
    }
    public Context getContext() {
        return mContext;
    }

    public RainbowService getmService() {
        if (mService == null) {
            mService = RainbowService.Creator.newService(mBaseUrl,mContext);
        }
        return mService;
    }

    /**
     * 获取服务
     *
     * @return
     */

    public PdMService getPdmService() {
        if (mPdMService == null) {
            mPdMService = PdMService.Creator.newService("http://49.234.194.161:8080/",mContext);
        }
        return mPdMService;
    }
    /*
    * 设置服务mBaseUrl
    * */
    public void setBaseUrl() {
        mService=null;
    }


}
