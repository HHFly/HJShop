package com.mark.app.hjshop4a.ui.business.zxing;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

/**
 * Created by pc on 2018/4/19.
 */

public class BusniessZxingActivity extends BaseActivity {
    Bitmap mBitmap;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_busniess_zxing;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"商家收豆二维码");
        mBitmap = CodeUtils.createImage("这是测试二维码", 300, 300, null);
        ImageView ZXing = (ImageView) getView(R.id.zxing);
        ZXing.setImageBitmap(mBitmap);
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
