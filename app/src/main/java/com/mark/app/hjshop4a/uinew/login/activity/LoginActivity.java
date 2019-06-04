package com.mark.app.hjshop4a.uinew.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.EditTextUtils;
import com.mark.app.hjshop4a.common.utils.PdUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.valid.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.model.login.AreaAgentInfo;
import com.mark.app.hjshop4a.model.login.BusniessInfo;
import com.mark.app.hjshop4a.model.login.MemberInfo;
import com.mark.app.hjshop4a.model.login.ProvenceAgentInfo;
import com.mark.app.hjshop4a.model.login.model.LoginRepo;
import com.mark.app.hjshop4a.ui.homepager.model.UserCenter;
import com.mark.app.hjshop4a.ui.userinfo.model.UserInfo;
import com.mark.app.hjshop4a.uinew.login.model.LoginParam;
import com.mark.app.hjshop4a.uinew.login.model.Token;


import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by pc on 2018/4/12.
 */

public class LoginActivity extends BaseActivity {
    //来源：@LoginSource
    int Role;
    //密码是否可见
    boolean isPwdShow;
    //是否自动登录
    boolean isautologin =true;
    String account;//账号
    LoginParam loginParam =new LoginParam();
    @Override
    public int getContentViewResId() {
      return R.layout.activity_login;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        Role =bundle.getInt("RoleType", RoleType.NOROLE);
        account=bundle.getString("Phone");
        if (account!=null){
            setTvText(R.id.login_et_username,account);
        }
    }

    @Override
    public void initView() {

        //设置标题栏
        setTvText(R.id.titlebar_tv_title, R.string.login_msg_login);
        setTvText(R.id.titlebar_tv_right,R.string.login_msg_register);
        setViewSelected(R.id.login_tv_quick_login, isautologin);
//        setViewVisibility(R.id.titlebar_iv_return, false);

    }

    @Override
    public void setListener() {
       setClickListener(R.id.login_tv_quick_login);
       setClickListener(R.id.login_tv_forget_pwd);
       setClickListener(R.id.titlebar_tv_right);
       setClickListener(R.id.titlebar_iv_return);
       setClickListener(R.id.login_iv_eye);
       setClickListener(R.id.login_btn);
        setClickListener(R.id.login_btn_phone);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case RESULT_OK:
                setResult(RESULT_OK);
                finish();
                break;
            case 2:
                setTvText(R.id.login_et_username,data.getStringExtra("Num"));
                break;
        }

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.login_tv_quick_login:{
//                自动登录
                isautologin =!isautologin;
                setViewSelected(R.id.login_tv_quick_login, isautologin);
                break;
            }
            case R.id.login_tv_forget_pwd:{
                //忘记密码
                String strPhone = getTvText(R.id.login_et_username);
                ActivityJumpUtils.actForgetPwd(getActivity(), "");
                break;
            }
            case R.id.titlebar_tv_right:{
//               注册
                String strPhone = getTvText(R.id.login_et_username);
                ActivityJumpUtils.actRegister(getActivity(), "");
                break;
            }
            case R.id.titlebar_iv_return:{
                finish();
                break;
            }
            case R.id.login_iv_eye: {
                //密码眼睛
                isPwdShow = !isPwdShow;
                changedEditTextVisiable(isPwdShow);
                break;
            }
            case R.id.login_btn:{
                //   登录
                login();
                break;
            }
            case R.id.login_btn_phone:
                ActivityJumpUtils.actPhoneLogin(getActivity());
                break;
        }
    }
//d登录
    private void login() {
        account = getTvText(R.id.login_et_username);
        String password = getTvText(R.id.login_et_pwd);
        loginParam.setAccount(account);
        loginParam.setPassword(password);
        loginParam.setType(1);
//        if (!ValidUtils.get().phone(account)) {
//
//            ToastUtils.show(R.string.login_手机号格式错误);
//            return;
//        }
        if(TextUtils.isEmpty(password)){
            ToastUtils.show("请输入密码");
            return;
        }
        if (!ValidUtils.get().password(password)) {
            ToastUtils.show(R.string.login_密码由6到20位字符组成);
            return;
        }
        showLoadingDialog();
        App.getServiceManager().getmService().login( loginParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<Token>() {


                    @Override
                    public void onSuccess(RainbowResultEntity<Token> obj) {
                        Token token =obj.getObj();
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
     * 密码输入框是否可见
     *
     * @param flag
     */
    private void changedEditTextVisiable(boolean flag) {
        EditText et = getView(R.id.login_et_pwd);
        EditTextUtils.changedEditTextVisiable(et, flag);
        setViewSelected(R.id.login_iv_eye, flag);
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
                        UserCenter data  =obj.getObj();
                        //设置信息
                        App.getAppContext().setUserInfo(data);
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
