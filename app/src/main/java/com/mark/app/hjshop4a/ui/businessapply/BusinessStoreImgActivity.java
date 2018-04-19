package com.mark.app.hjshop4a.ui.businessapply;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;
import com.mark.app.hjshop4a.ui.businessapply.adapter.GridViewAdapter;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/18.
 */

public class BusinessStoreImgActivity extends BaseActivity {
    private GridView gridView;
    private GridViewAdapter mGridViewAddImgAdapter; //展示上传的图片的适配器
    private int MAX_SELECT_PIC_NUM = 4;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_business_store_img;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, "商户照片");

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.imagebtn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.imagebtn:
                FunctionDialogFactory.showTakePhoneDialog(getAppCompatActivity(), R.id.imagebtn);
        }
    }

    private void initAdapter(final ArrayList<Uri> mPicList) {
        mGridViewAddImgAdapter = new GridViewAdapter(this, mPicList);
        gridView.setAdapter(mGridViewAddImgAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过5张，才能点击
                    if (mPicList.size() == MAX_SELECT_PIC_NUM) {
                        //最多添加5张图片
//                        FunctionDialogFactory.showTakePhoneDialog(getAppCompatActivity(),R.id.imagebtn1);
                    } else {
                        //添加凭证图片
                        FunctionDialogFactory.showTakePhoneDialog(getAppCompatActivity(), R.id.imagebtn1);
                    }
                } else {
//                    viewPluImg(position);  展示图片

                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TakeImgUtil.onActivityResult(this, requestCode, resultCode, data, new TakeImgUtil.CallBack() {
            @Override
            public void back(Uri var1, int id) {

                setSdvBig(id, var1);
            }
        });
    }
}
