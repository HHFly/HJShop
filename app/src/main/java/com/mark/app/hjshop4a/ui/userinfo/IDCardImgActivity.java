package com.mark.app.hjshop4a.ui.userinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.userinfo.UserInfoType;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.model.login.model.LoginType;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.ui.userinfo.model.CommitUserInfo;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/26.
 */

public class IDCardImgActivity extends BaseActivity {
    private String userIdCardFront;
    private String userIdCardSide;
    private Map<Integer,String> pic =new HashMap<>();
    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        if(bundle!=null){
            userIdCardFront=bundle.getString("userIdCardFront");
            userIdCardSide =bundle.getString("userIdCardSide");
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_idcardimg;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"身份验证");
        setTvText(R.id.titlebar_tv_right,"完成");
//        setViewVisibility(R.id.titlebar_tv_right,false);
        if(userIdCardFront!=""){
            setSdvBig(R.id.imagebtn1,userIdCardFront);
        }
        if(userIdCardSide!=""){
            setSdvBig(R.id.imagebtn2,userIdCardSide);
        }
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.titlebar_tv_right);
        setClickListener(R.id.imagebtn1);
        setClickListener(R.id.imagebtn2);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        String front =pic.get(R.id.imagebtn1);
        String side=pic.get(R.id.imagebtn2);

        if(front!=null){
            userIdCardFront =front;
        }
        if(side!=null){
            userIdCardSide=side;
        }
//        BundleUtils.getInstance().putString("userIdCardFront",userIdCardFront).putString("userIdCardSide",userIdCardSide).addIntent(getIntent());
        getIntent().putExtra("userIdCardFront",userIdCardFront);
        getIntent().putExtra("userIdCardSide",userIdCardSide);
        setResult(1,getIntent());
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.titlebar_iv_return:
                String front =pic.get(R.id.imagebtn1);
                String side=pic.get(R.id.imagebtn2);

                if(front!=null){
                    userIdCardFront =front;
                }
                if(side!=null){
                    userIdCardSide=side;
                }
//        BundleUtils.getInstance().putString("userIdCardFront",userIdCardFront).putString("userIdCardSide",userIdCardSide).addIntent(getIntent());
                getIntent().putExtra("userIdCardFront",userIdCardFront);
                getIntent().putExtra("userIdCardSide",userIdCardSide);
                setResult(1,getIntent());
                finish();
                break;
            case R.id.titlebar_tv_right:
                String front1 =pic.get(R.id.imagebtn1);
                String side2=pic.get(R.id.imagebtn2);

                if(front1!=null){
                    userIdCardFront =front1;
                }
                if(side2!=null){
                    userIdCardSide=side2;
                }
//        BundleUtils.getInstance().putString("userIdCardFront",userIdCardFront).putString("userIdCardSide",userIdCardSide).addIntent(getIntent());
                getIntent().putExtra("userIdCardFront",userIdCardFront);
                getIntent().putExtra("userIdCardSide",userIdCardSide);
                setResult(1,getIntent());
                finish();
                break;
            case R.id.imagebtn1:
                FunctionDialogFactory.showTakePhoneIDDialog(this,R.id.imagebtn1);
                break;
            case R.id.imagebtn2:
                FunctionDialogFactory.showTakePhoneIDDialog(this,R.id.imagebtn2);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TakeImgUtil.onActivityResult(getActivity(), requestCode, resultCode, data, new TakeImgUtil.CallBack() {
            @Override
            public void back(Uri var1, int id) {
                requestUpdateDataOfNewPic(var1,id);

            }
        });

    }
    /**
     * 请求更新数据
     */
    private boolean requestUpdateDataOfNewPic(Uri uri,final int id) {
        showLoadingDialog();
        final boolean[] isSuccess = new boolean[1];
        luban(uri, new DefOnUploadPicListener() {
            @Override
            public void onLoadPicFinish(String imgUrl) {
                super.onLoadPicFinish(imgUrl);
//                requestUpdateData(imgUrl);

                pic.put(id,imgUrl);
                CommitUserInfo commitUserInfo =new CommitUserInfo();
                switch (id){
                    case R.id.imagebtn1:
                        commitUserInfo.setUserIdCardFront(imgUrl);
                        requestData(R.id.imagebtn1,commitUserInfo,imgUrl);
                        break;
                    case R.id.imagebtn2:
                        commitUserInfo.setUserIdCardSide(imgUrl);
                        requestData(R.id.imagebtn2,commitUserInfo,imgUrl);
                        break;
                }

                isSuccess[0] =true;
            }

            @Override
            public void onLoadPicUnSuccessFinish() {
                super.onLoadPicUnSuccessFinish();
                ToastUtils.show("上传图片失败");
                hideLoadingDialog();
                isSuccess[0] =false;
            }
        });
        return isSuccess[0];
    }
    /**
     * 请求数据
     */
    private void requestData(final int type, final CommitUserInfo userInfo,final  String url) {
        showLoadingDialog();

        App.getServiceManager().getPdmService()
                .setUserInfo(UserInfoType.CERTIFI,userInfo.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {


                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        setSdvBig(type,url);
                        ToastUtils.show("上传图片成功");
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}
