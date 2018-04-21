package com.mark.app.hjshop4a.ui.homepager.start.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;
import com.mark.app.hjshop4a.model.launch.GuidePage;


import java.util.List;

/**
 * 功能：引导页ViewPager适配器
 * 作者：ljju
 */

public class GuideVpAdapter extends PagerAdapter {

    //数据源
    private List<GuidePage> mData;

    //ItemView
    private SimpleDraweeView[] mItemView;

    //点击监听器
    private OnItemClickListener onItemClickListener;

    public GuideVpAdapter(List<GuidePage> data) {
        mData = data;
        if (data != null) {
            mItemView = new SimpleDraweeView[data.size()];
        }
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mItemView[position]);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        SimpleDraweeView sdv = mItemView[position];
        if (sdv == null) {
            final GuidePage itemData = mData.get(position);
            sdv = (SimpleDraweeView) LayoutInflater.from(container.getContext()).inflate(R.layout.guide_sdv, container, false);
            FrescoUtils.sdvBig(sdv, FrescoUtils.getUriByResId(itemData.getImageResId()));
            if (position == getCount() - 1) {
                //最后一页
                sdv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onClickLastPage(position);
                        }
                    }
                });
            }
            mItemView[position] = sdv;
        }
        container.addView(sdv);
        return sdv;
    }

    /**
     * 最后一页点击监听器
     */
    public interface OnItemClickListener {
        /**
         * 点击最后一页
         *
         * @param position
         */
        void onClickLastPage(int position);
    }

    /**
     * 设置最后一页点击监听器
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }
}
