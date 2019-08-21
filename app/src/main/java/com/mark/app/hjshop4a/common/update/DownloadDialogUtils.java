package com.mark.app.hjshop4a.common.update;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.AppContext;
import com.mark.app.hjshop4a.common.utils.LogUtils;
import com.mark.app.hjshop4a.common.androidenum.other.UpdateType;
import com.mark.app.hjshop4a.common.utils.download.DownloadUtil;
import com.mark.app.hjshop4a.common.utils.download.DownloadUtilHelper;
import com.mark.app.hjshop4a.ui.homepager.model.Lookup;
import com.mark.app.hjshop4a.ui.dialog.NormalDialogOneBtn;
import com.mark.app.hjshop4a.ui.dialog.NormalDialogTwoBtn;

import com.mark.app.hjshop4a.ui.dialog.factory.NormalDialogFactory;
import com.mark.app.hjshop4a.uinew.homepager.model.UpgradeSetting;


import java.text.DecimalFormat;

/**
 * 更新帮助类
 * Created by lenovo on 2017/11/15.
 */

public class DownloadDialogUtils {
    private static final String TAG = "DownloadUtilsHelper";

    //参数
    private static AppCompatActivity mActivity;
    private static String mUpdateUrl;
    private static String mDesc;
    private static boolean mIsAutoFinish;

    //保存对话框
    private static NormalDialogTwoBtn mDownTipDialog;
    private static NormalDialogOneBtn mDownDialog;

    //进度对话框是否显示
    private static boolean isDownDialogShow;
    //下载提示对话框移除时是否自动关闭activity
    private static boolean isDownTipDialogAutoFinish = true;
    /**
     * 版本更新
     *
     * @param data
     */
    public static void updateVersion(AppCompatActivity activity, UpgradeSetting data) {
        LogUtils.logFormat(TAG, "updateVersion", "开始更新检测");
        if (data == null) {
            LogUtils.logFormat(TAG, "updateVersion", "更新接口返回数据为空");
            return;
        }
        if (data.getAppUpgradeVersion() <= AppContext.versionCode()) {
            //如果最新版本号小于等于当前版本号，不更新
            LogUtils.logFormat(TAG, "updateVersion", "当前版本已是最新");
            return;
        }

        String url = data.getAppUpgradeUrl();
        if (TextUtils.isEmpty(url)) {
            //更新地址为空
            LogUtils.logFormat(TAG, "updateVersion", "更新接口返回appUrl为空");
            return;
        }
        String desc = data.getAppUpgradeDesc();
        if (TextUtils.isEmpty(desc)) {
            desc = activity.getString(R.string.Upgrade_有新的版本);
        }

        //是否需要更新app
        switch (data.getAppUpgradeStatus()) {
            case UpdateType.MUST_UPDATE: {
                //必须更新
                LogUtils.logFormat(TAG, "updateVersion", "必须更新");
                LogUtils.logFormat(TAG, "updateVersion", "更新地址：" + url);
                showUpdateDialog(activity, desc, url, true);
                break;
            }
            case UpdateType.CAN_UPDATE: {
                //非必须更新
                LogUtils.logFormat(TAG, "updateVersion", "非必须更新");
                LogUtils.logFormat(TAG, "updateVersion", "更新地址：" + url);
                showUpdateDialog(activity, desc, url, false);
                break;
            }
            default:
            case UpdateType.UN_UPDATE: {
                //无更新
                LogUtils.logFormat(TAG, "updateVersion", "无更新");
                break;
            }
        }
        LogUtils.logFormat(TAG, "updateVersion", "结束更新检测");
    }
    /**
     * 版本更新
     *
     * @param data
     */
    public static void updateVersion(AppCompatActivity activity, Lookup data) {
        LogUtils.logFormat(TAG, "updateVersion", "开始更新检测");
        if (data == null) {
            LogUtils.logFormat(TAG, "updateVersion", "更新接口返回数据为空");
            return;
        }
        if (data.getAppUpgradeVersion() <= AppContext.versionCode()) {
            //如果最新版本号小于等于当前版本号，不更新
            LogUtils.logFormat(TAG, "updateVersion", "当前版本已是最新");
            return;
        }

        String url = data.getAppUpgradeUrl();
        if (TextUtils.isEmpty(url)) {
            //更新地址为空
            LogUtils.logFormat(TAG, "updateVersion", "更新接口返回appUrl为空");
            return;
        }
        String desc = data.getAppUpgradeDesc();
        if (TextUtils.isEmpty(desc)) {
            desc = activity.getString(R.string.Upgrade_有新的版本);
        }

        //是否需要更新app
        switch (data.getAppUpgradeStatus()) {
            case UpdateType.MUST_UPDATE: {
                //必须更新
                LogUtils.logFormat(TAG, "updateVersion", "必须更新");
                LogUtils.logFormat(TAG, "updateVersion", "更新地址：" + url);
                showUpdateDialog(activity, desc, url, true);
                break;
            }
            case UpdateType.CAN_UPDATE: {
                //非必须更新
                LogUtils.logFormat(TAG, "updateVersion", "非必须更新");
                LogUtils.logFormat(TAG, "updateVersion", "更新地址：" + url);
                showUpdateDialog(activity, desc, url, false);
                break;
            }
            default:
            case UpdateType.UN_UPDATE: {
                //无更新
                LogUtils.logFormat(TAG, "updateVersion", "无更新");
                break;
            }
        }
        LogUtils.logFormat(TAG, "updateVersion", "结束更新检测");
    }

