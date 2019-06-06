package com.mark.app.hjshop4a.uinew.login.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.CountDownUtils;
import com.mark.app.hjshop4a.common.utils.MD5Utils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidShowBtnUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.login.model.SMSParam;
import com.mark.app.hjshop4a.uinew.login.model.UpdateLoginPSWParam;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/13.
 */

public class ForgetActivity extends BaseActivity {
    //倒计时工具类
    private CountDownUtils countDownUtils;
    //从上个界面传递过来的手机号
    private String mPhone;
    private SMSParam smsParam =new SMSParam();
    private UpdateLoginPSWParam pswParam =new UpdateLoginPSWParam();
    @Override
    public int getContentViewResId() {
        return R.layout.activity_forget_pwd;
    }
    @Override
    public void getIntentParam(Bundle bundle) {
        mPhone = bundle.getString(BundleKey.PHONE);
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.login_忘记密码);
        setTvText(R.id.et_username, mPhone);
        setProhibitEmoji((EditText) getView(R.id.et_username));
        setProhibitEmoji((EditText) getView(R.id.et_code));
        setProhibitEmoji((EditText) getView(R.id.et_pwd));
        setProhibitEmoji((EditText) getView(R.id.et_pwd_again));

        initCountDown();
        textWatcher.onTextChanged("", 0, 0, 0);
    }

    @Override
    public void setListener() {
        super.setListener();
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.btn);
        setClickListener(R.id.btn_code);
        setEtTextWatcher(R.id.et_username, textWatcher);
        setEtTextWatcher(R.id.et_code, textWatcher);
        setEtTextWatcher(R.id.et_pwd, textWatcher);
        setEtTextWatcher(R.id.et_pwd_again, textWatcher);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.titlebar_iv_return: {
                finish();
                break;
            }
            case R.id.btn_code: {
                //获取验证码
                requestCode();
                break;
            }
            case R.id.btn: {
                //确认修改
                if (isValidPass(false)) {
                    commit();
                }
                break;
            }
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
            //是否处于倒计时
            boolean isCountDown = countDownUtils != null && countDownUtils.isCountDown();
            if (!isCountDown) {
                //设置获取验证码按钮是否可点击
                String strPhone = getTvText(R.id.et_username);
                boolean isCodeValidPass = ValidShowBtnUtils.phone(strPhone);
                View btnCode = getView(R.id.btn_code);
                btnCode.setEnabled(isCodeValidPass);
            }

            //设置确认修改按钮是否可以点击
            boolean isValid = isValidPass(true);
            setViewEnable(R.id.btn, isValid);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * 获取验证码
     */
    private void requestCode() {
        String strPhone = getTvText(R.id.et_username);
        if (!ValidShowBtnUtils.phone(strPhone)) {
            ToastUtils.show(R.string.login_手机号格式不正确);
            return;
        }

        if (countDownUtils != null) {
            countDownUtils.pre();
        }
        if (countDownUtils != null) {
                    countDownUtils.start();
                }
        smsParam.setUserPhone(strPhone);
        smsParam.setType(3);
        App.getServiceManager().getmService().sendSMS(smsParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {

                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        if(isDestroyed()){
                            return;
                        }

                        if (countDownUtils != null) {
                            countDownUtils.start();
                        }
                        ToastUtils.show("验证码发送成功");
                    }

                    @Override
                    public void onUnSuccessFinish() {
                        super.onUnSuccessFinish();
                        if(countDownUtils!=null){
                            countDownUtils.over();
                        }
                    }
                });

    }
    /**
     * 确认修改
     */
    private void commit() {
        showLoadingDialog();
        String phone = getTvText(R.id.et_username);
        String code = getTvText(R.id.et_code);
        String pwd = getTvText(R.id.et_pwd);
        String pwdAgain =getTvText(R.id.et_pwd_again);
        if (!ValidShowBtnUtils.phone(phone)) {
            ToastUtils.show(R.string.login_手机号格式不正确);
            return;
        }
        if (!ValidUtils.verifyCode(code)) {
            ToastUtils.show(R.string.login_验证码格式不正确);
            return;
        }
        if (!ValidUtils.pwd(pwd)) {
            ToastUtils.show(R.string.login_密码格式错误);
            return;
        }
        if (!pwdAgain.equals(pwd)) {
            ToastUtils.show(R.string.login_两次输入密码不一致);
            return;
        }
        pswParam.setType(2);
        pswParam.setNewPassword(pwd);
        pswParam.setVerificationCode(code);
        App.getServiceManager().getmService().updateLoginPassword(pswParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {


                    @Override
                    public void onSuccess(RainbowResultEntity obj) {

                        ToastUtils.show("密码修改成功");
                        finish();
                    }

                    @Override
                    public void onUnSuccessFinish() {
                        super.onUnSuccessFinish();

                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });


    }
    /**
     * 初始化倒计时
     */
    private void initCountDown() {
        //初始化倒计时工具类
        Button btnCode = getView(R.id.btn_code);
        countDownUtils = CountDownUtils.getInstance(this, btnCode);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownUtils != null) {
            countDownUtils.onDestroy();
            countDownUtils = null;
        }
    }
    /**
     * 验证是否通过
     *
     * @param isValidShowBtn 是否用于显示按钮可点击
     * @return
     */
    private boolean isValidPass(boolean isValidShowBtn) {
        String strPhone = getTvText(R.id.et_username);
        String strCoee = getTvText(R.id.et_code);
        String strPwd = getTvText(R.id.et_pwd);
        String strPwdAgain= getTvText(R.id.et_pwd_again);
        if (isValidShowBtn) {
            return ValidShowBtnUtils.phone(strPhone)
                    && ValidShowBtnUtils.verifyCode(strCoee)
                    && ValidShowBtnUtils.pwd(strPwd)
                     && ValidShowBtnUtils.pwd(strPwdAgain);

        } else {
            boolean result = false;
            if (!ValidUtils.phone(strPhone)) {
                ToastUtils.show(R.string.login_手机号格式不正确);
            } else if (!ValidUtils.verifyCode(strCoee)) {
                ToastUtils.show(R.string.login_验证码格式不正确);
            } else if (!ValidUtils.pwd(strPwd)) {
                ToastUtils.show(R.string.login_密码格式错误);
            }  else if (!strPwdAgain.equals(strPwd)) {
                ToastUtils.show(R.string.login_两次输入密码不一致);
            } else {
                result = true;
            }
            return result;
        }
    }
}
