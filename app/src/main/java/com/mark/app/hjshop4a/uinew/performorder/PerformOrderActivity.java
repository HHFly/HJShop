package com.mark.app.hjshop4a.uinew.performorder;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.uinew.homepager.adapter.HomeAdapter;
import com.mark.app.hjshop4a.uinew.homepager.model.Index;
import com.mark.app.hjshop4a.uinew.performorder.adapter.OneDetailAdapter;

public class PerformOrderActivity extends BaseActivity {
    int step;
    String subOrderSn;
    private OneDetailAdapter oneDetailAdapter;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_perform;

    }

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        step =bundle.getInt(BundleKey.ID);
        subOrderSn =bundle.getString(BundleKey.ORDER_SN);
    }
    @Override
    public void initView() {

    }
   
}
