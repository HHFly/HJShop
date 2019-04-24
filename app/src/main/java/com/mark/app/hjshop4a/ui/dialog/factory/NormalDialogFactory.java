package com.mark.app.hjshop4a.ui.dialog.factory;

import android.support.annotation.StringRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.ui.dialog.NormalDialog;
import com.mark.app.hjshop4a.ui.dialog.NormalDialogOneBtn;
import com.mark.app.hjshop4a.ui.dialog.NormalDialogTwoBtn;


/**
 * 对话框工厂
 * Created by lenovo on 2017/8/31.
 */

public class NormalDialogFactory {
    /**
     * 获取温馨提示对话框(2个按钮)
     *
     * @return
     */
    public static NormalDialogTwoBtn getReminderDialogTwoBtn(@StringRes int resId, View.OnClickListener onRightBtnClickListener) {
        NormalDialogTwoBtn dialog = new NormalDialogTwoBtn();
        dialog
                .setContentText(resId)
                .setRightBtnClickListener(onRightBtnClickListener);
        return dialog;
    }

    /**
     * 获取2个按钮的提示框
     *
     * @return
     */
    public static NormalDialogTwoBtn getNormalDialogTwoBtn() {
        return new NormalDialogTwoBtn();
    }

    /**
     * 获取1个按钮的提示框
     *
     * @return
     */
    public static NormalDialogOneBtn getNormalDialogOneBtn() {
        return new NormalDialogOneBtn();
    }


//    /**
//     * 获取权限申请对话框
//     */
//    public static void getPermissDialogTwoBtn(final AppCompatActivity activity, final String[] pers, final int requestId, final boolean finishActivity) {
//        getPermissDialogTwoBtn(activity, pers, requestId, finishActivity,
//                R.string.请允许获取位置信息,
//                R.string.应用正常所需权限被拒绝);
//    }

    /**
     * 获取权限申请对话框
     */
    public static void getPermissDialogTwoBtn(final AppCompatActivity activity, final String[] pers, final int requestId, final boolean finishActivity,
                                              @StringRes int message, final @StringRes int finishMsg) {
        NormalDialog dialog = NormalDialogFactory.getNormalDialogTwoBtn()
                .setContentText(message)
                .setOnDialogListener(new NormalDialogTwoBtn.DefOnDialogListener() {
                    boolean mFinishActivity = finishActivity;

                    @Override
                    public void onRightBtnClick() {
                        super.onRightBtnClick();
                        ActivityCompat.requestPermissions(activity,
                                pers,
                                requestId);
                        mFinishActivity = false;
                    }

                    @Override
                    public void onDismiss() {
                        super.onDismiss();
                        if (mFinishActivity) {
                            ToastUtils.show(finishMsg);
                            activity.finish();
                        }
                    }
                });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show(activity.getFragmentManager());
    }

    /**
     * 获取权限提示对话框
     */
    public static void getPermissInfoDialogOneBtn(final AppCompatActivity activity, final @StringRes int message, final @StringRes int finishMessage, final boolean finishActivity) {
        NormalDialog dialog = NormalDialogFactory.getNormalDialogOneBtn()
                .setContentText(message)
                .setOnDialogListener(new NormalDialogOneBtn.DefOnDialogListener() {
                    @Override
                    public void onDismiss() {
                        if (finishActivity) {
                            ToastUtils.show(finishMessage);
                            activity.finish();
                        }
                    }
                });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show(activity.getFragmentManager());
    }

}
