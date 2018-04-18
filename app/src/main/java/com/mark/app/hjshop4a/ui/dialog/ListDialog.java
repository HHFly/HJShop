package com.mark.app.hjshop4a.ui.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.BaseRvAdapter;
import com.mark.app.hjshop4a.base.fragment.BaseDialogFragment;
import com.mark.app.hjshop4a.base.model.BaseModel;
import com.mark.app.hjshop4a.ui.dialog.adapter.ListDialogRvAdapter;

import java.util.List;

/**
 * 修改头像对话框
 * Created by lenovo on 2017/9/5.
 */

public class ListDialog extends BaseDialogFragment {
    private RecyclerView mRv;

    private List<ListDialogModel> mData;
    private ListDialogRvAdapter mAdapter;

    //监听器
    private OnBtnClickListenr onBtnClickListenr;

    /**
     * 获取实例
     *
     * @param data
     * @return
     */
    public static ListDialog getInstance(List<ListDialogModel> data) {
        ListDialog dialog = new ListDialog();
        dialog.mData = data;
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_modify_photo, container, false);
        mRv = getView(rootView, R.id.dialog_rv);
        getView(rootView, R.id.dialog_btn_cancel).setOnClickListener(this);
        initRvAdapter();
        getDialog().getWindow().getAttributes().windowAnimations = R.style.ListDialogAnim;
        return rootView;
    }

    /**
     * 初始化适配器
     */
    private void initRvAdapter() {
        if (mAdapter == null) {
            mAdapter = new ListDialogRvAdapter(mData);
            mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new BaseRvAdapter.OnItemClickListener<ListDialogModel>() {
                @Override
                public void onItemClick(View view, int position, ListDialogModel data, int type) {
                    if (onBtnClickListenr != null) {
                        onBtnClickListenr.onItemClick(view, data, position);
                    }
                    dismiss();
                }
            });
        } else {
            if (mRv.getAdapter() == null) {
                mRv.setAdapter(mAdapter);
                mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            }
            mAdapter.notifyData(mData, true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_btn_cancel: {
                if (onBtnClickListenr != null) {
                    onBtnClickListenr.onCancel();
                }
                dismiss();
                break;
            }
        }
    }

    public interface OnBtnClickListenr {
        /**
         * 点击Item监听
         *
         * @param view
         * @param data
         * @param position
         */
        void onItemClick(View view, ListDialogModel data, int position);

        /**
         * 取消监听
         */
        void onCancel();
    }

    //设置按钮监听
    public ListDialog setOnBtnClickListenr(OnBtnClickListenr listenr) {
        onBtnClickListenr = listenr;
        return this;
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
        params.gravity = Gravity.BOTTOM;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        win.setAttributes(params);
    }

    /**
     * 列表对话框模型
     * Created by lenovo on 2017/9/5.
     */

    public static class ListDialogModel extends BaseModel {
        private int id;
        private String name;

        public ListDialogModel(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
