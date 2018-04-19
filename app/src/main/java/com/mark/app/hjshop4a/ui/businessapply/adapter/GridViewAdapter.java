package com.mark.app.hjshop4a.ui.businessapply.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;

import java.util.List;

import retrofit2.http.Url;

/**
 * Created by pc on 2018/4/19.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private List<Uri> mList;
    private LayoutInflater inflater;
    public GridViewAdapter(Context mContext, List<Uri> mList) {
        this.mContext = mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        //return mList.size() + 1;//因为最后多了一个添加图片的ImageView
        int count = mList == null ? 1 : mList.size() + 1;
        if (count > 4) {
            return mList.size();
        } else {
            return count;
        }
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.item_gird_storeimg, parent,false);
        SimpleDraweeView sdv =  (SimpleDraweeView) convertView.findViewById(R.id.imagebtn);

        if (position < mList.size()) {
            //代表+号之前的需要正常显示图片
            Uri picUrl = mList.get(position); //图片路径
            FrescoUtils.sdvBig(sdv, picUrl);
        } else {
//            sdv.setImageResource(R.mipmap.gird_storeimg_update);//最后一个显示加号图片
            FrescoUtils.sdvSmall( sdv, FrescoUtils.getUriByResId(R.mipmap.gird_storeimg_update));
        }
        return convertView;
    }


}
