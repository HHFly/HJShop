package com.mark.app.hjshop4a.ui.login.activity;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.CountDownUtils;
import com.mark.app.hjshop4a.common.utils.EditTextUtils;
import com.mark.app.hjshop4a.common.utils.ValidShowBtnUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.ui.dialog.WheelDialog;
import com.mark.app.hjshop4a.ui.dialog.factory.WheelDialogFactory;
import com.mark.app.hjshop4a.widget.PickerScrollView;
import com.white.lib.utils.ToastUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pc on 2018/4/13.
 */

public class RegisterActivity extends BaseActivity {
    //从上一个界面传递过来的手机号
    private String mPhone;

    //密码输入框的文本是否可见
    private boolean isPwdShow;
    //是否同意
    private boolean isAgree;
    //倒计时工具类
    private CountDownUtils countDownUtils;
    //选择dialog
    private WheelDialog wheelDialog;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_register;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(wheelDialog!=null){
            wheelDialog.onDestroy();
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
        setClickListener(R.id.register_iv_region);
        setClickListener(R.id.register_layout_agree);
        setClickListener(R.id.register_tv_agreement);
        setClickListener(R.id.register_tv_disclaimer);
        setClickListener(R.id.register_btn);


        setEtTextWatcher(R.id.register_et_username, textWatcher);
        setEtTextWatcher(R.id.register_et_code, textWatcher);
        setEtTextWatcher(R.id.register_et_pwd, textWatcher);
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
            case R.id.register_iv_region:{
                AssetManager asset = getAssets();
                InputStream input  =null;
                try {
                     input = asset.open("province_data.xml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                WheelDialog dialog = WheelDialogFactory.getProvinceWheelDialog(input);
                dialog.setOnDialogClickListener(new WheelDialog.OnDialogClickListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onOk(PickerScrollView.ItemModel data) {
                        setTvText(R.id.register_et_region,data.getName());
                    }
                }).show(getFragmentManager());
                break;
            }
        }
    }
    /**
     * 获取验证码
     */
    private void getCode() {
        String strPhone = getTvText(R.id.register_et_username);
        if (!ValidShowBtnUtils.phone(strPhone)) {
            ToastUtil.show(R.string.login_手机号格式不正确);
            return;
        }

        if (countDownUtils != null) {
            countDownUtils.pre();
        }
        if (countDownUtils != null) {
            countDownUtils.start();
        }
//        PhoneCodeParam phoneCodeParam = new PhoneCodeParam(mAreaCode);
//        App.getService().getUserService().getCode(phoneCodeParam, strPhone, new DefaultServiceListener() {
//            @Override
//            public void onSuccess(int code, JsonElement o) {
//                super.onSuccess(code, o);
//                if (isDestroyed()) {
//                    return;
//                }
//                if (countDownUtils != null) {
//                    countDownUtils.start();
//                }
//                ToastUtil.show(R.string.验证码发送成功);
//            }
//
//            @Override
//            public void onUnSuccessFinish() {
//                super.onUnSuccessFinish();
//                if (countDownUtils != null) {
//                    countDownUtils.over();
//                }
//            }
//        });
    }

    /**
     * 注册
     */
    private void register() {
        String strUserName = getTvText(R.id.register_et_username);
        String strCode = getTvText(R.id.register_et_code);
        String strPwd = getTvText(R.id.register_et_pwd);
        String strInvitation = getTvText(R.id.register_et_invitation);
        String strRegion = getTvText(R.id.register_et_region);

//        LoginParam param = new LoginParam();
//        param.setAccount(strUserName);
//        param.setCaptcha(strCode);
//        param.setPassword(strPwd);
//        param.setInviteCode(strInvitation);
//
//        PhoneCodeParam phoneCodeParam = new PhoneCodeParam(mAreaCode);
//        param.addMap(phoneCodeParam);
//
//        showLoadingDialog();
//        App.getService().getLoginService().userReg(param, new DefaultServiceListener() {
//            @Override
//            public void onSuccess(int code, JsonElement o) {
//                super.onSuccess(code, o);
//                ToastUtil.show(R.string.success_msg_register);
//                finish();
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                hideLoadingDialog();
//            }
//        });
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
        String strInvitation = getTvText(R.id.register_et_invitation);
        String strRegion = getTvText(R.id.register_et_region);

        if (isValidShowBtn) {
            return ValidShowBtnUtils.phone(strPhone)
                    && ValidShowBtnUtils.verifyCode(strCode)
                    && ValidShowBtnUtils.pwd(strPwd)
                    && ValidShowBtnUtils.phone(strRegion)
                    && isAgree;
        } else {
            boolean result = false;
            if (!ValidUtils.phone(strPhone)) {
                ToastUtil.show(R.string.login_手机号格式不正确);
            } else if (!ValidUtils.verifyCode(strCode)) {
                ToastUtil.show(R.string.login_验证码格式不正确);
            } else if (!ValidUtils.pwd(strPwd)) {
                ToastUtil.show(R.string.login_密码格式错误);
            }
            else if (!ValidUtils.phone(strInvitation)) {
                ToastUtil.show(R.string.login_手机号格式不正确);
            }
            else if (!TextUtils.isEmpty(strRegion)) {
                ToastUtil.show(R.string.login_请选择区域);
            }
            else if (!isAgree) {
                ToastUtil.show(R.string.login_注册需要同意协议);
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

}
