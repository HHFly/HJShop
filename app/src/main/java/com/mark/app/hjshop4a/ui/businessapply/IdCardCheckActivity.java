package com.mark.app.hjshop4a.ui.businessapply;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.white.lib.utils.TakePhoneUtil;

/**
 * Created by pc on 2018/4/18.
 */

public class IdCardCheckActivity extends BaseActivity {
    //上传的图片
    Uri mUri;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_business_idcard;
    }

    @Override
    public void initView() {
            setTvText(R.id.titlebar_tv_title,"身份证验证");
            setTvText(R.id.titlebar_tv_right,"完成");

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.titlebar_tv_right);
        setClickListener(R.id.imagebtn1);
        setClickListener(R.id.imagebtn2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.imagebtn1:
                FunctionDialogFactory.showTakePhoneDialog(this,R.id.imagebtn1);
                break;
            case R.id.imagebtn2:
                FunctionDialogFactory.showTakePhoneDialog(this,R.id.imagebtn2);
                break;
            case R.id.titlebar_tv_right:
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TakeImgUtil.onActivityResult(this, requestCode, resultCode, data, new TakeImgUtil.CallBack() {
            @Override
            public void back(Uri var1, int id) {
                mUri = var1;

                setSdvBig(id, var1);
            }
        });
//        TakePhoneUtil.onActivityResult(this, requestCode, resultCode, data, new TakePhoneUtil.CallBack() {
//            @Override
//            public void back(Uri imgUri) {
//                mUri = imgUri;
//                setSdvBig(R.id.imagebtn1, imgUri);
//            }
//        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        TakeImgUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults );
    }
}
