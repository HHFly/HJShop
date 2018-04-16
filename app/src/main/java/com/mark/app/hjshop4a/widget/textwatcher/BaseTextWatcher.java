package com.mark.app.hjshop4a.widget.textwatcher;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * 基类
 * Created by lenovo on 2017/10/24.
 */

public abstract class BaseTextWatcher<T extends EditText> implements TextWatcher {

    T et;

    public BaseTextWatcher(T et) {
        this.et = et;
    }

    public void setFilters(InputFilter[] data) {
        if (et != null) {
            et.setFilters(data);
        }
    }

    /**
     * getSelectionStart
     *
     * @return
     */
    protected int getSelectionStart() {
        if (et != null) {
            return et.getSelectionStart();
        }
        return 0;
    }


    /**
     * 设置焦点位置
     *
     * @param index
     */
    protected void setSelection(int index) {
        if (et != null) {
            et.setSelection(index);
        }
    }

    /**
     * 获取Editable
     */
    protected Editable getText() {
        if (et != null) {
            return et.getText();
        }
        return null;
    }

    /**
     * setText
     *
     * @param c
     */
    protected void setText(CharSequence c) {
        if (et != null) {
            et.setText(c);
        }
    }
}
