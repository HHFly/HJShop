package com.mark.app.hjshop4a.ui.recommend;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;

import com.mark.app.hjshop4a.base.adapter.BaseHasTopListRvAdapter;
import com.mark.app.hjshop4a.common.utils.NumParseUtils;
import com.mark.app.hjshop4a.common.utils.NumberUtils;
import com.mark.app.hjshop4a.ui.recommend.model.Recommend;
import com.mark.app.hjshop4a.ui.recommend.model.ZXingCode;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2018/4/16.
 */

public class RecommendAdapter extends BaseHasTopListRvAdapter <ZXingCode, Recommend> {

 Bitmap mBitmap;

    public RecommendAdapter(ZXingCode zXingCode, ArrayList<Recommend> redata) {
        super(zXingCode, redata);
    }

    @Override
    public void notifyData(ZXingCode zXingCode, List<Recommend> recommends, boolean isRefresh) {
        super.notifyData(zXingCode, recommends, isRefresh);

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
        if(getBodyData().size()==0){
            holder.visibility(R.id.tip,true);
        }
    }

    @Override
    public void bindBodyData(AutoViewHolder holder, int bodyPosition, Recommend recommend) {
        if(recommend!=null) {
            holder.text(R.id.name, recommend.getName());
//            holder.text(R.id.date, NumberUtils.getFormatDateTimeNEWYMD(NumParseUtils.parseLong(recommend.getDate())));
            holder.text(R.id.date,recommend.getDate());
        }

    }
}
