package com.mark.app.hjshop4a.ui.web;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseWebFragment;

/**
 * 网页加载
 * Created by lenovo on 2017/9/7.
 */

public class WebFragment extends BaseWebFragment {

    private String mUrl;
    private String mTitle;

    /**
     * 获取实例
     *
     * @param url
     * @param title
     * @return
     */
    public static WebFragment getInstance(String url, String title) {
        WebFragment webFragment = new WebFragment();
        webFragment.mUrl = url;
        webFragment.mTitle = title;
        return webFragment;
    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_web;
    }

    @Override
    public void bindViewResId() {
        setUrl(mUrl);
        setTitleText(mTitle);

        setTitleResId(R.id.titlebar_tv_title);
        setWebViewResId(R.id.web_webView);
        setPbResId(R.id.web_pb);
        setPbLoadingResId(R.id.web_pb_loading);
        setViewReturnResId(R.id.titlebar_iv_return);
        setPbLoadingIndeterminateDrawableAfter6(R.drawable.pb_loading_after6);
    }
}
