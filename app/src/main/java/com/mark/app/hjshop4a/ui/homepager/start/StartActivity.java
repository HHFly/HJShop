package com.mark.app.hjshop4a.ui.homepager.start;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.ActAnimationUtils;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.CountDownUtils;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;
import com.mark.app.hjshop4a.common.utils.StatusBarUtil;
import com.mark.app.hjshop4a.common.utils.StringUtils;
import com.mark.app.hjshop4a.ui.homepager.activity.HomePagerActivity;


/**
 * 启动页
 */
public class StartActivity extends BaseActivity {
    private CountDownUtils countDownUtils;

    private String mData;

    private SimpleDraweeView mSdv;
    private TextView mTv;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_start;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownUtils != null) {
            countDownUtils.onDestroy();
            countDownUtils = null;
        }
        mSdv = null;
        mTv = null;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        if (bundle != null) {
            mData = bundle.getString(BundleKey.IMGURL);
        }
    }

    @Override
    public void findView() {
        mSdv = getView(R.id.start_sdv);
        mTv = getView(R.id.start_tv_time);
    }

    @Override
    public void setListener() {
        mTv.setOnClickListener(this);
    }

    @Override
    public void initView() {
        StatusBarUtil.immersive(this);

        bindData(mData);

        countDownUtils = CountDownUtils.getInstance(this);
        countDownUtils.setTime(3 * 1000);
        countDownUtils.setCountDownListener(new CountDownUtils.DefaultCountDownListener() {
            @Override
            public void countDownOver() {
                super.countDownOver();
                gotoHome();
            }

            @Override
            public void countDownStart(long remainTime) {
                super.countDownStart(remainTime);
                setRemainTime(remainTime);
            }
        });
        countDownUtils.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_tv_time: {
                gotoHome();
                break;
            }
        }
    }

    /**
     * 设置启动页数据
     *
     * @param data
     */
    private void bindData(String data) {
        if (data != null) {
            FrescoUtils.sdvBig(mSdv, data);
        }
    }

    /**
     * 设置剩余时间
     *
     * @param remainTime
     */
    private void setRemainTime(long remainTime) {
        if (mTv != null) {
            int second = (int) (remainTime / 1000);
            if (remainTime % 1000 != 0) {
                second++;
            }
            String str = StringUtils.stringFormat(R.string.split_start, second + "");
            mTv.setText(str);
        }

    }

    /**
     * 前往首页
     */
    private void gotoHome() {
        if (countDownUtils != null) {
            countDownUtils.onDestroy();
            countDownUtils = null;
        }
        ActivityJumpUtils.actActivity(getActivity(), HomePagerActivity.class);
        finish();
        ActAnimationUtils.actAlphaOut(getActivity());
    }
}
