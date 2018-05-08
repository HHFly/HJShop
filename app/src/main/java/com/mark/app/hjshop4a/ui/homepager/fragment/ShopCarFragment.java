package com.mark.app.hjshop4a.ui.homepager.fragment;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;

/**
 * Created by pc on 2018/4/13.
 */

public class ShopCarFragment extends BaseFragment {
    @Override
    public int getContentResId() {
        return R.layout.fragment_blank;
    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar,"惠家商城");
        setIvImage(R.id.bg_img,R.mipmap.shopcar);
    }

    @Override
    public void onClick(View v) {

    }
}
