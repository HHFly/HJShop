package com.mark.app.hjshop4a.ui.dialog.factory;

import android.app.Activity;
import android.content.res.AssetManager;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.PhotoType;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;

import com.mark.app.hjshop4a.common.utils.TakePhoneUtil;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;
import com.mark.app.hjshop4a.ui.dialog.ListDialog;
import com.mark.app.hjshop4a.ui.dialog.WheelDialog;
import com.mark.app.hjshop4a.widget.PickerScrollView;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 包含特定功能的对话框
 * Created by lenovo on 2017/11/27.
 */

public class FunctionDialogFactory {
    /**
     * 打开上传图片对话框
     */
    public static void showTakePhoneIDDialog(final android.support.v4.app.Fragment activity, final @IdRes int id) {
        List<ListDialog.ListDialogModel> data = new ArrayList<>();
        data.add(new ListDialog.ListDialogModel(0,"拍照"));
        data.add(new ListDialog.ListDialogModel(1, "从相机选择"));
        TakeImgUtil.setRId(id);
        ListDialog dialog = ListDialog.getInstance(data);
        dialog.setOnBtnClickListenr(new ListDialog.OnBtnClickListenr() {
            @Override
            public void onItemClick(View view, ListDialog.ListDialogModel data, int position) {
                switch (data.getId()) {
                    case 1: {
                        TakeImgUtil.choosePhoto(activity,id);
                        break;
                    }
                    case 0: {
                        TakeImgUtil.takePhoto(activity,id);
                        break;
                    }
                }
            }

            @Override
            public void onCancel() {

            }
        });
        dialog.show(activity.getActivity().getFragmentManager());
    }
    /**
     * 打开上传图片对话框
     */
    public static void showTakePhoneIDDialog(final Activity activity,final @IdRes int id) {
        List<ListDialog.ListDialogModel> data = new ArrayList<>();
        data.add(new ListDialog.ListDialogModel(0,"拍照"));
        data.add(new ListDialog.ListDialogModel(1, "从相机选择"));
        TakeImgUtil.setRId(id);
        ListDialog dialog = ListDialog.getInstance(data);
        dialog.setOnBtnClickListenr(new ListDialog.OnBtnClickListenr() {
            @Override
            public void onItemClick(View view, ListDialog.ListDialogModel data, int position) {
                switch (data.getId()) {
                    case 1: {
                        TakeImgUtil.choosePhoto(activity,id);
                        break;
                    }
                    case 0: {
                        TakeImgUtil.takePhoto(activity,id);
                        break;
                    }
                }
            }

            @Override
            public void onCancel() {

            }
        });
        dialog.show(activity.getFragmentManager());
    }
    /**
     * 打开上传图片对话框
     */
    public static void showTakePhoneDialog(final Activity activity) {
        List<ListDialog.ListDialogModel> data = new ArrayList<>();
        data.add(new ListDialog.ListDialogModel(0,"拍照"));
        data.add(new ListDialog.ListDialogModel(1, "从相机选择"));
        ListDialog dialog = ListDialog.getInstance(data);
        dialog.setOnBtnClickListenr(new ListDialog.OnBtnClickListenr() {
            @Override
            public void onItemClick(View view, ListDialog.ListDialogModel data, int position) {
                switch (data.getId()) {
                    case PhotoType.ALBUM: {
                        TakePhoneUtil.choosePhoto(activity);
                        break;
                    }
                    case PhotoType.CAMERA: {
                        TakePhoneUtil.takePhoto(activity);
                        break;
                    }
                }
            }

            @Override
            public void onCancel() {

            }
        });
        dialog.show(activity.getFragmentManager());
    }



    /**
     * 显示一个参数的对话框
     */
    public static void showAddOneParamDialog(final BaseActivity activity, @StringRes int paramEmptyHint, @IdRes final int idres) {

        AddOneEtParamDialog mAddOneEtParamDialog = AddOneEtParamDialog.getInstance(false);

        mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
            @Override
            public void onClickCommit(AddOneEtParamDialog dialog, String data) {
//                requestUpdateParam(type, data);
                activity.setTvText(idres,data);

                dialog.dismiss();
            }
        });
//        mAddOneEtParamDialog.setTvParamName(paramName);
        mAddOneEtParamDialog.setTvEmptyParamHint(paramEmptyHint);
        mAddOneEtParamDialog.show(activity.getFragmentManager());
    }
    /**
     * 显示一个参数的对话框
     */
    public static void showAddOneParamDialog(final BaseActivity activity, String paramEmptyHint, @IdRes final int idres) {

        AddOneEtParamDialog mAddOneEtParamDialog = AddOneEtParamDialog.getInstance(false);

        mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
            @Override
            public void onClickCommit(AddOneEtParamDialog dialog, String data) {
//                requestUpdateParam(type, data);
                activity.setTvText(idres,data);
                dialog.dismiss();
            }
        });
//        mAddOneEtParamDialog.setTvParamName(paramName);
        mAddOneEtParamDialog.setTvEmptyParamHint(paramEmptyHint);
        mAddOneEtParamDialog.show(activity.getFragmentManager());
    }


    /**
     * 显示一个参数的对话框输入数字
     */
    public static void showAddOneParamDialogNum(final BaseActivity activity, String paramEmptyHint, @IdRes final int idres) {

        AddOneEtParamDialog mAddOneEtParamDialog = AddOneEtParamDialog.getInstance(true);

        mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
            @Override
            public void onClickCommit(AddOneEtParamDialog dialog, String data) {
//                requestUpdateParam(type, data);
                activity.setTvText(idres,data);

                dialog.dismiss();
            }
        });
//        mAddOneEtParamDialog.setTvParamName(paramName);
        mAddOneEtParamDialog.setTvEmptyParamHint(paramEmptyHint);
        mAddOneEtParamDialog.show(activity.getFragmentManager());
    }
    /*显示滚轮参数
   * */
    public static   void  showWheeDialog(final BaseActivity activity){
        AssetManager asset = activity.getAssets();

        InputStream input  =null;
        try {
            input = asset.open("province_data.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        WheelDialog dialog = WheelDialogFactory.getProvinceWheelDialog(input);
        dialog.setOnDialogClickListener(new WheelDialog.OnDialogClickListener() {
            @Override
            public void onCancel() {

            }

            @Override
            public void onOk(PickerScrollView.ItemModel data) {
                activity.setTvText(R.id.certification_tv_user_city,data.getName());
            }
        }).show(activity.getFragmentManager());
    }

}
