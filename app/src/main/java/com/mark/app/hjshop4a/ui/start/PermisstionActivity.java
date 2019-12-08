package com.mark.app.hjshop4a.ui.start;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.ActRequestCode;
import com.mark.app.hjshop4a.common.permisstion.deflistener.PermissionCheckCallBack;
import com.mark.app.hjshop4a.common.permisstion.deflistener.PermissionUtil;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.StatusBarUtil;
import com.mark.app.hjshop4a.common.utils.permission.FloatWindowManager;
import com.mark.app.hjshop4a.ui.dialog.factory.NormalDialogFactory;
import com.mark.app.hjshop4a.uinew.homepager.activity.HomePagerActivity;

import java.util.List;


import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * 权限申请界面
 * Created by lenovo on 2017/9/26.
 */

public class PermisstionActivity extends BaseActivity  implements EasyPermissions.PermissionCallbacks{
    private final String[] pers = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    public int getContentViewResId() {
        return R.layout.activity_permisstion;
    }

    @Override
    public void getIntentParam(Bundle bundle) {

    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {

    }

    @Override
    public void initView() {
        StatusBarUtil.immersive(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FloatWindowManager.getInstance().applyOrShowFloatWindow(getActivity());
                    String[] mPermissionList = new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW};
                    if (EasyPermissions.hasPermissions(getActivity(), mPermissionList)) {
                        //已经同意过
                        goNext();
                    } else {
                        //未同意过,或者说是拒绝了，再次申请权限
                        EasyPermissions.requestPermissions(
                                getActivity(),  //上下文
                                "保存图片需要读取sd卡的权限", //提示文言
                                10, //请求码
                                mPermissionList //权限列表
                        );
                    }

            }
        }, 2000);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        PermissionUtil.onRequestMorePermissionsResult(getActivity(), permissions, new PermissionCheckCallBack() {
            @Override
            public void onHasPermission() {
                goNext();
            }

            @Override
            public void onUserHasAlreadyTurnedDown(String... permission) {
                NormalDialogFactory.getPermissDialogTwoBtn(PermisstionActivity.this, permission, ActRequestCode.PERMISSION, true,
                        R.string.请允许获取位置信息,
                        R.string.应用正常所需权限被拒绝);
            }

            @Override
            public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                NormalDialogFactory.getNormalDialogTwoBtn()
                        .setContentText("当前已禁止定位权限申请")
                        .setRightBtnText(R.string.me_前往)
                        .setLeftBtnText(R.string.me_退出)
                        .setCanceledOnTouchOutside(false)
                        .setLeftBtnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                finish();
                            }
                        })
                        .setRightBtnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PermissionUtil.toAppSetting(getActivity());
                            }
                        }).show(getFragmentManager());
            }
        });
    }

    /**
     * 前往下个Activity
     */
    private void goNext() {
        ActivityJumpUtils.actActivity(getActivity(), HomePagerActivity.class);

        finish();
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        goNext();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(getActivity(), perms)) {
            //打开系统设置，手动授权
            new AppSettingsDialog.Builder(getActivity()).build().show();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            //拒绝授权后，从系统设置了授权后，返回APP进行相应的操作
           goNext();
        }
    }
}
