package com.mark.app.hjshop4a.ui.start;

import android.Manifest;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.ActRequestCode;
import com.mark.app.hjshop4a.common.permisstion.deflistener.PermissionCheckCallBack;
import com.mark.app.hjshop4a.common.permisstion.deflistener.PermissionRequestSuccessCallBack;
import com.mark.app.hjshop4a.common.permisstion.deflistener.PermissionUtil;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.StatusBarUtil;
import com.mark.app.hjshop4a.ui.dialog.factory.NormalDialogFactory;
import com.mark.app.hjshop4a.ui.homepager.activity.HomePagerActivity;


/**
 * 权限申请界面
 * Created by lenovo on 2017/9/26.
 */

public class PermisstionActivity extends BaseActivity {
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
                PermissionUtil.checkAndRequestMorePermissions(getActivity(), pers, ActRequestCode.PERMISSION, new PermissionRequestSuccessCallBack() {
                    @Override
                    public void onHasPermission() {
                        goNext();
                    }
                });
            }
        }, 100);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
//        if (AppContext.isDebudEnv()) {
//            ActivityJumpUtils.actTest(this.getActivity());
//        } else {
            ActivityJumpUtils.actActivity(getActivity(), HomePagerActivity.class);
//        }
        finish();
    }
}
