package com.mark.app.hjshop4a.uinew.userinfo;

import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.CountDownUtils;
import com.mark.app.hjshop4a.common.utils.EditTextUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidShowBtnUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.login.model.SMSParam;
import com.mark.app.hjshop4a.uinew.login.model.UpdateLoginPSWParam;
import com.mark.app.hjshop4a.uinew.userinfo.param.UpdatePayPSWParam;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ModifyPayPwActivity extends BaseActivity {
    private  boolean newflag =false;
    private  boolean oldflag =false;
    //倒计时工具类
    private CountDownUtils countDownUtils;
    UpdatePayPSWParam pswParam =new UpdatePayPSWParam();
    @Override
    public int getContentViewResId() {
        return R.layout.activity_user_modify_pay_pwd;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"修改支付密码");
        initCountDown();
    }

    @Override
    public void setListener() {
        setClickListener(R.id.iv_new_eye);
        setClickListener(R.id.iv_old_eye);
        setClickListener(R.id.btn_code);
        setClickListener(R.id.btn);
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.iv_old_eye:
                oldflag=!oldflag;
                EditText oet = getView(R.id.et_old_pwd);
                EditTextUtils.changedEditTextVisiable(oet, oldflag);
                setViewSelected(R.id.iv_old_eye, oldflag);
                break;
            case R.id.iv_new_eye:
                newflag =!newflag;
                EditText net = getView(R.id.et_new_pwd);
                EditTextUtils.changedEditTextVisiable(net, newflag);
                setViewSelected(R.id.iv_new_eye, newflag);
                break;
            case R.id.btn:
//                确定
                commit();
                break;
            case R.id.btn_code:
                //获取验证码
                requestCode();
                break;

        }
    }
    /**
     * 初始化倒计时
     */
    private void initCountDown() {
        //初始化倒计时工具类
        Button btnCode = getView(R.id.btn_code);
        countDownUtils = CountDownUtils.getInstance(this, btnCode);
    }
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
        SMSParam smsParam=new SMSParam();
        smsParam.setUserPhone(App.getAppContext().getUserInfo().getUserPhone());
        smsParam.setType(5);
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
    private  void commit(){
//        String old =getTvText(R.id.et_old_pwd);
        String news =getTvText(R.id.et_new_pwd);
        String code = getTvText(R.id.et_code);
//        String newsAgain =getTvText(R.id.modify_et_pwd_again);
//        if(TextUtils.isEmpty(old)){
//            ToastUtils.show("请输入旧密码");
//            return;
//        }
        if(TextUtils.isEmpty(news)){
            ToastUtils.show("请输入新密码");
            return;
        }

//        if(TextUtils.isEmpty(newsAgain)){
//            ToastUtils.show("请确认密码");
//            return;
//        }
//        if(!ValidUtils.pwd(old)){
//            ToastUtils.show("旧密码错误，请重试");
//            return;
//        }
        if(!ValidUtils.verifyCode(news)){
            ToastUtils.show("密码为6位数字");
            return;
        }
        if (!ValidUtils.verifyCode(code)) {
            ToastUtils.show(R.string.login_验证码格式不正确);
            return;
        }
//        if(!TextUtils.equals(news,newsAgain)){
//            ToastUtils.show("两次输入密码不一致");
//            return;
//        }

        pswParam.setNewPassword(news);
        pswParam.setVerificationCode(code);
        requestData();
    }
    /**
     * 请求数据
     */
    private void requestData() {
        showLoadingDialog();

        App.getServiceManager().getmService()
                .updatePayPassword(pswParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {


                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        ToastUtils.show("修改成功");

                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }


}