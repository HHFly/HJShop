package com.mark.app.hjshop4a.uinew.login.activity;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.CountDownUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.PdUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidShowBtnUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;

import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.model.login.model.LoginRepo;
import com.mark.app.hjshop4a.ui.homepager.model.UserCenter;
import com.mark.app.hjshop4a.ui.userinfo.model.UserInfo;
import com.mark.app.hjshop4a.uinew.login.model.LoginParam;
import com.mark.app.hjshop4a.uinew.login.model.Token;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PhoneActivity extends BaseActivity {
    //倒计时工具类
    private CountDownUtils countDownUtils;
    private LoginParam loginParam =new LoginParam();
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownUtils != null) {
            countDownUtils.onDestroy();
            countDownUtils = null;
        }
    }

    @Override
    public int getContentViewResId() {
        return R.layout.activity_login_phone;
    }

    @Override
    public void initView() {
        //设置标题栏
        setTvText(R.id.titlebar_tv_title, R.string.快捷登录);
        initCountDown();
    }

    @Override
    public void setListener() {
        setEtTextWatcher(R.id.et_username, textWatcher);
        setEtTextWatcher(R.id.et_code, textWatcher);
    }

    @Override
    public void onClick(View v) {
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
            case R.id.login_btn: {
                //确认修改

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
        App.getServiceManager().getPdmService().getCode(strPhone,"","2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {
                    @Override
                    public void onSuccess(BaseResultEntity obj) {
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
     * 初始化倒计时
     */
    private void initCountDown() {

        //初始化倒计时工具类
        Button btnCode = getView(R.id.register_btn_code);
        countDownUtils = CountDownUtils.getInstance(this, btnCode);
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
        if (isValidShowBtn) {
            return ValidShowBtnUtils.phone(strPhone)
                    && ValidShowBtnUtils.verifyCode(strCoee);


        } else {
            boolean result = false;
            if (!ValidUtils.phone(strPhone)) {
                ToastUtils.show(R.string.login_手机号格式不正确);
            } else if (!ValidUtils.verifyCode(strCoee)) {
                ToastUtils.show(R.string.login_验证码格式不正确);
            }  else {
                result = true;
            }
            return result;
        }
    }
    private void login() {
        String   account = getTvText(R.id.login_et_username);
        String Verification = getTvText(R.id.et_code);
        loginParam.setUserPhone(account);
        loginParam.setVerification(Verification);
        loginParam.setType(2);
        if (!ValidUtils.phone(account)) {

            ToastUtils.show(R.string.login_手机号格式错误);
            return;
        }
        if (!ValidUtils.verifyCode(Verification)) {
            ToastUtils.show(R.string.login_验证码格式不正确);
            return;
        }
        showLoadingDialog();
        App.getServiceManager().getmService().login( loginParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<Token>() {


                    @Override
                    public void onSuccess(RainbowResultEntity<Token> obj) {
                         Token token =JsonUtils.fromJson(obj.getResult(),Token.class);

//                        保存登陆信息
                        App.get().setLogin(token);
                        requestData();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }

    /**
     * 请求数据
     */
    private void requestData() {
//        showLoadingDialog();
        App.getServiceManager().getmService()
                .getCenter()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<UserCenter>() {


                    @Override
                    public void onSuccess(RainbowResultEntity<UserCenter> obj) {
                        UserCenter data = JsonUtils.fromJson(obj.getResult(),UserCenter.class);
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}
