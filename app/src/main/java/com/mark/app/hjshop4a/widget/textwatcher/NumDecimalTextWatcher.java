package com.mark.app.hjshop4a.widget.textwatcher;

import android.text.Editable;
import android.text.InputFilter;
import android.widget.EditText;

/**
 * 限制输入小数点后2位的监听
 * Created by lenovo on 2017/10/25.
 */

public class NumDecimalTextWatcher extends BaseTextWatcher {
    private static final int DEFAULT_MAX_INTEGER_LENGTH = 5;
    private static final int DEFAULT_DECIMAL_NUMBER = 2;

    private static final InputFilter[] INPUT_FILTER_ARRAY = new InputFilter[1];

    /**
     * 保留小数点后多少位
     */
    private int mDecimalNumber = DEFAULT_DECIMAL_NUMBER;
    /**
     * 允许最大的整数多少位
     */
    private int mMaxIntegralLength = DEFAULT_MAX_INTEGER_LENGTH;

    public NumDecimalTextWatcher(EditText et) {
        super(et);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() > 0) {
            String inputContent = s.toString();
            if (inputContent.contains(".")) {
                int maxLength = inputContent.indexOf(".") + mDecimalNumber + 1;
                INPUT_FILTER_ARRAY[0] = new InputFilter.LengthFilter(maxLength);
            } else {
                INPUT_FILTER_ARRAY[0] = new InputFilter.LengthFilter(mMaxIntegralLength);
            }
            setFilters(INPUT_FILTER_ARRAY);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
