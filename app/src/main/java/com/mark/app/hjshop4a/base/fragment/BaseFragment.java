package com.mark.app.hjshop4a.base.fragment;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.ShowLoadingDialogListener;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.PasswordUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.location.LocationManagerUtil;
import com.mark.app.hjshop4a.common.utils.luban.LubanUtil;
import com.mark.app.hjshop4a.common.utils.luban.model.LubanResultData;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;


import java.io.File;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * fragment基类
 */

public abstract class BaseFragment extends Fragment implements View.OnClickListener, ShowLoadingDialogListener {
    /**
     * 根布局
     */
    private View mRootView;

    /**
     * 存放View
     */
    private SparseArray<View> mViews;
    /**
     * 是否非第一次执行onResume方法
     */
    private boolean isSecondResume;
    /**
     * 定位管理工具类
     */
    private LocationManagerUtil mLocationManagerUtil;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater(inflater, container, getContentResId());
        findView();
        setListener();
        initView();
        isSecondResume = false;
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isSecondResume) {
            onUnFirstResume();
        } else {
            isSecondResume = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mLocationManagerUtil != null) {
            mLocationManagerUtil.unRegisterForThisCallback();
            mLocationManagerUtil = null;
        }
        mRootView = null;
        if (mViews != null) {
            int count = mViews.size();
            for (int i = 0; i < count; i++) {
                int key = mViews.keyAt(i);
                View view = mViews.get(key);
                destroyView(view);
            }
            mViews.clear();
            mViews = null;
        }
    }

    /**
     * 释放View
     *
     * @param view
     */
    private void destroyView(View view) {
        if (view != null) {
            if (view instanceof RecyclerView) {
                RecyclerView rv = (RecyclerView) view;
                rv.setAdapter(null);
            }
        }
    }

    /**
     * onResume中第一次不执行的方法
     */
    public void onUnFirstResume() {
    }

    /**
     * 显示加载中对话框
     */
    @Override
    public void showLoadingDialog() {
        Activity baseActivity = getActivity();
        if (baseActivity != null && !baseActivity.isDestroyed() && baseActivity instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) baseActivity;
            activity.showLoadingDialog();
        }
    }

    /**
     * 隐藏加载中对话框
     */
    @Override
    public void hideLoadingDialog() {
        Activity baseActivity = getActivity();
        if (baseActivity != null && !baseActivity.isDestroyed() && baseActivity instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) baseActivity;
            activity.hideLoadingDialog();
        }
    }

    /**
     * 获取内容资源文件
     *
     * @return
     */
    @LayoutRes
    public abstract int getContentResId();

    /**
     * 获取View
     */
    public abstract void findView();

    /**
     * 设置监听
     */
    public abstract void setListener();

    /**
     * 初始化View
     */
    public abstract void initView();

    /**
     * 是否销毁
     *
     * @return
     */
    public boolean isDestroyed() {
        return getActivity() == null || getActivity().isDestroyed();
    }

    /**
     * 获取实例
     *
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T getView(@IdRes int id) {
        return getView(mRootView, id);
    }

    /**
     * 获取实例
     *
     * @param rootView
     * @param id
     * @param <T>
     * @return
     */
    public <T extends View> T getView(View rootView, @IdRes int id) {
        if (rootView == null) {
            return null;
        }
        return (T) rootView.findViewById(id);
    }
    /**
     * 设置TextView文案
     *
     * @param id
     * @param resId
     */
    public void setIvImage(@IdRes int id, @DrawableRes int resId) {
        View view = getView(id);
        if (view != null && view instanceof ImageView) {
            ImageView tv = (ImageView) view;
            tv.setImageResource(resId);
        }
    }
    /**
     * 设置TextView文案
     *
     * @param id
     * @param resId
     */
    public void setTvText(@IdRes int id, @StringRes int resId) {
        View view = getView(id);
        if (view != null && view instanceof TextView) {
            TextView tv = (TextView) view;
            tv.setText(resId);
        }
    }

    /**
     * 设置TextView文案
     *
     * @param id
     * @param str
     */
    public void setTvText(@IdRes int id, String str) {
        View view = getView(id);
        if (view != null && view instanceof TextView) {
            TextView tv = (TextView) view;
            tv.setText(str);
        }
    }

    /**
     * 设置是否显示
     *
     * @param id
     * @param isShow
     */
    public void setVisibilityGone(@IdRes int id, boolean isShow) {
        View view = getView(id);
        if (view != null) {
            view.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置是否显示
     *
     * @param view
     * @param isShow
     */
    public void setVisibilityGone(View view, boolean isShow) {
        if (view != null) {
            view.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 创建View
     *
     * @param inflater
     * @param group
     * @param resId
     * @return
     */
    public View inflater(LayoutInflater inflater, ViewGroup group, @LayoutRes int resId) {
        return inflater.inflate(resId, group, false);
    }

    /**
     * 创建View
     *
     * @param group
     * @param resId
     * @return
     */
    public View inflater(ViewGroup group, @LayoutRes int resId) {
        return LayoutInflater.from(group.getContext()).inflate(resId, group, false);
    }

    /**
     * 设置SDV图片（小图）
     *
     * @param id
     * @param url
     */
    public void setSdvImgSmall(@IdRes int id, String url) {
        View view = getView(id);
        if (view != null && view instanceof SimpleDraweeView) {
            SimpleDraweeView sdv = (SimpleDraweeView) view;
            FrescoUtils.sdvSmall(sdv, url);
        }
    }

    /**
     * 设置SDV图片（中图）
     *
     * @param id
     * @param url
     */
    public void setSdvImgInside(@IdRes int id, String url) {
        View view = getView(id);
        if (view != null && view instanceof SimpleDraweeView) {
            SimpleDraweeView sdv = (SimpleDraweeView) view;
            FrescoUtils.sdvInside(sdv, url);
        }
    }

    /**
     * 设置SDV图片（中图）
     *
     * @param id
     * @param uri
     */
    public void setSdvImgInside(@IdRes int id, Uri uri) {
        View view = getView(id);
        if (view != null && view instanceof SimpleDraweeView) {
            SimpleDraweeView sdv = (SimpleDraweeView) view;
            FrescoUtils.sdvInside(sdv, uri);
        }
    }

    /**
     * 设置SDV图片（大图）
     *
     * @param id
     * @param url
     */
    public void setSdvImgBig(@IdRes int id, String url) {
        View view = getView(id);
        if (view != null && view instanceof SimpleDraweeView) {
            SimpleDraweeView sdv = (SimpleDraweeView) view;
            FrescoUtils.sdvBig(sdv, url);
        }
    }

    /**
     * 设置监听
     *
     * @param id
     */
    public void setClickListener(@IdRes int id) {
        View view = getView(id);
        if (view != null) {
            view.setOnClickListener(this);
        }
    }

    /**
     * 设置View的setVisibility
     *
     * @param id
     * @param visibility
     */
    public void setViewVisibilityGone(@IdRes int id, boolean visibility) {
        View view = getView(id);
        if (view != null) {
            view.setVisibility(visibility ? View.VISIBLE : View.GONE);
        }
    }

    /**
     * 设置View的setSelected
     *
     * @param id
     * @param selected
     */
    public void setViewSelected(@IdRes int id, boolean selected) {
        View view = getView(id);
        if (view != null) {
            view.setSelected(selected);
        }
    }

    /**
     * 设置View的
     *
     * @param id
     * @param isEnable
     */
    public void setViewEnable(@IdRes int id, boolean isEnable) {
        View view = getView(id);
        if (view != null) {
            view.setEnabled(isEnable);
        }
    }
    /**
     * 压缩图片
     *
     * @param uri
     */
    public void luban(Uri uri, final DefOnUploadPicListener listener) {
        LubanUtil.getInstance(this.getContext()).setOnLubanListener(new LubanUtil.DefOnLubanListener() {
            @Override
            public void onSuccess(LubanResultData lubanResultData) {
                requestUploadImage(lubanResultData.getAfterFile(), listener);
            }
        }).startForUri(uri);
    }

    /**
     * 压缩图片
     *
     * @param uri
     */
    public void luban(List<Uri> uri, final DefOnUploadPicListener listener) {
        LubanUtil.getInstance(this.getContext()).setOnLubanListener(new LubanUtil.DefOnLubanListener() {
            @Override
            public void onSuccess(LubanResultData lubanResultData) {
                requestUploadImage(lubanResultData.getAfterFile(), listener);
            }
        }).startForUri(uri);
    }

    /**
     * 上传图片接口
     *
     * @param file
     */
    private void requestUploadImage(File file, final DefOnUploadPicListener listenr) {
        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
//                .addFormDataPart("uploadType", "上传图片")
                .addFormDataPart("img", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                .build();
        App.getServiceManager().getPdmService().uploadImage(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<String>() {
                    @Override
                    public void onSuccess(@io.reactivex.annotations.NonNull BaseResultEntity<String> obj) {
                        String data = JsonUtils.fromJson(PasswordUtil.decode(obj.getResult()),String.class);
                        if (listenr != null) {
                            if (TextUtils.isEmpty(data)) {

                                listenr.onLoadPicUnSuccessFinish();
                            } else {
                                ToastUtils.show("图片上传成功");
                                listenr.onLoadPicFinish(data);
                            }
                        }
                    }

                    @Override
                    public void onUnSuccessFinish() {
                        super.onUnSuccessFinish();
                        ToastUtils.show("图片上传失败");
                        if (listenr != null) {
                            listenr.onLoadPicUnSuccessFinish();
                        }
                    }
                });
    }

}
