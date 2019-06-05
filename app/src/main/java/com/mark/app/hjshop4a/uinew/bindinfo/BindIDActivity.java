package com.mark.app.hjshop4a.uinew.bindinfo;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidShowBtnUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;

public class BindIDActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_bind_id;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.绑定身份信息);

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
        }
    }
    /**
     * 文本监听
     */
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {


            //设置确认修改按钮是否可以点击
            boolean isValid = isValidPass(true);
            setViewEnable(R.id.btn, isValid);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    /**
     * 验证是否通过
     *
     * @param isValidShowBtn 是否用于显示按钮可点击
     * @return
     */
    private boolean isValidPass(boolean isValidShowBtn) {
        String strPhone = getTvText(R.id.et_name);
        String strCoee = getTvText(R.id.et_num);

        if (isValidShowBtn) {
            return ValidShowBtnUtils.phone(strPhone)
                    && ValidShowBtnUtils.verifyCode(strCoee)
                    ;

        } else {
            boolean result = false;
            if (!ValidUtils.phone(strPhone)) {
                ToastUtils.show(R.string.login_手机号格式不正确);
            } else if (!ValidUtils.verifyCode(strCoee)) {
                ToastUtils.show(R.string.login_验证码格式不正确);
            }
             else {
                result = true;
            }
            return result;
        }
    }
}
