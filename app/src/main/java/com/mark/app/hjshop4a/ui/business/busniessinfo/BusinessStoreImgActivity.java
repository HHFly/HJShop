package com.mark.app.hjshop4a.ui.business.busniessinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;
import com.mark.app.hjshop4a.common.utils.ListUtils;
import com.mark.app.hjshop4a.common.utils.LogUtils;
import com.mark.app.hjshop4a.common.utils.MapUtils;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;

import com.mark.app.hjshop4a.ui.businessapply.model.CompanyInfo;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.white.lib.utils.TakePhoneUtil;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2018/4/18.
 */

public class BusinessStoreImgActivity extends BaseActivity {


    private int MAX_SELECT_PIC_NUM = 4;
    private List<Uri> mPicUris;  //上传图片Uri集合
    private List<String> mPicUrls;//上传图片地址集合

    private Map<Integer,Uri>mUris;
    private SimpleDraweeView sdv1;
    private SimpleDraweeView sdv2;
    private SimpleDraweeView sdv3;
    private SimpleDraweeView sdv4;

    ViewGroup parent;
    private CompanyInfo mData;
    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        mData= (CompanyInfo) bundle.getSerializable("CompanyInfo");
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_business_store_img;
    }

    @Override
    public void initView() {
        sdv1 =(SimpleDraweeView) findViewById(R.id.item_sdv_1);
        sdv2 =(SimpleDraweeView) findViewById(R.id.item_sdv_2);
        sdv3 =(SimpleDraweeView) findViewById(R.id.item_sdv_3);
        sdv4 =(SimpleDraweeView) findViewById(R.id.item_sdv_4);
        mUris =new LinkedHashMap<>();
        mPicUris =new ArrayList<>();
        setTvText(R.id.titlebar_tv_title, "商户照片");
        setSdvInside(R.id.imagebtn_licence,mData.getLicencePic());
        setSdvInside(R.id.imagebtn_shop,mData.getShopImages());
        setSdvBig(R.id.item_sdv_1,mData.getShopImagesIn().get(0));
        setViewVisibility(R.id.item_iv_delete_1,false);
        setViewVisibility(R.id.item_iv_delete_2,false);
        setViewVisibility(R.id.item_iv_delete_3,false);
        setViewVisibility(R.id.item_iv_delete_4,false);


        if (mData.getShopImagesIn().get(1)!=""){
            setSdvBig(R.id.item_sdv_2,mData.getShopImagesIn().get(1));
            setViewVisibility(R.id.item_rl_2,true);

        }
        if (mData.getShopImagesIn().get(2)!=""){
            setSdvBig(R.id.item_sdv_3,mData.getShopImagesIn().get(2));
            setViewVisibility(R.id.item_rl_3,true);
        }
        if (mData.getShopImagesIn().get(3)!=""){
            setSdvBig(R.id.item_sdv_4,mData.getShopImagesIn().get(3));
            setViewVisibility(R.id.item_rl_4,true);
        }

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.imagebtn);
        setClickListener(R.id.item_sdv_1);
        setClickListener(R.id.item_iv_delete_1);
        setClickListener(R.id.item_sdv_2);
        setClickListener(R.id.item_iv_delete_2);
        setClickListener(R.id.item_sdv_3);
        setClickListener(R.id.item_iv_delete_3);
        setClickListener(R.id.item_sdv_4);
        setClickListener(R.id.item_iv_delete_4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.imagebtn:
//                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(), R.id.imagebtn);
                break;
            case R.id.item_sdv_1:
//                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(),R.id.item_sdv_1);
                break;
            case R.id.item_sdv_2:
//                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(),R.id.item_sdv_2);
                break;
            case R.id.item_sdv_3:
//                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(),R.id.item_sdv_3);
                break;
            case R.id.item_sdv_4:
//                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(),R.id.item_sdv_4);
                break;
            case R.id.item_iv_delete_1:
                remove(R.id.item_sdv_1);
                break;
            case R.id.item_iv_delete_2:
                remove(R.id.item_sdv_2);
                break;
            case R.id.item_iv_delete_3:
                remove(R.id.item_sdv_3);
                break;
            case R.id.item_iv_delete_4:
                remove(R.id.item_sdv_4);
                break;
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        TakePhoneUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        TakePhoneUtil.onActivityResult(this, requestCode, resultCode, data, new TakePhoneUtil.CallBack() {
//            @Override
//            public void back(Uri imgUri) {
//                addPicView(imgUri);
//            }
//        });
        TakeImgUtil.onActivityResult(this, requestCode, resultCode, data, new TakeImgUtil.CallBack() {
            @Override
            public void back(Uri var1, int id) {
                switch (id){
                    case R.id.imagebtn:
                        setSdvBig(id,var1);
                        break;
                    default:
                        addpic( var1,id);
                        break;
                }

            }
        });
    }
    private void addpic(Uri uri,int id){
        mUris.put(id,uri);

        showpic(mUris);
    }

    private void showpic(Map<Integer, Uri> mUris) {
        for (Integer in : mUris.keySet()) {
                       //map.keySet()返回的是所有key的值
            Uri str = mUris.get(in);//得到每个key多对用value的值

            mPicUris.add(str);
        }

        setViewVisibility(R.id.item_rl_2,false);
        setViewVisibility(R.id.item_rl_3,false);
        setViewVisibility(R.id.item_rl_4,false);
        Uri uri = Uri.parse("res:///" + R.mipmap.gird_storeimg_update);
        setSdvBig(R.id.item_sdv_1,uri);
        setSdvBig(R.id.item_sdv_2,uri);
        setSdvBig(R.id.item_sdv_3,uri);
        setSdvBig(R.id.item_sdv_4,uri);
        mUris.clear();
        if (mPicUris.size()>0){
            setSdvBig(R.id.item_sdv_1,mPicUris.get(0));
            this.mUris.put(R.id.item_sdv_1,mPicUris.get(0));
            mPicUris.remove(0);
            setViewVisibility(R.id.item_rl_2,true);
        }
        if (mPicUris.size()>0){
            this.mUris.put(R.id.item_sdv_2,mPicUris.get(0));
            setViewVisibility(R.id.item_rl_3,true);
            setSdvBig(R.id.item_sdv_2,mPicUris.get(0));
            mPicUris.remove(0);
        }
        if (mPicUris.size()>0){
            this.mUris.put(R.id.item_sdv_3,mPicUris.get(0));
            setViewVisibility(R.id.item_rl_4,true);
            setSdvBig(R.id.item_sdv_3,mPicUris.get(0));
            mPicUris.remove(0);
        }
        if (mPicUris.size()>0){
            this.mUris.put(R.id.item_sdv_4,mPicUris.get(0));
            setSdvBig(R.id.item_sdv_4,mPicUris.get(0));
            mPicUris.remove(0);
        }
    }



    private void  remove(int id){
        mUris.remove(id);
        showpic(mUris);
}

    private void commit() {

        showLoadingDialog();
        if (mPicUrls != null) {
            mPicUrls.clear();
        }
        if (mPicUris == null) {
//            requestCommit("", strDesc);
        } else {
//            final String strDescFinal = strDesc;
            luban(mPicUris, new DefOnUploadPicListener() {
                @Override
                public void onLoadPicFinish(String imgUrl) {
                    super.onLoadPicFinish(imgUrl);
                    if (mPicUrls == null) {
                        mPicUrls = new ArrayList<>();
                    }
                    mPicUrls.add(imgUrl);

                    int count = mPicUris == null ? 0 : mPicUris.size();
                    if (count <= mPicUrls.size()) {
                        String imgUrls = ListUtils.toStringUnEmpty(mPicUrls, ",");
//                        requestCommit(imgUrls, strDescFinal);
                    }
                }

                @Override
                public void onLoadPicUnSuccessFinish() {
                    super.onLoadPicUnSuccessFinish();
                    if (mPicUrls == null) {
                        mPicUrls = new ArrayList<>();
                    }
                    mPicUrls.add("");

                    int count = mPicUris == null ? 0 : mPicUris.size();
                    if (count <= mPicUrls.size()) {
                        String imgUrls = ListUtils.toStringUnEmpty(mPicUrls, ",");
//                        requestCommit(imgUrls, strDescFinal);
                    }
                }
            });
        }
    }
}
