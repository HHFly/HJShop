package com.mark.app.hjshop4a.uinew.dialog;

import android.Manifest;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseDialogFragment;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * 查看大图
 * Created by lenovo on 2017/12/1.
 */

public class BigSdvDialog extends BaseDialogFragment implements EasyPermissions.PermissionCallbacks {
    private static final int REQUEST_CODE_SAVE_IMG = 10;
    private String mImageUrl;
    private  int url;
    int type ;
    public static BigSdvDialog getInstance(String imageUrl) {
        BigSdvDialog dialog = new BigSdvDialog();
        dialog.mImageUrl = imageUrl;
        return dialog;
    }
    public static BigSdvDialog getInstance(int imageUrl,int type) {
        BigSdvDialog dialog = new BigSdvDialog();
        dialog.url = imageUrl;
        dialog.type =type;
        return dialog;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_big_sdv, container, false);
        SimpleDraweeView sdv = (SimpleDraweeView) view.findViewById(R.id.dialog_sdv);
        switch (type){
            case 0:
                FrescoUtils.sdvBig(sdv, mImageUrl);
                break;
            case 1:
                sdv.setImageResource(url);
                break;
                default:FrescoUtils.sdvBig(sdv, mImageUrl);
                break;
        }
        sdv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        sdv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                switch (type){
                    case 0:
                        showYesNoDialog();
                        break;
                    case 1:

                        break;

                }

                return true;
            }
        });
        return view;
    }
    private SaveImgDialog saveImgDialog;
    /*
     * 显示性别选择*/
    private  void  showYesNoDialog(){
        if(saveImgDialog ==null){
            saveImgDialog =new SaveImgDialog();
        }
        saveImgDialog.setOnDialogClickListener(new SaveImgDialog.OnDialogClickListener() {
            @Override
            public void onClickYes() {
                switch (type){
                    case 0:
                        requestPermission();
                        break;

                }
            }
        });
        saveImgDialog.setContent(this.getActivity());

        saveImgDialog.show(getFragmentManager());
    }
    @Override
    public void onStart() {
        super.onStart();
        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(new ColorDrawable(0x00ffffff));

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager.LayoutParams params = win.getAttributes();
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;

        win.setAttributes(params);
    }

    /**
     * 请求读取sd卡的权限
     */
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //读取sd卡的权限
            String[] mPermissionList = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (EasyPermissions.hasPermissions(getContext(), mPermissionList)) {
                //已经同意过
                saveImage();
            } else {
                //未同意过,或者说是拒绝了，再次申请权限
                EasyPermissions.requestPermissions(
                        getActivity(),  //上下文
                        "保存图片需要读取sd卡的权限", //提示文言
                        REQUEST_CODE_SAVE_IMG, //请求码
                        mPermissionList //权限列表
                );
            }
        } else {
            saveImage();
        }
    }


    //保存图片
    private void saveImage() {

        boolean isSaveSuccess = FrescoUtils.saveImageToGallery(getActivity(), Uri.parse(mImageUrl));
        if (isSaveSuccess) {
            Toast.makeText(getActivity(), "保存图片成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "保存图片失败，请稍后重试", Toast.LENGTH_SHORT).show();
        }
    }

    //授权结果，分发下去
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        //跳转到onPermissionsGranted或者onPermissionsDenied去回调授权结果
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    //同意授权
    @Override
    public void onPermissionsGranted(int requestCode, List<String> list) {

        saveImage();
    }

    //拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

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
            saveImage();
        }
    }
}
