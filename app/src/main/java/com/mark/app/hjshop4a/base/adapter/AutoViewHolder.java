package com.mark.app.hjshop4a.base.adapter;

import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.app.base.recylerview.MkViewHolder;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * RecyclerView的ViewHolder
 * Created by lenovo on 2017/8/29.
 */

public class AutoViewHolder extends MkViewHolder {
    public AutoViewHolder(View view) {
        super(view);
        AutoUtils.autoSize(view);
    }

    /**
     * 根据id获取View
     *
     * @param resId
     * @param <T>
     * @return
     */
    public <T extends View> T get(@IdRes int resId) {
        return get(itemView, resId);
    }

    /**
     * 根据id获取View
     *
     * @param resId
     * @param <T>
     * @return
     */
    private <T extends View> T get(View view, @IdRes int resId) {
        SparseArray<View> sparse = (SparseArray<View>) view.getTag();
        if (sparse == null) {
            sparse = new SparseArray<>();
            view.setTag(sparse);
        }
        View childView = sparse.get(resId);
        if (childView == null) {
            childView = view.findViewById(resId);
            sparse.put(resId, childView);
        }
        return (T) childView;
    }

    /**
     * 设置TextView文本
     *
     * @param id
     * @param sequence
     * @return
     */
    public AutoViewHolder text(@IdRes int id, CharSequence sequence) {
        View view = get(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(sequence);
        }
        return this;
    }

    /**
     * 设置TextView文本
     *
     * @param id
     * @param stringRes
     * @return
     */
    public AutoViewHolder text(@IdRes int id, @StringRes int stringRes) {
        View view = get(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(stringRes);
        }
        return this;
    }

    /**
     * 设置TextView字体颜色
     *
     * @param id
     * @param colorId
     * @return
     */
    public AutoViewHolder textColorId(@IdRes int id, int colorId) {
        View view = get(id);
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(ContextCompat.getColor(view.getContext(), colorId));
        }
        return this;
    }

    /**
     * 设置ImageView图片
     *
     * @param id
     * @param imageId
     * @return
     */
    public AutoViewHolder image(@IdRes int id, int imageId) {
        View view = get(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(imageId);
        }
        return this;
    }

    /**
     * 设置SimpleDraweeView图片
     *
     * @param id
     * @param imgUrl
     * @return
     */
    public AutoViewHolder sdvBig(@IdRes int id, String imgUrl) {
        View view = get(id);
        if (view instanceof SimpleDraweeView) {
            FrescoUtils.sdvBig((SimpleDraweeView) view, imgUrl);
        }
        return this;
    }

    /**
     * 设置SimpleDraweeView图片
     *
     * @param id
     * @param imgUrl
     * @return
     */
    public AutoViewHolder sdvInside(@IdRes int id, String imgUrl) {
        View view = get(id);
        if (view instanceof SimpleDraweeView) {
            FrescoUtils.sdvInside((SimpleDraweeView) view, imgUrl);
        }
        return this;
    }

    /**
     * 设置SimpleDraweeView图片
     *
     * @param id
     * @param uri
     * @return
     */
    public AutoViewHolder sdvSmall(@IdRes int id, Uri uri) {
        View view = get(id);
        if (view instanceof SimpleDraweeView) {
            FrescoUtils.sdvSmall((SimpleDraweeView) view, uri);
        }
        return this;
    }

    /**
     * 设置SimpleDraweeView图片
     *
     * @param id
     * @param imgUrl
     * @return
     */
    public AutoViewHolder sdvSmall(@IdRes int id, String imgUrl) {
        View view = get(id);
        if (view instanceof SimpleDraweeView) {
            FrescoUtils.sdvSmall((SimpleDraweeView) view, imgUrl);
        }
        return this;
    }

    /**
     * 设置SimpleDraweeView图片
     *
     * @param id
     * @param
     * @return
     */
    public AutoViewHolder sdvSmall(@IdRes int id, int resId) {
        View view = get(id);
        if (view instanceof SimpleDraweeView) {
            FrescoUtils.sdvSmall((SimpleDraweeView) view, FrescoUtils.getUriByResId(resId));
        }
        return this;
    }

    /**
     * 设置View是否显示
     *
     * @param id
     * @param isVisibility
     * @return
     */
    public AutoViewHolder visibility(@IdRes int id, boolean isVisibility) {
        View view = get(id);
        if (view != null) {
            view.setVisibility(isVisibility ? View.VISIBLE : View.GONE);
        }
        return this;
    }
    public AutoViewHolder backgroundColor(@IdRes int id, int color) {
        View view = get(id);
        if (view != null) {
            view.setBackgroundColor(color);
        }
        return this;
    }

    /**
     * 设置View是否显示
     *
     * @param id
     * @param isVisibility
     * @return
     */
    public AutoViewHolder visibilityInVisible(@IdRes int id, boolean isVisibility) {
        View view = get(id);
        if (view != null) {
            view.setVisibility(isVisibility ? View.VISIBLE : View.INVISIBLE);
        }
        return this;
    }
}
