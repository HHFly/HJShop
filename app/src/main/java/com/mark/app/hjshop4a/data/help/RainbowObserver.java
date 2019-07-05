package com.mark.app.hjshop4a.data.help;

import android.app.Activity;
import android.app.FragmentManager;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.app.AppContext;
import com.mark.app.hjshop4a.common.androidenum.service.ServiceResultCode;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.LogUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.ui.dialog.factory.NormalDialogFactory;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class RainbowObserver <T> implements Observer<RainbowResultEntity<T>>, RainbowServiceListener<T> {
    /**
     * 返回值1012：数据为空是否认为是成功
     */
    boolean isNeed1012IsSuccess;
    /**
     * 是否显示系统提示
     */
    boolean isNeedShowSystemMsg;

    public RainbowObserver() {
        this(false);
    }

    public RainbowObserver(boolean isNeed1012IsSuccess) {
        this(isNeed1012IsSuccess, true);
    }

    public RainbowObserver(boolean isNeed1012IsSuccess, boolean isNeedShowSystemMsg) {
        this.isNeed1012IsSuccess = isNeed1012IsSuccess;
        this.isNeedShowSystemMsg = isNeedShowSystemMsg;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull RainbowResultEntity<T> obj) {
        if (AppContext.isDebudEnv()) {
            next(obj);
        } else {
            try {
                next(obj);
            } catch (Exception e) {
                toast(R.string.系统繁忙请稍后再试);
            }
        }
    }

    private void next(@NonNull RainbowResultEntity<T> obj) {
        if (AppContext.isDebudEnv()) {
            LogUtils.logFormat(this, "接口请求回调", JsonUtils.toJson(obj));
        }

        int resultCode = obj.getCode();
        if (resultCode == ServiceResultCode.SUCCESS
                || (resultCode == ServiceResultCode.SUCCESS_NODATA && isNeed1012IsSuccess)) {
            if(obj.getResult()==null)
            {
                onNullResult();
            }
            onSuccess(obj);
            onSuccessFinish();
        } else {
            boolean intecept = onOther(obj);
            if (!intecept) {
                //拦截登陆
                interceptLogin(resultCode);

                //如果不拦截，按照默认方式处理
                String reason = obj.getReason();
                if (!TextUtils.isEmpty(reason)) {
                    toast(reason);

                }

                if (AppContext.isDebudEnv()) {
                    Exception e = new Exception("返回值不是1000:    "+obj.getCode());
                    e.printStackTrace();
                }
            }
            onUnSuccessFinish();
        }
        onAllFinish();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (AppContext.isDebudEnv()) {
            error(e);
        } else {
            try {
                error(e);
            } catch (Exception ex) {
                toast(R.string.系统繁忙请稍后再试);
            }
        }
    }

    private void error(@NonNull Throwable e) {
        if (AppContext.isDebudEnv()) {
            LogUtils.logFormat(this, "接口请求回调", e.getMessage());

            e.printStackTrace();
        }
        onUnSuccessFinish();
        onAllFinish();
        toast(R.string.系统繁忙请稍后再试);
    }

    @Override
    public void onNullResult() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onUnSuccessFinish() {

    }

    @Override
    public void onSuccessFinish() {

    }

    @Override
    public void onAllFinish() {

    }

    @Override
    public boolean onOther(@NonNull RainbowResultEntity<T> obj) {
        return false;
    }

    /**
     * 显示对话框
     *
     * @param msg
     */
    private void showDialog(String msg) {
        FragmentManager manager = App.get().getFragmentManager();
        NormalDialogFactory.getNormalDialogOneBtn().setContentText(msg).show(manager);
    }

    /**
     * 拦截登陆
     *
     * @param resultCode
     */
    private void interceptLogin(int resultCode) {
        if (resultCode == ServiceResultCode.UN_LOGIN) {
            //未登录，清空登录信息
            App.get().setLogin(null);
            Activity activity = App.get().getCurActivity();
            if (activity != null) {
                ActivityJumpUtils.actLogin(activity);
            }
        }
    }

    /**
     * 弹窗
     *
     * @param data
     */
    private void toast(String data) {
        if (isNeedShowSystemMsg) {
            ToastUtils.show(data);
        }
    }

    /**
     * 弹窗
     *
     * @param data
     */
    private void toast(@StringRes int data) {
        if (isNeedShowSystemMsg) {
            ToastUtils.show(data);
        }
    }
    
}
