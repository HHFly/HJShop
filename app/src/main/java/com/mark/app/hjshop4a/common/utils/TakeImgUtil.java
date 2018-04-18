package com.mark.app.hjshop4a.common.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Build.VERSION;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;


import com.mark.app.hjshop4a.R;
import com.white.lib.utils.ToastUtil;
import com.white.lib.utils.UtilsConfig;
import com.white.lib.utils.permisstion.PermissionCheckCallBack;
import com.white.lib.utils.permisstion.PermissionRequestSuccessCallBack;
import com.white.lib.utils.permisstion.PermissionUtil;
import java.io.File;

import static android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION;
import static android.content.Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

/**
 * Created by pc on 2018/4/18.
 */

public class TakeImgUtil {
    private static final String permissionWrite = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final String permissionCamera = "android.permission.CAMERA";
    private static Uri mUri;
    private static  @IdRes int id;
    public TakeImgUtil() {

    }
    public static void  setRId(final @IdRes int rid){
        id= rid;
    }
    public static void choosePhoto(final Activity activity,final @IdRes int id) {
        PermissionUtil.checkAndRequestMorePermissions(activity, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 2, new PermissionRequestSuccessCallBack() {
            public void onHasPermission() {
                TakeImgUtil.openChooser(activity,id);
            }
        });
    }

    private static void openChooser(Activity activity,final @IdRes int id) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "选择图片"), 2);
    }

    public static void takePhoto(final Activity activity ,final @IdRes int id) {
        String[] pers = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
        PermissionUtil.checkAndRequestMorePermissions(activity, pers, 1, new PermissionRequestSuccessCallBack() {
            public void onHasPermission() {
                TakeImgUtil.openPhoto(activity,id);
            }
        });
    }

    private static void openPhoto(Activity context,final @IdRes int id) {
        File file = getFile();
        Intent intentFromCapture = new Intent("android.media.action.IMAGE_CAPTURE");
        if(Build.VERSION.SDK_INT >= 24) {
            String strFileProvider = UtilsConfig.getInstance(context).getFileProvider();
            intentFromCapture.addFlags(FLAG_GRANT_READ_URI_PERMISSION);
            intentFromCapture.addFlags(FLAG_GRANT_WRITE_URI_PERMISSION);
            mUri = FileProvider.getUriForFile(context, strFileProvider, file);
        } else {
            mUri = Uri.fromFile(file);
        }
        intentFromCapture.putExtra("output", mUri);
        context.startActivityForResult(intentFromCapture, 1);
    }

    private static File getFile() {
        File imagePath = new File(Environment.getExternalStorageDirectory(), "images");
        if(!imagePath.exists()) {
            imagePath.mkdirs();
        }

        return new File(imagePath, System.currentTimeMillis() + ".jpg");
    }

    public static void onRequestPermissionsResult(final Activity activity, int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 1:
                PermissionUtil.onRequestMorePermissionsResult(activity, permissions, new PermissionCheckCallBack() {
                    public void onHasPermission() {
                        TakeImgUtil.openPhoto(activity,0);
                    }

                    public void onUserHasAlreadyTurnedDown(String... permission) {
                        ToastUtil.show(com.white.lib.R.string.mcs_1);
                    }

                    public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                    }
                });
                break;
            case 2:
                PermissionUtil.onRequestMorePermissionsResult(activity, permissions, new PermissionCheckCallBack() {
                    public void onHasPermission() {
                        TakeImgUtil.openChooser(activity,0);
                    }

                    public void onUserHasAlreadyTurnedDown(String... permission) {
                        ToastUtil.show(com.white.lib.R.string.mcs_1);
                    }

                    public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                    }
                });
        }

    }

    public static void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data, TakeImgUtil.CallBack call) {
        switch(requestCode) {
            case 1:
                if(resultCode == -1 && call != null) {
                    call.back(mUri,id);
                }

                mUri = null;
                break;
            case 2:
                if(resultCode == -1 && data != null) {
                    mUri = data.getData();
                    if(mUri == null) {
                        Bundle bundle = data.getExtras();
                        mUri = (Uri)bundle.get("data");
                    }

                    if(call != null && mUri != null) {
                        call.back(mUri, id);
                    }
                }

                mUri = null;
        }

    }

    public interface CallBack {
        void back(Uri var1,@IdRes int id);
    }
}
