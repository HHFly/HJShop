package com.mark.app.hjshop4a.ui.dialog;

import android.content.Context;
import android.graphics.Color;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.BaseRvAdapter;
import com.mark.app.hjshop4a.base.fragment.BaseDialogFragment;

import com.mark.app.hjshop4a.ui.bankcard.model.BankCard;
import com.mark.app.hjshop4a.ui.dialog.adapter.BankCardRvAdapter;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/20.
 */

public class BankCardDialog extends BaseDialogFragment {
private BankCardRvAdapter mAdapter;
    private RecyclerView mRv;
    private ArrayList<BankCard> mData;
    //动画
    private Animation mListIn;
    private Animation mListOut;


    private Context context;
    private  View mRootView;
    /**
     * 获取实例
     *
     * @param data
     * @return
     */
    public static BankCardDialog getInstance(ArrayList<BankCard> data, Context context) {
        BankCardDialog dialog = new BankCardDialog();
        dialog.mData = data;
        dialog.context =context;
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mListIn = AnimationUtils.loadAnimation(context, R.anim.dialog_push_in_translate);
        mListOut = AnimationUtils.loadAnimation(context, R.anim.dialog_push_out_translate);

        View rootView = inflater.inflate(R.layout.dialog_bankcard, container, false);
        mRootView=rootView;
        mRv = getView(rootView, R.id.recyclerView);
        getView(rootView, R.id.dialog_btn_cancel).setOnClickListener(this);
        getView(rootView, R.id.item_add_card).setOnClickListener(this);

        initRvAdapter();
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        setMaskVisibility(rootView,true,true);
        return rootView;
    }
    /**
     * 初始化适配器
     */
    private void initRvAdapter() {
        if (mAdapter == null) {
            mAdapter = new BankCardRvAdapter(mData);
            mRv.setLayoutManager(new LinearLayoutManager(getActivity()));
            mRv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new BaseRvAdapter.OnItemClickListener<BankCard>() {
                @Override
                public void onItemClick(View view, int position, BankCard data, int type) {
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
                setMaskVisibility(mRootView,false,true);
                dismiss();
                break;
            }
            case R.id.item_add_card:
                if (onBtnClickListenr != null) {
                    onBtnClickListenr.addbank();
                }
        }
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
        setMaskVisibility(mRootView,true,true);
    }
    //监听器
    private OnBtnClickListenr onBtnClickListenr;
    public interface OnBtnClickListenr {
        /**
         * 点击Item监听
         *
         * @param view
         * @param data
         * @param position
         */
        void onItemClick(View view, BankCard data, int position);

        /**
         * 取消监听
         */
        void onCancel();

        void addbank();
    }

    //设置按钮监听
    public BankCardDialog setOnBtnClickListenr(BankCardDialog.OnBtnClickListenr listenr) {
        onBtnClickListenr = listenr;
        return this;
    }
    /**
     * 设置蒙层是否显示
     *
     * @param isShow
     */
    private void setMaskVisibility( View rootView,boolean isShow, boolean isStartAnim) {
        View view = getView(rootView,R.id.item_layout_all_bottom);
        if (view != null) {
            view.setVisibility(isShow ? View.VISIBLE : View.GONE);
            if (isStartAnim) {
                Animation anim = isShow ? mListIn : mListOut;
                view.startAnimation(anim);
            }
        }
    }
}
