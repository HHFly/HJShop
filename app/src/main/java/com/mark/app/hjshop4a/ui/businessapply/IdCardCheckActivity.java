package com.mark.app.hjshop4a.ui.businessapply;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;
import com.mark.app.hjshop4a.ui.businessapply.model.CardIntentMessage;
import com.mark.app.hjshop4a.ui.businessapply.model.UserInfo;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.white.lib.utils.TakePhoneUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pc on 2018/4/18.
 */

public class IdCardCheckActivity extends BaseActivity {
    //上传的图片
    Uri mUri;
   Map<Integer,String>pic =new HashMap<>();
   UserInfo mdata;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_business_apply_idcard;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        mdata= (UserInfo) bundle.get("userInfo");
    }

    @Override
    public void initView() {
            setTvText(R.id.titlebar_tv_title,"身份证验证");
            setTvText(R.id.tv_crad_id,mdata.getUserIdCard());
        setSdvBig(R.id.imagebtn1, mdata.getUserIdCardFront());
        setSdvBig(R.id.imagebtn2, mdata.getUserIdCardSide());

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.layout_card_id);
        setClickListener(R.id.imagebtn1);
        setClickListener(R.id.imagebtn2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
//                String cardid= getTvText(R.id.tv_crad_id);
//                CardIntentMessage cardIntentMessage =new CardIntentMessage();
//                cardIntentMessage.setCardId(cardid);
//                cardIntentMessage.setMap(pic);
//                Intent intent =this.getIntent();
//                BundleUtils.getInstance().putSerializable("CardIntentMessage",cardIntentMessage).addIntent(intent);
//                this.setResult(1,intent);
                finish();
                break;
            case R.id.imagebtn1:
//                FunctionDialogFactory.showTakePhoneIDDialog(this,R.id.imagebtn1);
                break;
            case R.id.imagebtn2:
//                FunctionDialogFactory.showTakePhoneIDDialog(this,R.id.imagebtn2);
                break;
            case R.id.layout_card_id:
//                FunctionDialogFactory.showAddOneParamDialogNum(this,"",R.id.tv_crad_id);
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
//                requestUpdateDataOfNewPic(var1,id);
            }
        });

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @android.support.annotation.NonNull String[] permissions, @android.support.annotation.NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        TakeImgUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults );
    }

    /**
     * 请求更新数据，有新图片
     */
    private void requestUpdateDataOfNewPic(Uri var1 ,final int id) {

        luban(var1, new DefOnUploadPicListener() {
            @Override
            public void onLoadPicFinish(String imgUrl) {
                super.onLoadPicFinish(imgUrl);

                pic.put(id,imgUrl);
            }

            @Override
            public void onLoadPicUnSuccessFinish() {
                super.onLoadPicUnSuccessFinish();
                hideLoadingDialog();
            }
        });
    }
}
