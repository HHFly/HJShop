package com.mark.app.hjshop4a.ui.business.zxing;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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
        requestData();
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

    private  void requestData(){
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .merchantQRcode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {
                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                       String data =obj.getResult().toString();
                        initRvAdapter(data);
                    }


                    @Override
                    public void onUnSuccessFinish() {
                        initRvAdapter(null);

                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });


    }

    private void initRvAdapter(String data) {
        mBitmap = CodeUtils.createImage(data, 300, 300, null);
        ImageView ZXing = (ImageView) getView(R.id.zxing);
        ZXing.setImageBitmap(mBitmap);
    }
}
