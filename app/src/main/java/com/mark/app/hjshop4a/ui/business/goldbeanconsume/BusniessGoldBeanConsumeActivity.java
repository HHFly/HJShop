package com.mark.app.hjshop4a.ui.business.goldbeanconsume;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;

/**
 * Created by pc on 2018/4/19.
 */

public class BusniessGoldBeanConsumeActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_gold_bean_consume_business;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"商家兑换金豆");
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.button);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.button:
                break;
        }
    }
}
