package com.mark.app.hjshop4a.app;

import android.content.Context;

import com.mark.app.hjshop4a.data.net.PdMService;

/**
 * Created by zhuwh on 2018/4/11.
 */

public class AppService {
    //网络服务
//    @Inject
  private   PdMService mPdMService;
    private Context mContext;
    private String mBaseUrl;
    public AppService(Context context) {
        mContext = context;
        mBaseUrl = AppContext.getBaseUrl();
    }
    public Context getContext() {
        return mContext;
    }
    /**
     * 获取服务
     *
     * @return
     */

    public PdMService getPdmService() {
        if (mPdMService == null) {
            mPdMService = PdMService.Creator.newService(mBaseUrl,mContext);
        }
        return mPdMService;
    }
    /*
    * 设置服务mBaseUrl
    * */
    public void setBaseUrl() {
        mPdMService=null;
    }


}
