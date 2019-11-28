package com.mark.app.hjshop4a.uinew.bill.fragment;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.utils.EditTextUtils;

public class FiltrateSnFragment extends BaseFragment {
     EditText et;
    @Override
    public int getContentResId() {
        return R.layout.fragment_filtrate_sn;
    }

    @Override
    public void findView() {
        et =getView(R.id.et_search);
        EditTextUtils.openKeyBroad(et);
        et.setSelection(et.getText().length());
        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String search = et.getText().toString().trim();
                    if(onItemClickListener!=null){
                        onItemClickListener.onClickOK(search);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onUnFirstResume() {
        if(et!=null) {
            EditTextUtils.openKeyBroad(et);
            et.setSelection(et.getText().length());
        }
    }

    @Override
    public void setListener() {
        setClickListener(R.id.tv_cancel);
    }

    @Override
    public void initView() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_cancel:
                if(onItemClickListener!=null){
                    onItemClickListener.onClickCancel();
                }
                break;
        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        /**
         * 点击个人信息
         *
         *
         */
        void onClickOK(String strDate);
        void onClickCancel();

    }
}
