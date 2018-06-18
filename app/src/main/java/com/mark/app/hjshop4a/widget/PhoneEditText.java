package com.mark.app.hjshop4a.widget;

import android.content.Context;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * 给手机号用的
 * Created by lenovo on 2017/10/24.
 */

public class PhoneEditText extends android.support.v7.widget.AppCompatEditText {
    private TextWatcher textWatcher;

    public PhoneEditText(Context context) {
        super(context);
    }

    public PhoneEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PhoneEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
//        textWatcher = new AutoSpacePhoneTextWatcher(this);
//        addTextChangedListener(textWatcher);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setMaxLength(13);
//        if (textWatcher == null) {
//            textWatcher = new AutoSpacePhoneTextWatcher(this);
//            addTextChangedListener(textWatcher);
//        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
//        removeTextChangedListener(textWatcher);
//        textWatcher = null;
    }

    /**
     * 设置MaxLength，并保留已经存在的Filter
     *
     * @param filterLen
     */
    public void setMaxLength(int filterLen) {
        InputFilter[] oldFilters = getFilters();
        InputFilter[] newFilters;
        if (oldFilters == null) {
            //如果未设置过Filter
            newFilters = new InputFilter[]{
                    new InputFilter.LengthFilter(filterLen)
            };
        } else {
            //如果已经设置过Filter
            boolean isModifyLengthFilter = false;
            //更改data中的InputFilter.LengthFilter
            if (oldFilters != null) {
                int len = oldFilters.length;
                for (int i = 0; i < len; i++) {
                    if (oldFilters[i] instanceof InputFilter.LengthFilter) {
                        oldFilters[i] = new InputFilter.LengthFilter(filterLen);
                        isModifyLengthFilter = true;
                    }
                }
            }

            if (isModifyLengthFilter) {
                //如果更改data中的InputFilter.LengthFilter成功
                newFilters = oldFilters;
            } else {
                //如果data中不包含InputFilter.LengthFilter
                int oldLen = oldFilters.length;
                newFilters = new InputFilter[oldLen + 1];
                for (int i = 0; i < oldLen; i++) {
                    newFilters[i] = oldFilters[i];
                }
                newFilters[oldLen] = new InputFilter.LengthFilter(filterLen);
            }
        }
        setFilters(newFilters);
    }

    /**
     * 获取字符串
     *
     * @return
     */
    public String getTextString() {
        return getTextTrim(getText().toString());
    }

    /**
     * 获取除去首位0
     *
     * @param data
     * @return
     */
    public String getTextTrim(String data) {
//        return data.replaceAll("^[0]+", "");
        return data;
    }
}
