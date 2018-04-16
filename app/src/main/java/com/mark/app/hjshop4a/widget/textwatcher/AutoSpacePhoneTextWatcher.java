package com.mark.app.hjshop4a.widget.textwatcher;

import android.text.Editable;

import com.mark.app.hjshop4a.widget.AutoSpaceBankEditText;


/**
 * 自动添加空格-手机号
 * 适配44 1234 123456
 * 适配01234 123456
 * 适配123456789
 * 适配18969784998
 * Created by lenovo on 2017/10/24.
 */

public class AutoSpacePhoneTextWatcher extends BaseTextWatcher<AutoSpaceBankEditText> {

    //旧的文本
    String oldText;

    public AutoSpacePhoneTextWatcher(AutoSpaceBankEditText et) {
        super(et);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        oldText = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //新的文本
        String newText = s.toString();

        if (newText.equals(oldText)) {
            //如果新的和旧的一样，不处理
            return;
        }

        //新的去空格文本
        String newTextTrim = getTextTrim(newText);
        //重新添加空格
        String newTextFilter = filter(newTextTrim);

        if (newTextFilter != null) {
            setText(newTextFilter);
        }

        Editable editable = getText();
        if (editable != null) {
            setSelection(editable.length());
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    /**
     * 格式化字符串
     * 加入空格
     *
     * @param data
     * @return
     */
    private String filter(String data) {
        if (data != null) {
            char[] chars = data.toCharArray();
            StringBuilder builder = new StringBuilder();
            int charLen = chars.length;
            for (int i = 0; i < charLen; i++) {
                if (i % 4 == 0) {
                    //添加空格
                    builder.append(" ");
                }
                //添加字符
                builder.append(chars[i]);
            }
            return builder.toString();
        }
        return "";
    }

    /**
     * getTextTrim
     *
     * @param data
     * @return
     */
    protected String getTextTrim(String data) {
        if (et != null) {
            return et.getTextTrim(data);
        }
        return data;
    }

    /**
     * 设置MaxLength，并保留已经存在的Filter
     *
     * @param filterLen
     */
    protected void setMaxLength(int filterLen) {
        if (et != null) {
            et.setMaxLength(filterLen);
        }
    }
}