    /**
     * 显示更新对话框
     *
     * @param desc     描述信息
     * @param url      app下载地址
     * @param isFinish 如果不更新是否关闭app
     */
    private static void showUpdateDialog(AppCompatActivity activity, String desc, String url, boolean isFinish) {
        mActivity = activity;
        mUpdateUrl = url;
        mDesc = desc;
        mIsAutoFinish = isFinish;

        int leftBtnText = isFinish ? R.string.退出 : R.string.取消;
        int rightBtnText = R.string.更新;

        mDownTipDialog = NormalDialogFactory.getNormalDialogTwoBtn()
                .setContentText(mDesc)
                .setBtnText(leftBtnText, rightBtnText)
                .setOnDialogListener(new NormalDialogTwoBtn.DefOnDialogListener() {
                    @Override
                    public void onRightBtnClick() {
                        startDownload();
                    }

                    @Override
                    public void onDismiss() {
                        if (mIsAutoFinish && mActivity != null && isDownTipDialogAutoFinish) {
                            mActivity.finish();
                        }
                    }
                });
        mDownTipDialog.setAutoUseDismiss(false);
        mDownTipDialog.setCanceledOnTouchOutside(false);
        mDownTipDialog.show(mActivity.getFragmentManager());
        isDownTipDialogAutoFinish = true;
    }

    /**
     * 开始下载
     */
    private static void startDownload() {
        DownloadUtilHelper.startDownload(mActivity, mUpdateUrl, new DownloadUtil.ProgressCallback() {
            @Override
            public void call(int curProgress, int allProgress) {
                if (mDownTipDialog != null) {
                    isDownTipDialogAutoFinish = false;
                    mDownTipDialog.dismissAllowingStateLoss();
                }

                float mDownloadSoFar = (float) curProgress / (1024 * 1024);
                float mDownloadAll = (float) allProgress / (1024 * 1024);

                DecimalFormat format = new DecimalFormat("#0.00");

                String str = "已完成:" + format.format(mDownloadSoFar) + " M /"
                        + "总进度:" + format.format(mDownloadAll) + " M";

                boolean isDownFinish = allProgress > 0 && curProgress >= allProgress;
                int btnResId = isDownFinish ? R.string.安装 : R.string.正在下载请稍后;
                showDownloadDialog(str, isDownFinish, btnResId);
            }
        });
    }

    /**
     * 显示下载进度对话框
     *
     * @param str
     * @param isDownFinish
     * @param btnResId
     */
    private static void showDownloadDialog(String str, boolean isDownFinish, int btnResId) {
        try {
            if (mDownDialog == null) {
                mDownDialog = NormalDialogFactory.getNormalDialogOneBtn()
                        .setOnDialogListener(new NormalDialogOneBtn.DefOnDialogListener() {

                            @Override
                            public void onRightBtnClick() {
                                super.onRightBtnClick();
                                startDownload();
                            }

                            @Override
                            public void onDismiss() {
                                super.onDismiss();
                                isDownDialogShow = false;
                                if (mIsAutoFinish && mActivity != null) {
                                    mActivity.finish();
                                }
                                mDownDialog = null;
                            }
                        });
                mDownDialog.setAutoUseDismiss(false);
                mDownDialog.setCanceledOnTouchOutside(false);
                mDownDialog.show(mActivity.getFragmentManager());
                isDownDialogShow = true;
            }
            mDownDialog.setContentText(str);
            mDownDialog.setBtnText(btnResId);
            mDownDialog.setUseClickListener(isDownFinish);
            if (!isDownDialogShow) {
                mDownDialog.show(mActivity.getFragmentManager());
                isDownDialogShow = true;
            }
        } catch (Exception e) {
            if (AppContext.isDebudEnv()) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 权限申请回调
     *
     * @param activity
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void onRequestPermissionsResult(final AppCompatActivity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        DownloadUtilHelper.onRequestPermissionsResult(activity, requestCode, permissions, grantResults);
    }

    public static void onDestroy() {
        DownloadUtilHelper.onDestroy();
    }
}
