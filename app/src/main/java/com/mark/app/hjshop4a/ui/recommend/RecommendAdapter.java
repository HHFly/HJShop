package com.mark.app.hjshop4a.ui.recommend;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;

import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.ui.recommend.model.Redata;
import com.mark.app.hjshop4a.ui.recommend.model.ZXingCode;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/16.
 */

public class RecommendAdapter extends BaseHasTopListRvAdapter <ZXingCode, Redata> {

 Bitmap mBitmap;

    public RecommendAdapter(ZXingCode zXingCode, ArrayList<Redata> redata) {
        super(zXingCode, redata);
    }

    @Override
    public void notifyData(List<Redata> redata, boolean isRefresh) {
        super.notifyData(redata, isRefresh);
    }

    @Override
    public int getTopItemResId() {
        return R.layout.activity_recommend;
    }

    @Override
    public int getBodyItemResId() {
        return R.layout.item_recommend;
    }

    @Override
    public void bindTopData(AutoViewHolder holder, int topPos, ZXingCode zXingCode) {

        mBitmap = CodeUtils.createImage(zXingCode.getCode(), 300, 300, null);
        ImageView ZXing = (ImageView) holder.get(R.id.zxingcode);
        ZXing.setImageBitmap(mBitmap);
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, Redata redata) {
       holder.text(R.id.name,redata.getName());
       holder.text(R.id.date,redata.getDate());
    }
}
