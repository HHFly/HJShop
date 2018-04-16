package com.mark.app.hjshop4a.ui.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseDialogFragment;
import com.mark.app.hjshop4a.widget.PickerScrollView;

import java.util.List;

/**
 * 功能：滑动选择对话框

 */

public class WheelDialog extends BaseDialogFragment implements PickerScrollView.onSelectListener {

    private List<PickerScrollView.ItemModel> mData;

    //选中的数据模型
    private PickerScrollView.ItemModel mSelectItem;

    //按钮监听
    private OnDialogClickListener onDialogClickListener;

    /**
     * 获取实例
     *
     * @return WheelDialog
     */
    public static WheelDialog getInstance(List<PickerScrollView.ItemModel> data) {
        WheelDialog dialog = new WheelDialog();
        dialog.mData = data;
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_wheel, container, false);

        PickerScrollView psv = getView(view, R.id.co_psv);
        psv.setData(mData);
        psv.setSelected(0);
        psv.setOnSelectListener(this);

        getView(view, R.id.wheel_btn_cancel).setOnClickListener(this);
        getView(view, R.id.wheel_btn_finish).setOnClickListener(this);

        getDialog().getWindow().getAttributes().windowAnimations = R.style.ListDialogAnim;
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wheel_btn_cancel: {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onCancel();
                }
                dismiss();
                break;
            }
            case R.id.wheel_btn_finish: {
                if (onDialogClickListener != null) {
                    onDialogClickListener.onOk(mSelectItem);
                }
                dismiss();
                break;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Window win = getDialog().getWindow();
        // 一定要设置Background，如果不设置，window属性设置无效
        win.setBackgroundDrawable(new ColorDrawable(0xffffffff));

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity = Gravity.BOTTOM;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        win.setAttributes(params);
    }

    @Override
    public void onSelect(PickerScrollView.ItemModel data) {
        mSelectItem = data;
    }

    /**
     * 按钮监听
     */
    public interface OnDialogClickListener {
        /**
         * 取消
         */
        void onCancel();

        /**
         * 确定
         *
         * @param data
         */
        void onOk(PickerScrollView.ItemModel data);
    }

    /**
     * 设置按钮监听
     *
     * @param listener
     */
    public WheelDialog setOnDialogClickListener(OnDialogClickListener listener) {
        onDialogClickListener = listener;
        return this;
    }
}