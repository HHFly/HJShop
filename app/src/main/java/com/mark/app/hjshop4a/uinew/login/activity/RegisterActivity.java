package com.mark.app.hjshop4a.uinew.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.CountDownUtils;
import com.mark.app.hjshop4a.common.utils.EditTextUtils;
import com.mark.app.hjshop4a.common.utils.MD5Utils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;

import com.mark.app.hjshop4a.common.utils.ValidShowBtnUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.model.login.LoginParam;
import com.mark.app.hjshop4a.ui.dialog.SelectAddressDialog;
import com.mark.app.hjshop4a.ui.dialog.model.AddressData;
import com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.listener.SelectInterface;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/13.
 */

public class RegisterActivity extends BaseActivity  implements SelectInterface {
    //从上一个界面传递过来的手机号
    private String mPhone;

    //密码输入框的文本是否可见
    private boolean isPwdShow;
    //是否同意
    private boolean isAgree;
    //倒计时工具类
    private CountDownUtils countDownUtils;
    //选择dialog
    private SelectAddressDialog wheelDialog;
    LoginParam param;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_register;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(wheelDialog!=null){
            wheelDialog=null;
        }

        if (countDownUtils != null) {
            countDownUtils.onDestroy();
            countDownUtils = null;
        }
    }
    @Override
    public void getIntentParam(Bundle bundle) {
        mPhone = bundle.getString(BundleKey.PHONE);
    }
    @Override
    public void initView() {
        isAgree= true;
        setTvText(R.id.titlebar_tv_title, R.string.login_msg_register);
        initCountDown();
        setTvText(R.id.register_et_username, mPhone);
        changedEditTextVisiable(isPwdShow);
        changedAgree(isAgree);
        textWatcher.onTextChanged("", 0, 0, 0);
    }
    /**
     * 初始化倒计时
     */
    private void initCountDown() {
         param = new LoginParam();
        //初始化倒计时工具类
        Button btnCode = getView(R.id.register_btn_code);
        countDownUtils = CountDownUtils.getInstance(this, btnCode);
    }

    @Override
    public void setListener() {
        super.setListener();
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.register_btn_code);
        setClickListener(R.id.register_iv_eye);
        setClickListener(R.id.register_rl_region);
        setClickListener(R.id.register_layout_agree);
        setClickListener(R.id.register_tv_agreement);
        setClickListener(R.id.register_tv_disclaimer);
        setClickListener(R.id.register_btn);


        setEtTextWatcher(R.id.register_et_username, textWatcher);
        setEtTextWatcher(R.id.register_et_code, textWatcher);
        setEtTextWatcher(R.id.register_et_pwd, textWatcher);
        setEtTextWatcher(R.id.login_tv_pwd_again, textWatcher);
        setEtTextWatcher(R.id.register_et_invitation, textWatcher);
        setEtTextWatcher(R.id.register_et_region, textWatcher);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                //回退
                finish();
                break;
            case R.id.register_btn: {
                //注册
                if (isValidPass(false)) {
                    register();
                }
                break;
            }
            case R.id.register_btn_code: {
                //获取验证码
                getCode();
                break;
            }
            case R.id.register_tv_disclaimer: {
                //同意协议内容
                String url = "file:///android_asset/agreement/terms_and_conditions.html";
                String title = getString(R.string.login_同意协议内容);
                ActivityJumpUtils.actWebActivity(getActivity(), url, title);
                break;
            }
            case R.id.register_tv_agreement: {
                //同意协议内容
                String url = "file:///android_asset/agreement/terms_and_conditions.html";
                String title = getString(R.string.login_同意协议内容);
                ActivityJumpUtils.actWebActivity(getActivity(), url, title);
                break;
            }
            case R.id.register_iv_eye: {
                //显示或隐藏密码
                isPwdShow = !isPwdShow;
                changedEditTextVisiable(isPwdShow);
                break;
            }
            case R.id.layout_phone_code: {
//                ActivityJumpUtils.actCountrySelect(getActivity());
                break;
            }
            case R.id.register_layout_agree: {
                //同意
                isAgree = !isAgree;
                changedAgree(isAgree);
                break;
            }
            case R.id.register_rl_region:{
             showDateDiaglog();
                break;
            }
        }
    }
    /**
     * 获取验证码
     */
    private void getCode() {
       String macId= MD5Utils.md5(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+android.os.Build.SERIAL);
        String strPhone = getTvText(R.id.register_et_username);
        if (!ValidShowBtnUtils.phone(strPhone)) {
            ToastUtils.show(R.string.login_手机号格式不正确);
            return;
        }

        if (countDownUtils != null) {
            countDownUtils.pre();
        }

        App.getServiceManager().getPdmService().getCode(strPhone,macId,"1")
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
     * 注册
     */
    private void register() {
        final String strUserName = getTvText(R.id.register_et_username);
        String strCode = getTvText(R.id.register_et_code);
        String strPwd = getTvText(R.id.register_et_pwd);
        String strInvitation = getTvText(R.id.register_et_invitation);
        String strRegion = getTvText(R.id.register_et_region);
//        isValidPass(false);

        param.setAccount(strUserName);
        param.setCaptcha(strCode);
        param.setPassword(strPwd);
        param.setInviteCode(strInvitation);
//        param.setProvinceId("1");
//        param.setCityId("1");
//        param.setCountyId("1");
        showLoadingDialog();
        App.getServiceManager().getPdmService().register(param.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {

                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        ToastUtils.show("注册成功");
                        Intent intent = getIntent();
                        intent.putExtra("Num", strUserName);
                        setResult(2, intent);
                        finish();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });



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
                String strPhone = getTvText(R.id.register_et_username);
                boolean isCodeValidPass = ValidShowBtnUtils.phone(strPhone);
                setViewEnable(R.id.register_btn_code, isCodeValidPass);
            }

            boolean isValidPass = isValidPass(true);
            setViewEnable(R.id.register_btn, isValidPass);
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
        String strPhone = getTvText(R.id.register_et_username);
        String strCode = getTvText(R.id.register_et_code);
        String strPwd = getTvText(R.id.register_et_pwd);
        String  strRePwd = getTvText(R.id.register_et_pwd_again);
        String strInvitation = getTvText(R.id.register_et_invitation);
//        String strRegion = getTvText(R.id.register_et_region);

        if (isValidShowBtn) {
            return ValidShowBtnUtils.phone(strPhone)
                    && ValidShowBtnUtils.verifyCode(strCode)
                    && ValidShowBtnUtils.pwd(strPwd)
                    && !TextUtils.isEmpty(strRePwd)
                    && isAgree;
        } else {
            boolean result = false;
            if (!ValidUtils.phone(strPhone)) {
                ToastUtils.show(R.string.login_手机号格式不正确);
            } else if (!ValidUtils.verifyCode(strCode)) {
                ToastUtils.show(R.string.login_验证码格式不正确);
            } else if (!ValidUtils.pwd(strPwd)) {
                ToastUtils.show(R.string.login_密码格式错误);
            }
            else if (TextUtils.isEmpty(strRePwd)) {
                ToastUtils.show(R.string.login_请确认密码);
            }
            else if(!TextUtils.equals(strPwd,strRePwd)){
                ToastUtils.show(R.string.login_两次输入密码不一致);
            }
            else if (!isAgree) {
                ToastUtils.show(R.string.login_注册需要同意协议);
            } else {
                result = true;
            }

            return result;
        }
    }
    /**
     * 修改同意状态
     *
     * @param isAgree
     */
    private void changedAgree(boolean isAgree) {
        setViewSelected(R.id.register_layout_agree, isAgree);
        if (textWatcher != null) {
            textWatcher.onTextChanged("", 0, 0, 0);
        }
    }

    /**
     * 密码输入框是否可见
     *
     * @param flag
     */
    private void changedEditTextVisiable(boolean flag) {
        EditText et = getView(R.id.register_et_pwd);
        EditTextUtils.changedEditTextVisiable(et, flag);
        setViewSelected(R.id.register_iv_eye, flag);
    }

    /**
     * 弹出地址对话框--三级联动的效果
     *
     *
     */
    public void showDateDiaglog() {
        wheelDialog = new SelectAddressDialog(this,
                this, SelectAddressDialog.STYLE_THREE, null);
        wheelDialog.showDialog();
    }



    @Override
    public void selectedResult(AddressData result1, AddressData result2, AddressData result3) {
        setTvText(R.id.register_et_region,result1.getName() +"-"+ result2.getName() +"-"+ result3.getName());

        param.setProvinceId(result1.getId());
        param.setCityId(result2.getId());
        param.setCountyId(result3.getId());
        if (textWatcher != null) {
            textWatcher.onTextChanged("", 0, 0, 0);
        }
    }

    @Override
    public void selectedResult(String result) {


    }
}
