package com.mark.app.hjshop4a.uinew.performorder.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.StepTwo;

public class TwoSearchAdapter extends MultipleSourcesRvAdapter {
    StepTwo data ;

    public void setData(StepTwo data) {
        this.data = data;
    }

    public TwoSearchAdapter(StepTwo data) {
        this.data = data;
    }

    @Override
    public int getRowsCountInSection(int var1) {
        return 1;
    }

    @Override
    public View onCreateView(ViewGroup var1, int var2) {
        return null;
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, IndexPath indexPath) {

    }
}
