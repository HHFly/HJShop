package com.mark.app.hjshop4a.ui.business.consumecommit;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;

/**
 * Created by pc on 2018/4/19.
 */

public class CommitRuleActivity extends BaseActivity{
    @Override
    public int getContentViewResId() {
        return R.layout.activity_consumetip;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"线下消费说明");
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
        }
    }
}
