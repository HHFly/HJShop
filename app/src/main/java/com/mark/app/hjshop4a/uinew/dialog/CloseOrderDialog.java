package com.mark.app.hjshop4a.uinew.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseDialogFragment;
import com.mark.app.hjshop4a.uinew.dialog.adapter.CloseOrderAdapter;
import com.mark.app.hjshop4a.uinew.dialog.model.CloseData;
import com.mark.app.hjshop4a.uinew.performorder.adapter.OneDetailAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.StepOne;

import java.util.List;

public class CloseOrderDialog extends BaseDialogFragment {
    //根布局
    CloseOrderAdapter closeOrderAdapter;
    View mRootView;
    Context content;



    public void setContent(Context content) {
        this.content = content;
    }
    @Override
    protected boolean getStartInBottom() {
        return true;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_closeorder, container, false);
        final AlertDialog dialog = new AlertDialog.Builder(content, R.style.dialog_lhp)
                .create();

        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.mystyle);
        initView(rootView);
        initRvAdapter(CloseData.getData());
        return rootView;
    }
    /**
     * 初始化
     *
     * @param rootView
     */
    private void initView(View rootView) {
        mRootView = rootView;
       Button mBtnConfirm = (Button) getView(rootView,R.id.btn_confirm);
        Button  mBtnCancel = (Button) getView(rootView,R.id.btn_cancel);
        setListener(mBtnConfirm, this);
        setListener(mBtnCancel, this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_confirm:
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickYes(closeOrderAdapter.getType());
                    dismiss();
                }
                break;
            case R.id.btn_cancel:
                if (onDialogClickListener != null) {
                    onDialogClickListener.onClickNo(this);
                    dismiss();

                }
                break;

        }
    }
    private void initRvAdapter(List<CloseData> data){

        if(closeOrderAdapter==null){
            RecyclerView rv = getView(mRootView,R.id.recyclerView);
            closeOrderAdapter = new CloseOrderAdapter(data);
            rv.setLayoutManager(new LinearLayoutManager(content));
            rv.setAdapter(closeOrderAdapter);

        }
        else {

            closeOrderAdapter.notifyData(data,true);
        }
//        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
//        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }
    private OnDialogClickListener onDialogClickListener;

    public void setOnDialogClickListener(OnDialogClickListener listener) {
        onDialogClickListener = listener;
    }

    public interface OnDialogClickListener {
        void onClickNo(CloseOrderDialog dialog);
        void onClickYes(int data);

    }
}
