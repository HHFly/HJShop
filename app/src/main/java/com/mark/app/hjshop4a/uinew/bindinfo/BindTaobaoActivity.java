package com.mark.app.hjshop4a.uinew.bindinfo;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;

public class BindTaobaoActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_bind_taobao;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.绑定淘宝账号);
    }
}
